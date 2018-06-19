package service;

import dto.CartProductDto;
import dto.ShopProductDto;
import entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ShopProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShopProductServiceImpl implements ShopProductService {

    private static final Integer ZERO_QUANTITY = 0;

    private ShopProductRepository shopProductRepository;

    @Autowired
    public ShopProductServiceImpl(ShopProductRepository shopProductRepository) {
        this.shopProductRepository = shopProductRepository;
    }

    @Override
    public CartProductDto findFirstByProductAndShop(Long productId, Long shopId) {
        ShopProduct shopProduct = shopProductRepository.findFirstByProductIdAndShopId(productId, shopId);

        return new CartProductDto().builder()
                .productId(shopProduct.getProduct().getId())
                .productName(shopProduct.getProduct().getDescription())
                .productImage(shopProduct.getProduct().getImage())
                .options(shopProduct.getProduct().getOptions())
                .price(shopProduct.getPrice())
                .shopId(shopProduct.getShop().getId())
                .availableQuantity(shopProduct.getQuantity())
                .build();
    }

    @Override
    public ShopProduct findByProductAndShop(Long productId, Long shopId) {
        return shopProductRepository.findFirstByProductIdAndShopId(productId, shopId);
    }


    @Override
    public ShopProduct save(ShopProduct shopProduct) {
        return shopProductRepository.save(shopProduct);
    }

    @Override
    public List<ShopProductDto> findAllByShopIdAndQuantityGreaterThan(Long id) {
        List<ShopProductDto> productList = new ArrayList<>();

        List<ShopProduct> products = shopProductRepository.findAllByShopIdAndQuantityGreaterThan(id, ZERO_QUANTITY);
        products.forEach(it -> productList.add(new ShopProductDto()
                .builder()
                .productId(it.getProduct().getId())
                .productName(it.getProduct().getDescription())
                .productImage(it.getProduct().getImage())
                .price(it.getPrice())
                .quantity(it.getQuantity())
                .build()));

    return productList;
    }
}
