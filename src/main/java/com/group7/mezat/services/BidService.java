package com.group7.mezat.services;


import com.group7.mezat.documents.Auction;
import com.group7.mezat.documents.Bid;
import com.group7.mezat.documents.User;
import com.group7.mezat.repos.BidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BidService {
    private BidRepository bidRepository;

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

    public void publishBid(Bid bid) {
        bidRepository.insert(bid);
    }
}
