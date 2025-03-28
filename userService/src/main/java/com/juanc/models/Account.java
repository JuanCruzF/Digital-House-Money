package com.juanc.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "Account")
@Table(name = "\"Accounts\"")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements Serializable {
    @Id
    @Column(name = "cvu")
    String cvu;
    @Column(name = "moneyAmount", columnDefinition = "decimal default 0.00")
    BigDecimal moneyAmount;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
