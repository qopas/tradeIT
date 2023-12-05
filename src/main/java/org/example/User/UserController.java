package org.example.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(
            @PathVariable Integer user_id
    ){
        return new ResponseEntity<>(userRepository.findById(user_id).orElse(null), HttpStatus.OK);
    }
}
