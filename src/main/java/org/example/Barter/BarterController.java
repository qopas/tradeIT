package org.example.Barter;

import org.checkerframework.checker.units.qual.A;
import org.example.Notifications.Notification;
import org.example.Notifications.NotificationDTO;
import org.example.Notifications.NotificationRepository;
import org.example.Notifications.NotificationService;
import org.example.Product.Product;
import org.example.Product.ProductRepository;
import org.example.User.User;
import org.example.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/barters")
public class BarterController {
    @Autowired
    private BarterService barterService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private NotificationService notificationService;
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> newProposal(@RequestBody BarterRequest tradeProposal){
        return new ResponseEntity<>(barterService.saveProduct(tradeProposal), HttpStatus.CREATED);
    }
    @GetMapping("/{barter_id}")
    public ResponseEntity<BarterDTO> getBarterById(@PathVariable("barter_id") Integer barterId) {
        Barter barter = barterService.getBarterById(barterId);
        if (barter != null) {
            BarterDTO barterDTO = BarterDTO.fromEntity(barter);
            return new ResponseEntity<>(barterDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/{barter_id}")
    public ResponseEntity<String> updateBarterStatus(
            @PathVariable("barter_id") Integer barterId,
            @RequestBody() BarterStatusUpdateRequest status,
            @RequestHeader("user_id") Integer userId) {

        Barter barter = barterService.getBarterById(barterId);
        if (barter == null) {
            return new ResponseEntity<>("Barter not found", HttpStatus.NOT_FOUND);
        }
        String newStatus = status.getStatus().toLowerCase();
        barter.setLast_updated(LocalDate.now());
        if(newStatus.equals("completion_pending")){
            barter.setFirst_to_complete_id(userId);
        }
        if(newStatus.equals("accepted")){
            Product product = productRepository.findById(barter.getOfferedId().getId()).get();
            Product product2 = productRepository.findById(barter.getRequestedId().getId()).get();
            product.setStatus("Unavailable");
            product2.setStatus("Unavailable");
            productRepository.save(product);
            productRepository.save(product2);
        }
        Notification newNotification = new Notification();
        newNotification.setBarter_id(barter);
        newNotification.setUser(userRepository.findById(userId).get());
        newNotification.setType(status.getStatus());
        newNotification.setStatus("unread");
        newNotification.setMessage("Barter Proposal with user " + userRepository.findById(userId).get().getUsername() + " is " + status.getStatus());
        newNotification.setTimestamp(LocalDate.now());
        notificationService.save(newNotification);
        notificationService.notifyNewBarter(newNotification);
        barter.setStatus(newStatus);
        barterService.updateStatus(barter);
        return new ResponseEntity<>("Status updated successfully", HttpStatus.OK);
    }

    private boolean isUserAuthorized(User userDetails, Barter barter) {
        return userDetails.getId().equals(barter.getOfferedId().getSeller().getId()) || userDetails.getId().equals(barter.getRequestedId().getSeller().getId()) ;
    }

    /** TO DO:
     *
    @GetMapping("/{proposal_id}")
    public ResponseEntity<List<ProductDTO>> getProducts(
            @PathVariable Integer proposal_id
    ){
        Optional<TradeProposal> product = productRepository.findById(product_id);
        List<ProductDTO> p = product.stream().map(ProductDTO::fromProduct).toList();
        return new ResponseEntity<>(p, HttpStatus.OK);
    } **/


}
