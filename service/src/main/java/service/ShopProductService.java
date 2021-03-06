package service;


import dto.CartProductDto;
import dto.ShopProductDto;
import entity.ShopProduct;

import java.util.List;

public interface ShopProductService {

    CartProductDto findFirstByProductAndShop(Long productId, Long shopId);

    ShopProduct findByProductAndShop(Long productId, Long shopId);

    ShopProduct findByProductAndShopAndVersion(Long productId, Long shopId, Long version);

    ShopProduct save(ShopProduct shopProduct);

    List<ShopProductDto> findAllByShopIdAndQuantityGreaterThan(Long id);
}
