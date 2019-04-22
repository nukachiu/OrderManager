package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DataBase.ConnectionFactory;

public class View {
	private JFrame frame = new JFrame ("Simple Frame");
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	
	//pt panel1: (clienti)
	private JLabel l1 = new JLabel("Nume:");
	private JLabel l2 = new JLabel("Prenume");
	private JTextField tf1 = new JTextField("Turcia");
	private JTextField tf2 = new JTextField("Rares");
	private JButton b1 = new JButton("Adauga Client");
	private JButton b2 = new JButton("Lista Clienti");
	private JLabel l1_1 = new JLabel("Nume:");
	private JLabel l1_2 = new JLabel("Prenume:");
	private JButton buttonUpdateClient = new JButton("Update !");

	
	private JTextField tf1_1 = new JTextField("Rica");
	private JTextField tf1_2 = new JTextField("Rica");
	private JButton buttonDeleteClient = new JButton("Sterge Client");
	private JTable clientTable = new JTable();
	//private JTable t1;// = new JTable();
	
	//pt panel2: (produs)
	private JLabel l3 = new JLabel("Nume");
	private JLabel l4 = new JLabel("Cantitate");
	private JTextField tf3 = new JTextField("Crema");
	private JTextField tf4 = new JTextField("20");
	private JButton b3 = new JButton("Adauga Produs");
	private JButton b4 = new JButton("Lista Produse");
	private JLabel l2_1 = new JLabel("Nume:");
	private JLabel l2_2 = new JLabel("Prenume:");
	private JButton buttonUpdateProduct = new JButton("Update !");

	
	private JTextField tf2_1 = new JTextField("Nivea");
	private JTextField tf2_2 = new JTextField("20");
	
	
	JButton buttonDeleteProduct = new JButton("Sterge Produs");
	JTable productTable = new JTable();
	
	public JLabel getL2_1() {
		return l2_1;
	}

	public void setL2_1(JLabel l2_1) {
		this.l2_1 = l2_1;
	}

	public JLabel getL2_2() {
		return l2_2;
	}

	public void setL2_2(JLabel l2_2) {
		this.l2_2 = l2_2;
	}

	public JButton getButtonUpdateProduct() {
		return buttonUpdateProduct;
	}

	public void setButtonUpdateProduct(JButton buttonUpdateProduct) {
		this.buttonUpdateProduct = buttonUpdateProduct;
	}

	public JTextField getTf2_1() {
		return tf2_1;
	}

	public void setTf2_1(JTextField tf2_1) {
		this.tf2_1 = tf2_1;
	}

	public JTextField getTf2_2() {
		return tf2_2;
	}

	public void setTf2_2(JTextField tf2_2) {
		this.tf2_2 = tf2_2;
	}

	//pt panel3: (order)
	private JTable tableClients,tableProduct; 
	private JTextField tf5 = new JTextField("3");
	private JButton b5 = new JButton("Adauga Comanda");
	
