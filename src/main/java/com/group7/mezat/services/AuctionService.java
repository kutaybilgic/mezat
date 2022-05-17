package com.group7.mezat.services;

import com.group7.mezat.documents.Auction;
import com.group7.mezat.documents.AuctionStatus;
import com.group7.mezat.documents.FishPackage;
import com.group7.mezat.documents.FishStatus;
import com.group7.mezat.repos.AuctionRepository;
import com.group7.mezat.requests.AddPackageRequest;
import com.group7.mezat.requests.AuctionUpdateRequest;
import com.group7.mezat.responses.AuctionResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Auction> auction = auctionRepository.findById(id);
        if (auction.isPresent()){
            return auction.get();
        }
        return null;
    }

    public void addAuction(Auction auction) { auctionRepository.insert(auction);}

    public void deleteAuction(String auctionId) {auctionRepository.deleteById(auctionId);}

    public void addFishPackageToAuction(AddPackageRequest packageRequest) {
        List<Auction> auctions = auctionRepository.findAllByAuctionStatus(Sort.by(Sort.Direction.ASC, "auctionStart"), AuctionStatus.STARTING);
        Auction foundAuction = auctions.get(0);
        FishPackage fishPackage = new FishPackage();
        fishPackage.setFishType(packageRequest.getFishType());
        fishPackage.setBasePrice(packageRequest.getBasePrice());
        fishPackage.setFishAmount(packageRequest.getFishAmount());
        fishPackage.setAuctionId(foundAuction.getId());
        fishPackage.setStatus(FishStatus.UNSOLD);
        fishPackage.setSoldPrice(0);
        fishPackage.setBuyerId(null);
        fishPackage.setSellerId(null);
        fishPackage.setSoldDate(null);
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

    public AuctionResponse getCurrentAuction() {
        List<Auction> auctions = auctionRepository.findAllByAuctionStatus(Sort.by(Sort.Direction.ASC, "auctionStart"), AuctionStatus.STARTING);
        Auction foundAuction = auctions.get(0);
        AuctionResponse response = new AuctionResponse(foundAuction);
        return response;
    }
}
