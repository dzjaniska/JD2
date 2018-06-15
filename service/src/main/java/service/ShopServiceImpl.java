package service;

import entity.Product;
import entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ShopRepository;

import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    private ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop findById(Long id) {
        return shopRepository.findById(id).get();
    }

    @Override
    public List<Shop> findAllByProducts(Product product) {
        return shopRepository.findAllByProducts(product);
    }
}
