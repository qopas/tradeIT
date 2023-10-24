package org.example.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //generate getters and setters
@Builder //build object
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categories")  // Use the actual table name if different
public class Category {
    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name", length = 20)
    private String categoryName;

}