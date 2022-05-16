package com.group7.mezat.services;

import com.group7.mezat.controllers.PackageController;
import com.group7.mezat.documents.Auction;
import com.group7.mezat.documents.AuctionStatus;
import com.group7.mezat.documents.FishPackage;
import com.group7.mezat.documents.Status;
import com.group7.mezat.repos.AuctionRepository;
import com.group7.mezat.requests.AuctionUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuctionService {
    private AuctionRepository auctionRepository;
    private PackageService packageService;

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
        return auctionRepository.getOneAuctionById(id); // optişonal find by id
    }

    public void addAuction(Auction auction) { auctionRepository.insert(auction);}

    public void deleteAuction(String auctionId) {auctionRepository.deleteById(auctionId);}

//    public void addFishPackageToAuction(FishPackage fishPackage) {
//        Optional<Auction> auction = auctionRepository.findById(fishPackage.getAuctionId());
//        List<Auction> auctions = auctionRepository.findAllByAuctionStatus(AuctionStatus.STARTING);
//        System.out.println(auctions.get(0));
//        if (auction.isPresent()){
//            Auction foundAuction = auction.get();
//            foundAuction.getFishList().add(fishPackage);
//            auctionRepository.save(foundAuction);
//        }
//    }

    public void addFishPackageToAuction(FishPackage fishPackage) {
        List<Auction> auctions = auctionRepository.findAllByAuctionStatus(Sort.by(Sort.Direction.ASC, "auctionStart"), AuctionStatus.STARTING);
        System.out.println(auctions.get(0));
        Auction foundAuction = auctions.get(0);
        fishPackage.setAuctionId(foundAuction.getId());
        packageService.addPackage(fishPackage);
        foundAuction.getFishList().add(fishPackage);
        auctionRepository.save(foundAuction);
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
