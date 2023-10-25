package org.example.Images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Integer> {
    @Query("SELECT i FROM Images i WHERE i.product.id = :productId")
    List<Images> findByProductIdId(@Param("productId") Integer productId);
}
