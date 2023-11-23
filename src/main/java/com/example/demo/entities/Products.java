package com.example.demo.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Builder
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer productId;
    String productName;
    String productImage;
    String productDesc;
    Integer productPrice;

}
