package com.example.springbatchtutorial.job;

import com.example.springbatchtutorial.pay.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JpaItemWriterJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final MemberRepository memberRepository;

    private static final int chunkSize = 10;

    @Bean
    public Job jpaItemWriterJob() {
        return jobBuilderFactory.get("jpaItemWriterJob")
                .start(jpaItemWriterStep(null))
                .build();

    }

    @Bean
    @JobScope
    public Step jpaItemWriterStep(@Value("#{jobParameters[reqeustDate]}") String requestDate) {
        return stepBuilderFactory.get("jpaItemWriterStep")
                .<TransactionAll, Transaction>chunk(chunkSize)
                .reader(jpaPagingItemReader())
                .processor(jpaItemProcessor())
                .writer(jpaItemWriter())
                .build();
    }

    @Bean
    public JpaItemWriter<Transaction> jpaItemWriter() {
        JpaItemWriter<Transaction> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);

        return jpaItemWriter;
    }

    @Bean
    public ItemProcessor<TransactionAll, Transaction> jpaItemProcessor() {
        return transactionAll -> {
            log.info("Current transaction= {}", transactionAll);

            String memberName = transactionAll.getName();
            String memberAddress = transactionAll.getAddress();
            Member member = memberRepository.findByNameAndAddress(memberName, memberAddress);

            if (member == null) {
                member = new Member(memberName, memberAddress);
                memberRepository.save(member);
            }

            Transaction transaction = new Transaction();
            transaction.setAmount(transactionAll.getAmount());
            transaction.setMember(member);
            transaction.setCreatedAt(transactionAll.getCreatedAt());
            transaction.getMember().getTransactions().add(transaction);

            return transaction;
        };
    }

    @Bean
    public JpaPagingItemReader<TransactionAll> jpaPagingItemReader() {
        return new JpaPagingItemReaderBuilder<TransactionAll>()
                .name("jpaItemWriterReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("select p from TransactionAll p")
                .build();
    }
}
