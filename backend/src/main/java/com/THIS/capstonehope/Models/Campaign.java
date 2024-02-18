package com.THIS.capstonehope.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.THIS.capstonehope.security.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@Document(collection = "campaigns")
public class Campaign {
	@Id
	private String id;
	private String title;
	private String description;
	private int requiredAmount;
	private int requiredVolunteers;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private List<String> imageLink;
	private String phoneNumber;
	private String email;
	private int monetaryProgress;
	private int volunteerProgress;
	private List<CampaignType> type;
	private Boolean legitimacy;
	private String hostedBy;
	private List<Donation> donors;
	private List<Volunteer> volunteers;
	public void appendDonation(Donation newDonation) {
        if (this.donors == null) {
            this.donors = new ArrayList<>();
        }
        this.donors.add(newDonation);
    }	
	public void appendVolunteers(Volunteer newVolunteer) {
        if (this.volunteers == null) {
            this.volunteers = new ArrayList<>();
        }
        this.volunteers.add(newVolunteer);
    }	
	

}
