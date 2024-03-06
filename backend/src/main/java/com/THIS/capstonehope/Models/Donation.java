package com.THIS.capstonehope.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.THIS.capstonehope.security.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Donation {
	@Id
	private String id;
	private String donorId;
	private String donorName;
	private String transactionId;
	private Integer amount;
	private LocalDateTime donatedOn;
	private String campaignId;
	private String campaignTitle;
	//doantion creation

public Donation(int amount ) {
	this.amount=amount;
	
}

}
