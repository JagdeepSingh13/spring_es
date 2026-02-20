package com.example.restrO.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

// although not safe to use Data but we don't have bi-dirn relationship
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Field(type= FieldType.Keyword)
    private String id;

    @Field(type=FieldType.Text)
    private String username;

    @Field(type=FieldType.Text)
    private String givenName;

    @Field(type=FieldType.Text)
    private String familyName;

}
