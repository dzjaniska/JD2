package service;

import dto.ShopPageDto;
import entity.Product;
import entity.Shop;

import java.util.List;

public interface ShopService {

    Shop save(Shop shop);

    Shop findById(Long id);

    ShopPageDto findByIdDto(Long id);
}
