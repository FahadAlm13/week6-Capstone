package spring.boot.week6day12capstone.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.MerchantStock;
import spring.boot.week6day12capstone.Model.Product;
import spring.boot.week6day12capstone.Model.GiftDetiles;
import spring.boot.week6day12capstone.Model.User;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantStockService merchantStockService;
    ArrayList<User> users = new ArrayList<>();
    private final ArrayList<GiftDetiles> giftDetiles = new ArrayList<>();
    private final ProductService productService;
    private final MerchantService merchantService;




    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (user.getWishlist() == null) {
            user.setWishlist(new ArrayList<>());
        }
        users.add(user);
    }
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
        if (user.isPrime()){
            merchantStock.setStock(merchantStock.getStock() - 1);
            user.setBalance(user.getBalance() - (product.getPrice()*0.10));
            System.out.println("Product purchased successfully");
            return "Product purchased successfully with 10% discount";
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
    // if it's prime he weill have 10% discount when he buy product
    public boolean subscribePrime(int userId) {
       double monthlyFee =39;
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }
        if (user.getBalance() < monthlyFee) {
            return false;
        }
        user.setBalance(user.getBalance() - monthlyFee);
        user.setPrime(true);
        return true;
    }
    // If you gift someone , you can use this mathod to see the gift details
    public ArrayList<GiftDetiles> getGiftByUserDetalis(int userId) {
        ArrayList<GiftDetiles> userDetalise = new ArrayList<>();
        for (GiftDetiles detiles : giftDetiles) {
            if (detiles.getUserId() == userId) {
                userDetalise.add(detiles);
            }
        }
        return userDetalise;
    }

    public String giftProduct(int giverId, int receiverId, int productId, int merchantId) {
        User giver = getUserById(giverId);
        User receiver = getUserById(receiverId);
        if (giver == null || receiver == null) {
            return "User not found";
        }
        if (!giver.getRole().equalsIgnoreCase("Customer") || !receiver.getRole().equalsIgnoreCase("Customer")) {
            return "Only customers can gift products";
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            return "Product not found";
        }
        MerchantStock merchantStock = merchantStockService.getMerchantStock(productId, merchantId);
        if (merchantStock == null) {
            return "Merchant stock not found";
        }
        if (merchantStock.getStock() <= 0) {
            return "Product out of stock";
        }
        double price = giver.isPrime() ? product.getPrice() * 0.95 : product.getPrice();
        if (giver.getBalance() < price) {
            return "Insufficient balance";
        }
        merchantStock.setStock(merchantStock.getStock() - 1);
        giver.setBalance(giver.getBalance() - price);
        giftDetiles.add(new GiftDetiles(giverId, productId, price, "Gifted to " + receiverId));
        return "Product gifted successfully";
    }
}
