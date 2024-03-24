import React, { useState } from 'react';
import {
  Card,
  Input,
  Checkbox,
  Button,
  Typography,
} from "@material-tailwind/react";
import AddCampaignForm from '../components/AddCampaign';




const CampaignForm = () => {
  return(
    <AddCampaignForm/>
  );
}
// const CampaignForm = () => {
//   const [formData, setFormData] = useState({
//     title: '',
//     description: '',
//     phoneNumber: '',
//     email: '',
//     requiredAmount: '',
//     requiredVolunteers: '',
//     imageLinks: '',
//     type: 'DONATION',
//   });

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setFormData({
//       ...formData,
//       [name]: value,
//     });
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     console.log(formData);
//     // send data to server
//   };

//   return (
//     <div className="min-h-screen bg-green-100 flex items-center justify-center">
//       <form id="campaign-form" className="bg-white p-6 rounded-md shadow-md w-full max-w-md" onSubmit={handleSubmit}>
//         <Card color="transparent" shadow={false}>
//           <Typography variant="h4" color="blue-gray">
//             Create a New Campaign
//           </Typography>
//           <div className="grid grid-cols-1 gap-y-6 sm:grid-cols-2">
//             <div className="mb-4">
//               <label htmlFor="title" className="block text-sm font-medium text-gray-700">Title:</label>
//               <input type="text" id="title" name="title" value={formData.title} onChange={handleChange} required className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
//             </div>
//             <div className="mb-4">
//               <label htmlFor="description" className="block text-sm font-medium text-gray-700">Description:</label>
//               <textarea id="description" name="description" value={formData.description} onChange={handleChange} required className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"></textarea>
//             </div>
//             <div className="mb-4">
//               <label htmlFor="phoneNumber" className="block text-sm font-medium text-gray-700">Phone Number:</label>
//               <input type="tel" id="phoneNumber" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} required className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
//             </div>
//             <div className="mb-4">
//               <label htmlFor="email" className="block text-sm font-medium text-gray-700">Email:</label>
//               <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} required className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
//             </div>
//             <div className="mb-4">
//               <label htmlFor="requiredAmount" className="block text-sm font-medium text-gray-700">Required Amount:</label>
//               <input type="number" id="requiredAmount" name="requiredAmount" value={formData.requiredAmount} onChange={handleChange} step="0.01" required className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
//             </div>
//             <div className="mb-4">
//               <label htmlFor="requiredVolunteers" className="block text-sm font-medium text-gray-700">Required Volunteers:</label>
//               <input type="number" id="requiredVolunteers" name="requiredVolunteers" value={formData.requiredVolunteers} onChange={handleChange} required className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
//             </div>
//             <div className="mb-4">
//     <label htmlFor="date" className="block text-sm font-medium text-gray-700">Date:</label>
//     <input 
//       type="date" 
//       id="date" 
//       name="date" 
//       value={formData.date} 
//       onChange={handleChange} 
//       required 
//       className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" 
//     />
//   </div>

//             <div className="mb-4">
//               <label htmlFor="imageInput" className="block text-sm font-medium text-gray-700">Image Links:</label>
//               <input type="file" accept="image/*" id="imageInput" name="imageLinks" value={formData.imageLinks} onChange={handleChange} className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
//             </div>
//             <div className="mb-4">
//               <label htmlFor="type" className="block text-sm font-medium text-gray-700">Type:</label>
//               <select id="type" name="type" value={formData.type} onChange={handleChange} required className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
//                 <option value="DONATION">Donation</option>
//                 <option value="VOLUNTEERING">Volunteering</option>
//                 <option value="BOTH">Both</option>
//               </select>
//             </div>
//           </div>
//           {/* <Checkbox
//             label={
//               <Typography variant="body" color="gray">
//                 I agree to the <a href="#">Terms and Conditions</a>
//               </Typography>
//             }
//             color="blue"
//             containerProps={{ className: "-ml-2.5 mt-4" }}
//           /> */}
//           <Button
//             type="submit"
//             color="lightBlue"
//             className="mt-6 w-full"
//           >
//             Create Campaign
//           </Button>
//         </Card>
//       </form>
//     </div>
//   );
// };

export default CampaignForm;
