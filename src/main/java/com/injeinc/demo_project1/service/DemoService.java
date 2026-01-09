package com.injeinc.demo_project1.service;

import java.util.List;

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
	
	// TODO [3단계] 게시글 작성 메서드 추가하기
	//  - Board createBoard(BoardRequestDto dto); 메서드 선언
	//  - DTO를 받아서 엔티티로 변환 후 저장
	//  💡 학습 포인트: DTO와 Entity를 분리하는 이유
	
	// TODO [4단계] 게시글 수정 메서드 추가하기
	//  - Board updateBoard(String id, BoardUpdateDto dto); 메서드 선언
	//  - 더티 체킹(Dirty Checking)을 활용한 수정
	//  💡 핵심: @Transactional 안에서 엔티티 필드만 변경하면 자동 UPDATE
	
	// TODO [4단계] 게시글 삭제 메서드 추가하기
	//  - void deleteBoard(String id); 메서드 선언
	//  - Repository의 deleteById() 호출
	//  💡 고민: 물리 삭제 vs 논리 삭제(소프트 삭제)
}
