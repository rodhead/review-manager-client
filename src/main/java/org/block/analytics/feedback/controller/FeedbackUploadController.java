package org.block.analytics.feedback.controller;

import org.block.analytics.feedback.model.AmazonReviews;
import org.block.analytics.feedback.model.MasterReviewES;
import org.block.analytics.feedback.model.Vehicle;
import org.block.analytics.feedback.repository.AmazonReviewsRepository;
import org.block.analytics.feedback.service.FeedbackService;
import org.block.analytics.feedback.service.FeedbackUploadService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upstream")
@CrossOrigin("http://localhost:4200")
public class FeedbackUploadController {

    @Autowired
    FeedbackUploadService uploadService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    AmazonReviewsRepository amazonReviewsRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/testmongo")
    public List<AmazonReviews> getMongo(){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is("4"));
        //return mongoTemplate.findOne(query, AmazonReviews.class).toString();
        return amazonReviewsRepository.getReviewById("B00813GRG4");
    }

    @PostMapping("/upload")
    public String uploadFeedbackFile(@RequestParam("file")MultipartFile file) throws IOException {
        String response = uploadService.initialFeedbackProcess(file);
        return response;
    }

    /*@PostMapping("getData/{entityId}")
    public String getAllData(@PathVariable("entityId") String entityId) throws JSONException {
        JSONObject js = new JSONObject();
        JSONArray dataArray = new JSONArray();
        List<MasterReviewES> data = feedbackService.findAllDataByEntityId(entityId);
        for(MasterReviewES master : data){
            dataArray.put(master.jsonify());
        }
        js.put("data",dataArray);
        js.put("recordsTotal",dataArray.length());
        js.put("recordsFiltered",dataArray.length());

        return js.toString();
    }*/

    @GetMapping("/startupData/{entityId}")
    public String getUploadPageStartupData(@PathVariable("entityId") String entityId) throws JSONException {
        String response ="";
        if(null != entityId && !entityId.equals("")){
            response = uploadService.getStartupDataForUpload();
            System.out.println(response);
        }
        return response;
    }

    /*@PostMapping
    public void index(@RequestBody final Vehicle vehicle){
        vehicleService.index(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable final String id){
        return vehicleService.getById(id);
    }*/
}
