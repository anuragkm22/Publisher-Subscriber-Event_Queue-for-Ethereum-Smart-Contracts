package com.implemantation.dynamicpublisher.Service;

import com.implemantation.dynamicpublisher.dto.SignHashItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SignHashRepo extends MongoRepository<SignHashItem,String> {


}
