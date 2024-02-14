package com.THIS.capstonehope.Controller;

import com.THIS.capstonehope.Models.Campaign;

import com.THIS.capstonehope.Repository.CampaignRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import java.util.Optional;

// import java.util.List;
// import java.util.stream.Collectors;

// @RestController
// public class DashboardController {

//     @Autowired
//     private DonationRepository donationRepository;

//     @Autowired
//     private VolunteerCampaignRepository volunteerCampaignRepository;

//     @GetMapping("/dashboard")
//     public DashboardData getDashboardData() {
//         List<Donation> donations = donationRepository.findAll();
//         List<VolunteerCampaign> volunteerCampaigns = volunteerCampaignRepository.findAll();
//         return new DashboardData(donations, volunteerCampaigns);
//     }

//     @GetMapping("/donations/{id}")
//     public ResponseEntity<?> getDonationById(@PathVariable String id) {
//         Optional<Donation> donationOptional = donationRepository.findById(id);
//         return findById(id, donationOptional, "Donation");
//     }
    
//     @GetMapping("/volunteer-campaigns/{id}")
//     public ResponseEntity<?> getVolunteerCampaignById(@PathVariable String id) {
//         Optional<VolunteerCampaign> volunteerCampaignOptional = volunteerCampaignRepository.findById(id);
//         return findById(id, volunteerCampaignOptional, "Volunteer campaign");
//     }
    
//     // The findById method here
//     private ResponseEntity<?> findById(String id, Optional<?> optional, String objectType) {
//         if (optional.isPresent()) {
//             return new ResponseEntity<>(optional.get(), HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(objectType + " not found", HttpStatus.NOT_FOUND);
//         }
//     }
    
    

//     @GetMapping("/search")
//     public ResponseEntity<?> searchProjects(@RequestParam String query) {
//         List<Donation> donations = donationRepository.findAll().stream()
//                 .filter(d -> d.getTitle().toLowerCase().contains(query.toLowerCase()))
//                 .collect(Collectors.toList());
//         List<VolunteerCampaign> volunteerCampaigns = volunteerCampaignRepository.findAll().stream()
//                 .filter(v -> v.getTitle().toLowerCase().contains(query.toLowerCase()))
//                 .collect(Collectors.toList());

//         SearchResults searchResults = new SearchResults(donations, volunteerCampaigns);
//         return new ResponseEntity<>(searchResults, HttpStatus.OK);
//     }

//     static class DashboardData {
//         private List<Donation> donations;
//         private List<VolunteerCampaign> volunteerCampaigns;

//         public DashboardData(List<Donation> donations, List<VolunteerCampaign> volunteerCampaigns) {
//             this.donations = donations;
//             this.volunteerCampaigns = volunteerCampaigns;
//         }

//         // Getters for donations and volunteerCampaigns
//         public List<Donation> getDonations() {
//             return donations;
//         }

//         public List<VolunteerCampaign> getVolunteerCampaigns() {
//             return volunteerCampaigns;
//         }
//     }

//     static class SearchResults {
//         private List<Donation> donations;
//         private List<VolunteerCampaign> volunteerCampaigns;

//         public SearchResults(List<Donation> donations, List<VolunteerCampaign> volunteerCampaigns) {
//             this.donations = donations;
//             this.volunteerCampaigns = volunteerCampaigns;
//         }

//         // Getters for donations and volunteerCampaigns
//         public List<Donation> getDonations() {
//             return donations;
//         }

//         public List<VolunteerCampaign> getVolunteerCampaigns() {
//             return volunteerCampaigns;
//         }
//     }
// }





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private CampaignRepository campaignRepo;
    
    
    @GetMapping("/all")
    public String allAccess() {
      return "Public Content.";
    }
    
    
    @GetMapping("/view")
    public ResponseEntity<?> getDashboardData() {
        List<Campaign> campaigns = campaignRepo.findAll();
      
        return new ResponseEntity<>(new DashboardData(campaigns), HttpStatus.OK);
    }
    
    

    @GetMapping("/search")
    public ResponseEntity<?> searchProjects(@RequestParam String query) {
        if(StringUtils.hasText(query)) {
            
            List<Campaign> campaigns = campaignRepo.findByTitleContainingIgnoreCase(query);
            return new ResponseEntity<>(new SearchResults(campaigns), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Query parameter is required for search", HttpStatus.BAD_REQUEST);
        }
    }
    
   //encapsulates dashboard funcs
    static class DashboardData {
        private List<Campaign> campaigns;
        public DashboardData(List<Campaign> campaigns) {
			// TODO Auto-generated constructor stub
        	 this.campaigns = campaigns;
		}
     // Getters for donations and volunteerCampaigns
        public List<Campaign> getCampaigns() {
            return campaigns;
        }
    }
//encapsulates search
    static class SearchResults {
        private List<Campaign> campaigns;
        public SearchResults(List<Campaign> campaigns) {
            this.campaigns = campaigns;
        
        }
        // Getters for donations and volunteerCampaigns
        public List<Campaign> getCampaigns() {
            return campaigns;
        } 
    }
}
