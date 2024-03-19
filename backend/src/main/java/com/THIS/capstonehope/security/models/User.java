package  com.THIS.capstonehope.security.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.THIS.capstonehope.Models.Donation;
import com.THIS.capstonehope.Models.Volunteer;

@Document(collection = "users")
@AllArgsConstructor
@Data
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  // for otp generation and MFA
  // private boolean mfaEnabled;

  // private String secret;


//------------------
  @DBRef
  private Set<Role> roles = new HashSet<>();
  
private List<Donation> donations;

private List<Volunteer> volunteerings;




  public User() {
  }
//creation +email
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
  
  //mongo without pass
  public User(String Id,String username,List<Donation> donations,List<Volunteer>volunteerings) {
  this.id=Id;
  this.username=username;
  this.donations=donations;
  this.volunteerings=volunteerings;
  }

	public void appendDonation(Donation newDonation) {
        if (this.donations == null) {
            this.donations = new ArrayList<>();
        }
        this.donations.add(newDonation);
    }	
	public void appendVolunteers(Volunteer newVolunteer) {
        if (this.volunteerings == null) {
            this.volunteerings = new ArrayList<>();
        }
        this.volunteerings.add(newVolunteer);
    }	
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
