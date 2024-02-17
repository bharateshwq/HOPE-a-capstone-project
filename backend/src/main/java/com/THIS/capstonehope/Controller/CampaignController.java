package com.THIS.capstonehope.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.THIS.capstonehope.Models.Campaign;
import com.THIS.capstonehope.Repository.CampaignRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/campaigns")
public class CampaignController {

	 @Autowired
	 	private CampaignRepository campaignRepo;


	    @PostMapping("/add")
	    public Campaign createDonation(@RequestBody Campaign campaign) {
	        return campaignRepo.save(campaign);
	    }
	  
	     @GetMapping("/listAll")
		    public List<Campaign> getAllCampaigns() {
		        return campaignRepo.findAll();
		    }
	    @GetMapping("/list")
	    public String getList() {
	    	return "hello world";
	    }
	     @GetMapping("/all")
	     public String allAccess() {
	       return "Public Content.";
	     }
	     
	     
	     @GetMapping("/listAllStruct")
	     public ResponseEntity<?> getDashboardData() {
	         List<Campaign> campaigns = campaignRepo.findAll();
	       
	         return new ResponseEntity<>(new DashboardData(campaigns), HttpStatus.OK);
	     }
	     
	     

	     @GetMapping("/searchStruct")
	     public ResponseEntity<?> searchProjects(@RequestParam String query) {
	         if(StringUtils.hasText(query)) {
	             
	             List<Campaign> campaigns = campaignRepo.findByTitleContainingIgnoreCase(query);
	             return new ResponseEntity<>(new SearchResults(campaigns), HttpStatus.OK);
	         } else {
	             return new ResponseEntity<>("Query parameter is required for search", HttpStatus.BAD_REQUEST);
	         }
	     }
	     
	    //encapsulates dashboard funcs
	     static class DashboardData {
	         private List<Campaign> campaigns;
	         public DashboardData(List<Campaign> campaigns) {
	 			// TODO Auto-generated constructor stub
	         	 this.campaigns = campaigns;
	 		}
	      // Getters for donations and volunteerCampaigns
	         public List<Campaign> getCampaigns() {
	             return campaigns;
	         }
	     }
	 //encapsulates search
	     static class SearchResults {
	         private List<Campaign> campaigns;
	         public SearchResults(List<Campaign> campaigns) {
	             this.campaigns = campaigns;
	         
	         }
	         // Getters for donations and volunteerCampaigns
	         public List<Campaign> getCampaigns() {
	             return campaigns;
	         } 
	     }
}
