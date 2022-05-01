package org.block.analytics.feedback.controller;

import org.block.analytics.core.model.DataTableRequest;
import org.block.analytics.feedback.model.AmazonReviews;
import org.block.analytics.feedback.service.FeedbackViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/view/feedback")
@CrossOrigin("http://localhost:4200")
public class FeedbackViewController {
    private static final Logger log = LoggerFactory.getLogger(FeedbackViewController.class);

    @Autowired
    FeedbackViewService feedbackViewService;

    @GetMapping
    public Map<String, Object>  getModelObject(){
        log.info("Inside getModelObject with path: /view/feedback");
        Map<String, Object> response = new HashMap<>();
        response.put("productTypeFilterData",feedbackViewService.getAllUniqueFilterType());
        return response;
    }

    @PostMapping("/data")
    public Map<String, Object> getData(@RequestBody DataTableRequest dataTableRequest){
        log.info("Inside getData with path: /view/feedback/data");
        List<AmazonReviews> amazonReviewsList = new ArrayList<>();
        Map<String, Object> response = feedbackViewService.getAllPaginatedData(dataTableRequest);
        return  response;
    }
}
