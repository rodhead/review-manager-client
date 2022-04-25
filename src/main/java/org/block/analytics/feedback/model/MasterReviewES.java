package org.block.analytics.feedback.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Document(indexName = "block_analytics")
@Setting(settingPath = "static/es-settings.json")
@Getter
@Setter
public class MasterReviewES {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "reviewId")
    private String reviewId;

    @Field(type = FieldType.Text, name = "entityId")
    private String entityId;

    @Field(type = FieldType.Text, name = "review")
    private String review;

    @Field(type = FieldType.Integer, name = "reviewRating")
    private String reviewRating;

    @Field(type = FieldType.Integer, name = "ratingMaxValue")
    private String ratingMaxValue;

    @Field(type = FieldType.Text, name = "reviewDateCreated")
    private String reviewDateCreated;

    @Field(type = FieldType.Keyword, name = "reviewSupportImage")
    private List<LinkedHashMap<Object,Object>> reviewSupportImage;

    @Field(type = FieldType.Text, name = "reviewUrl")
    private String reviewUrl;

    @Field(type = FieldType.Date, name = "createdDate")
    private String createdDate;

    @Field(type = FieldType.Text, name = "status")
    private String status;

    @Field(type = FieldType.Integer, name = "createdBy")
    private String createdBy;


    public JSONObject jsonify() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id",this.id);
        json.put("reviewId",this.reviewId);
        json.put("entityId",this.entityId);
        json.put("review",this.review);
        json.put("reviewRating",this.reviewRating);
        json.put("ratingMaxValue",this.ratingMaxValue);
        json.put("reviewDateCreated",this.reviewDateCreated);
        json.put("reviewUrl",this.reviewUrl);
        json.put("createdDate",this.createdDate);
        json.put("status",this.status);
        json.put("createdBy",this.createdBy);
        return json;
    }
}
