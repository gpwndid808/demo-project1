package com.injeinc.demo_project1.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//TODO [10단계] Swagger 어노테이션 import하기
//- API 문서화를 위한 어노테이션들
//💡 실습: 아래 import 문의 주석을 해제하세요
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;

import com.injeinc.demo_project1.dto.BoardRequestDto;
import com.injeinc.demo_project1.dto.BoardResponseDto;
import com.injeinc.demo_project1.dto.BoardUpdateDto;
import com.injeinc.demo_project1.dto.CommentRequestDto;
import com.injeinc.demo_project1.dto.CommentResponseDto;
import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.entity.Comment;
import com.injeinc.demo_project1.service.CommentService;
import com.injeinc.demo_project1.service.DemoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// TODO [1단계] @RestController 어노테이션 이해하기
//  - @Controller + @ResponseBody의 조합입니다.
//  - 모든 메서드가 JSON 형태로 데이터를 반환합니다.1단계
//  💡 학습 포인트: REST API를 만들 때 사용하는 핵심 어노테이션입니다.

//TODO [10단계] @Tag로 API 그룹 설명 추가하기
//- Swagger UI에서 컨트롤러 단위로 그룹화
//- API의 전반적인 설명 제공
//💡 실습: 아래 어노테이션의 주석을 해제하세요
//@Tag(name = "게시판 API", description = "게시판 CRUD 관련 API를 제공합니다.")

@RequiredArgsConstructor
@RestController
public class DemoController {
	
	// TODO [1단계] 첫 번째 API 만들기
	//  - @GetMapping("/hello") 어노테이션을 사용하여 새로운 메서드 작성
	//  - "Hello, Spring Boot!" 문자열을 반환하는 메서드 구현
	//  - 브라우저에서 http://localhost:8080/hello 로 테스트
	//  💡 힌트: public String hello() { return "Hello, Spring Boot!"; }
	@GetMapping("/hello")
	public String hello() {
		return "Hello, Spring Boot!";
	}

	// TODO [3단계] 의존성 주입(DI) 이해하기
	// - 생성자를 통한 의존성 주입 방식입니다.
	// - Spring이 DemoService 구현체를 자동으로 주입합니다.
	// 💡 학습 포인트: @Autowired 없이도 생성자가 하나면 자동 주입됩니다.
	// 💡 추후 학습: Lombok의 @RequiredArgsConstructor로 간소화 가능
	private final DemoService demoService;
	private final CommentService commentService;

	// TODO [2단계] 기본 조회 API 분석하기
	// - @GetMapping: HTTP GET 요청을 처리하는 어노테이션
	// - "/list" 경로로 요청 시 모든 게시글 목록을 반환
	// 💡 테스트: Postman이나 브라우저에서 http://localhost:8080/list 호출
	// 엔티티로 받게되면 JSON을 만드려고 직렬화하는 순간 양방향 연관관계 때문에 무한 루프가 돌면서 오류, 그래서 dto 로 받도록 수정 !!
	
	// TODO [10단계] @Operation으로 API 설명 추가하기
	//  - Swagger UI에서 API의 요약과 상세 설명 표시
	//  - summary: 간단한 요약
	//  - description: 상세 설명
	//  💡 실습: 아래 어노테이션의 주석을 해제하세요
	/*
	@Operation(
		summary = "게시글 목록 조회",
		description = "모든 게시글 목록을 조회합니다. 페이징 없이 전체 목록을 반환합니다."
	)
	*/
	@GetMapping("/list")
	public List<BoardResponseDto> retvLstBoard() {
		List<Board> boards = demoService.retvLstBoard();
		return boards.stream().map(BoardResponseDto::from).toList();
	}

	// TODO [3단계] 단건 조회 API 구현하기
	// - @GetMapping("/boards/{id}") 형태로 경로 변수 사용
	// - @PathVariable 어노테이션으로 id 값 받기
	// - Service에서 findById()를 호출하여 특정 게시글 조회
	// 💡 예시: GET /boards/1 -> id가 1인 게시글 반환
	
	// TODO [10단계] @Parameter로 파라미터 설명 추가하기
	//  - 경로 변수에 대한 설명 제공
	//  - required: 필수 여부
	//  - example: 예시 값
	//  💡 실습: 아래 어노테이션의 주석을 해제하세요
	/*
	@Operation(summary = "게시글 단건 조회", description = "ID로 특정 게시글을 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "조회 성공"),
		@ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
	})
	*/
	@GetMapping("/boards/{id}")
	public BoardResponseDto findById(@PathVariable("id") String id) {
		
		Board board = demoService.findById(id);
		return BoardResponseDto.from(board);
	}
	
