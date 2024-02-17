package  com.THIS.capstonehope.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.THIS.capstonehope.Models.Campaign;
import com.THIS.capstonehope.Repository.CampaignRepository;

//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class CampControl {

    @Autowired
    private CampaignRepository campaignRepo;

    @GetMapping("/view")
    public ResponseEntity<?> getDashboardData() {
        List<Campaign> campaigns = campaignRepo.findAll();
      
        return new ResponseEntity<>(new DashboardData(campaigns), HttpStatus.OK);
    }
  @GetMapping("/testAll")
  public String testAll() {
    return "Public control.";
  }

  @GetMapping("/testUser")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public String user() {
    return "User control.";
  }

  @GetMapping("/testAdmin")
  @PreAuthorize("hasRole('ADMIN')")
  public String admin() {
    return "Admin Board.";
  }
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
