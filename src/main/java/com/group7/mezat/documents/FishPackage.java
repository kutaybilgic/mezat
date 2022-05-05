package com.group7.mezat.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document
public class FishPackage {

    @Id
    private String id;
    private String fishType;
    private float fishAmount;
    private String sellerId;
    private String buyerId;
    private float basePrice;
    private float soldPrice;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date soldDate;
    private String auctionId;
    private Status status;

}

