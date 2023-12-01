package org.example.Barter;

import org.example.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TradeProposalService {
    @Autowired
    private TradeProposalRepository tradeProposalRepository;
    @Autowired
    private ProductRepository productRepository;
    public Map<String, Object> saveProduct(TradeProposalRequest tradeProposalRequest){
        Barter newProposal = new Barter();
        newProposal.setOfferedId(productRepository.findById(tradeProposalRequest.getOffered_product_id()).orElse(null));
        newProposal.setRequestedId(productRepository.findById(tradeProposalRequest.getDesired_product_id()).orElse(null));
        newProposal.setStatus("pending");
        Barter saved = tradeProposalRepository.save(newProposal);
        Map<String, Object> response = new HashMap<>();
        response.put("message", tradeProposalRequest.getMessage());
        response.put("proposal_id", saved.getId());
        return response;
    }
}
