package com.THIS.capstonehope.security.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

import com.THIS.capstonehope.security.models.ERole;
import com.THIS.capstonehope.security.models.Role;
import com.THIS.capstonehope.security.models.User;
import com.THIS.capstonehope.security.payload.request.LoginRequest;
import com.THIS.capstonehope.security.payload.request.SignupRequest;
import com.THIS.capstonehope.security.payload.response.MessageResponse;
import com.THIS.capstonehope.security.payload.response.UserInfoResponse;
import com.THIS.capstonehope.security.repository.RoleRepository;
import com.THIS.capstonehope.security.repository.UserRepository;
import com.THIS.capstonehope.security.security.jwt.JwtUtils;
import com.THIS.capstonehope.security.security.services.UserDetailsImpl;
import com.THIS.capstonehope.security.util.OtpUtil;
import com.THIS.capstonehope.service.EmailService;


//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;


  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  EmailService emailService;

  @Autowired
  JwtUtils jwtUtils;

 

  // @GetMapping("/signin")
  // public String showSignInForm(Model model) {
  //     return "login"; // This will return the signin.html Thymeleaf template
  // }
 

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
   
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    
                //Mail sender block added
    try {
      emailService.login(userDetails.getEmail(),userDetails.getUsername());
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new UserInfoResponse(userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            break;
          default:
            // If no valid role is provided, default to USER role
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    //mail sender block
    try {
      emailService.register(signUpRequest.getEmail(),signUpRequest.getUsername());
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}

// package com.THIS.capstonehope.security.controllers;

// import java.io.UnsupportedEncodingException;
// import java.time.LocalDateTime;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseCookie;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.THIS.capstonehope.security.models.ERole;
// import com.THIS.capstonehope.security.models.Role;
// import com.THIS.capstonehope.security.models.User;
// import com.THIS.capstonehope.security.payload.request.LoginRequest;
// import com.THIS.capstonehope.security.payload.request.SignupRequest;
// import com.THIS.capstonehope.security.payload.response.MessageResponse;
// import com.THIS.capstonehope.security.payload.response.UserInfoResponse;
// import com.THIS.capstonehope.security.repository.RoleRepository;
// import com.THIS.capstonehope.security.repository.UserRepository;
// import com.THIS.capstonehope.security.security.jwt.JwtUtils;
// import com.THIS.capstonehope.security.security.services.UserDetailsImpl;
// import com.THIS.capstonehope.service.EmailService;

// import jakarta.mail.MessagingException;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @Controller
// @RequestMapping("/api/auth")
// public class AuthController {
//     @Autowired
//     AuthenticationManager authenticationManager;

//     @Autowired
//     UserRepository userRepository;

//     @Autowired
//     RoleRepository roleRepository;

//     @Autowired
//     PasswordEncoder encoder;

//     @Autowired
//     JwtUtils jwtUtils;

//     @Autowired
//     EmailService emailService;

//     @PostMapping("/signin")
//     public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//         Authentication authentication = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//         ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

//         List<String> roles = userDetails.getAuthorities().stream()
//             .map(item -> item.getAuthority())
//             .collect(Collectors.toList());

//         // Send login email
//         try {
//             emailService.login(userDetails.getEmail(), userDetails.getUsername());
//         } catch (UnsupportedEncodingException | MessagingException e) {
//             e.printStackTrace();
//         }

//         return ResponseEntity.ok()
//             .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//             .body(new UserInfoResponse(userDetails.getId(),
//                                        userDetails.getUsername(),
//                                        userDetails.getEmail(),
//                                        roles));
//     }

//     @PostMapping("/signup")
//     public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
//         if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//             return ResponseEntity
//                 .badRequest()
//                 .body(new MessageResponse("Error: Username is already taken!"));
//         }

//         if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//             return ResponseEntity
//                 .badRequest()
//                 .body(new MessageResponse("Error: Email is already in use!"));
//         }

//         // Create new user's account
//         User user = new User(signUpRequest.getUsername(),
//                               signUpRequest.getEmail(),
//                               encoder.encode(signUpRequest.getPassword()));

//         Set<String> strRoles = signUpRequest.getRoles();
//         Set<Role> roles = new HashSet<>();

//         if (strRoles == null) {
//             Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//             roles.add(userRole);
//         } else {
//             strRoles.forEach(role -> {
//                 switch (role) {
//                     case "admin":
//                         Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                             .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                         roles.add(adminRole);
//                         break;
//                     default:
//                         // If no valid role is provided, default to USER role
//                         Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                             .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                         roles.add(userRole);
//                 }
//             });
//         }

//         user.setRoles(roles);
//         userRepository.save(user);

//         // Send registration email
//         try {
//             emailService.register(signUpRequest.getEmail(), signUpRequest.getUsername());
//         } catch (UnsupportedEncodingException | MessagingException e) {
//             e.printStackTrace();
//         }

//         return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//     }

//     @PostMapping("/donation")
//     public ResponseEntity<?> processDonation(@RequestParam("email") String email,
//                                               @RequestParam("userName") String userName,
//                                               @RequestParam("amount") String amount,
//                                               @RequestParam("hostedBy") String hostedBy,
//                                               @RequestParam("transactionId") String transactionId,
//                                               @RequestParam("donationDate") LocalDateTime donationDate) {
//         try {
//             emailService.donation(email, userName, amount, hostedBy, transactionId, donationDate);
//             return ResponseEntity.ok().body(new MessageResponse("Donation processed successfully!"));
//         } catch (UnsupportedEncodingException | MessagingException e) {
//             e.printStackTrace();
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .body(new MessageResponse("Failed to process donation. Please try again later."));
//         }
//     }
// }
