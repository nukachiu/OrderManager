/**
 * O clasa in care fiecare functie apeleaza functia aferenta din pachetul dao (din motive de securitate)
 * insertClient- insereaza un nou client in abza de date
 * deleteClient- sterge din tabela un client in functie de id-ul sau
 * updateUpdate- updateaza informatiile despre un client
 */

package bll;

import model.*;
import dao.*;

public class ClientBLL {
	
	public boolean insertClient(Client client) {
		return ClientDAO.insert(client) > 0 ? true : false; 
	}
	
	public boolean deleteClient(int id) {
		 return ClientDAO.delete(id) > 0 ? true : false;
	}
	
	public boolean updateClient(int id, Client newClient) {
		return ClientDAO.update(id, newClient) > 0 ? true : false;
	}
}
