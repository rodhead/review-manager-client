package org.block.analytics.feedback.repository;

import org.block.analytics.feedback.model.AmazonReviews;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AmazonReviewsRepository extends MongoRepository<AmazonReviews, String> {

    @Query("{productId:'?0'}")
    public List<AmazonReviews> getReviewById(String s);

    @Aggregation(pipeline = {"{'$group':{'id':'$productId'}}"})
    List<String> findAllUniqueProductType();
}
