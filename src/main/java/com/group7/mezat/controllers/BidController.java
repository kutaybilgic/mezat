package com.group7.mezat.controllers;


import com.group7.mezat.documents.Auction;
import com.group7.mezat.documents.Bid;
import com.group7.mezat.manager.MQConfig;
import com.group7.mezat.services.BidService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/bid")
@AllArgsConstructor
@Component
public class BidController {
    private BidService bidService;

    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(Bid bid){
        System.out.println(bid);

    }

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