	// TODO [3단계] 게시글 작성 API 구현하기
	// - @PostMapping("/boards") 어노테이션 사용
	// - @RequestBody로 BoardRequestDto 객체 받기
	// - Service의 createBoard() 메서드 호출
	// - 생성된 Board 엔티티 또는 BoardResponseDto 반환
	// 💡 학습 포인트: POST는 데이터를 생성할 때 사용하는 HTTP 메서드입니다.
	
	// TODO [10단계] @ApiResponses로 응답 상태 코드 설명 추가하기
	//  - 가능한 HTTP 응답 코드와 설명 제공
	//  - 200: 성공, 400: 잘못된 요청, 404: 찾을 수 없음 등
	//  💡 실습: 아래 어노테이션의 주석을 해제하세요
	/*
	@Operation(summary = "게시글 작성", description = "새로운 게시글을 작성합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "생성 성공",
			content = @Content(schema = @Schema(implementation = Board.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청 (Validation 실패)")
	})
	*/
	
	@PostMapping("/boards")
	public Board regtBoard(@RequestBody BoardRequestDto request) {
		return demoService.createBoard(request);
	}

	// TODO [4단계] 게시글 수정 API 구현하기
	// - @PutMapping("/boards/{id}") 또는 @PatchMapping 사용
	// - @PathVariable로 id, @RequestBody로 수정할 내용 받기
	// - Service의 updateBoard() 메서드 호출
	// 💡 학습 포인트: 더티 체킹(Dirty Checking)으로 자동 업데이트
	@PutMapping("/boards/{id}")
	public BoardResponseDto uptBoard(@PathVariable("id") String id,@Valid @RequestBody BoardUpdateDto update) {
		Board board = demoService.updateBoard(id, update);
		return BoardResponseDto.from(board);
	}

	// TODO [4단계] 게시글 삭제 API 구현하기
	// - @DeleteMapping("/boards/{id}") 사용
	// - Service의 deleteBoard() 메서드 호출
	// - 삭제 성공 시 적절한 응답 반환 (예: 204 No Content)
	// 💡 학습 포인트: RESTful API의 HTTP 메서드 규칙
	@DeleteMapping("/boards/{id}")
	public void delBoard(@PathVariable("id") String id) {
		demoService.deleteBoard(id);
	}

	// TODO [5단계] 입력값 검증 추가하기
	//  - @Valid 어노테이션을 @RequestBody 앞에 추가
	//  - 검증 실패 시 GlobalExceptionHandler가 자동으로 처리
	//  💡 실습: 게시글 작성/수정 API에 @Valid 추가
	//  💡 예시:
//	  @PostMapping("/boards")
//	  public Board createBoard(@Valid @RequestBody BoardRequestDto dto) {
//	      return demoService.createBoard(dto);
//	  }
//	
	// TODO [5단계] 예외 처리 적용 확인하기
	//  - 빈 제목으로 게시글 작성 시도 → 400 Bad Request
	//  - 없는 ID로 조회 시도 → 404 Not Found
	//  - 콘솔에서 에러 로그 확인
	//  💡 실습: Postman으로 잘못된 요청 보내보기

	// TODO [7단계] 페이징 처리된 목록 조회 API 구현하기
	//  - Pageable 파라미터로 페이징 정보 받기
	//  - @PageableDefault로 기본값 설정
	//  - Page<Board> 또는 Page<BoardResponseDto> 반환
	//  💡 실습: 아래 코드를 참고하여 구현하세요
	
	@GetMapping("/api/boards")
	public Page<BoardResponseDto> getBoards(
	    @PageableDefault(size = 10, sort = "rgstrDt", direction = Sort.Direction.DESC) 
	    Pageable pageable) {
	    return demoService.findAllWithPaging(pageable);
	}
	
	// TODO [7단계] Pageable 사용법 이해하기
	//	- import org.springframework.data.domain.Page;
	//  - import org.springframework.data.domain.Pageable;
	//  - import org.springframework.data.domain.Sort;
	//  - import org.springframework.data.web.PageableDefault;
	//  - 클라이언트는 쿼리 파라미터로 페이징 정보 전달
	//  - 예: GET /api/boards?page=0&size=5&sort=boardTitle,asc
	
	// TODO [7단계] Page 응답 구조 이해하기
	//  - content: 실제 데이터 배열
	//  - pageable: 페이징 정보
	//  - totalElements: 전체 데이터 개수
	//  - totalPages: 전체 페이지 수
	//  - number: 현재 페이지 번호 (0부터 시작)
	//  - size: 페이지 크기
	//  - first/last: 첫/마지막 페이지 여부
	//  💡 학습 포인트: 프론트엔드에서 페이지네이션 UI 구현에 필요한 정보

