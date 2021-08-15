package com.example.springbatchtutorial.pay;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);

    Member findByNameAndAddress(String name, String address);

}
