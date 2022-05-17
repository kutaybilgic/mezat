package com.group7.mezat.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PackageSoldRequest {

    private String buyerId;
    private float soldPrice;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date soldDate;
}
