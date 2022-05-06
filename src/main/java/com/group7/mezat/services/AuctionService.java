package com.group7.mezat.services;

import com.group7.mezat.documents.Auction;
import com.group7.mezat.documents.AuctionStatus;
import com.group7.mezat.documents.FishPackage;
import com.group7.mezat.documents.Status;
import com.group7.mezat.repos.AuctionRepository;
import com.group7.mezat.requests.AuctionUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuctionService {
    private AuctionRepository auctionRepository;

    public void updateAuction(String auctionId, AuctionUpdateRequest updateRequest) {
        Optional<Auction> auction = auctionRepository.findById(auctionId);
        if (auction.isPresent()){
            Auction foundAuction = auction.get();
            foundAuction.setAuctionStatus(updateRequest.getAuctionStatus());
            foundAuction.setAuctionStart(updateRequest.getAuctionStart());
            auctionRepository.save(foundAuction);
        }
    }

    public List<Auction> getAllAuction(){
        return auctionRepository.findAll();
    }

    public Auction getOneAuctionById(String id) {
        return auctionRepository.getOneAuctionById(id);
    }

    public void addAuction(Auction auction) { auctionRepository.insert(auction);}

    public void deleteAuction(String auctionId) {auctionRepository.deleteById(auctionId);}

    public void addFishPackageToAuction(FishPackage fishPackage) {
        Optional<Auction> auction = auctionRepository.findById(fishPackage.getAuctionId());
        if (auction.isPresent()){
            Auction foundAuction = auction.get();
            foundAuction.getFishList().add(fishPackage);
            auctionRepository.save(foundAuction);
        }
    }

    public void startAuction(String auctionId) {
        Optional<Auction> auction = auctionRepository.findById(auctionId);
        if (auction.isPresent()){
            Auction foundAuction = auction.get();
            foundAuction.setAuctionStatus(AuctionStatus.OPEN);
            auctionRepository.save(foundAuction);
        }
    }

    public void endAuction(String auctionId) {
        Optional<Auction> auction = auctionRepository.findById(auctionId);
        if (auction.isPresent()){
            Auction foundAuction = auction.get();
            foundAuction.setAuctionStatus(AuctionStatus.FINISHED);
            auctionRepository.save(foundAuction);
        }
    }
}
