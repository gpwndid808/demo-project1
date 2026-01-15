package com.injeinc.demo_project1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// TODO [9단계] 테스트 코드 이해하기
//  - @SpringBootTest: Spring Boot 애플리케이션 전체를 로드하여 테스트
//  - 통합 테스트에 사용됩니다.
//  💡 학습 포인트: 단위 테스트 vs 통합 테스트

@SpringBootTest
class DemoProject1ApplicationTests {

	// TODO [9단계] 기본 테스트 메서드
	//  - @Test: JUnit 테스트 메서드임을 표시
	//  - contextLoads(): Spring 컨텍스트가 정상적으로 로드되는지 확인
	//  💡 실습: 테스트 실행 (우클릭 -> Run Test)
	@Test
	void contextLoads() {
		// TODO [9단계] 첫 번째 테스트 작성하기
		//  - 간단한 assertion을 추가해보세요.
		//  💡 예시: assertEquals(1, 1);
		//  💡 import static org.junit.jupiter.api.Assertions.*;
		assertEquals(1, 1);
	}
	
	// TODO [9단계] Service 테스트 추가하기
	//  - BoardServiceTest.java 파일을 새로 생성하세요.
	//  - Service 계층의 비즈니스 로직을 테스트합니다.
	//  💡 학습 포인트: @Autowired로 Bean 주입받아 테스트

	// TODO [9단계] Repository 테스트 추가하기
	//  - BoardRepositoryTest.java 파일을 새로 생성하세요.
	//  - @DataJpaTest 어노테이션 사용
	//  - JPA 쿼리 메서드를 테스트합니다.
	//  💡 실습: 데이터 저장 후 조회가 제대로 되는지 검증

}
