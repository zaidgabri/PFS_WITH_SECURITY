package com.Sk09Team.Doctor.entity;

import com.Sk09Team.Doctor.model.PaymentMode;
import com.Sk09Team.Doctor.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// implements UserDetails
public class Doctor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long doctorId;
    @Column(name="firstName")
    private String firstName ;
    @Column(name="lastName")
    private String lastName ;
    @Column(name="password")
    private String password ;
    @Column(name="registrationNumber")
    private String registrationNumber ;
    @Column(name="address")
    private String address ;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String doctorPhone;
    @Column(name="city")
    private String city ;
    @Column(name="post_code")
    private  String postCode;
    @Column(name="specialty")
    private String specialty ;
    @Column(name="location")
    private String location ;
    @ElementCollection
    @Column(name="diplomas")
    private List<String> diplomas;
    @Column(name="place")
    private String place;
    @Column(name="paymentMode")
    private PaymentMode paymentMode;
    @ElementCollection
    @Column(name="languages")
    private List<String> languages;



   @Column(columnDefinition ="TEXT")
    private String  calendar;
    @Column(name="src")
    private String src;
    @Column(name="latitude")
    private String latitude;
    @Column(name="longitude")
    private String longitude;

//    @JsonBackReference
//    @JsonIgnore
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
//    private List<Motif> motifs;






    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Token> tokens;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}


