//package com.example.gbatch.job;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by gavinkim at 2018-12-02
// *
// * 조건별로 Step 을 사용하도록 한다.
// */
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class StepNextConditionalJobConfiguration {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    /**
//     * step1이 실패하냐 성공하냐에 따라 아래 조건대로 스텝들을 실행 시킨다.
//     * 실패 시나리오: step1->step3 => step2 실패
//     * 성공 시나리오: step1->step2->step3
//     */
//    @Bean
//    public Job stepNextConditionalJob() {
//        return jobBuilderFactory.get("stepNextConditionalJob")
//                .start(conditionalJobStep1())
//                .on("FAILED") // FAILED 일 경우
//                .to(conditionalJobStep3()) // step3으로 이동한다.
//                .on("*") // step3의 결과 관계 없이
//                .end() // step3으로 이동하면 Flow가 종료한다.
//                .from(conditionalJobStep1()) // step1로부터
//                .on("*") // FAILED 외에 모든 경우
//                .to(conditionalJobStep2()) // step2로 이동한다.
//                .next(conditionalJobStep3()) // step2가 정상 종료되면 step3으로 이동한다.
//                .on("*") // step3의 결과 관계 없이
//                .end() // step3으로 이동하면 Flow가 종료한다.
//                .end() // Job 종료
//                .build();
//    }
//
//    @Bean
//    public Step conditionalJobStep1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info(">>>>> This is stepNextConditionalJob Step1");
//
//                    /**
//                     ExitStatus를 FAILED로 지정한다.
//                     해당 status를 보고 flow가 진행된다.
//                     **/
//                    //contribution.setExitStatus(ExitStatus.FAILED);
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step conditionalJobStep2() {
//        return stepBuilderFactory.get("conditionalJobStep2")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info(">>>>> This is stepNextConditionalJob Step2");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step conditionalJobStep3() {
//        return stepBuilderFactory.get("conditionalJobStep3")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info(">>>>> This is stepNextConditionalJob Step3");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//}
