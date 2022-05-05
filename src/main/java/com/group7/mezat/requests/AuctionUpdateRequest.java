package com.group7.mezat.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group7.mezat.documents.AuctionStatus;
import lombok.Data;

import java.util.Date;

@Data
public class AuctionUpdateRequest {
    private AuctionStatus auctionStatus;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date auctionStart;
}
