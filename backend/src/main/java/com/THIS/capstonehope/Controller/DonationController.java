package com.THIS.capstonehope.Controller;

import com.THIS.capstonehope.Models.Donation;
// import com.THIS.capstonehope.Models.VolunteerCampaign;
import com.THIS.capstonehope.Repository.DonationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationController {
    @Autowired
    private DonationRepository donationRepository;

    @PostMapping("/donations")
    public Donation createDonation(@RequestBody Donation donation) {
        return donationRepository.save(donation);
    }
     @GetMapping("/donations")
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }
     // Add method to approve or decline donation campaign
    @PostMapping("/admin/approveDonation/{id}")
    public String approveDonation(@PathVariable String id) {
        Donation donation = donationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Donation not found"));
        // Approve donation logic here
        return "Donation approved successfully";
    }

    @PostMapping("/admin/declineDonation/{id}")
    public String declineDonation(@PathVariable String id) {
        donationRepository.deleteById(id);
        return "Donation declined successfully";
    }
}
