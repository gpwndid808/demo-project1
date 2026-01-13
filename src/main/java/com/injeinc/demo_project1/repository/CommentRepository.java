package com.injeinc.demo_project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.injeinc.demo_project1.entity.Comment;

import java.util.List;

// TODO [6단계] CommentRepository 생성하기
//  - Comment 엔티티를 위한 Repository 인터페이스
//  - JpaRepository를 상속받아 기본 CRUD 메서드 제공
//  💡 학습 포인트: 엔티티마다 Repository가 필요합니다.

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    // TODO [6단계] 게시글별 댓글 조회하기
    //  - 특정 게시글의 모든 댓글을 조회하는 메서드
    //  - 메서드 이름으로 자동 쿼리 생성
    //  💡 실습: 아래 메서드의 주석을 해제하세요
     List<Comment> findByBoardBoardId(String boardId);
    
    // TODO [6단계] 쿼리 메서드 네이밍 이해하기
    //  - findByBoard_BoardId: Board의 boardId로 검색
    //  - 언더스코어(_)는 연관관계를 타고 들어감을 의미
    //  💡 학습 포인트: 중첩된 프로퍼티 접근 방법
    
    // TODO [6단계] 정렬 추가하기
    //  - 최신 댓글 먼저 조회
    //  💡 실습: 아래 메서드의 주석을 해제하세요
    List <Comment> findByBoardBoardIdOrderByRgstrDtDesc(String boardId);
    
    // TODO [6단계] 작성자로 댓글 찾기
    //  - 특정 사용자가 작성한 모든 댓글 조회
    //  💡 실습: 적절한 메서드명을 작성하세요
     List<Comment> findByWriter(String writer);
    
    // TODO [6단계] 게시글별 댓글 개수 세기
    //  - 특정 게시글의 댓글 개수 조회
    //  💡 실습: 아래 메서드의 주석을 해제하세요
     long countByBoardBoardId(String boardId);
    
    // TODO [6단계] 댓글 삭제 (게시글 기준)
    //  - 특정 게시글의 모든 댓글 삭제
    //  - cascade 옵션으로도 가능하지만 직접 삭제도 가능
    //  💡 주의: @Modifying, @Transactional 필요
    
    // TODO [7단계] 페이징 적용하기
    //  - 댓글이 많을 경우 페이징 처리
    //  - import org.springframework.data.domain.Page;
    //  - import org.springframework.data.domain.Pageable;
    //  💡 실습: 아래 메서드의 주석을 해제하세요
    // Page<Comment> findByBoardBoardId(String boardId, Pageable pageable);
    
    // TODO [9단계] 테스트 작성하기
    //  - CommentRepositoryTest 클래스 생성
    //  - 댓글 저장 및 조회 테스트
    //  - 게시글 삭제 시 댓글도 함께 삭제되는지 테스트 (cascade 확인)
}
