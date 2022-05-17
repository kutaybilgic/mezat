package com.group7.mezat.requests;

import lombok.Data;

@Data
public class AddPackageRequest {
    private String fishType;
    private float fishAmount;
    private float basePrice;

//    private String sellerId;

}
