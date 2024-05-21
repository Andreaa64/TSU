package main.java.it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.Collection;

public class ProductModelDS implements ProductModel {

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shop");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "prodotto";

	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModelDS.TABLE_NAME
			+ " (Descrizione, colore, prezzo, quantita, tipo, marca, immagine) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
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
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModelDS.TABLE_NAME + " WHERE idP = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdP(rs.getInt("IDP"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setColore(rs.getString("COLORE"));
				bean.setPrezzo(rs.getInt("PREZZO"));
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setTipo(rs.getString("TIPO"));
				bean.setMarca(rs.getString("MARCA"));
				bean.setImg(rs.getString("IMMAGINE"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModelDS.TABLE_NAME + " WHERE idP = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();
		String selectSQL = "SELECT * FROM " + ProductModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
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
				bean.setImg(rs.getString("IMMAGINE"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
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
	    return;
	}

	

}