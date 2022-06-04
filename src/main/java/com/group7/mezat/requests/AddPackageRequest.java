package com.group7.mezat.requests;

import com.group7.mezat.documents.Bid;
import lombok.Data;

import java.util.List;

@Data
public class AddPackageRequest {
    private String fishType;
    private float fishAmount;
    private float basePrice;
    private String email;
}
