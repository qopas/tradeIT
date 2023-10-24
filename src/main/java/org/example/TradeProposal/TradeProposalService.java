package org.example.TradeProposal;

import org.example.Product.Product;
import org.example.Product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TradeProposalService {
    private TradeProposalRepository tradeProposalRepository;
    private ProductRepository productRepository;
    public Map<String, Object> saveProduct(TradeProposalRequest tradeProposalRequest){
        TradeProposal tradeProposal = new TradeProposal();
        tradeProposal.setOfferedId(productRepository.findById(tradeProposalRequest.getOffered_product_id()).orElse(null));
        tradeProposal.setRequestedId(productRepository.findById(tradeProposalRequest.getDesired_product_id()).orElse(null));
        tradeProposal.setStatus("pending");
        TradeProposal saved = tradeProposalRepository.save(tradeProposal);
        Map<String, Object> response = new HashMap<>();
        response.put("message", tradeProposalRequest.getMessage());
        response.put("proposal_id", saved.getId());
        return response;
    }
}
