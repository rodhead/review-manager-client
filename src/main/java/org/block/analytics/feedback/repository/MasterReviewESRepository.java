package org.block.analytics.feedback.repository;

import org.block.analytics.feedback.model.MasterReviewES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MasterReviewESRepository extends ElasticsearchRepository<MasterReviewES, String> {

    List<MasterReviewES> findByEntityId(String entityId);
}
