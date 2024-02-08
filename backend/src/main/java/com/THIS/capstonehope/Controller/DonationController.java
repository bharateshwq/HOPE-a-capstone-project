package com.THIS.capstonehope.Controller;

import com.THIS.capstonehope.Models.Donation;
// import com.THIS.capstonehope.Models.VolunteerCampaign;
import com.THIS.capstonehope.Repository.DonationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
