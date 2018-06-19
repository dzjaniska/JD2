package service;

import entity.ReviewShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ReviewShopRepository;

@Service
@Transactional
public class ReviewShopServiceImpl implements ReviewShopService {

    private ReviewShopRepository reviewShopRepository;

    @Autowired
    public ReviewShopServiceImpl(ReviewShopRepository reviewShopRepository) {
        this.reviewShopRepository = reviewShopRepository;
    }

    @Override
    public ReviewShop save(ReviewShop reviewShop) {
        return reviewShopRepository.save(reviewShop);
    }
}
