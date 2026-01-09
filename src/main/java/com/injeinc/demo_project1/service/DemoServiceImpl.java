package com.injeinc.demo_project1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.repository.BoardRepository;

//import lombok.RequiredArgsConstructor;

// TODO [3단계] @Service 어노테이션 이해하기
//  - Spring이 이 클래스를 Service Bean으로 등록합니다.
//  - @Component의 특수한 형태입니다.
//  💡 학습 포인트: Controller -> Service -> Repository 계층 구조

// TODO [1단계] Lombok @RequiredArgsConstructor 활용하기
//  - final 필드를 파라미터로 받는 생성자를 자동 생성
//  - 주석을 해제하고 수동 생성자를 제거해보세요
//  💡 실습: 위 주석을 풀고 생성자 제거 후 실행

// TODO [4단계] @Transactional 어노테이션 이해하기
//  - readOnly = true: 조회 전용 트랜잭션 (성능 최적화)
//  - CUD 작업 시에는 메서드에 @Transactional(readOnly = false) 또는 @Transactional만 붙이기
//  💡 학습 포인트: 트랜잭션은 데이터 일관성을 보장합니다.

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DemoServiceImpl implements DemoService {

    // TODO [3단계] 의존성 주입(DI) 이해하기
    //  - Repository를 주입받아 DB 작업을 수행합니다.
    //  - final 키워드로 불변성을 보장합니다.
    //  💡 학습 포인트: 생성자 주입 방식이 권장됩니다.
    private final BoardRepository boardRepository;
    
    public DemoServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    
    // TODO [3단계] 전체 조회 구현 분석하기
    //  - Repository의 findAll() 메서드를 호출합니다.
    //  - JPA가 자동으로 SELECT * FROM board 쿼리를 생성합니다.
    //  💡 실습: 애플리케이션 실행 후 API를 호출하고 콘솔에서 SQL 로그 확인
    @Override
    public List<Board> retvLstBoard() {
        return boardRepository.findAll();
    }
    
    // TODO [3단계] 단건 조회 메서드 구현하기
    //  - public Board findById(String id) 메서드 작성
    //  - boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    //  💡 학습 포인트: Optional을 사용한 null 안전성
    //  💡 5단계에서 커스텀 예외로 개선합니다.
    
    // TODO [3단계] 게시글 작성 메서드 구현하기
    //  - public Board createBoard(BoardRequestDto dto) 메서드 작성
    //  - DTO를 Entity로 변환: Board.builder()...build() 또는 dto.toEntity()
    //  - boardRepository.save(board);
    //  - 저장된 엔티티를 반환
    //  💡 실습: Postman에서 POST 요청으로 테스트
    
    // TODO [4단계] 게시글 수정 메서드 구현하기 (더티 체킹)
    //  - @Transactional 어노테이션 추가 (readOnly = false가 기본)
    //  - public Board updateBoard(String id, BoardUpdateDto dto) 메서드 작성
    //  - 1) findById()로 엔티티 조회
    //  - 2) 엔티티의 update() 메서드 호출로 필드 변경
    //  - 3) save() 호출 없이도 자동으로 UPDATE 쿼리 실행됨 (더티 체킹)
    //  💡 핵심: @Transactional 안에서 엔티티를 변경하면 JPA가 자동으로 DB에 반영합니다.
    //  💡 예시:
    //  @Transactional
    //  public Board updateBoard(String id, BoardUpdateDto dto) {
    //      Board board = findById(id);
    //      board.update(dto.getTitle(), dto.getContent());
    //      return board; // save() 없이도 UPDATE 됨!
    //  }
    
    // TODO [4단계] 게시글 삭제 메서드 구현하기
    //  - @Transactional 어노테이션 추가
    //  - public void deleteBoard(String id) 메서드 작성
    //  - 존재 여부 확인 후 boardRepository.deleteById(id) 호출
    //  💡 실습: Postman에서 DELETE 요청으로 테스트
    //  💡 고민: 삭제 전 존재 여부를 확인해야 할까요?
}
