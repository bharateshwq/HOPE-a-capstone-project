package com.THIS.capstonehope.Models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.THIS.capstonehope.security.models.User;

import lombok.Data;

@Data
@Document(collection="volunteer")
public class Volunteer {
	@Id
	String id;
	private User volunteer;
	private Campaign campaign;
	private LocalDateTime enrolledOn;
}
