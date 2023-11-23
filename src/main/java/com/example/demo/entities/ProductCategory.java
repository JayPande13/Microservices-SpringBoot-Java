package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Builder
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer categoryId;
    String categoryName;
    String categoryDesc;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id")
    List<Products> products;

}
