package org.block.analytics.feedback.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection ="amazonReviews")
@NoArgsConstructor
@AllArgsConstructor
public class AmazonReviews {
    @Id
    private String id;

    @Field(value = "ProductId")
    private String productId;

    @Field(value = "UserId")
    private String userId;

    @Field(value = "ProfileName")
    private String profileName;

    @Field(value = "Score")
    private String score;

    @Field(value = "Summary")
    private String summary;

    @Field(value = "Text")
    private String text;

}
