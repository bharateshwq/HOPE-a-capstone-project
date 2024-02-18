package com.THIS.capstonehope.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.THIS.capstonehope.Models.Campaign;
import com.THIS.capstonehope.Models.Donation;
import com.THIS.capstonehope.Models.Volunteer;
import com.THIS.capstonehope.Repository.CampaignRepository;
import com.THIS.capstonehope.service.CampaignService;

import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/campaigns")
public class CampaignController {

	 @Autowired
private final CampaignService campaignService;

	 
	 	@PostMapping
	    public Campaign addCampaign(@RequestBody Campaign projectCreation) {
	        return campaignService.addCampaign(projectCreation);
	    } 
	 	@GetMapping("{id}")
	    public Campaign getProjectById(@PathVariable String id) {
	        return campaignService.getCampaignById(id);
	    } 
	 
	 	@GetMapping
	    public List<Campaign> getAllProjects() {
	        return campaignService.getAllCampaign();
	    }	 
	 	@PutMapping("{id}")
	    public ResponseEntity<Campaign> updateCampaign(@PathVariable String id, @RequestBody Campaign projectNoId) {
	        try {
	            Campaign updatedProject = campaignService.updateCampaign(id, projectNoId);
	            return ResponseEntity.ok(updatedProject);
	        } catch (NoSuchElementException e) {
	            return ResponseEntity.notFound().build();
	        }
	    } 
	 	@DeleteMapping("{id}")
	    public ResponseEntity<String> deleteCampaign(@PathVariable String id) {

	        try {
	            campaignService.deleteCampaign(id);
	            return ResponseEntity.ok("Project deleted successfully");

	        } catch (NoSuchElementException e) {
	            return ResponseEntity.notFound().build();
	        }
	    } 
	 	@PostMapping("/donate/{id}")
	    public Campaign addDonation(@PathVariable String id, @RequestBody Donation donationCreation) {
	        return campaignService.addDonation(id, donationCreation);
	    }

	    @PostMapping("/volunteer/{id}")
	    public Campaign addParticipation(@PathVariable String id, @RequestBody Volunteer VolunteerCollection) {
	        return campaignService.addParticipation(id, VolunteerCollection);
	    } 
	 
	 
	 
	 
	 
	 
}
