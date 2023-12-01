package org.example.Barter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/barters")
public class TradeProposalController {
    @Autowired
    private TradeProposalService tradeProposalService;
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> newProposal(@RequestBody TradeProposalRequest tradeProposal){
        return new ResponseEntity<>(tradeProposalService.saveProduct(tradeProposal), HttpStatus.CREATED);
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
