package com.injeinc.demo_project1.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.injeinc.demo_project1.dto.BoardRequestDto;
import com.injeinc.demo_project1.dto.BoardResponseDto;
import com.injeinc.demo_project1.dto.BoardUpdateDto;
import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.exception.BoardNotFoundException;
import com.injeinc.demo_project1.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DemoServiceImpl implements DemoService {

    // TODO [3단계] 의존성 주입(DI) 이해하기
    //  - Repository를 주입받아 DB 작업을 수행합니다.
    //  - final 키워드로 불변성을 보장합니다.
    //  💡 학습 포인트: 생성자 주입 방식이 권장됩니다.
    private final BoardRepository boardRepository;
    
//    public DemoServiceImpl(BoardRepository boardRepository) {
//        this.boardRepository = boardRepository;
//    }
//    
    // TODO [3단계] 전체 조회 구현 분석하기
    //  - Repository의 findAll() 메서드를 호출합니다.
    //  - JPA가 자동으로 SELECT * FROM board 쿼리를 생성합니다.
    //  💡 실습: 애플리케이션 실행 후 API를 호출하고 콘솔에서 SQL 로그 확인
    @Override
    public List<Board> retvLstBoard() {
        return boardRepository.findAll();
    }



//	@Override
//	public Board createBoard(BoardRequestDto request) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public void updateBoard(BoardUpdateDto update) {
//		// TODO Auto-generated method stub
//		
//	}



//	@Override
//	public Board findById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
    
    // TODO [3단계] 단건 조회 메서드 구현하기
    //  - findById() 메서드 구현
    //  - Optional 처리 필수!
    //  💡 실습: 아래 주석을 해제하고 완성하세요
	
	@Override 
	public Board findById(String id) { 
		
		return boardRepository.findById(id)
				.orElseThrow(() -> new BoardNotFoundException(id)); 
	}
	 
    
    // TODO [5단계] 예외 처리 개선하기
    //  - RuntimeException 대신 BoardNotFoundException 사용
    //  - 더 명확한 예외 메시지 제공
    //  💡 실습: 위 메서드의 예외를 BoardNotFoundException으로 변경
    
    // TODO [3단계] 게시글 작성 메서드 구현하기
    //  - @Transactional 어노테이션 추가 (쓰기 작업)
    //  - DTO를 Entity로 변환
    //  - Repository의 save() 메서드 호출
    //  💡 실습: 아래 주석을 해제하고 완성하세요
    
    @Transactional
    @Override
    public Board createBoard(BoardRequestDto dto) {
        Board board = dto.toEntity();
        return boardRepository.save(board);
    }
    
    
    // TODO [4단계] 게시글 수정 메서드 구현하기 (더티 체킹)
    //  - @Transactional 필수! (더티 체킹은 트랜잭션 내에서만 동작)
    //  - findById로 엔티티 조회
    //  - 엔티티의 update 메서드 호출
    //  - save() 호출 불필요! (더티 체킹이 자동으로 UPDATE)
    //  💡 실습: 아래 주석을 해제하고 완성하세요
    /*
    @Transactional
    @Override
    public Board updateBoard(String id, BoardUpdateDto dto) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new BoardNotFoundException(id));
        
        // 더티 체킹: 엔티티 필드만 변경하면 자동으로 UPDATE 쿼리 실행!
        board.update(dto.getBoardTitle(), dto.getBoardCn());
        
        // save() 호출 불필요!
        return board;
    }
    */
	@Transactional
	@Override
	public Board updateBoard(String id, BoardUpdateDto dto) {
		
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new BoardNotFoundException(id));
		
		board.update(dto.getBoardTitle() , dto.getBoardCn());
		
		return board;
	}
    
    // TODO [4단계] 게시글 삭제 메서드 구현하기
    //  - @Transactional 추가
    //  - 존재 여부 확인 후 삭제
    //  💡 실습: 아래 주석을 해제하고 완성하세요
    /*
    @Transactional
    @Override
    public void deleteBoard(String id) {
        if (!boardRepository.existsById(id)) {
            throw new BoardNotFoundException(id);
        }
        boardRepository.deleteById(id);
    }
    */
	@Transactional
	@Override
	public void deleteBoard(String id) {
		if(!boardRepository.existsById(id)){
			throw new BoardNotFoundException(id);
		}
//		Board board = boardRepository.findById(id)
//				.orElseThrow(() -> new BoardNotFoundException(id));
		
		boardRepository.deleteById(id);
	}
    
    // TODO [7단계] 페이징 처리 메서드 구현하기
    //  - Pageable 파라미터 받기
    //  - Page<Board>를 Page<BoardResponseDto>로 변환
    //  - Page.map() 메서드 활용
    //  💡 실습: 아래 주석을 해제하고 완성하세요
    
    @Override
    public Page<BoardResponseDto> findAllWithPaging(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);
        return boardPage.map(BoardResponseDto::from);
    }
    
    // TODO [7단계] 제목 검색 메서드 구현하기
    //  - Repository의 쿼리 메서드 호출
    //  - 페이징과 함께 검색 결과 반환
    //  💡 실습: 아래 주석을 해제하고 완성하세요
    
    @Transactional
    @Override
    public Page<BoardResponseDto> searchByTitle(String keyword, Pageable pageable) {
        Page<Board> boardPage = boardRepository.findByBoardTitleContaining(keyword, pageable);
        return boardPage.map(BoardResponseDto::from);
    }
    
    // TODO [7단계] 검색 조건이 복잡한 경우 (심화)
    //  - 여러 필드를 동시에 검색 (제목 OR 내용)
    //  - Specification 또는 Querydsl 사용 고려
    //  💡 실습: 제목 또는 내용에서 검색하는 메서드 작성
    public List<Board> searchByKeyword(String keyword){
    	
    	return boardRepository.searchByKeyword(keyword);
    }
    // TODO [5단계] 공통 로직 추출하기 (리팩토링)
    //  - findById를 여러 메서드에서 반복 사용
    //  - private 메서드로 추출하여 코드 중복 제거
    //  💡 실습:
    /*
    private Board findBoardById(String id) {
        return boardRepository.findById(id)
            .orElseThrow(() -> new BoardNotFoundException(id));
    }
    */
    
    // TODO [6단계] 연관관계 조회 최적화하기
    //  - 게시글과 댓글을 함께 조회할 때 N+1 문제 발생 가능
    //  - fetch join 또는 @EntityGraph 사용
    //  💡 학습 포인트: 지연 로딩(LAZY)과 N+1 문제
}
