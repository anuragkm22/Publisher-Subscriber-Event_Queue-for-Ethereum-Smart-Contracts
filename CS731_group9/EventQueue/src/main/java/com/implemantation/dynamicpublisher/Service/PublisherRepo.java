package com.implemantation.dynamicpublisher.Service;

import com.implemantation.dynamicpublisher.dto.PublisherItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PublisherRepo extends MongoRepository<PublisherItem,String> {
    @Query(value = "{topic_id:?0}")
    List<PublisherItem> findByTopicId(String topic_id);
}
