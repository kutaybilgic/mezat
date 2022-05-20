package com.group7.mezat.services;

import com.group7.mezat.documents.*;
import com.group7.mezat.repos.AuctionRepository;
import com.group7.mezat.repos.UserRepository;
import com.group7.mezat.requests.AddPackageRequest;
import com.group7.mezat.requests.AuctionUpdateRequest;
import com.group7.mezat.responses.AuctionResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuctionService {
    private AuctionRepository auctionRepository;
    private UserRepository userRepository;
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

    public void addAuction(Auction auction) throws Exception {
        LocalDateTime today = LocalDateTime.now();
        Date res = Date.from(today.atZone(ZoneId.of("UTC")).toInstant());
        if (res.before(auction.getAuctionStart())){
            auctionRepository.insert(auction);
        }else {
            throw new Exception("geçmiş tarih");
        }
    }

    public void deleteAuction(String auctionId) {auctionRepository.deleteById(auctionId);}

    public void addFishPackageToAuction(AddPackageRequest packageRequest) throws Exception {
        List<Auction> auctions = auctionRepository.findAllByAuctionStatus(Sort.by(Sort.Direction.ASC, "auctionStart"), AuctionStatus.STARTING);
        Auction foundAuction = auctions.get(0);
        Optional<User> optionalUser  = Optional.ofNullable(userRepository.findByUserMail(packageRequest.getEmail()));
        if (optionalUser.isPresent() && packageRequest.getFishType().matches("[a-zA-Z]+")){
            User foundUser = optionalUser.get();
            FishPackage fishPackage = new FishPackage();
            fishPackage.setFishType(packageRequest.getFishType());
            fishPackage.setBasePrice(packageRequest.getBasePrice());
            fishPackage.setFishAmount(packageRequest.getFishAmount());
            fishPackage.setAuctionId(foundAuction.getId());
            fishPackage.setStatus(FishStatus.UNSOLD);
            fishPackage.setSoldPrice(0);
            fishPackage.setBuyerId(null);
            fishPackage.setSellerId(foundUser.getId());
            fishPackage.setSoldDate(null);
            packageService.addPackage(fishPackage);
            foundAuction.getFishList().add(fishPackage);
            auctionRepository.save(foundAuction);
        }else {
            throw new Exception("email bulunamadı");
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

    public AuctionResponse getCurrentAuction() {
        List<Auction> auctions = auctionRepository.findAllByAuctionStatus(Sort.by(Sort.Direction.ASC, "auctionStart"), AuctionStatus.STARTING);
        Auction foundAuction = auctions.get(0);
        AuctionResponse response = new AuctionResponse(foundAuction);
        return response;
    }

    public List<Auction> getSortedAuctions() {
        List<Auction> auctions = auctionRepository.findAll(Sort.by(Sort.Direction.ASC, "auctionStart"));
//        List<AuctionResponse> responses = null;
//        auctions.stream().map(auction -> new AuctionResponse(auction)).forEach(auctionResponse -> responses.add(auctionResponse));
//        return responses;
        return auctions;
    }
}
