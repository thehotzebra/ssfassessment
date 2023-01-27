package com.example.ssfassessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.ssfassessment.model.Order;

import jakarta.validation.OverridesAttribute.List;

@Qualifier("PizzaService")
@Service
public class PizzaService {

    static final String CONTACT_ENTITY = "contactlist";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void save(final Order ctc){
        redisTemplate.opsForList()
            .leftPush(CONTACT_ENTITY, ctc.getOrderId());
        redisTemplate.opsForHash()
            .put( CONTACT_ENTITY+ "_Map", ctc.getOrderId(), ctc);
    }

    public Order findById(final String contactId){
        Order result= (Order)redisTemplate.opsForHash()
                .get(CONTACT_ENTITY+ "_Map", 
                contactId);
        return result;
    }

    // public List<Contact> findAll(int startIndex){
    //     List<Object> fromContactList = redisTemplate.opsForList()
    //         .range(CONTACT_ENTITY, startIndex, 10);
    //     List<Contact> ctcs = redisTemplate.opsForHash()
    //         .multiGet(CONTACT_ENTITY+ "_Map", fromContactList)
    //         .stream()
    //         .filter(Order.class::isInstance)
    //         .map(Order.class::cast)
    //         .toList();
        
    //     return ctcs;
    // }
    
// }
}
