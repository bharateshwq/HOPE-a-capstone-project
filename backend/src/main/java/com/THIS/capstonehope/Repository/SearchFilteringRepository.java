package com.THIS.capstonehope.Repository;

import java.util.List;

import com.THIS.capstonehope.Models.Campaign;

public interface SearchFilteringRepository {
	
	List<Campaign> findByText(String query);
	List<Campaign> filterCampaign(String type);
	List<Campaign> sortCampaign(Boolean asc);
	List<Campaign> VerifiedCampaign();
	List<Campaign> SortedVerifiedandUnverified();
	

}
