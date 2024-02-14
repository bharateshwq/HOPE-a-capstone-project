package com.THIS.capstonehope.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.THIS.capstonehope.security.models.User;



@Document(collection = "campaigns")
public class Campaign {
	public String getRequiredVolunteers() {
		return requiredVolunteers;
	}

	public void setRequiredVolunteers(String requiredVolunteers) {
		this.requiredVolunteers = requiredVolunteers;
	}

	public String getDonatedAmount() {
		return donatedAmount;
	}

	public void setDonatedAmount(String donatedAmount) {
		this.donatedAmount = donatedAmount;
	}

	public String getEnrolledVolunteers() {
		return enrolledVolunteers;
	}

	public void setEnrolledVolunteers(String enrolledVolunteers) {
		this.enrolledVolunteers = enrolledVolunteers;
	}

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
	private Donation donors;
	private Volunteer participants;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRequiredAmount() {
		return requiredAmount;
	}

	public void setRequiredAmount(Double requiredAmount) {
		this.requiredAmount = requiredAmount;
	}

	public List<String> getImageLink() {
		return imageLink;
	}

	public void setImageLink(List<String> imageLink) {
		this.imageLink = imageLink;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CampaignType getType() {
		return type;
	}

	public void setType(CampaignType type) {
		this.type = type;
	}



	public User getHostedBy() {
		return hostedBy;
	}

	public void setHostedBy(User hostedBy) {
		this.hostedBy = hostedBy;
	}



	

	public Boolean getLegitimacy() {
		return legitimacy;
	}

	public void setLegitimacy(Boolean legitimacy) {
		this.legitimacy = legitimacy;
	}

	public Volunteer getParticipants() {
		return participants;
	}

	public void setParticipants(Volunteer participants) {
		this.participants = participants;
	}

	public Donation getDonors() {
		return donors;
	}

	public void setDonors(Donation donors) {
		this.donors = donors;
	}
	

}
