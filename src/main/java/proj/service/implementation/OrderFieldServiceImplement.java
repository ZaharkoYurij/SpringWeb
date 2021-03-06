package proj.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.entity.OrderField;
import proj.repository.OrderFieldRepository;
import proj.service.OrderFieldService;

import java.util.List;

/**
 * Created by SC on 07.02.2017.
 */
@Service
public class OrderFieldServiceImplement implements OrderFieldService{

    @Autowired
    private OrderFieldRepository orderFieldRepository;

    @Override
    public OrderField findById(int id) {
        return orderFieldRepository.findOne(id);
    }

    @Override
    public List<OrderField> findOrderFieldByUserOrderId(int id) {
        return orderFieldRepository.findOrderFieldByUserOrderId(id);
    }
}
