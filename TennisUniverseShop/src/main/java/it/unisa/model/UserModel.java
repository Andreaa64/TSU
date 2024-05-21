package main.java.it.unisa.model;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserModel {

	private static final String TABLE_NAME = "utente";
	
	public synchronized void doRegister(User u) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
		    connection = DriverManagerConnectionPool.getConnection();
		    String insertSQL = "INSERT INTO " + UserModel.TABLE_NAME
		        + " (Email, Username, Cognome, Nome, DataNascita, Psw) VALUES (?, ?, ?, ?, ?, ?)";
		    preparedStatement = connection.prepareStatement(insertSQL);
		    preparedStatement.setString(1, u.getEmail());
		    preparedStatement.setString(2, u.getUsername());
		    preparedStatement.setString(3, u.getCognome());
		    preparedStatement.setString(4, u.getNome());

		    // Converti la stringa di data di nascita in java.sql.Date
		    String dataNascitaString = u.getDataNascita();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date dataNascita = dateFormat.parse(dataNascitaString);

		    // Converti java.util.Date in java.sql.Date
		    java.sql.Date sqlDate = new java.sql.Date(dataNascita.getTime());
		    preparedStatement.setDate(5, sqlDate);

		    // Cifra la password prima di inserirla nel database
		    String hashedPassword = PasswordHashing.hashPassword(u.getPsw());
		    // Imposta l'hash della password invece della password originale
		    preparedStatement.setString(6, hashedPassword);

		    preparedStatement.executeUpdate();
		    connection.commit();
		} catch (SQLException | ParseException e) {
		    // Gestione dell'eccezione
		    e.printStackTrace();
		} finally {
		    try {
		        if (preparedStatement != null)
		            preparedStatement.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DriverManagerConnectionPool.releaseConnection(connection);
		    }
		}
}
		
	
	public synchronized boolean doArleadyRegister(String Email) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "SELECT Email FROM " + UserModel.TABLE_NAME
			+ " WHERE Email = ?";
		
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, Email);
			
			ResultSet rs =  preparedStatement.executeQuery();
			
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}
	
	public synchronized String checkLogin(String Email, String psw) throws SQLException{
		if(!this.doArleadyRegister(Email)) {
			return "errorEmail";
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "SELECT Email FROM " + UserModel.TABLE_NAME
			+ " WHERE Email = ? AND Psw = ?";
		
		String hashedInputPassword = PasswordHashing.hashPassword(psw);
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, Email);
			preparedStatement.setString(2, hashedInputPassword);
			ResultSet rs =  preparedStatement.executeQuery();
			
			if(rs.next())
				return "noError";
			else
				return "errorPsw";

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		
				
	 }
	}
}
