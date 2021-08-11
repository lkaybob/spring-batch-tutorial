package com.example.springbatchtutorial.pay;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seq;
    private String name;
    private String address;

    @OneToMany(mappedBy = "member_no")
    private List<Transaction> transactions = new ArrayList<>();

    public Member(Long seq, String name, String address) {
        this.seq = seq;
        this.name = name;
        this.address = address;
    }

    public Member(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
