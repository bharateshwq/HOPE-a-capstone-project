package  com.THIS.capstonehope.security.payload.response;

import java.util.List;

public class UserInfoResponse {
  private String id;
  private String username;
  private String email;
  private List<String> roles;
  private String jwttoken;

  public UserInfoResponse(String id, String username, String email, List<String> roles ,String token) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.jwttoken = token;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getjwttoken() {
    return jwttoken;
  }

  public void setjwttoken(String jwttoken) {
    this.jwttoken = jwttoken;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}
