package org.block.analytics.feedback.service;

import org.block.analytics.feedback.model.MasterReviewES;
import org.block.analytics.feedback.repository.MasterReviewESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    MasterReviewESRepository masterReviewESRepository;

    public List<MasterReviewES> findAllDataByEntityId(String entityId){
        return masterReviewESRepository.findByEntityId(entityId);
    }
}
