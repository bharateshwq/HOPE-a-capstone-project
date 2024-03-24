import React, { useState } from 'react';
import CampaignService from '../api/services/CampaignService.js' // Import the function for filtering campaigns

const FilterCampaign = () => {
  const [selectedType, setSelectedType] = useState(''); // State to store the selected type

  const handleTypeChange = async (e) => {
    const selectedValue = e.target.value; // Get the selected value from the dropdown
    setSelectedType(selectedValue); // Update the selected type state
    if (selectedValue) {
      try {
        // Call the filterCampaigns function with the selected type
        const filteredCampaigns = await CampaignService.filterCampaigns(selectedValue);
        console.log('Filtered campaigns:', filteredCampaigns);
      } catch (error) {
        console.error('Error filtering campaigns:', error);
      }
    }
  };

  return (
    <div>
      <label htmlFor="campaignType">Select Campaign Type:</label>
      <select id="campaignType" value={selectedType} onChange={handleTypeChange}>
        <option value="">-- Select Type --</option>
        <option value="DONATION">Donation</option>
        <option value="VOLUNTEERING">Volunteering</option>
      </select>
    </div>
  );
};

export default FilterCampaign;