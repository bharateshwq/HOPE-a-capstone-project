package com.THIS.capstonehope.security.security.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.THIS.capstonehope.security.models.ERole;
import com.THIS.capstonehope.security.models.Role;
import com.THIS.capstonehope.security.models.User;
import com.THIS.capstonehope.security.payload.request.SignupRequest;
import com.THIS.capstonehope.security.payload.response.MessageResponse;
import com.THIS.capstonehope.security.repository.RoleRepository;
import com.THIS.capstonehope.security.repository.UserRepository;
import com.THIS.capstonehope.security.security.jwt.JwtUtils;
import com.THIS.capstonehope.service.IdService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	    private final IdService idService;
	    @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder encoder;

	    @Autowired
	    JwtUtils jwtUtils;
    public User updateUser(User mongoUserWithoutPassword) {
        User mongoUser = userRepository.findById(mongoUserWithoutPassword.getId()).orElseThrow(() -> new UsernameNotFoundException("Username " + mongoUserWithoutPassword.getUsername() + " not found"));
         mongoUserWithoutPassword.setPassword(mongoUser.getPassword());
        User returnUser = userRepository.save(mongoUserWithoutPassword);
        return returnUser;
    }
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

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
      }
    public User findByUsername(String username) {
        if (userRepository.findByUsername(username).isEmpty()) {
            return new User("unknown", "anonymousUser", new ArrayList<>(), new ArrayList<>());
        }
        User mongoUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        return new User(mongoUser.getId(), mongoUser.getUsername(), mongoUser.getDonations(), mongoUser.getVolunteerings());
    }

}
