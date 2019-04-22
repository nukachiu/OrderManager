/**
 * O clasa in care fiecare functie apeleaza functia aferenta din pachetul dao (din motive de securitate)
 * insertOrder- insereaza o noua comanda in abza de date
 * deleteOrder- sterge din tabela o comada in functie de id-ul sau
 * updateOrder- updateaza informatiile despre o comanda
 */

package bll;

import dao.OrderDAO;
import model.Order;

public class OrderBLL {
	
	public boolean insertOrder(Order newOrder) {
		return OrderDAO.insert(newOrder) > 0 ? true : false;
	}
}
