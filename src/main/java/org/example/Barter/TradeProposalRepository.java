package org.example.Barter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeProposalRepository extends JpaRepository<Barter, Integer> {

}
