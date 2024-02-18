package com.THIS.capstonehope.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.THIS.capstonehope.Models.Campaign;
import com.THIS.capstonehope.Models.CampaignType;
import com.THIS.capstonehope.Models.Donation;
@Component
public class CampaignCalc {
	 public  Campaign donationProgressUpdate(Campaign campaign) {
	      Boolean isDonation =false;
			for(CampaignType i:campaign.getType()){
			    if(i.equals(CampaignType.DONATION)){
			        isDonation=true;
			    }
			}		
			if(isDonation){
			    BigDecimal sumOfDonations = BigDecimal.ZERO; 
			    for(Donation d:campaign.getDonors()){
			        sumOfDonations = sumOfDonations.add(new BigDecimal(d.getAmount()));
			    }
//	     // Calculate the percntage of progress
	    int newProgress = sumOfDonations.divide(BigDecimal.valueOf(campaign.getRequiredAmount()), 2, RoundingMode.DOWN)
	            .multiply(BigDecimal.valueOf(100)).intValue();
	     campaign.setMonetaryProgress(newProgress);	
			}
			return campaign;
		}	
	 public  Campaign volunteerProgressUpdate(Campaign campaign) {

	      Boolean isVolunteering =false;
			for(CampaignType i:campaign.getType()){
			    if(i.equals(CampaignType.VOLUNTEER)){
			        isVolunteering=true;
			        break;
			    }
			}		
		if(isVolunteering) {
			int newParticipations = campaign.getVolunteers().size();
int newProgress = (int) ((double) newParticipations / campaign.getRequiredVolunteers() *100);
           campaign.setVolunteerProgress(newProgress);
		}
		return campaign;
	}
	
	
	
	
	
	
	
}
