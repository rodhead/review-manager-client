package org.block.analytics.feedback.service;

import org.block.analytics.core.model.DataTableRequest;
import org.block.analytics.feedback.model.AmazonReviews;
import org.block.analytics.feedback.repository.AmazonReviewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackViewService {

    private static final Logger log = LoggerFactory.getLogger(FeedbackViewService.class);
    private static final String COLLECTION = "amazonReviews";

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    AmazonReviewsRepository amazonReviewsRepository;

    public Map<String, Object> getAllPaginatedData(DataTableRequest dataTableRequest) {
        log.info("Inside getAllPaginatedData method for request of " +
                " page "+dataTableRequest.getStart()+" with draw "+dataTableRequest.getDraw());

        Map<String, Object> response = new HashMap<>();

        final Pageable pageableRequest = PageRequest.of((dataTableRequest.getStart()/dataTableRequest.getLength())+1,dataTableRequest.getLength());
        Page<AmazonReviews> pageResult = amazonReviewsRepository.findAll(pageableRequest);
        response.put("data",pageResult.getContent());
        response.put("draw", dataTableRequest.getDraw());
        response.put("recordsFiltered",pageResult.getTotalElements());
        response.put("recordsTotal",pageResult.getTotalElements());

        return response;
    }

    public List<String> getAllUniqueFilterType() {
        return mongoTemplate.getCollection(COLLECTION).distinct("ProductId", String.class).into(new ArrayList<>());
    }
}
