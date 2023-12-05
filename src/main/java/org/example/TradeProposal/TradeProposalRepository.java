package org.example.TradeProposal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeProposalRepository extends JpaRepository<TradeProposal, Integer> {

}
