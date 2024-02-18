package com.THIS.capstonehope.payload;
import java.util.List;

import com.THIS.capstonehope.Models.CampaignType;


public record CampaignCreation(
	String 	title,
	String description,
	Double requiredAmount,
	Double requiredVolunteers,
	List<String> imageLink,
	String phoneNumber,
	String email,
	List<CampaignType> type,
	String location
	
		
		
		
		) {

}
