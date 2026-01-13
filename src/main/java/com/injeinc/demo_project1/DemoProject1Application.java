/*
 * ========================================
 * Spring Boot + JPA CRUD 실습 프로젝트
 * ========================================
 * 
 */

package com.injeinc.demo_project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// TODO [8단계] JPA Auditing 활성화를 위한 import
//  - @EnableJpaAuditing 사용을 위해 필요
//  💡 실습: 아래 import 주석을 해제하세요
// import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// TODO [1단계] @SpringBootApplication 어노테이션의 역할 이해하기
//  - 이 어노테이션은 다음 3가지를 포함합니다:
//    1) @Configuration: 설정 클래스임을 명시
//    2) @EnableAutoConfiguration: Spring Boot의 자동 설정 활성화
//    3) @ComponentScan: 현재 패키지 이하의 컴포넌트를 스캔
//  💡 학습 포인트: Spring Boot는 이 어노테이션 하나로 복잡한 설정을 자동화합니다.

// TODO [8단계] @EnableJpaAuditing 추가하기
//  - JPA Auditing 기능을 활성화합니다.
//  - @CreatedDate, @LastModifiedDate가 자동으로 동작하게 됩니다.
//  - BaseTimeEntity를 상속받은 모든 엔티티에 적용됩니다.
//  💡 실습: 아래 어노테이션의 주석을 해제하세요
// @EnableJpaAuditing
@SpringBootApplication
public class DemoProject1Application {

	// TODO [1단계] main 메서드의 역할 이해하기
	//  - Spring Boot 애플리케이션의 진입점(Entry Point)입니다.
	//  - SpringApplication.run()은 임베디드 톰캣 서버를 실행하고 Spring 컨테이너를 초기화합니다.
	//  💡 실습: 이 파일을 실행하고 콘솔에서 "Started DemoProject1Application" 메시지를 확인하세요.
	
	// TODO [8단계] JPA Auditing 동작 확인하기
	//  - @EnableJpaAuditing 추가 후 애플리케이션 재시작
	//  - Board, Comment 엔티티가 BaseTimeEntity 상속받도록 수정
	//  - 게시글 생성/수정 시 createdDate, modifiedDate 자동 설정 확인
	//  - DB 또는 API 응답에서 시간 필드 확인
	//  💡 실습: Postman으로 게시글 생성 후 시간 필드 확인
	
	// TODO [8단계] TimeZone 설정 (선택사항)
	//  - 서버 시간대를 명시적으로 설정
	//  - application.properties에 추가:
	//  - spring.jpa.properties.hibernate.jdbc.time_zone=Asia/Seoul
	//  💡 실습: 한국 시간으로 올바르게 저장되는지 확인
	
	public static void main(String[] args) {
		SpringApplication.run(DemoProject1Application.class, args);
	}

}
