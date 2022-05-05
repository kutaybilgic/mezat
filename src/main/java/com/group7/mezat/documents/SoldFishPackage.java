package com.group7.mezat.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SoldFishPackage {

    @Id
    private String id;
    private String fishType;
    private float fishAmount;
    private String buyerId;
    private float soldPrice;
    private String auctionId;

}