	// TODO [7단계] 제목 검색 API 구현하기
	//  - @RequestParam으로 검색 키워드 받기
	//  - required = false로 선택적 파라미터 설정
	//  💡 실습: 아래 코드를 참고하여 구현하세요
	@GetMapping("/api/boards/search")
	public Page<BoardResponseDto> searchBoards(
	    @RequestParam(name = "keyword", required = false) String keyword, Pageable pageable) {
	    if (keyword == null || keyword.trim().isEmpty()) {
	        return demoService.findAllWithPaging(pageable);
	    }
	    return demoService.searchByTitle(keyword, pageable);
	}
	
	@GetMapping("/api/boards/searchByKeyword")
	public List<BoardResponseDto> searchByKeyword(@RequestParam(name = "keyword") String keyword){
		List<Board> board =  demoService.searchByKeyword(keyword);
		 return board.stream().map(BoardResponseDto::from).toList();
	}
	
	
	// TODO [7단계] @RequestParam 이해하기
	//  - URL 쿼리 파라미터를 메서드 파라미터로 받기
	//  - required: 필수 여부 (기본값 true)
	//  - defaultValue: 값이 없을 때 기본값
	//  - 예: @RequestParam(defaultValue = "0") int page
	
	// TODO [6단계] 댓글 작성 API 구현하기
	//  - 게시글에 댓글을 추가하는 API
	//  - POST /api/boards/{boardId}/comments
	//  💡 실습: 아래 코드를 참고하여 구현하세요
	
	@PostMapping("/api/boards/{boardId}/comments")
	public CommentResponseDto createComment(@PathVariable("boardId") String boardId, @Valid @RequestBody CommentRequestDto dto) {
		Comment comment = commentService.createComment(boardId, dto);
		return CommentResponseDto.from(comment);
	}
	
	
	// TODO [6단계] 게시글의 댓글 목록 조회 API 구현하기
	//  - 특정 게시글의 모든 댓글 조회
	//  - GET /api/boards/{boardId}/comments
	//  💡 실습: 아래 코드를 참고하여 구현하세요
	
	@GetMapping("/api/boards/{boardId}/comments")
	public List<CommentResponseDto> getComments(@PathVariable("boardId") String boardId) {
		List<Comment> comments = commentService.getCommentsByBoardId(boardId);
		return comments.stream().map(CommentResponseDto::from).toList();
	}
	
	@GetMapping("/api/boards/{writer}/commentsByWriter")
	public List<CommentResponseDto> getCommentsByWriter(@PathVariable("writer") String writer){
		
		List<Comment> comments = commentService.getCommentsByWriter(writer);
		return comments.stream().map(CommentResponseDto::from).toList();
	}
	
	@DeleteMapping("/api/boards/comment/{commentNum}")
	public void deleteComment(@PathVariable("commentNum") Long commentNum) {
		
		commentService.deleteComment(commentNum);
	}
	
	// TODO [6단계] RESTful API 설계 원칙 이해하기
	//  - /api/boards/{boardId}/comments: 리소스 계층 구조 표현
	//  - 게시글(boards) 아래에 댓글(comments)이 속함
	//  - URL로 리소스 간의 관계를 표현
	//  💡 학습 포인트: RESTful URL 설계 패턴
	
	// TODO [7단계] 응답 상태 코드 명시하기 (선택사항)
	//  - @ResponseStatus로 HTTP 상태 코드 지정
	//  - ResponseEntity로 더 세밀한 제어
	//  💡 실습:
	//  @PostMapping("/api/boards")
	//  @ResponseStatus(HttpStatus.CREATED)  // 201 Created
	//  public Board createBoard(@Valid @RequestBody BoardRequestDto dto)
	
	// TODO [7단계] ResponseEntity 사용하기 (심화)
	//  - 헤더, 상태 코드를 포함한 세밀한 응답 제어
	//  💡 실습:
	//  public ResponseEntity<Board> createBoard(@Valid @RequestBody BoardRequestDto dto) {
	//      Board board = demoService.createBoard(dto);
	//      return ResponseEntity.status(HttpStatus.CREATED).body(board);
	//  }
	
	// TODO [10단계] Swagger 어노테이션 import하기
	//  - API 문서화를 위한 어노테이션들
	//  💡 실습: 아래 import 문의 주석을 해제하세요
	// import io.swagger.v3.oas.annotations.Operation;
	// import io.swagger.v3.oas.annotations.Parameter;
	// import io.swagger.v3.oas.annotations.responses.ApiResponse;
	// import io.swagger.v3.oas.annotations.responses.ApiResponses;
	// import io.swagger.v3.oas.annotations.tags.Tag;
}
