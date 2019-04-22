/**
 * O clasa care simuleaza operatiile sql pe tabela OrderTable
 * insert- insereaza un o comanda in abza de date
 * delete- sterge din tabela o comanda in functie de id-ul ei
 * update- updateaza informatiile despre o comanda
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataBase.ConnectionFactory;
import model.Order;

public class OrderDAO {
protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	
	private final static String insertStatementString = "INSERT INTO ordertable (client_name, product_name, quantity) VALUES (?, ?, ?)";
	
	public static int insert(Order newOrder) {
		Connection con = ConnectionFactory.getConnection();
		
		PreparedStatement insertStatement = null;
		int insertId = -1;
		try {
			insertStatement = con.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, newOrder.getClientName());
			insertStatement.setString(2, newOrder.getProductName());
			insertStatement.setInt(3, newOrder.getQuantity());
			
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
}
