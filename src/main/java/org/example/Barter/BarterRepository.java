package org.example.Barter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarterRepository extends JpaRepository<Barter, Integer> {

    List<Barter> findBartersByOfferedIdSellerIdOrRequestedIdSellerId(Integer offeredSellerId, Integer requestedSellerId);
}
