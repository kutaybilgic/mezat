package com.group7.mezat.documents;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Bid {
    @Id
    private String bidId;
    private String bidderId;
    private String auctionId;
    private String fishPackageId;
    private float  bid;
}
