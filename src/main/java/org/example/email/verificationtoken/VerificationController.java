package org.example.email.verificationtoken;

import lombok.RequiredArgsConstructor;
import org.example.User.User;
import org.example.User.UserRepository;
import org.example.auth.AuthenticationResponse;
import org.example.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;


@RestController
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @GetMapping("/api/confirm")
    public ResponseEntity<?> verifyAccount(@RequestParam("token") String token){
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElse(null);
        if(verificationToken == null){
            return ResponseEntity.badRequest().body("Invalid token");
        }
        if(verificationToken.getExpiryDate().before(Calendar.getInstance().getTime())){
            return ResponseEntity.badRequest().body("Token expired");
        }

        User user = verificationToken.getUser();
        if(user==null){
            return ResponseEntity.badRequest().body("User not found");
        }

        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(verificationToken.calculateExpiryDate(VerificationToken.EXPIRATION));
        verificationTokenRepository.save(verificationToken);

        user.setEnabled(true);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

}
