package spring.boot.week6day12capstone.Service;

import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.MerchantStock;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(MerchantStock merchantStock, int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(int id) {
        return merchantStocks.removeIf(merchantStock -> merchantStock.getId() == id);
    }

    //Create endpoint where user can add more stocks of product to a merchant Stock
    public boolean addMoreStock(int productId, int merchantId, int amount) {
        for(MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductId() == productId && merchantStock.getMerchantId() == merchantId) {
                merchantStock.setStock(merchantStock.getStock() + amount);
                return true;
            }
        }
        return false;
    }

}
