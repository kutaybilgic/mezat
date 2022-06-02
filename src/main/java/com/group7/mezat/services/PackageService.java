package com.group7.mezat.services;

import com.group7.mezat.documents.BidStatus;
import com.group7.mezat.documents.FishPackage;
import com.group7.mezat.documents.FishStatus;
import com.group7.mezat.repos.PackageRepository;
import com.group7.mezat.requests.PackageSoldRequest;
import com.group7.mezat.requests.PackageUpdateRequest;
import com.group7.mezat.responses.PackageResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PackageService {

    private PackageRepository packageRepository;

    public List<FishPackage> getOneUsersPackages(String buyerId) {
        System.out.println("service");
        return packageRepository.findAllByBuyerId(buyerId);
    }

    public void addPackage(FishPackage fishPackage) {
        packageRepository.insert(fishPackage);
    }

    public List<FishPackage> getAllSoldPackages() {
        return packageRepository.findAllByStatus(FishStatus.SOLD);
    }

    public List<FishPackage> getAllUnsoldPackages() {
        return packageRepository.findAllByStatus(FishStatus.UNSOLD);
    }

    public void deletePackage(String packageId) {
        packageRepository.deleteById(packageId);
    }

    public void updatePackage(String packageId, PackageUpdateRequest updateRequest) {
        Optional<FishPackage> fishPackage = packageRepository.findById(packageId);
        if (fishPackage.isPresent()){
            FishPackage foundPackage = fishPackage.get();
            foundPackage.setFishType(updateRequest.getFishType());
            foundPackage.setFishAmount(updateRequest.getFishAmount());
            foundPackage.setSellerId(updateRequest.getSellerId());
            foundPackage.setBuyerId(updateRequest.getBuyerId());
            foundPackage.setBasePrice(updateRequest.getBasePrice());
            foundPackage.setSoldPrice(updateRequest.getSoldPrice());
            foundPackage.setSoldDate(updateRequest.getSoldDate());
            foundPackage.setAuctionId(updateRequest.getAuctionId());
            foundPackage.setStatus(updateRequest.getStatus());
            packageRepository.save(foundPackage);
        }
    }

    public void sellPackage(String packageId, PackageSoldRequest soldRequest) {
        Optional<FishPackage> fishPackage = packageRepository.findById(packageId);
        if (fishPackage.isPresent()){
            FishPackage foundPackage = fishPackage.get();
            foundPackage.setBuyerId(soldRequest.getBuyerId());
            foundPackage.setSoldPrice(soldRequest.getSoldPrice());
            foundPackage.setSoldDate(soldRequest.getSoldDate());
            foundPackage.setStatus(FishStatus.SOLD);
            packageRepository.save(foundPackage);
        }
    }

    public PackageResponse getCurrentFish() {
        FishPackage packageFish = packageRepository.findByBidStatus(BidStatus.OPEN);
        System.out.println(packageFish);
        return new PackageResponse(packageFish);
    }

    public FishPackage getFishPackageById(String currentPackageId) {
        return packageRepository.findById(currentPackageId).get();
    }

    public void updateFishPackage(FishPackage fishPackage) {
        packageRepository.save(fishPackage);
    }
}
