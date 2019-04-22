/**
 * O clasa care simuleaza operatiile sql pe tabela Client
 * insert- insereaza un nou client in abza de date
 * delete- sterge din tabela un client in functie de id-ul sau
 * update- updateaza informatiile despre un client
 */


package dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;

import DataBase.ConnectionFactory;
import model.Client;

public class ClientDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	
	private static final String insertStatementString = "INSERT INTO client (name, surname) VALUES (?, ?)";
	private static final String deleteStatementString = "DELETE FROM client WHERE id_client = ?";
	private static final String updateStatementString = "UPDATE client SET name = ?, surname = ? WHERE id_client = ?";
	
	public static int insert(Client newClient) {
		Connection con = ConnectionFactory.getConnection();
		
		PreparedStatement insertStatement = null;
		int insertId = -1;
		try {
			insertStatement = con.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, newClient.getName());
			insertStatement.setString(2, newClient.getSurname());
			
			insertStatement.executeUpdate();
			ResultSet rs = insertStatement.getGeneratedKeys();
			
			if(rs.next()) {
				insertId = rs.getInt(1);
			}
		} catch(Exception e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(con);
		}
		
		return insertId;
	}
	
	public static int delete(int id) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		int row = -1;
		try {
			deleteStatement = con.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, id);
			
			row = deleteStatement.executeUpdate();
			//ResultSet rs = deleteStatement.getGeneratedKeys();
		}
		catch(Exception e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(con);
		}
		
		return row;
	}
	
	public static int update(int id, Client newClient) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		int row = -1;
		try {
			updateStatement = con.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, newClient.getName());
			updateStatement.setString(2, newClient.getSurname());
			updateStatement.setInt(3, id);
			
			row = updateStatement.executeUpdate();
			//ResultSet rs = updateStatement.getGeneratedKeys();
		}
		catch(Exception e) {LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(con);
	}
		
	return row;
	}
}
