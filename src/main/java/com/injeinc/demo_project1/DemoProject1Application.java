/*
 * ========================================
 * Spring Boot + JPA CRUD 실습 프로젝트
 * ========================================
 * 
 */

package com.injeinc.demo_project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO [1단계] @SpringBootApplication 어노테이션의 역할 이해하기
//  - 이 어노테이션은 다음 3가지를 포함합니다:
//    1) @Configuration: 설정 클래스임을 명시
//    2) @EnableAutoConfiguration: Spring Boot의 자동 설정 활성화
//    3) @ComponentScan: 현재 패키지 이하의 컴포넌트를 스캔
//  💡 학습 포인트: Spring Boot는 이 어노테이션 하나로 복잡한 설정을 자동화합니다.

@SpringBootApplication
public class DemoProject1Application {

	// TODO [1단계] main 메서드의 역할 이해하기
	//  - Spring Boot 애플리케이션의 진입점(Entry Point)입니다.
	//  - SpringApplication.run()은 임베디드 톰캣 서버를 실행하고 Spring 컨테이너를 초기화합니다.
	//  💡 실습: 이 파일을 실행하고 콘솔에서 "Started DemoProject1Application" 메시지를 확인하세요.
	public static void main(String[] args) {
		SpringApplication.run(DemoProject1Application.class, args);
	}

}
