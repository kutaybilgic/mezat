package com.group7.mezat.manager;


import com.group7.mezat.config.MQConfig;
import com.group7.mezat.controllers.AuctionController;
import com.group7.mezat.controllers.UserController;
import com.group7.mezat.documents.FishPackage;
import com.group7.mezat.documents.User;
import com.group7.mezat.requests.BidRequest;
import com.group7.mezat.requests.PackageSoldRequest;
import com.group7.mezat.services.AuctionService;
import com.group7.mezat.services.BidService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctionManager" )
@AllArgsConstructor
public class AuctionManager {

    private AuctionController auctionController;
    private AuctionService auctionService;
    private List<User> userList;
    //notification
    private BidService bidService;
    private UserController userController;

    @Autowired
    private RabbitTemplate template;

//    @PostMapping("/bid") take a {@link bidRequest} and make a bid
    @PostMapping("/bid")
    public void takeBid(@RequestBody BidRequest bidRequest) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, bidRequest);
    }

    public void nextFishPackage() {

    }

    @PutMapping("/sellPackage/{fishPackageId}")
    public void sellPackage(@PathVariable String fishPackageId, @RequestBody PackageSoldRequest packageSoldRequest) {
        auctionService.sellPackage(fishPackageId, packageSoldRequest);
    }



}
