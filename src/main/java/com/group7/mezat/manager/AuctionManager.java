package com.group7.mezat.manager;


import com.group7.mezat.controllers.AuctionController;
import com.group7.mezat.controllers.BidController;
import com.group7.mezat.documents.FishPackage;
import com.group7.mezat.documents.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auctionManager")
@AllArgsConstructor
public class AuctionManager {

    private AuctionController auctionController;
    private List<User> userList;
    private BidController bidController;
    //notification

    @PutMapping("/start/{auctionId}")
    public void startAuction(@PathVariable String auctionId) throws Exception {
        auctionController.startAuction(auctionId);
    }

    @PutMapping("/end/{auctionId}")
    public void endAuction(@PathVariable String auctionId){
        auctionController.endAuction(auctionId);
    }

    public void takeBid(User bidder, float bid){

    }

    public void sellPackage(User bidder, FishPackage fishPackage){

    }



}
