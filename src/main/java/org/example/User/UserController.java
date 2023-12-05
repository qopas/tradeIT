package org.example.User;

import org.example.Barter.BarterDTO;
import org.example.Barter.BarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BarterService barterService;
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(
            @PathVariable Integer user_id
    ){
        return new ResponseEntity<>(userRepository.findById(user_id).orElse(null), HttpStatus.OK);
    }
    @GetMapping("/{user_id}/barters")
    public ResponseEntity<List<BarterDTO>> getBartersByUser(@PathVariable("user_id") Integer userId) {
        List<BarterDTO> userBarters = barterService.getBartersByUser(userId);

        if (userBarters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userBarters, HttpStatus.OK);
        }
    }
}
