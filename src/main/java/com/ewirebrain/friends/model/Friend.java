package com.ewirebrain.friends.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    private int age;
    private boolean marred;

    @OneToMany(cascade = CascadeType.ALL)
    List<Address> address;
}
