package com.THIS.capstonehope.Repository;

import com.THIS.capstonehope.Models.VolunteerCampaign;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VolunteerCampaignRepository extends MongoRepository<VolunteerCampaign, String> {
 List<VolunteerCampaign> findByTitleContainingIgnoreCase(String title);
}
