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
@Table(name ="users")

public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String city;
    private String status;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_and_certification" ,joinColumns = {
            @JoinColumn(name = "user_id")},inverseJoinColumns = {
            @JoinColumn(name = "certification_id")})
    private List<Certification> certifications;
    }
