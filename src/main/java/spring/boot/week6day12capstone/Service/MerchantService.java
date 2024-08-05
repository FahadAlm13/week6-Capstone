package spring.boot.week6day12capstone.Service;

import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.Merchant;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    public boolean updateMerchant(Merchant merchant, int id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.set(i, merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(int id) {
        return merchants.removeIf(merchant -> merchant.getId() == id);
    }
}
