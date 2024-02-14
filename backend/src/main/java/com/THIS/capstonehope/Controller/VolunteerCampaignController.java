package com.THIS.capstonehope.Controller;

import com.THIS.capstonehope.Models.VolunteerCampaign;
import com.THIS.capstonehope.Repository.VolunteerCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VolunteerCampaignController {
    @Autowired
    private VolunteerCampaignRepository volunteerCampaignRepository;

    @PostMapping("/volunteer-campaigns")
    public VolunteerCampaign createVolunteerCampaign(@RequestBody VolunteerCampaign volunteerCampaign) {
        return volunteerCampaignRepository.save(volunteerCampaign);
    }

    @GetMapping("/volunteer-campaigns")
    public List<VolunteerCampaign> getAllVolunteerCampaigns() {
        return volunteerCampaignRepository.findAll();
    }
    // Add method to approve or decline volunteer campaign
    @PostMapping("/admin/approveVolunteerCampaign/{id}")
    public String approveVolunteerCampaign(@PathVariable String id) {
        VolunteerCampaign campaign = volunteerCampaignRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Volunteer campaign not found"));
        // Approve volunteer campaign logic here
        return "Volunteer campaign approved successfully";
    }

    @PostMapping("/admin/declineVolunteerCampaign/{id}")
    public String declineVolunteerCampaign(@PathVariable String id) {
        volunteerCampaignRepository.deleteById(id);
        return "Volunteer campaign declined successfully";
    }
}
