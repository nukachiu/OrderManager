/**
 * O clasa care simuleaza operatiile sql pe tabela Produs
 * insert- insereaza un nou produs in abza de date
 * delete- sterge din tabela un produs in functie de id-ul sau
 * update- updateaza informatiile despre un produs
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataBase.ConnectionFactory;
import model.*;

public class ProductDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	
	private final static String insertStatementString = "INSERT INTO product (name, quantity) VALUES (?, ?)";
	private final static String deleteStatementString = "DELETE FROM product WHERE id_product = ?";
	private final static String updateStatementString = "UPDATE product SET name = ?, quantity = ? WHERE  id_product = ?;";
	//private final static String findStatementString = "SELECT * FROM product where id_product = ?";
	
	
	
	public static int insert(Product newProduct) {
		Connection con = ConnectionFactory.getConnection();
		
		PreparedStatement insertStatement = null;
		int insertId = -1;
		try {
			insertStatement = con.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, newProduct.getName());
			insertStatement.setInt(2, newProduct.getQuantity());
			
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
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(con);
		}
		
		return row;
	}
	
	public static int update(int id, Product newProduct) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		int row = -1;
		try {
			updateStatement = con.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, newProduct.getName());
			updateStatement.setInt(2, newProduct.getQuantity());
			updateStatement.setInt(3, id);
			
			row = updateStatement.executeUpdate();
		}catch(Exception e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(con);
		}
		
		return row;
	}
}
