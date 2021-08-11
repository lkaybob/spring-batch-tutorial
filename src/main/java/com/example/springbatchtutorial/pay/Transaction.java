package com.example.springbatchtutorial.pay;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seq;
    private Long amount;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "member_no'")
    private Member member;

}
