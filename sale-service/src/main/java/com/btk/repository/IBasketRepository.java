package com.btk.repository;

import com.btk.entity.Basket;
import com.btk.entity.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBasketRepository extends MongoRepository<Basket,String> {
    Optional<Basket> findOptionalByBasketId(String basketId);
    Optional<Basket> findOptionalByUserId(String userId);
    Optional<Basket> findOptionalByUserIdAndStatus(String userId, EStatus status);
    Optional<List<Basket>> findAllByUserIdAndStatus(String userId,EStatus status);
}
