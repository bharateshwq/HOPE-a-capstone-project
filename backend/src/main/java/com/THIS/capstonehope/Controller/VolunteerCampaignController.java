package com.THIS.capstonehope.Controller;

import com.THIS.capstonehope.Models.VolunteerCampaign;
import com.THIS.capstonehope.Repository.VolunteerCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
