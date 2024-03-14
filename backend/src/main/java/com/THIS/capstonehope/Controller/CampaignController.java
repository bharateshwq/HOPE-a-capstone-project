package com.THIS.capstonehope.Controller;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.THIS.capstonehope.Repository.SearchFilteringRepository;
import com.THIS.capstonehope.service.CampaignService;
import com.THIS.capstonehope.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaigns")
public class CampaignController {

	 @Autowired
private final CampaignService campaignService;
// @Autowired
// private final Campaign campaign;




@Autowired
EmailService emailService;


	 
	 @Autowired
	 private final SearchFilteringRepository sfrep;

	@GetMapping("/check")
	public UserDetails check() {
		UserDetails userDetails =
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return userDetails;
	}
	//add campaign
	 	@PostMapping
	    public Campaign addCampaign(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Campaign projectCreation) {
	        return campaignService.addCampaign(projectCreation);
	    } 

	 	@GetMapping("{id}")
	    public Campaign getProjectById(@PathVariable String id) {
	        return campaignService.getCampaignById(id);
	    } 
	 
	 	@GetMapping
	 	@PreAuthorize("hasRole('ADMIN')")
	    public List<Campaign> getAllProjects() {
	        return campaignService.getAllCampaign();
	    }	 
	 	//search
	 @GetMapping("/masterSearch")	
	 public List<Campaign> findByText(@RequestParam String query){
		return sfrep.findByText(query) ;
		 
	 }
	 //Volunteer or donation fiilter
	 @GetMapping("filter/{type}")
	 public List<Campaign> filterCampaign(@PathVariable String type){
		 return sfrep.filterCampaign(type);
	 }
//main dash sort
	 @GetMapping("sort")
	 public List<Campaign> sortCampaign(@RequestParam int asc){
		 return sfrep.sortCampaign(asc);
	 }
	 //mainDash
	 @GetMapping("mainDash")
	 public List<Campaign> verifiedCampaigns(){
		 return sfrep.VerifiedCampaign();
	 }
	 //admin dashboard
	 @GetMapping("mainAdminDash")
  @PreAuthorize("hasRole('ADMIN')")
	 public List<Campaign> sortedVerifiedandUnverCampaigns(){
		 return sfrep.SortedVerifiedandUnverified();
	 }
//	 	
	 	@PostMapping("/admin/verify/{id}")
  @PreAuthorize("hasRole('ADMIN')")
	 	public Campaign verifyCampaign(@PathVariable String id, @RequestParam Boolean legitimacy ) {
	 		Campaign updateProject = campaignService.verifyCampaign(id,legitimacy);
	 		return updateProject;
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
			@PostMapping("/donate/{projectId}")
		public ResponseEntity<Campaign> addDonation(@PathVariable String projectId, @RequestBody Donation donationCreation) {
			
				Campaign updatedCampaign = campaignService.addDonation(projectId, donationCreation);
				// emailService.sendCertificate(updatedCampaign.getEmail(), updatedCampaign);
				return ResponseEntity.ok(updatedCampaign);

		}

	    @PostMapping("/volunteer/{id}")
	    public Campaign addParticipation(@PathVariable String id) {
	        return campaignService.addParticipation(id);
	    } 
	    
	 
	 
	 
	 
	 
	 
}
