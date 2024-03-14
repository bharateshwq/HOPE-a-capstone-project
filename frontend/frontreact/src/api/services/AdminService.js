import axios from '../axios'

const getCampaigns = async() => {
    try{
        const response = await axios.get('/api/campaigns/mainAdminDash',

        )
    }
    catch(error){
        console.log(error)
    }
}

const AdminService ={


}
export default AdminService