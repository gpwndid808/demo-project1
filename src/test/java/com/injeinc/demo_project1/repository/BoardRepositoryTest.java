package com.injeinc.demo_project1.repository;

import com.injeinc.demo_project1.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// TODO [9단계] @DataJpaTest 이해하기
//  - JPA 관련 컴포넌트만 로드하는 경량 테스트
//  - 테스트용 임베디드 DB(H2) 자동 설정
//  - 각 테스트 후 자동 롤백 (데이터가 남지 않음)
//  💡 학습 포인트: @SpringBootTest보다 빠르고 격리된 테스트

// TODO [9단계] 테스트 작성 전 준비사항
//  - H2 데이터베이스 의존성 추가 (build.gradle)
//  - testRuntimeOnly 'com.h2database:h2'
//  💡 실습: build.gradle에서 H2 의존성 확인

@DataJpaTest
public class BoardRepositoryTest {
    
    // TODO [9단계] @Autowired로 테스트 대상 주입
    //  - 실제 구현체를 Spring이 자동으로 주입합니다.
    //  - 테스트에서는 필드 주입을 사용해도 괜찮습니다.
    @Autowired
    private BoardRepository boardRepository;
    
    // TODO [9단계] @Test 어노테이션 이해하기
    //  - JUnit 테스트 메서드임을 표시
    //  - 각 테스트는 독립적으로 실행됩니다.
    //  💡 학습 포인트: 테스트 격리의 중요성
    
    // TODO [9단계] @DisplayName으로 테스트 설명 추가
    //  - 테스트 실행 결과에 한글 설명이 표시됩니다.
    //  - 테스트의 목적을 명확히 전달합니다.
    
    // TODO [9단계] given-when-then 패턴 이해하기
    //  - given: 테스트를 위한 준비 (데이터 생성)
    //  - when: 실제 테스트할 동작 실행
    //  - then: 결과 검증 (assertion)
    //  💡 학습 포인트: 테스트 코드 구조화 패턴
    
    @Test
    @DisplayName("게시글 저장 테스트")
    void saveBoard() {
        // TODO [9단계] given - 테스트 데이터 준비
        //  - 저장할 Board 엔티티 생성
        //  💡 실습: 아래 주석을 해제하고 완성하세요
        /*
        Board board = Board.builder()
            .boardTitle("테스트 제목")
            .boardCn("테스트 내용")
            .rgstrUsrId("testUser")
            .build();
        */
        
        // TODO [9단계] when - 저장 동작 실행
        //  - Repository의 save() 메서드 호출
        /*
        Board savedBoard = boardRepository.save(board);
        */
        
        // TODO [9단계] then - 결과 검증 (AssertJ 사용)
        //  - assertThat: AssertJ의 검증 시작
        //  - isNotNull(): null이 아닌지 확인
        //  - isEqualTo(): 값이 같은지 확인
        //  💡 학습 포인트: AssertJ는 읽기 쉬운 assertion을 제공합니다.
        /*
        assertThat(savedBoard.getBoardId()).isNotNull();
        assertThat(savedBoard.getBoardTitle()).isEqualTo("테스트 제목");
        assertThat(savedBoard.getBoardCn()).isEqualTo("테스트 내용");
        */
    }
    
    @Test
    @DisplayName("게시글 ID로 조회 테스트")
    void findById() {
        // TODO [9단계] 조회 테스트 작성하기
        //  - given: 게시글 저장
        //  - when: findById()로 조회
        //  - then: 조회된 데이터 검증
        //  💡 실습: 위 패턴을 참고하여 작성하세요
    }
    
    @Test
    @DisplayName("제목으로 검색 테스트")
    void findByTitleContaining() {
        // TODO [9단계] 검색 테스트 작성하기
        //  - given: 여러 게시글 저장 (제목이 다른 게시글들)
        //  - when: findByBoardTitleContaining() 호출
        //  - then: 검색 결과 개수 및 내용 검증
        //  💡 실습: 아래 주석을 해제하고 완성하세요
        /*
        // given
        boardRepository.save(Board.builder()
            .boardTitle("스프링 부트 학습")
            .boardCn("내용1")
            .build());
        boardRepository.save(Board.builder()
            .boardTitle("JPA 학습")
            .boardCn("내용2")
            .build());
        
        // when
        List<Board> result = boardRepository.findByBoardTitleContaining("스프링");
        
        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBoardTitle()).contains("스프링");
        */
    }
    
    @Test
    @DisplayName("게시글 삭제 테스트")
    void deleteBoard() {
        // TODO [9단계] 삭제 테스트 작성하기
        //  - given: 게시글 저장
        //  - when: deleteById() 호출
        //  - then: findById()가 빈 Optional 반환하는지 확인
        //  💡 실습: Optional.isEmpty() 또는 assertThat().isEmpty() 사용
    }
    
    @Test
    @DisplayName("게시글 수정 테스트")
    void updateBoard() {
        // TODO [9단계] 수정 테스트 작성하기 (더티 체킹 확인)
        //  - given: 게시글 저장
        //  - when: 엔티티 필드 변경
        //  - then: flush() 후 다시 조회하여 변경사항 확인
        //  💡 학습 포인트: 테스트에서 flush()를 명시적으로 호출
    }
    
    // TODO [9단계] 페이징 테스트 작성하기
    //  - PageRequest.of()로 Pageable 생성
    //  - Page 객체의 메서드 검증
    //  - getTotalElements(), getTotalPages() 등
    
    // TODO [9단계] AssertJ 주요 메서드 익히기
    //  - isEqualTo(), isNotEqualTo()
    //  - isNull(), isNotNull()
    //  - hasSize(), isEmpty()
    //  - contains(), containsExactly()
    //  💡 학습 포인트: 다양한 assertion 메서드 활용
    
    // TODO [9단계] 테스트 실행 방법
    //  - IDE: 클래스 우클릭 → Run Test
    //  - Gradle: ./gradlew test
    //  - 특정 테스트만: ./gradlew test --tests BoardRepositoryTest.saveBoard
    //  💡 실습: 모든 테스트가 통과하는지 확인
}
