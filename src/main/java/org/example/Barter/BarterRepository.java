package org.example.Barter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarterRepository extends JpaRepository<Barter, Integer> {

}
