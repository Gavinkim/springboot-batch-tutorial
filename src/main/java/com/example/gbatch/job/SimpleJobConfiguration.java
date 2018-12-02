//package com.example.gbatch.job;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by gavinkim at 2018-12-02
// * Batch Job을 생성하는 simpleJob 설정
// * Tasklet 하나와 Reader & Processor & Writer 한 묶음이 같은 레벨
// */
//@Slf4j
//@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
//@Configuration
//public class SimpleJobConfiguration {
//
//    // 생성자 DI
//    private final JobBuilderFactory jobBuilderFactory;
//    // 생성자 DI
//    private final StepBuilderFactory stepBuilderFactory;
//
//    /**
//     * simpleJob 이란 이름의 Batch Job을 생성합니다.
//     * job의 이름은 별도로 지정하지 않고, Builder를 통해 지정한다.
//     */
//    @Bean
//    public Job simpleJob(){
//        return jobBuilderFactory.get("simpleJob")
//                .start(simpleStep1(null))
//                .next(simpleStep2(null))
//                .build();
//    }
//
//    /**
//     * simpleStep1 이라는 이름의 Batch Step을 생성한다.
//     * Builder 를 통해 이름을 지정한다.
//     * tasklet을 통해 Step 안에서 수행될 기능들을 명시한다.
//     * Tasklet은 Step안에서 단일로 수행될 커스텀한 기능들을 선언할때 사용한다.
//     */
//    @Bean
//    @JobScope
//    public Step simpleStep1(@Value("#{jobParameters[requestDate]}") String requestDate){
//        return stepBuilderFactory.get("simpleStep1")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info(">>>> Step 01"); // Batch 가 수행되면 로그로 출력 한도록 한다.
//                    log.info(">>> requestDate = {}",requestDate);
//                    return RepeatStatus.FINISHED;
////                    throw new IllegalArgumentException("step 1 에서 실패 하였습니다.");
//                }).build();
//    }
//
//    @Bean
//    @JobScope
//    public Step simpleStep2(@Value("#{jobParameters[requestDate]}") String requestDate){
//        return stepBuilderFactory.get("simpleStep1")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info(">>>> Step 02"); // Batch 가 수행되면 로그로 출력 한도록 한다.
//                    log.info(">>> requestDate = {}",requestDate);
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//}
