package com.injeinc.demo_project1.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// TODO [6단계] Comment 엔티티 생성하기
//  - 게시글에 달리는 댓글을 표현하는 엔티티입니다.
//  - Board와 1:N 관계를 맺습니다. (게시글 1개에 댓글 여러 개)
//  💡 학습 포인트: 연관관계 매핑의 실제 사례

// TODO [1단계] Lombok 적용하기
//  - @Getter, @NoArgsConstructor(access = AccessLevel.PROTECTED)
//  - @Builder 어노테이션 추가
//  💡 실습: Lombok으로 코드 간소화

// TODO [6단계] @Entity 어노테이션 추가하기
//  - 이 클래스가 JPA 엔티티임을 명시
//  💡 실습: 아래 어노테이션의 주석을 해제하세요
// @Entity
public class Comment {
    
    // TODO [6단계] 기본키 설정
    //  - Long 타입의 ID 사용 (Auto Increment)
    //  - @Id와 @GeneratedValue 필수
    //  💡 실습: 아래 어노테이션의 주석을 해제하세요
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // TODO [6단계] 댓글 내용 필드
    //  - 댓글의 실제 내용을 저장하는 필드
    //  - @Column으로 제약조건 추가 가능
    //  💡 실습: nullable = false, length = 500 등 추가
    private String content;
    
    // TODO [6단계] 작성자 필드
    //  - 댓글 작성자 정보
    private String writer;
    
    // TODO [6단계] 작성시간, 수정시간
    //  - 8단계에서 BaseTimeEntity를 상속받으면 자동 관리됩니다.
    //  - 지금은 수동으로 추가하거나 생략 가능
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    
    // TODO [6단계] Board와의 연관관계 설정 (핵심!)
    //  - @ManyToOne: 댓글(N) : 게시글(1) 관계
    //  - FetchType.LAZY: 지연 로딩 (성능 최적화)
    //  - @JoinColumn: 외래키 컬럼 이름 지정
    //  💡 학습 포인트: 연관관계의 주인은 외래키를 가진 쪽(Comment)
    //  💡 실습: 아래 어노테이션의 주석을 해제하세요
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    */
    
    // TODO [6단계] FetchType 이해하기
    //  - LAZY: 실제로 사용할 때 쿼리 실행 (권장)
    //  - EAGER: 즉시 로딩, 항상 JOIN 쿼리 실행
    //  💡 학습 포인트: N+1 문제를 방지하려면 LAZY 사용
    
    // TODO [6단계] 기본 생성자
    //  - JPA는 기본 생성자가 필요합니다.
    //  - protected로 선언하여 외부에서 new Comment() 생성 방지
    protected Comment() {}
    
    // TODO [6단계] 생성자 추가하기
    //  - 댓글 생성 시 필요한 필드를 받는 생성자
    //  - 또는 @Builder 패턴 사용
    //  💡 실습: 아래 주석을 해제하고 완성하세요
    /*
    public Comment(String content, String writer, Board board) {
        this.content = content;
        this.writer = writer;
        this.board = board;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }
    */
    
    // TODO [6단계] 연관관계 편의 메서드 (선택사항)
    //  - Board 설정 시 양쪽 관계를 동시에 설정
    //  💡 실습:
    /*
    public void setBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }
    */
    
    // TODO [6단계] 수정 메서드 추가하기
    //  - 더티 체킹을 활용한 댓글 수정
    //  💡 실습: updateContent(String content) 메서드 작성
    
    // Getter 메서드들
    // TODO [1단계] Lombok @Getter로 대체 가능
    public Long getId() {
        return id;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getWriter() {
        return writer;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
    
    // TODO [6단계] Board getter 추가
    //  - 연관관계 필드의 getter도 필요합니다.
    /*
    public Board getBoard() {
        return board;
    }
    */
}
