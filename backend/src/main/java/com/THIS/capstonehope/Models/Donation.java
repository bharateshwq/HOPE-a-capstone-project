package com.THIS.capstonehope.Models;

import java.time.LocalDateTime;
import java.util.List;

import com.THIS.capstonehope.security.models.User;

import lombok.Data;

@Data
public class Donation {
	private String id;
	private List<Payment> payment;
	private User donor;
	private LocalDateTime donatedOn;
	private Campaign campaign;
	

}
