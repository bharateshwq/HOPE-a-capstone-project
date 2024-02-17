package com.THIS.capstonehope.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.THIS.capstonehope.security.models.User;

import lombok.Data;


@Data
@Document(collection = "campaigns")
public class Campaign {

	@Id
	private String id;
	private String title;
	private String description;
	private Double requiredAmount;
	private String requiredVolunteers;
	private List<String> imageLink;
	private String phoneNumber;
	private String email;
	private String donatedAmount;
	private String enrolledVolunteers;
	private CampaignType type;
	private Boolean legitimacy;
	private User hostedBy;
	private List<Donation> donors;
	private List<Volunteer> volunteers;
	
	

}
