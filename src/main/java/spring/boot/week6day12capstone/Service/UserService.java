package spring.boot.week6day12capstone.Service;

import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.MerchantStock;
import spring.boot.week6day12capstone.Model.Product;
import spring.boot.week6day12capstone.Model.User;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();


    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(User user, int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

    // Buy product
    public boolean buyProduct(int userId, int productId, int merchantId) {
        User user = getUserById(userId);
        Product product = getProductById(productId);
        MerchantStock merchantStock = getMerchantStock(productId, merchantId);

        if (user == null || product == null || merchantStock == null || merchantStock.getStock() <= 0 || user.getBalance() < product.getPrice()) {
            return false;
        }

        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());
        return true;
    }

    private User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private MerchantStock getMerchantStock(int productId, int merchantId) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductId() == productId && merchantStock.getMerchantId() == merchantId) {
                return merchantStock;
            }
        }
        return null;
    }
}
