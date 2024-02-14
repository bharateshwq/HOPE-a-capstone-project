package com.THIS.capstonehope.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.THIS.capstonehope.Models.Campaign;


public interface CampaignRepository extends MongoRepository<Campaign, String> {
	  List<Campaign> findByTitleContainingIgnoreCase(String title);
	}
