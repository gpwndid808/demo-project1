package com.injeinc.demo_project1.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.injeinc.demo_project1.dto.BoardRequestDto;
import com.injeinc.demo_project1.dto.BoardResponseDto;
import com.injeinc.demo_project1.dto.BoardUpdateDto;
import com.injeinc.demo_project1.entity.Board;

// TODO [3단계] Service 인터페이스의 역할 이해하기
//  - 비즈니스 로직을 정의하는 계층입니다.
//  - Controller와 Repository 사이에서 중간 역할을 합니다.
//  💡 학습 포인트: 인터페이스를 사용하면 구현체를 쉽게 교체할 수 있습니다.

public interface DemoService {
	
	// TODO [3단계] 기본 조회 메서드
	//  - 전체 게시글 목록을 조회하는 메서드
	public List<Board> retvLstBoard();

	// TODO [3단계] 단건 조회 메서드 추가하기
	//  - Board findById(String id); 메서드 선언
	//  - 특정 ID의 게시글을 조회하는 메서드
	//  💡 고민: 없는 ID 조회 시 어떻게 처리할까? (Optional, Exception)
	public Board findById(String id);
	
	// TODO [3단계] 게시글 작성 메서드 추가하기
	//  - Board createBoard(BoardRequestDto dto); 메서드 선언
	//  - DTO를 받아서 엔티티로 변환 후 저장
	//  💡 학습 포인트: DTO와 Entity를 분리하는 이유
	public Board createBoard(BoardRequestDto request);
	
	// TODO [4단계] 게시글 수정 메서드 추가하기
	//  - Board updateBoard(String id, BoardUpdateDto dto); 메서드 선언
	//  - 더티 체킹(Dirty Checking)을 활용한 수정
	//  💡 핵심: @Transactional 안에서 엔티티 필드만 변경하면 자동 UPDATE
	public Board updateBoard(String id, BoardUpdateDto update);
	
	// TODO [4단계] 게시글 삭제 메서드 추가하기
	//  - void deleteBoard(String id); 메서드 선언
	//  - Repository의 deleteById() 호출
	//  💡 고민: 물리 삭제 vs 논리 삭제(소프트 삭제)
	public void deleteBoard(String id);
	
	// TODO [5단계] 예외 처리 적용하기
	//  - 게시글이 없을 때 커스텀 예외 던지기
	//  - RuntimeException 대신 BoardNotFoundException 사용
	//  💡 실습: findById 메서드에서 예외 처리
	//  Board board = boardRepository.findById(id)
	//      .orElseThrow(() -> new BoardNotFoundException(id));
	
	// TODO [7단계] 페이징 처리 메서드 추가하기
	//  - Page<BoardResponseDto> findAllWithPaging(Pageable pageable);
	//  - Repository의 findAll(Pageable)을 호출
	//  - Page<Board>를 Page<BoardResponseDto>로 변환
	//  💡 실습: Page.map() 메서드 활용
	//  return boardRepository.findAll(pageable).map(BoardResponseDto::from);
	public Page<BoardResponseDto> findAllWithPaging(Pageable pageable);
	
	// TODO [7단계] 검색 메서드 추가하기
	//  - Page<BoardResponseDto> searchByTitle(String keyword, Pageable pageable);
	//  - Repository의 쿼리 메서드 호출
	//  - 결과를 DTO로 변환하여 반환
	//  💡 학습 포인트: Entity를 DTO로 변환하는 이유
	public Page<BoardResponseDto> searchByTitle(String keyword, Pageable pageable);
	
	public List<Board> searchByKeyword(String keyword);
	
	// TODO [7단계] Page.map() 이해하기
	//  - Stream의 map과 유사한 기능
	//  - Page<Board> → Page<BoardResponseDto> 변환
	//  - 페이징 정보는 유지하면서 내용만 변환
	//  💡 예시: page.map(board -> new BoardResponseDto(board))
	//  💡 메서드 레퍼런스: page.map(BoardResponseDto::from)
	
	// TODO [6단계] 댓글 관련 메서드 추가하기 (선택사항)
	//  - 댓글 기능은 별도의 CommentService로 분리 권장
	//  - 또는 BoardService에 댓글 관련 메서드 추가
	//  💡 고민: Service를 기능별로 나눌까, 도메인별로 나눌까?
}
