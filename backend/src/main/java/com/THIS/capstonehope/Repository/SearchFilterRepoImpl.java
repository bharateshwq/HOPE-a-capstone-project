package com.THIS.capstonehope.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.THIS.capstonehope.Models.Campaign;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
@Service
public class SearchFilterRepoImpl implements SearchFilteringRepository{

		@Autowired
		private MongoClient client;
		
		
		@Autowired
	private  CampaignRepository campaignRepo;
		@Autowired
		MongoConverter converter;
		
		 public  List<Campaign> filterLegitimateCampaigns(List<Campaign> campaigns) {
		        return campaigns.stream()
		                .filter(campaign -> campaign.getLegitimacy())
		                .collect(Collectors.toList());
		    }
		
		
	@Override
	public List<Campaign> findByText(String query) {
		final List<Campaign> campaigns = new ArrayList<>();
			MongoDatabase database = client.getDatabase("behumantest");
			MongoCollection<Document> collection = database.getCollection("campaigns");
			AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
			    new Document("index", "default")
			            .append("text", 
			    new Document("query", query)
			                .append("path", Arrays.asList("title", "description")))), 
			    new Document("$sort", 
			    new Document("endDate", 1L))));
			result.forEach(doc -> campaigns.add(converter.read(Campaign.class, doc)));
		return filterLegitimateCampaigns(campaigns);
	}
	@Override
	public List<Campaign> filterCampaign(String type) {
		// TODO Auto-generated method stub

		final List<Campaign> campaigns = new ArrayList<>();
		MongoDatabase database = client.getDatabase("behumantest");
		MongoCollection<Document> collection = database.getCollection("campaigns");
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("index", "default")
		            .append("text", 
		    new Document("query", type)
		                .append("path", "type")))));	
		
		
		
			result.forEach(doc -> campaigns.add(converter.read(Campaign.class, doc)));
		return filterLegitimateCampaigns(campaigns);
	}
	@Override
	public List<Campaign> sortCampaign(int asc) {
		// TODO Auto-generated method stub
		final List<Campaign> campaigns = new ArrayList<>();
		MongoDatabase database = client.getDatabase("behumantest");
		MongoCollection<Document> collection = database.getCollection("campaigns");
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$sort", 
		    new Document("endDate", asc))));	
			result.forEach(doc -> campaigns.add(converter.read(Campaign.class, doc)));
			
		return filterLegitimateCampaigns(campaigns);
	}
	@Override
	public List<Campaign> VerifiedCampaign() {
		// TODO Auto-generated method stub

		List<Campaign> campaigns = new ArrayList<>();
		Sort sort = Sort.by(Sort.Direction.ASC, "endDate");
for(Campaign c : campaignRepo.findAll(sort)) {
	if(c.getLegitimacy().booleanValue()) {
		campaigns.add(c);
	}
}
	return campaigns;
	}
	@Override
	public List<Campaign> SortedVerifiedandUnverified() {
		// TODO Auto-generated method stub
		List<Campaign> campaigns = new ArrayList<>();

		MongoDatabase database = client.getDatabase("behumantest");
		MongoCollection<Document> collection = database.getCollection("campaigns");
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$sort", 
		    new Document("legitimacy", 1L))));
			result.forEach(doc -> campaigns.add(converter.read(Campaign.class, doc)));
		return campaigns;
	}



}
