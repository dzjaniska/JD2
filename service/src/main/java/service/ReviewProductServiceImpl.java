package service;

import entity.ReviewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ReviewProductRepository;

@Service
@Transactional
public class ReviewProductServiceImpl implements ReviewProductService {

    private ReviewProductRepository reviewProductRepository;

    @Autowired
    public ReviewProductServiceImpl(ReviewProductRepository reviewProductRepository) {
        this.reviewProductRepository = reviewProductRepository;
    }

    @Override
    public ReviewProduct save(ReviewProduct reviewProduct) {
        return reviewProductRepository.save(reviewProduct);
    }
}
