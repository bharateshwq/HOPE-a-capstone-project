package com.THIS.capstonehope.Repository;

import com.THIS.capstonehope.Models.Donation;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonationRepository extends MongoRepository<Donation, String> {
  List<Donation> findByTitleContainingIgnoreCase(String title);
}
