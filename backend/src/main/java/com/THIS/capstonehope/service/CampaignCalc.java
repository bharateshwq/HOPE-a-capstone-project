package com.THIS.capstonehope.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.THIS.capstonehope.Models.Campaign;
import com.THIS.capstonehope.Models.CampaignType;

public class CampaignCalc {
	public Campaign donationProgressUpdate(Campaign campaign) {
		if(campaign.getType().stream().anyMatch(type -> type.equals("DONATION"))){
//			BigDecimal sumOfDonations = campaign.getDonors().stream().reduce(BigDecimal.ZERO,(sum,donation)->sum.add(donation.getDonatedAmount()),BigDecimal::add);
			//calculates the sum of donations
			BigDecimal sumOfDonations = campaign.getDonors().stream().reduce(BigDecimal.ZERO, (sum, donation) -> sum.add(donation.getAmount()), BigDecimal::add);
			//percentage of progress
            int newProgress = sumOfDonations.divide(BigDecimal.valueOf(campaign.getRequiredAmount()), 2, RoundingMode.DOWN).multiply(BigDecimal.valueOf(100)).intValue();
            campaign.setMonetaryProgress(newProgress);	
            
		}
		return campaign;
	}
	public Campaign volunteerProgressUpdate(Campaign campaign) {
		if(campaign.getType().stream().anyMatch(type -> type.equals("VOLUNTEER"))) {
			int newParticipations = campaign.getVolunteers().size();
            int newProgress = (int) ((double) newParticipations / campaign.getRequiredVolunteers() * 100);
            campaign.setVolunteerProgress(newProgress);
		}
		return campaign;
	}
	
	
	
	
	
}