	public View() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 800);
		//Panel1: pentru clienti
		

		String [] clientColumnNames = {"ID", "First Name", "Last Name"};
		String [] productColumnNames = {"ID", "Name", "quantity"};
		
		JPanel panel1_1 = new JPanel();
		JPanel panel1_2 = new JPanel();
		panel1_1.add(l1);
		panel1_1.add(tf1);
		panel1_1.add(l2);
		panel1_1.add(tf2);
		panel1_1.add(b1);
		panel1_1.add(b2);
		panel1_1.setLayout(new GridLayout(4,2));
		
		//panel1_2.add(t1);
		
		panel1.add(panel1_1);
		// Gata cu clientii
		
		//Panel2: pentru produse
		JPanel panel2_1 = new JPanel();
		panel2_1.add(l3);
		panel2_1.add(tf3);
		panel2_1.add(l4);
		panel2_1.add(tf4);
		panel2_1.add(b3);
		panel2_1.add(b4);
		
		panel2_1.setLayout(new GridLayout(3,2));
		panel2.add(panel2_1);
		//Gata cu product
		
		//Panel3: pentru order
		JPanel panel3_1 = new JPanel();
		tableClients = new JTable();
		tableProduct = new JTable();
		DefaultTableModel tableTemp = new DefaultTableModel();
		DefaultTableModel tableTemp1 = new DefaultTableModel();
		
		tableTemp.setColumnIdentifiers(clientColumnNames);
		FillTable(tableTemp, "select * from client");
		tableClients.setModel(tableTemp);
		
		tableTemp1.setColumnIdentifiers(productColumnNames);
		FillTable(tableTemp1, "select * from product");
		tableProduct.setModel(tableTemp1);
		
		panel3_1.add(tableProduct);
		panel3_1.add(tableClients);
		
		panel3.add(panel3_1);
		panel3.add(tf5);
		panel3.add(b5);
		//Gata cu order
		
		
		tabbedPane.add("Clienti", panel1);
		tabbedPane.add("Produs", panel2);
		tabbedPane.add("Comanda", panel3);
		
		frame.add(tabbedPane);
		frame.setVisible(true);
	}
	
	private void FillTable(DefaultTableModel table, String query) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(table.getRowCount() > 0) 
	        {
	            table.removeRow(0);
	        }
	        int columns = rs.getMetaData().getColumnCount();
	        while(rs.next())
	        {  
	            Object[] row = new Object[columns];
	            for (int i = 1; i <= columns; i++)
	            {  
	                row[i - 1] = rs.getObject(i);
	                //System.out.println(row[i-1]);
	            }
	            table.insertRow(rs.getRow()-1,row);
	        }
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(con);
			ConnectionFactory.close(stmt);
			ConnectionFactory.close(rs);
		}
	}
	
	public JTable getTableClients() {
		return tableClients;
	}

	public JTable getTableProduct() {
		return tableProduct;
	}
	
	public JTextField getTextf1() {
		return tf1;
	}
	
	public JTextField getTextf2() {
		return tf2;
	}
	
	public String getTf1() {
		return tf1.getText();
	}

	public String getTf2() {
		return tf2.getText();
	}

	public String getTf3() {
		return tf3.getText();
	}

	public String getTf4() {
		return tf4.getText();
	}
	
	public String getTf5() {
		return tf5.getText();
	}

	public JTable getClientTable() {
		return clientTable;
	}
	
	public JTable getProductTable() {
		return productTable;
	}
	
	public void deleteClientListener(ActionListener button)
	{
		buttonDeleteClient.addActionListener(button);
	}
	
	public void deleteProductListener(ActionListener button)
	{
		buttonDeleteProduct.addActionListener(button);
	}
	
	public void updateClientListener(ActionListener button)
	{
		buttonUpdateClient.addActionListener(button);
	}
	
	public void updateProductListener(ActionListener button)
	{
		buttonUpdateProduct.addActionListener(button);
	}
	
	public JButton getButtonDeleteClient() {
		return buttonDeleteClient;
	}
	public JButton getButtonDeleteProduct() {
		return buttonDeleteProduct;
	}
	
	public void addClientListener(ActionListener button)
	{
		b1.addActionListener(button);
	}
	
	public void writeAllClientsListener(ActionListener button)
	{
		b2.addActionListener(button);
	}
	
	public void addProductListener(ActionListener button) {
		b3.addActionListener(button);
	}
	
	public void writeAllProductsListener(ActionListener button)
	{
		b4.addActionListener(button);
	}
	
	public void addOrderListener(ActionListener button)
	{
		b5.addActionListener(button);
	}
	
	public JButton getButtonUpdateClient() {
		return buttonUpdateClient;
	}
	
	public JLabel getL1_1() {
		return l1_1;
	}

	public void setL1_1(JLabel l1_1) {
		this.l1_1 = l1_1;
	}

	public JLabel getL1_2() {
		return l1_2;
	}

	public void setL1_2(JLabel l1_2) {
		this.l1_2 = l1_2;
	}

	public JTextField getTf1_1() {
		return tf1_1;
	}

	public void setTf1_1(JTextField tf1_1) {
		this.tf1_1 = tf1_1;
	}

	public JTextField getTf1_2() {
		return tf1_2;
	}

	public void setTf1_2(JTextField tf1_2) {
		this.tf1_2 = tf1_2;
	}

}
