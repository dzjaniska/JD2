package service;

import dto.ReviewDto;
import dto.ShopPageDto;
import entity.ReviewShop;
import entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ShopRepository;

import java.util.Comparator;
import java.util.stream.Collectors;

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
    public ShopPageDto findByIdDto(Long id) {
        Shop shop = shopRepository.findById(id).get();

        return new ShopPageDto().builder()
                .id(shop.getId())
                .shopName(shop.getName())
                .description(shop.getDescription())
                .shopLogo(shop.getLogo())
                .rating(shop.getReviews().stream().mapToDouble(ReviewShop::getRating).average().orElse(Double.NaN))
                .reviews(shop.getReviews().stream().map(it -> new ReviewDto().builder()
                        .userName(it.getUser().getUserInfo().getName())
                        .userSecondName(it.getUser().getUserInfo().getSurname())
                        .rating(it.getRating())
                        .text(it.getText())
                        .date(it.getDate())
                        .build())
                        .sorted(Comparator.comparing(ReviewDto::getDate).reversed())
                        .collect(Collectors.toSet()))
                .build();
    }
}
