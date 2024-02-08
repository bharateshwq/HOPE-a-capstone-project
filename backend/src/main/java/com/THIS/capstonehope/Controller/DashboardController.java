package com.THIS.capstonehope.Controller;

import com.THIS.capstonehope.Models.Donation;
import com.THIS.capstonehope.Models.VolunteerCampaign;
import com.THIS.capstonehope.Repository.DonationRepository;
import com.THIS.capstonehope.Repository.VolunteerCampaignRepository;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private VolunteerCampaignRepository volunteerCampaignRepository;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardData() {
        List<Donation> donations = donationRepository.findAll();
        List<VolunteerCampaign> volunteerCampaigns = volunteerCampaignRepository.findAll();
        return new ResponseEntity<>(new DashboardData(donations, volunteerCampaigns), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProjects(@RequestParam String query) {
        if(StringUtils.hasText(query)) {
            List<Donation> donations = donationRepository.findByTitleContainingIgnoreCase(query);
            List<VolunteerCampaign> volunteerCampaigns = volunteerCampaignRepository.findByTitleContainingIgnoreCase(query);
            return new ResponseEntity<>(new SearchResults(donations, volunteerCampaigns), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Query parameter is required for search", HttpStatus.BAD_REQUEST);
        }
    }

    static class DashboardData {
        private List<Donation> donations;
        private List<VolunteerCampaign> volunteerCampaigns;

        public DashboardData(List<Donation> donations, List<VolunteerCampaign> volunteerCampaigns) {
            this.donations = donations;
            this.volunteerCampaigns = volunteerCampaigns;
        }

        // Getters for donations and volunteerCampaigns
        public List<Donation> getDonations() {
            return donations;
        }

        public List<VolunteerCampaign> getVolunteerCampaigns() {
            return volunteerCampaigns;
        }
    }

    static class SearchResults {
        private List<Donation> donations;
        private List<VolunteerCampaign> volunteerCampaigns;

        public SearchResults(List<Donation> donations, List<VolunteerCampaign> volunteerCampaigns) {
            this.donations = donations;
            this.volunteerCampaigns = volunteerCampaigns;
        }

        // Getters for donations and volunteerCampaigns
        public List<Donation> getDonations() {
            return donations;
        }

        public List<VolunteerCampaign> getVolunteerCampaigns() {
            return volunteerCampaigns;
        }
    }
}
