package service;

import entity.Product;
import entity.Shop;

import java.util.List;

public interface ShopService {

    Shop save(Shop shop);

    Shop findById(Long id);

    List<Shop> findAllByProducts(Product product);
}
