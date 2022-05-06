package com.group7.mezat.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Auction {
    @Id
    private String id;
    private AuctionStatus auctionStatus;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date auctionStart;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date auctionEnd;
    private List<FishPackage> fishList;
    private List<Bid> bidList;

}
