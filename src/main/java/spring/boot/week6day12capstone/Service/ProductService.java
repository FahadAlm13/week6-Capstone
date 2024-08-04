package spring.boot.week6day12capstone.Service;

import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.Product;

import java.util.ArrayList;

@Service
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();

    //Get
    public ArrayList<Product> getProducts() {
        return products;
    }

    //Post
    public void addProducts(Product products) {
        this.products.add(products);
    }

    //update
    public boolean updateProduct(Product product, int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    //delete
    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
}
