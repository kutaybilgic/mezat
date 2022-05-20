package com.group7.mezat.controllers;


import com.group7.mezat.documents.Role;
import com.group7.mezat.documents.User;
import com.group7.mezat.requests.LoginRequest;
import com.group7.mezat.requests.RegisterRequest;
import com.group7.mezat.responses.AuthResponse;
import com.group7.mezat.responses.UserResponse;
import com.group7.mezat.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.group7.mezat.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
//@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserMail(),
                loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        UserResponse user = userService.getOneUserByEmail(loginRequest.getUserMail());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Bearer " + jwtToken);
        authResponse.setUserId(user.getId());
        return authResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) throws Exception {
        AuthResponse authResponse = new AuthResponse();
        if(userService.getOneUserByEmail(registerRequest.getUserMail()) != null) {
            authResponse.setMessage("Email zaten kayıtlı.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST); // unutma
        }

        if (!(registerRequest.getPassword().equals(registerRequest.getPasswordAgain()))){
            authResponse.setMessage("Şifreler eşleşmiyor");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setSurname(registerRequest.getSurname());
        user.setUserMail(registerRequest.getUserMail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setAddress(registerRequest.getAddress());
        user.setPhoneNum(registerRequest.getPhoneNum());
        Role role = new Role();
        role.setName("ROLE_USER");
        user.getRoles().add(role);
        userService.saveUser(user);
        authResponse.setMessage("Başarıyla kayıt olundu");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
}
