import axios from "../axios";

const getMainDash = async () => {
    try {
      const response = await axios.get('/api/campaigns/mainDash', {
        headers: {
          'Content-Type': 'application/json',
          //  'Authorization': `${auth.accessToken}`, // Include the JWT token in the Authorization header
        },
        withCredentials: true, // Include cookies in the request
      });

      console.log(response.data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

const getSorted = async () => {
    try {
      const response = await axios.get('/api/campaigns/sort?asc=1', {
        headers: {
          'Content-Type': 'application/json',
          // 'Authorization': `Bearer ${auth.accessToken}`, // Include the JWT token in the Authorization header
        },
        withCredentials: true, // Include cookies in the request
      });

      console.log(response.data);
    } catch (error) {
      console.error('Error:', error);
    }
  };







 const CampaignService = {
    getMainDash,
    getSorted
}
export default CampaignService;