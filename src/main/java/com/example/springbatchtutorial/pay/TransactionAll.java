package com.example.springbatchtutorial.pay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TransactionAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String name;
    private String address;
    private Long amount;
    private LocalDateTime createdAt;

    public TransactionAll(Long seq, String name, String address, Long amount, LocalDateTime createdAt) {
        this.seq = seq;
        this.name = name;
        this.address = address;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public TransactionAll(String name, String address, Long amount, LocalDateTime createdAt) {
        this.name = name;
        this.address = address;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
