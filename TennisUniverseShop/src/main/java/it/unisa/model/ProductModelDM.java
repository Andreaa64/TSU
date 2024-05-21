package main.java.it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProductModelDM implements ProductModel {

	private static final String TABLE_NAME = "prodotto";

	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModelDM.TABLE_NAME
			+ " (Descrizione, colore, prezzo, quantita, tipo, marca, immagine) VALUES (?, ?, ?, ?, ?, ?, ?)";
		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getDescrizione());
			preparedStatement.setString(2, product.getColore());
			preparedStatement.setInt(3, product.getPrezzo());
			preparedStatement.setInt(4, product.getQuantita());
			preparedStatement.setString(5, product.getTipo());
			preparedStatement.setString(6, product.getMarca());
			preparedStatement.setString(7, product.getImg());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized ProductBean doRetrieveByKey(int idp) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME + " WHERE idP = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idp);

			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				bean.setIdP(rs.getInt("IDP"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setColore(rs.getString("COLORE"));
				bean.setPrezzo(rs.getInt("PREZZO"));
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setTipo(rs.getString("TIPO"));
				bean.setMarca(rs.getString("MARCA"));
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public synchronized boolean doDelete(int idp) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModelDM.TABLE_NAME + " WHERE idP = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1,idp);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		System.out.println("prima");
		try {
			System.out.println("Dopo");
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setIdP(rs.getInt("IDP"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setColore(rs.getString("COLORE"));
				bean.setPrezzo(rs.getInt("PREZZO"));
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setTipo(rs.getString("TIPO"));
				bean.setMarca(rs.getString("MARCA"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	@Override
	public void doOrdine(Cart c) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    List<ProductBean> products = c.getProducts();

	    String insertSQL = "INSERT INTO ordine (Prezzo_Tot, Data_Consegna, Data_Ordine) VALUES (?, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW())";

	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        connection.setAutoCommit(false); // Imposta l'autocommit a false per gestire manualmente le transazioni

	        preparedStatement = connection.prepareStatement(insertSQL);
	        preparedStatement.setFloat(1, c.spesaTot());
	        preparedStatement.executeUpdate();

	        String updateSQL = "UPDATE prodotto SET quantita = quantita - ? WHERE idP = ?";
	        preparedStatement = connection.prepareStatement(updateSQL);

	        for (ProductBean p : products) {
	            preparedStatement.setInt(1, p.getQC());
	            preparedStatement.setInt(2, p.getIdP());
	            preparedStatement.executeUpdate(); // Esegui l'aggiornamento per ciascun prodotto
	        }

	        connection.commit(); // Commit della transazione
	    } catch (SQLException e) {
	        System.out.println("Errore!: " + e);
	        if (connection != null) {
	            try {
	                connection.rollback(); // Rollback della transazione in caso di eccezione
	            } catch (SQLException ex) {
	                System.out.println("Errore durante il rollback della transazione: " + ex);
	            }
	        }
	    } finally {
	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }
	        if (connection != null) {
	            DriverManagerConnectionPool.releaseConnection(connection);
	        }
	    }

	    // Elimina i prodotti dal carrello e rilascia la memoria
	    c.elimina();
	}

}