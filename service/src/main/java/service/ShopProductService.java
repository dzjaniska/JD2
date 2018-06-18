package service;

import dto.CartProductDto;
import entity.Product;
import entity.Shop;
import entity.ShopProduct;

public interface ShopProductService {

    CartProductDto findFirstByProductAndShop(Long productId, Long shopId);

    ShopProduct findByProductAndShop(Long productId, Long shopId);

    ShopProduct save(ShopProduct shopProduct);

//    ShopProduct findFirstByProductAndShop(Product product, Shop shop);
}
