package com.group7.mezat.controllers;

import com.group7.mezat.documents.Auction;
import com.group7.mezat.requests.AddPackageRequest;
import com.group7.mezat.requests.AuctionUpdateRequest;
import com.group7.mezat.responses.AuctionResponse;
import com.group7.mezat.services.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
@AllArgsConstructor
public class AuctionController {
    private AuctionService auctionService;

    @GetMapping
    public List<Auction> getAllAuction(){
        return auctionService.getAllAuction();
    }

    @GetMapping("/getSortedAuctions")
    public List<Auction> getSortedAuctions(){
        return auctionService.getSortedAuctions();
    }

    @GetMapping("/getCurrentAuction")
    public AuctionResponse getCurrentAuction(){
        return auctionService.getCurrentAuction();
    }

    @GetMapping("/auctionId/{id}")
    public Auction getOneAuctionById(@PathVariable String id){
        return auctionService.getOneAuctionById(id);
    }

    @PostMapping
    public void addAuction(@RequestBody Auction auction) throws Exception {
        auctionService.addAuction(auction);
    }

    @DeleteMapping("/delete/{auctionId}")
    public void deleteAuction(@PathVariable String auctionId){
        auctionService.deleteAuction(auctionId);
    }

    @PutMapping("/update/{auctionId}")
    public void updateAuction(@PathVariable String auctionId, @RequestBody AuctionUpdateRequest updateRequest){
        auctionService.updateAuction(auctionId, updateRequest);
    }

    @PutMapping("/addFish")
    public void addFishPackageToAuction(@RequestBody AddPackageRequest addFishRequest) throws Exception {
        auctionService.addFishPackageToAuction(addFishRequest);
    }

    @PutMapping("/start/{auctionId}")
    public void startAuction(@PathVariable String auctionId){
        auctionService.startAuction(auctionId);
    }

    @PutMapping("/end/{auctionId}")
    public void endAuction(@PathVariable String auctionId){
        auctionService.endAuction(auctionId);
    }
}
