import React, { useState } from 'react';
import CampaignService from '../api/services/CampaignService.js'
import { format } from 'date-fns';


const AddCampaignForm = () => {
  const [formData, setFormData] = useState({
    "id": null,
    "title": "",
    "description": "",
    "requiredAmount": 0,
    "requiredVolunteers":0,
    "startDate":"",
    "endDate":"",
    "imageLink": [],
    "phoneNumber": "",
    "email":"",
    "monetaryProgress":0,
    "volunteerProgress":0,
    "type":[],
    "legitimacy":"false",
    "hostedBy":"me",
    "donors":[],
    "volunteers":[]
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    console.log(name,value)
    if (['startDate', 'endDate'].includes(name)) {
        const formattedDate = format(new Date(value), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        setFormData({ ...formData, [name]: formattedDate });
      }
      if (['imageLink', 'type'].includes(name)) {
        setFormData({ ...formData, [name]: value.split(',') });
      } 
      else {
        // For other inputs, update the form data as usual
        setFormData({ ...formData, [name]: value });
      }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await CampaignService.addCampaign(formData);
      console.log('Campaign added successfully');
      // Reset form fields after successful submission
      setFormData({
        title: '',
        description: '',
        requiredAmount: 0,
        requiredVolunteers: 0,
        startDate: '',
        endDate: '',
        imageLink: '',
        phoneNumber: '',
        email: '',
        monetaryProgress: 0,
        volunteerProgress: 0,
        type: '',
        legitimacy: '',
        hostedBy: '',
      });
    } catch (error) {
      console.error('Error adding campaign:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
    <label>
      Title:
      <input
        type="text"
        name="title"
        value={formData.title}
        onChange={handleChange}
      />
    </label><br />
  
    <label>
      Description:
      <textarea
        name="description"
        value={formData.description}
        onChange={handleChange}
      ></textarea>
    </label><br />
  
    <label>
      Required Amount:
      <input
        type="number"
        name="requiredAmount"
        value={formData.requiredAmount}
        onChange={handleChange}
      />
    </label><br />
  
    <label>
      Required Volunteers:
      <input
        type="number"
        name="requiredVolunteers"
        value={formData.requiredVolunteers}
        onChange={handleChange}
      />
    </label><br />
  
    <label>
  Start Date:
  <input
    type="datetime-local"
    name="startDate"
    value={formData.startDate}
    onChange={handleChange}
  />
</label><br />

<label>
  End Date:
  <input
    type="datetime-local"
    name="endDate"
    value={formData.endDate}
    onChange={handleChange}
  />
</label><br />
  
    <label>
      Image Link:
      <input
        type="text"
        name="imageLink"
        value={formData.imageLink}
        onChange={handleChange}
      />
    </label><br />
  
    <label>
      Phone Number:
      <input
        type="text"
        name="phoneNumber"
        value={formData.phoneNumber}
        onChange={handleChange}
      />
    </label><br />
  
    <label>
      Email:
      <input
        type="email"
        name="email"
        value={formData.email}
        onChange={handleChange}
      />
    </label><br />
  
 
  
 
  
    <label>
      Type:
      <input
        type="text"
        name="type"
        value={formData.type}
        onChange={handleChange}
      />
    </label><br />
  
   
  
   
  
    <button type="submit">Add Campaign</button>
  </form>
  
  );
};

export default AddCampaignForm;
