package com.project.pgmanagement.model.constant;

import com.project.pgmanagement.model.constant.Amenities;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


 @NoArgsConstructor
@Getter
@Embeddable
public class PgAmenities implements Serializable {

    @Column
    private  String amenitieslist ;

     public PgAmenities(String amenitieslist) {
         this.amenitieslist = Amenities.FOOD.toString();
     }

     public void setAmenitieslist(String amenitieslist) {
         StringBuilder builder = new StringBuilder();

         for(Amenities s :Amenities.values())
            builder.append(s.toString()+",");

         this.amenitieslist = builder.toString();
     }
 }





