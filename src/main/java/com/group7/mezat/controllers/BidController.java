package com.group7.mezat.controllers;


import com.group7.mezat.documents.Auction;
import com.group7.mezat.documents.Bid;
import com.group7.mezat.services.BidService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bid")
@AllArgsConstructor
public class BidController {
    private BidService bidService;

    @GetMapping
    public List<Bid> getAllBids(){
        return bidService.getAllBids();
    }

    @GetMapping("/bidId/{id}")
    public Bid getOneBidById(@PathVariable String id){
        return bidService.getOneBidById(id);
    }

    @GetMapping("/userId/{userId}")
    public List<Bid> getUserBids(@PathVariable String userId){return bidService.getUserBids(userId);}

}
