package com.THIS.capstonehope.Models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="payment")
public class Payment {
	@Id
	private String id;
	private String transactionId;
	private double amount;
	private Boolean status;
	private LocalDateTime paymentProcessedOn;
	private Donation donor;
}
