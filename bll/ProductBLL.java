/**
 * O clasa in care fiecare functie apeleaza functia aferenta din pachetul dao (din motive de securitate)
 * insertProduc- insereaza un nou produs in abza de date
 * deleteProduct- sterge din tabela un produs in functie de id-ul sau
 * updateProduct- updateaza informatiile despre un produs
 */

package bll;

import dao.ProductDAO;
import model.Product;;

public class ProductBLL {
	
	public boolean insertProduct(Product product) {
		return ProductDAO.insert(product) > 0 ? true : false;
	}
	
	public boolean deleteProduct(int id) {
		return ProductDAO.delete(id) > 0 ? true : false;
	}
	
	public boolean updateProduct(int id, Product product) {
		return ProductDAO.update(id, product) > 0 ? true : false;
	}
}
