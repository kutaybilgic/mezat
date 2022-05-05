package com.group7.mezat.repos;

import com.group7.mezat.documents.Auction;
import com.group7.mezat.documents.FishPackage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuctionRepository extends MongoRepository<Auction, String> {
    Auction getOneAuctionById(String id);
}
