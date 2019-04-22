/**
 * O clasa in se afla logica aplicatiei, aici se apeleaza toate functiile creeate in celelalte. 
 * functiile actionPErformed din clasele care ostenesc ActionListener sunt folosit pentru a traminte un raspuns de la aplicatie la user in caz ca se apasa un buton
 */

package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import model.*;
import dao.*;
import bll.*;
import DataBase.ConnectionFactory;

public class Controller {
	private View view;
	
	public Controller(View view) {
		this.view = view;
		
		this.view.addClientListener(new Button0Listener());
		this.view.writeAllClientsListener(new Button1Listener());
		this.view.addProductListener(new Button2Listener());
		this.view.writeAllProductsListener(new Button3Listener());
		this.view.addOrderListener(new Button4Listener());
	}
	
	class Button0Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String surName = view.getTf1();
			String name = view.getTf2();
			
			Client newClient = new Client(name, surName);
			ClientBLL clientbll = new ClientBLL();
			JFrame frame = new JFrame ("Rezultat");
			JLabel p;
			if(clientbll.insertClient(newClient) == true) {
				p = new JLabel("Succes!");
			}
			else {
				p = new JLabel("Esec la adaugarea clientului");
			}
			
			frame.setSize(200, 200);
			frame.setVisible(true);
			
