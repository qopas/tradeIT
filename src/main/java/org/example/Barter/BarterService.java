package org.example.Barter;

import org.example.Product.ProductDTO2;
import org.example.Product.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BarterService {
    @Autowired
    private BarterRepository barterRepository;
    @Autowired
    private ProductRepository productRepository;
    public Map<String, Object> saveProduct(@NotNull BarterRequest barterRequest){
        Barter newProposal = new Barter();
        newProposal.setOfferedId(productRepository.findById(barterRequest.getOffered_product_id()).orElse(null));
        newProposal.setRequestedId(productRepository.findById(barterRequest.getDesired_product_id()).orElse(null));
        newProposal.setStatus("pending");
        newProposal.setInitialize_at(LocalDate.now());
        newProposal.setLast_updated(LocalDate.now());
        Barter saved = barterRepository.save(newProposal);
        Map<String, Object> response = new HashMap<>();
        response.put("message", barterRequest.getMessage());
        response.put("barter_id", saved.getId());
        return response;
    }
    public List<BarterDTO> getBartersByUser(Integer userId) {
        List<Barter> userBarters = barterRepository.findBartersByOfferedIdSellerIdOrRequestedIdSellerId(userId, userId);
        return userBarters.stream().map(BarterDTO::fromEntity).collect(Collectors.toList());
    }
    public void updateStatus(Barter barter){
        barterRepository.save(barter);
    }
    public Barter getBarterById(Integer barterId){
        return barterRepository.findById(barterId).get();
    }
}
