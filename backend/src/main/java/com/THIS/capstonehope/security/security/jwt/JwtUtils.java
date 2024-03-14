package  com.THIS.capstonehope.security.security.jwt;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.THIS.capstonehope.security.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${bezkoder.app.jwtSecret}")
  private String jwtSecret;

  @Value("${bezkoder.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  @Value("${bezkoder.app.jwtCookieName}")
  private String jwtCookie;

  public String getJwtFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }

  public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
    String jwt = generateTokenFromUsername(userPrincipal.getUsername(),userPrincipal.getEmail(),
    userPrincipal.getAuthorities().stream()
    .map(item -> item.getAuthority())
    .collect(Collectors.toList())
    
    );
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
    return cookie;
  }

  public ResponseCookie getCleanJwtCookie() {
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
    return cookie;
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
        .parseClaimsJws(token).getBody().getSubject();
  }
  
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
  
  public String generateTokenFromUsername(String username,String email,List<String> roles) {   
    return Jwts.builder()
              .setSubject("userDeets")
              .claim("username",username)
              .claim("email", email)
              .claim("roles", roles)
              .setIssuedAt(new Date())
              .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
              .signWith(key(), SignatureAlgorithm.HS256)
              .compact();
  }

//////////////////////////////my funcs
// public UserDetailsImpl getUserDetailsFromCookie(ResponseCookie cookie) {
//   String jwtToken = cookie.getValue();
//   if (jwtToken != null && validateJwtToken(jwtToken)) {
//     String username = getUserNameFromJwtToken(jwtToken);
//     // Additional logic to fetch user details from your data source
//     // For now, returning a UserDetailsImpl object with only the username set
//     return new UserDetailsImpl(username);
//   }
//   return null;
// }

// Method to get username from ResponseCookie
public String getUsernameFromCookie(ResponseCookie cookie) {
  String jwtToken = cookie.getValue();
  if (jwtToken != null && validateJwtToken(jwtToken)) {
    return getUserNameFromJwtToken(jwtToken);
  }
  return null;
}

// Method to get email from ResponseCookie
public String getEmailFromCookie(ResponseCookie cookie) {
  String jwtToken = cookie.getValue();
  if (jwtToken != null && validateJwtToken(jwtToken)) {
    // Extracting email from claims, adjust as per your JWT structure
    Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwtToken).getBody();
    return claims.get("email", String.class); // Assuming email is stored as a claim named "email"
  }
  return null;
}

// Method to get role from ResponseCookie
public List<String> getRolesFromCookie(ResponseCookie cookie) {
  String jwtToken = cookie.getValue();
  if (jwtToken != null && validateJwtToken(jwtToken)) {
    // Extracting roles from claims, adjust as per your JWT structure
    Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwtToken).getBody();
    // Assuming roles are stored as a list of strings under a claim named "roles"
    List<String> roles = claims.get("roles", List.class); 
    return roles != null ? roles : Collections.emptyList();
  }
  return Collections.emptyList();
}



}
