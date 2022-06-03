package com.group7.mezat.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group7.mezat.documents.Bid;
import com.group7.mezat.documents.BidStatus;
import com.group7.mezat.documents.FishPackage;
import com.group7.mezat.documents.FishStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PackageResponse {
    private String id;
    private String fishType;
    private float fishAmount;
    private String sellerName;
    private String buyerName;
    private float basePrice;
    private float soldPrice;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date soldDate;
    private String auctionId;
    private FishStatus status;
    private BidStatus bidStatus;
    private List<Bid> bids;
    private int turn;

    public PackageResponse(FishPackage fishPackage) {
        this.id = fishPackage.getId();
        this.fishType = fishPackage.getFishType();
        this.fishAmount = fishPackage.getFishAmount();
        this.basePrice = fishPackage.getBasePrice();
        this.soldPrice = fishPackage.getSoldPrice();
        this.soldDate = fishPackage.getSoldDate();
        this.auctionId = fishPackage.getAuctionId();
        this.status = fishPackage.getStatus();
        this.bidStatus = fishPackage.getBidStatus();
        this.bids = fishPackage.getBids();
    }
}
