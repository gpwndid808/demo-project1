package com.injeinc.demo_project1.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO [2단계] JPA 엔티티 어노테이션 이해하기
//  - @Entity: 이 클래스가 JPA 엔티티임을 명시 (DB 테이블과 매핑)
//  - @Table: 매핑할 테이블 이름 지정 (생략 시 클래스명을 테이블명으로 사용)
//  💡 학습 포인트: 엔티티는 DB 테이블과 1:1 대응되는 객체입니다.

// TODO [1단계] Lombok 적용하기
//  - @Getter, @NoArgsConstructor(access = AccessLevel.PROTECTED) 어노테이션 추가
//  - 수동으로 작성된 getter 메서드 제거
//  - @Builder 어노테이션 추가하여 빌더 패턴 사용
//  💡 학습 포인트: Lombok은 반복적인 코드를 자동 생성해줍니다.

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "board")
public class Board {

    // TODO [2단계] @Id와 @GeneratedValue 이해하기
    //  - @Id: 기본키(Primary Key) 지정
    //  - @GeneratedValue: 자동 생성 전략 지정
    //  - IDENTITY: DB의 AUTO_INCREMENT 사용 (MySQL, PostgreSQL)
    //  💡 주의: boardId의 타입이 String인데 IDENTITY 전략은 Long 타입에 적합합니다.
    //  💡 권장: Long 타입으로 변경 고려
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    // TODO [2단계] 엔티티 필드 설계하기
    //  - boardTitle: 게시글 제목
    //  - boardCn: 게시글 내용 (content)
    //  💡 고민: 컬럼명을 영문으로 title, content로 변경 고려
    private String boardTitle;
    private String boardCn;
    
    // TODO [2단계] 작성자/수정자 정보 필드
    //  - rgstrUsrId: 등록 사용자 ID
    //  - mdfcnUsrId: 수정 사용자 ID
    //  💡 8단계에 Spring Security 적용 시 자동화 가능
    private String rgstrUsrId;
    private String mdfcnUsrId;

    // TODO [8단계] BaseTimeEntity로 이동 예정
    //  - 생성일시, 수정일시는 모든 엔티티에서 공통으로 사용
    //  - BaseTimeEntity를 만들어 상속받는 구조로 리팩토링
    private LocalDateTime rgstrDt;
    private LocalDateTime mdfcnDt;

    // TODO [2단계] 기본 생성자의 중요성
    //  - JPA는 리플렉션을 사용하므로 기본 생성자 필수
    //  - protected로 선언하여 외부 생성 방지
    //  💡 학습 포인트: Lombok의 @NoArgsConstructor(access = AccessLevel.PROTECTED)로 대체 가능
//    protected Board() {}
    
    // TODO [3단계] 생성자 또는 Builder 추가하기
    //  - 엔티티 생성 시 필수 값을 받는 생성자 작성
    //  - 또는 @Builder 어노테이션 활용
    //  💡 예시: Board.builder().boardTitle("제목").boardCn("내용").build()
    public static Board create(String title, String content, String rgstrUsrId, String mdfcnUsrId) {
        return Board.builder()
                .boardTitle(title)
                .boardCn(content)
                .rgstrUsrId(rgstrUsrId)
                .mdfcnUsrId(mdfcnUsrId)
                .rgstrDt(LocalDateTime.now())
                .mdfcnDt(LocalDateTime.now())
                .build();
    }
    
    // TODO [4단계] 수정 메서드 추가하기 (더티 체킹)
    //  - updateTitle(String title), updateContent(String content) 메서드 생성
    //  - 또는 update(String title, String content) 통합 메서드
    //  - @Transactional 내에서 엔티티 필드 변경 시 자동으로 UPDATE 쿼리 실행
    //  💡 예시:
    //  public void update(String title, String content) {
    //      this.boardTitle = title;
    //      this.boardCn = content;
    //  }
    
    // TODO [6단계] Comment 연관관계 매핑하기
    //  - @OneToMany(mappedBy = "board") List<Comment> comments 필드 추가
    //  - cascade, orphanRemoval 옵션 학습
    //  💡 학습 포인트: 양방향 연관관계 vs 단방향 연관관계

    // Getter 메서드들
    // TODO [1단계] Lombok @Getter로 대체 가능
//    public String getBoardId() { return boardId; }
//    public String getBoardTitle() { return boardTitle; }
//    public String getBoardCn() { return boardCn; }
//    public String getRgstrUsrId() { return rgstrUsrId; }
//    public Date getRgstrDt() { return rgstrDt; }
//    public String getMdfcnUsrId() { return mdfcnUsrId; }
//    public Date getMdfcnDt() { return mdfcnDt; }
}