package com.injeinc.demo_project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.injeinc.demo_project1.entity.Board;

// TODO [2단계] Repository 인터페이스 이해하기
//  - JpaRepository<엔티티, ID타입>을 상속받으면 기본 CRUD 메서드 자동 제공
//  - 제공되는 메서드: save(), findById(), findAll(), deleteById() 등
//  - @Repository 어노테이션: Spring이 이 인터페이스의 구현체를 자동 생성
//  💡 학습 포인트: 인터페이스만 선언하면 JPA가 구현체를 자동으로 만들어줍니다!

// TODO [2단계] 기본 제공 메서드 테스트하기
//  - findAll(): 모든 엔티티 조회
//  - findById(id): ID로 단건 조회 (Optional 반환)
//  - save(entity): 엔티티 저장 (ID가 없으면 INSERT, 있으면 UPDATE)
//  - deleteById(id): ID로 엔티티 삭제
//  - count(): 전체 개수 조회
//  💡 실습: Service에서 이 메서드들을 호출해보세요

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {

    // TODO [7단계] 쿼리 메서드 작성하기
    //  - JPA는 메서드 이름으로 쿼리를 자동 생성합니다.
    //  - 제목으로 검색: List<Board> findByBoardTitleContaining(String title);
    //  - 작성자로 검색: List<Board> findByRgstrUsrId(String userId);
    //  - 제목과 내용으로 검색: List<Board> findByBoardTitleContainingOrBoardCnContaining(String title, String content);
    //  💡 학습 포인트: findBy, Containing, And, Or 등의 키워드 조합
    
    // TODO [7단계] 페이징 쿼리 메서드 추가
    //  - Page<Board> findByBoardTitleContaining(String title, Pageable pageable);
    //  - Pageable 파라미터를 추가하면 자동으로 페이징 처리
    //  💡 실습: PageRequest.of(0, 10, Sort.by("rgstrDt").descending())
    
    // TODO [7단계] @Query 어노테이션 학습 (심화)
    //  - 복잡한 쿼리는 JPQL 또는 Native Query 사용
    //  - @Query("SELECT b FROM Board b WHERE b.boardTitle LIKE %:keyword%")
    //  - List<Board> searchByKeyword(@Param("keyword") String keyword);
    //  💡 학습 포인트: JPQL은 객체 지향 쿼리 언어입니다.
    
    // TODO [9단계] 테스트 작성하기
    //  - BoardRepositoryTest 클래스 생성
    //  - @DataJpaTest 어노테이션 사용
    //  - 각 쿼리 메서드에 대한 테스트 케이스 작성
    //  💡 실습: 데이터 저장 후 조회가 제대로 되는지 검증

}
