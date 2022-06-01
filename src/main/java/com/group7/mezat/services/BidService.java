package com.group7.mezat.services;

import com.group7.mezat.documents.Auction;
import com.group7.mezat.documents.Bid;
import com.group7.mezat.repos.BidRepository;
import com.group7.mezat.repos.PackageRepository;
import com.group7.mezat.requests.BidRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BidService {
    private BidRepository bidRepository;
    private PackageService packageService;
    private AuctionService auctionService;

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Bid getOneBidById(String id) {
        Bid bid = bidRepository.findById(id).orElse(null);
        return bid;
    }

    public List<Bid> getUserBids(String userId) {
        return bidRepository.findOneBidByBidderId(userId);
    }

    public void takeBid(BidRequest bidRequest) {
        Bid bid = new Bid();
        bid.setBid(bidRequest.getBid());
        bid.setBidderId(bidRequest.getBidderId());
        bid.setAuctionId(auctionService.getCurrentAuction().getId());
        bid.setFishPackageId(packageService.getCurrentFish().getId());
        bidRepository.insert(bid);
    }

    public List<Bid> getFishPackageBids(String fishPackageId) {
        return bidRepository.findAllByFishPackageId(Sort.by(Sort.Direction.DESC, "bid"),fishPackageId);
    }


}
