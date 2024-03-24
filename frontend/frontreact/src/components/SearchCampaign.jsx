import React, { useState } from 'react';
import CampaignService from '../api/services/CampaignService' // Import the search function

const SearchCampaigns = () => {
  const [query, setQuery] = useState(''); // State to hold the search query
  const [searchResults, setSearchResults] = useState([]); // State to hold the search results

  const handleSearch = async () => {
    try {
      const results = await CampaignService.searchCampaigns(query); // Call the search function with the query
      setSearchResults(results); // Update the search results state
      console.log(results);
      setQuery('')
    } catch (error) {
      console.error('Error searching campaigns:', error);
    }
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Search campaigns..."
        value={query}
        onChange={(e) => setQuery(e.target.value)} // Update the query state as the user types
      />
      <button onClick={handleSearch}>Search</button>
      <ul>
        {searchResults.map((campaign) => (
          <li key={campaign.id}>{campaign.title}</li> // Display search results as list items
        ))}
      </ul>
    </div>
  );
};

export default SearchCampaigns;
