import React from 'react'

import AdminDashList from '../components/AdminDashList';
import { useEffect } from 'react';
import { useState } from 'react';
import axios from '../api/axios';






const AdminDash = () => {
  const [campaigns, setCampaigns] = useState([]);
  let x=[1,2,3];
  const getCampaigns = async () => {
    try {
     
      const response = await axios.get('/api/campaigns/mainDash'
    
      );
      console.log("getting campaigns")
      setCampaigns(response.data);
      console.log(response.data);
      console.log(campaigns);
    } catch (error) {
      console.log("failed")
      console.error('Error fetching campaigns:', error);
    }
  };
  useEffect(() => {
    getCampaigns();
  }, []); // Empty dependency array to run effect only once



  return (

    // <div>
    //   <h1>Main Admin Dashboard</h1>
    //   <ul>
    //     {x.map(campaign => (
    //       <li key={campaign}>{campaign}</li>

    //       // Adjust the mapping according to your campaign object structure
    //     ))}
    //   </ul>
    // </div>
    <AdminDashList />

  )
}

export default AdminDash