			frame.setContentPane(p);
		}
	}
	
	class Button1Listener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			JFrame frame = new JFrame ("Rezultat");
			
			String [] clientColumnNames = {"ID", "First Name", "Last Name"};
			//JTable tableClients = new JTable();
			DefaultTableModel tableTemp = new DefaultTableModel();
			tableTemp.setColumnIdentifiers(clientColumnNames);
			FillTable(tableTemp, "select * from client");
			view.getClientTable().setModel(tableTemp);
			JTable tableClients = view.getClientTable();
			//tableClients.setModel(tableTemp);
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			//JButton buttonDelete = new JButton("StergeClient");
			
			
			p1.add(tableClients);
			p2.add(view.getButtonDeleteClient());
			JPanel p3 = new JPanel();
			p3.add(p1);
			p3.add(p2);
			p3.add(view.getL1_1());
			p3.add(view.getTf1_1());
			p3.add(view.getL1_2());
			p3.add(view.getTf1_2());
			p3.add(view.getButtonUpdateClient());
			p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
			frame.setSize(600, 500);
			frame.setVisible(true);
			
			frame.setContentPane(p3);
			
			view.deleteClientListener(new ButtonDeleteListener());
			view.updateClientListener(new ButtonUpdateListener());
		}
		
		class ButtonUpdateListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				int[] selectedRow = view.getClientTable().getSelectedRows();
				int row = selectedRow[0];
				int id = (int) view.getClientTable().getValueAt(row, 0);
				
				String newName = view.getTf1_1().getText();
				String newSurName = view.getTf1_2().getText();
				
				Client newClient = new Client(newName, newSurName);
				ClientBLL clientbll = new ClientBLL();
				clientbll.updateClient(id, newClient);
				
			}
		}
		
		class ButtonDeleteListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				int[] selectedRow = view.getClientTable().getSelectedRows();
				int row = selectedRow[0];
				int id = (int) view.getClientTable().getValueAt(row, 0);
				
				ClientBLL clientbll = new ClientBLL();
				if(clientbll.deleteClient(id) == true)
					System.out.println("Sters");
				else
					System.out.println("Nu s a sters");
			}
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
	}
	
	class Button2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name = view.getTf3();
			int quantity = Integer.parseInt(view.getTf4());
			
			Product newProduct = new Product(name, quantity);
			ProductBLL productbll = new ProductBLL();
			JFrame frame = new JFrame ("Rezultat");
			JLabel p;
			if(productbll.insertProduct(newProduct) == true) {
				p = new JLabel("Succes!");
			}
			else {
				p = new JLabel("Esec la adaugarea clientului");
			}
			
			frame.setSize(200, 200);
			frame.setVisible(true);
			
			frame.setContentPane(p);
		}
	}
	
	class Button3Listener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			JFrame frame = new JFrame ("Rezultat");
			
			String [] clientColumnNames = {"ID", "Name", "Quantity"};
			
			DefaultTableModel tableTemp = new DefaultTableModel();
			tableTemp.setColumnIdentifiers(clientColumnNames);
			FillTable(tableTemp, "select * from product");
			view.getProductTable().setModel(tableTemp);
			JTable tableProduct = view.getProductTable();
			
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			
			p1.add(tableProduct);
			p2.add(view.getButtonDeleteProduct());
			p3.add(p1);
			p3.add(p2);
			p3.add(view.getL2_1());
			p3.add(view.getTf2_1());
			p3.add(view.getL2_2());
			p3.add(view.getTf2_2());
			p3.add(view.getButtonUpdateProduct());
			p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
			frame.setSize(600, 500);
			frame.setVisible(true);
			
			frame.setContentPane(p3);
			
			view.deleteProductListener(new ButtonProductDeleteListener());
			view.updateProductListener(new ButtonProductUpdateListener());
		}
		
		class ButtonProductUpdateListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int[] selectedRow = view.getProductTable().getSelectedRows();
				int row = selectedRow[0];
				int id = (int) view.getProductTable().getValueAt(row, 0);
				
				String newName = view.getTf2_1().getText();
				int newQuantity = Integer.parseInt(view.getTf2_2().getText());
				
				Product newProduct = new Product(newName,newQuantity);
				ProductBLL productbll = new ProductBLL();
				productbll.updateProduct(id, newProduct);
			}
		}
		
		class ButtonProductDeleteListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int[] selectedRow = view.getProductTable().getSelectedRows();
				int row = selectedRow[0];
				int id = (int) view.getProductTable().getValueAt(row, 0);
				
				ProductBLL productbll = new ProductBLL();
				
				if(productbll.deleteProduct(id) == true)
					System.out.println("Sters");
				else
					System.out.println("Nu s a sters");
			}
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
	}

	class Button4Listener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			int selectedRowClient[] = view.getTableClients().getSelectedRows();
			int selectedRowProduct[] = view.getTableProduct().getSelectedRows();
			int rowClient = selectedRowClient[0];
			int rowProduct = selectedRowProduct[0];			
			String dataClient = (String) view.getTableClients().getValueAt(rowClient, 1);
			String dataProduct = (String) view.getTableProduct().getValueAt(rowProduct, 1);
			int idProduct = (int) view.getTableProduct().getValueAt(rowProduct, 0);
			int quantityProduct = (int) view.getTableProduct().getValueAt(rowProduct, 2);
			
			int quantity = Integer.parseInt(view.getTf5());
			
			if(quantity <= quantityProduct) {
			
				Order newOrder = new Order(dataClient, dataProduct, quantity);
				OrderBLL orderbll = new OrderBLL();
				
				JFrame frame = new JFrame ("Rezultat");
				JLabel p, p1;
				
				if(orderbll.insertOrder(newOrder) == true) {
					//p = new JLabel("Succes!");
					p = new JLabel(dataClient + " " + "a comandat " + quantity + "x" + dataProduct);
					Product newProduct = new Product(dataProduct, quantityProduct - quantity);
					ProductBLL productbll = new ProductBLL();
					productbll.updateProduct(idProduct, newProduct);
				}
				else {
					p = new JLabel("Esec la adaugarea comenzii");
				}
				
				frame.setSize(200, 200);
				frame.setVisible(true);
				
				frame.setContentPane(p);
				//frame.setContentPane(p1);
			}
			else {
				JFrame frame = new JFrame("Ghinion");
				JLabel p = new JLabel("Prea putine produse pe stoc");
				
				frame.setContentPane(p);
				frame.setSize(300,200);
				frame.setVisible(true);
			}
				
		}
	}
	
}
