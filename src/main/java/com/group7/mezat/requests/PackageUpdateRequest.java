package com.group7.mezat.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group7.mezat.documents.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PackageUpdateRequest {
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
