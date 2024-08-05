package spring.boot.week6day12capstone.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.MerchantStock;
import spring.boot.week6day12capstone.Model.Product;
import spring.boot.week6day12capstone.Model.User;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantStockService merchantStockService;
    ArrayList<User> users = new ArrayList<>();
    private final ProductService productService;
    private final MerchantService merchantService;
//    ArrayList<Product> products = new ArrayList<>();
//    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();


    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (user.getWishlist() == null) {
            user.setWishlist(new ArrayList<>());
        }
        users.add(user);
    }

//    // Add a product
//    public void addProduct(Product product) {
//        products.add(product);
//        System.out.println("Product added: " + product);
//    }
//
//    // Add a merchant stock
//    public void addMerchantStock(MerchantStock merchantStock) {
//        merchantStocks.add(merchantStock);
//        System.out.println("MerchantStock added: " + merchantStock);
//    }

    public boolean updateUser(User user, int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                if (user.getWishlist() == null) {
                    user.setWishlist(new ArrayList<>());
                }
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
    public String buyProduct(int userId, int productId, int merchantId) {
//        System.out.println("Attempting to buy product with productId: " + productId);
//        System.out.println("Current products: " + products);
//        System.out.println("Current users: " + users);
//        System.out.println("Current merchant stocks: " + merchantStocks);


        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found");
            return "User not found";
        }
        System.out.println("User found: " + user);

        if (!user.getRole().equalsIgnoreCase("Customer")) {
            return "Only customers can buy products";
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println("Product not found in list: " + productId);
            return "Product not found";
        }

        System.out.println("Product found: " + product);

        MerchantStock merchantStock = merchantStockService.getMerchantStock(productId, merchantId);
        if (merchantStock == null) {
            System.out.println("Merchant stock not found");
            return "Merchant stock not found";
        }

        System.out.println("Merchant stock found: " + merchantStock);

        if (merchantStock.getStock() <= 0) {
            System.out.println("Product out of stock");
            return "Product out of stock";
        }

        if (user.getBalance() < product.getPrice()) {
            System.out.println("Insufficient balance");
            return "Insufficient balance";
        }

        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());
        System.out.println("Product purchased successfully");
        return "Product purchased successfully";
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
