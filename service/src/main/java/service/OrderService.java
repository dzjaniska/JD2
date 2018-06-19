package service;

import dto.UserOrderDto;
import entity.Orders;

import java.util.List;

public interface OrderService {

    Orders save(Orders user);

    List<UserOrderDto> findAllByUserId(Long id);

}
