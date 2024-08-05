package spring.boot.week6day12capstone.Service;

import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.Product;

import java.util.ArrayList;
import java.util.Comparator;

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

    //search for name of product | extra 1
    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
    //extra 2
    public ArrayList<Product> getProductSortedByPrice() {
        ArrayList<Product> productSorted = new ArrayList<>(products);
        productSorted.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        return productSorted;
    }


    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
