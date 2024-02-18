package com.THIS.capstonehope.Models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.THIS.capstonehope.security.models.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Volunteer {
	@Id
	String id;
	private String volunteerid;
	private String volunteerName;
	private LocalDateTime enrolledOn;
	private String campaignId;
	private String campaignName;
}
