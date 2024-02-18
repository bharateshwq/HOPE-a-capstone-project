package com.THIS.capstonehope.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;

import com.THIS.capstonehope.Models.Campaign;
import com.THIS.capstonehope.Models.Donation;
import com.THIS.capstonehope.Models.Volunteer;
import com.THIS.capstonehope.Repository.CampaignRepository;
import com.THIS.capstonehope.Repository.SearchFilterRepoImpl;
import com.THIS.capstonehope.Repository.SearchFilteringRepository;
import com.THIS.capstonehope.security.models.User;
import com.THIS.capstonehope.security.security.services.UserService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CampaignService {

	private final CampaignRepository campaignRepo;
	private final CampaignCalc campaignCalc;
	private final IdService idService;
	private final UserService userService;
	
	
	private static final String NOT_FOUND_MESSAGE1 = "======================================================No project with ";
    private static final String NOT_FOUND_MESSAGE2 = " found===========================================================================================================================================";

    
    
    public List<Campaign> getAllCampaign(){
    	return campaignRepo.findAll();
    }
    
    public Campaign addCampaign(Campaign campaignCreation) {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        campaignCreation.setId(idService.createRandomId());
        campaignCreation.setMonetaryProgress(0);
        campaignCreation.setVolunteerProgress(0);
        campaignCreation.setDonors(new ArrayList<>());
        campaignCreation.setVolunteers(new ArrayList<>());
        campaignCreation.setHostedBy(user.getId());
        campaignCreation.setLegitimacy(false);
    	return campaignRepo.insert(campaignCreation);
    }
    public Campaign getCampaignById(String projectId) {
        return campaignRepo.findById(projectId).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE1 + projectId + NOT_FOUND_MESSAGE2));
    }
    public Campaign verifyCampaign(String projectId,Boolean legitimacy) {
        Campaign campaign = campaignRepo.findById(projectId).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE1 + projectId + NOT_FOUND_MESSAGE2));
        campaign.setLegitimacy(legitimacy);
        return campaignRepo.save(campaign);
    }
    public Campaign updateCampaign(String id, Campaign projectNoId) {
        if (!campaignRepo.existsById(id)) throw new NoSuchElementException(NOT_FOUND_MESSAGE1 + id + NOT_FOUND_MESSAGE2);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
       User user = userService.findByUsername(username);

        if (!user.getId().toString().equals(projectNoId.getHostedBy()))
            throw new MethodNotAllowedException("You are not allowed to edit this project", null);
        projectNoId.setId(id);
        Campaign updatedCampaign = campaignRepo.save(projectNoId);
        return updatedCampaign;
    }
    public void deleteCampaign(String projectId) {
        if (!campaignRepo.existsById(projectId)) throw new NoSuchElementException(NOT_FOUND_MESSAGE1 + projectId + NOT_FOUND_MESSAGE2);
        campaignRepo.deleteById(projectId);
    } 
    public Campaign addDonation(String projectId, Donation donationCreation) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Campaign campaign = campaignRepo.findById(projectId).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE1 + projectId + NOT_FOUND_MESSAGE2));
        Donation newDonation = Donation.builder()
        		.id(idService.createRandomId())
        		.campaignId(projectId)
        		.campaignTitle(campaign.getTitle())
        		.donatedOn(LocalDateTime.now())
        		.amount(donationCreation.getAmount())
        		.transactionId(idService.createRandomId())
        		.donorName(user.getUsername())
        		.donorId(user.getId()).build();
        user.appendDonation(newDonation);
        userService.updateUser(user);

        campaign.appendDonation(newDonation);

        return campaignRepo.save(campaignCalc.donationProgressUpdate(campaign));
    }
    public Campaign addParticipation(String projectId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        
        Campaign campaign = campaignRepo.findById(projectId).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE1 + projectId + NOT_FOUND_MESSAGE2));
        Boolean hasVolunteered = false;
        for(Volunteer vol:campaign.getVolunteers()) {
        if(vol.getVolunteerid().equals(user.getId())) { hasVolunteered = true; break;}
        }
        if(!hasVolunteered) {
        Volunteer newParticipation = Volunteer.builder()
        		.id(idService.createRandomId())
        		.volunteerid(user.getId())
        		.volunteerName(user.getUsername())
				.enrolledOn(LocalDateTime.now())
				.campaignId(projectId)
				.campaignName(campaign.getTitle())
				.build();
        user.appendVolunteers(newParticipation);
        userService.updateUser(user);

        campaign.appendVolunteers(newParticipation);

        return campaignRepo.save(campaignCalc.volunteerProgressUpdate(campaign));
        }else {
        return campaign;
        }
        }
    
    
    
    
    
    } 

	
	
	
	
