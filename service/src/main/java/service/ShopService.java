package service;

import dto.ShopPageDto;
import entity.Shop;

public interface ShopService {

    Shop save(Shop shop);

    Shop findById(Long id);

    ShopPageDto findByIdDto(Long id);
}
