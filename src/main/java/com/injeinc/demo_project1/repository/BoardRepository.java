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

    // TODO [7단계] 쿼리 메서드 네이밍 규칙 이해하기
    //  - JPA는 메서드 이름을 분석하여 쿼리를 자동 생성합니다.
    //  - findBy + 필드명 + 조건키워드 + [And/Or + ...]
    //  - 조건 키워드: Containing, StartingWith, EndingWith, GreaterThan, LessThan 등
    //  💡 학습 포인트: 메서드 이름만으로 쿼리를 작성할 수 있습니다!

    // TODO [7단계] 제목으로 검색하기
    //  - Containing: LIKE 검색 (부분 일치)
    //  💡 실습: 아래 메서드의 주석을 해제하세요
    // List<Board> findByBoardTitleContaining(String keyword);
    
    // TODO [7단계] 작성자로 검색하기
    //  - Equals 생략 가능 (기본값)
    //  💡 실습: 아래 메서드의 주석을 해제하세요
    // List<Board> findByRgstrUsrId(String userId);
    
    // TODO [7단계] 여러 조건으로 검색하기 (OR 조건)
    //  - 제목 또는 내용에 키워드가 포함된 게시글 검색
    //  💡 실습: 아래 메서드의 주석을 해제하세요
    // List<Board> findByBoardTitleContainingOrBoardCnContaining(String title, String content);
    
    // TODO [7단계] 정렬 추가하기
    //  - OrderBy + 필드명 + Asc/Desc
    //  💡 실습: 최신 게시글 먼저 조회하기
    // List<Board> findByBoardTitleContainingOrderByRgstrDtDesc(String keyword);
    
    // TODO [7단계] 페이징 처리하기 (핵심!)
    //  - Pageable 파라미터를 추가하면 자동으로 페이징 처리
    //  - import org.springframework.data.domain.Page;
    //  - import org.springframework.data.domain.Pageable;
    //  💡 실습: 아래 메서드의 주석을 해제하세요
    /*
    Page<Board> findAll(Pageable pageable);
    Page<Board> findByBoardTitleContaining(String keyword, Pageable pageable);
    */
    
    // TODO [7단계] Pageable 사용법 이해하기
    //  - PageRequest.of(page, size, sort)로 생성
    //  - 예시: PageRequest.of(0, 10, Sort.by("rgstrDt").descending())
    //  - page: 0부터 시작 (0 = 첫 페이지)
    //  - size: 한 페이지에 표시할 개수
    //  - sort: 정렬 조건
    
    // TODO [7단계] Page 객체 이해하기
    //  - Page.getContent(): 실제 데이터 목록
    //  - Page.getTotalElements(): 전체 데이터 개수
    //  - Page.getTotalPages(): 전체 페이지 개수
    //  - Page.getNumber(): 현재 페이지 번호
    //  - Page.hasNext(), hasPrevious(): 다음/이전 페이지 존재 여부
    
    // TODO [7단계] @Query 어노테이션 사용하기 (심화)
    //  - 메서드 이름으로 표현하기 어려운 복잡한 쿼리는 직접 작성
    //  - JPQL (Java Persistence Query Language) 사용
    //  - import org.springframework.data.jpa.repository.Query;
    //  - import org.springframework.data.repository.query.Param;
    //  💡 실습: 아래 메서드의 주석을 해제하세요
    /*
    @Query("SELECT b FROM Board b WHERE b.boardTitle LIKE %:keyword% OR b.boardCn LIKE %:keyword%")
    List<Board> searchByKeyword(@Param("keyword") String keyword);
    */
    
    // TODO [7단계] JPQL vs Native Query
    //  - JPQL: 객체 지향 쿼리 (FROM Board b) - 권장
    //  - Native Query: SQL 그대로 사용 (FROM board) - DB 종속적
    //  💡 예시:
    //  @Query(value = "SELECT * FROM board WHERE title LIKE %?1%", nativeQuery = true)
    
    // TODO [7단계] 동적 쿼리 (선택사항)
    //  - 검색 조건이 동적으로 변하는 경우
    //  - Querydsl 또는 Specification 사용
    //  💡 실습: 검색어가 있을 때만 검색하는 로직
    
    // TODO [7단계] @EntityGraph로 N+1 문제 해결하기 (심화)
    //  - 연관된 엔티티를 한 번에 조회
    //  💡 실습:
    //  @EntityGraph(attributePaths = {"comments"})
    //  Page<Board> findAll(Pageable pageable);
    
    // TODO [9단계] 테스트 작성하기
    //  - BoardRepositoryTest 클래스 생성
    //  - @DataJpaTest 어노테이션 사용
    //  - 각 쿼리 메서드에 대한 테스트 케이스 작성
    //  💡 실습: 데이터 저장 후 조회가 제대로 되는지 검증

}
