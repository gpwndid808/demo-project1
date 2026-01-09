package com.injeinc.demo_project1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.service.DemoService;

// TODO [1단계] @RestController 어노테이션 이해하기
//  - @Controller + @ResponseBody의 조합입니다.
//  - 모든 메서드가 JSON 형태로 데이터를 반환합니다.
//  💡 학습 포인트: REST API를 만들 때 사용하는 핵심 어노테이션입니다.

// TODO [1단계] 첫 번째 API 만들기
//  - @GetMapping("/hello") 어노테이션을 사용하여 새로운 메서드 작성
//  - "Hello, Spring Boot!" 문자열을 반환하는 메서드 구현
//  - 브라우저에서 http://localhost:8080/hello 로 테스트
//  💡 힌트: public String hello() { return "Hello, Spring Boot!"; }

@RestController
public class DemoController {

    // TODO [3단계] 의존성 주입(DI) 이해하기
    //  - 생성자를 통한 의존성 주입 방식입니다.
    //  - Spring이 DemoService 구현체를 자동으로 주입합니다.
    //  💡 학습 포인트: @Autowired 없이도 생성자가 하나면 자동 주입됩니다.
    //  💡 추후 학습: Lombok의 @RequiredArgsConstructor로 간소화 가능
    private final DemoService demoservice;
    
    public DemoController(DemoService demoservice) {
        this.demoservice = demoservice;
    }

    // TODO [2단계] 기본 조회 API 분석하기
    //  - @GetMapping: HTTP GET 요청을 처리하는 어노테이션
    //  - "/list" 경로로 요청 시 모든 게시글 목록을 반환
    //  💡 테스트: Postman이나 브라우저에서 http://localhost:8080/list 호출
    @GetMapping("/list")
    public List<Board> retvLstBoard() {
        return demoservice.retvLstBoard();
    }
    
    // TODO [3단계] 단건 조회 API 구현하기
    //  - @GetMapping("/boards/{id}") 형태로 경로 변수 사용
    //  - @PathVariable 어노테이션으로 id 값 받기
    //  - Service에서 findById()를 호출하여 특정 게시글 조회
    //  💡 예시: GET /boards/1 -> id가 1인 게시글 반환
    
    // TODO [3단계] 게시글 작성 API 구현하기
    //  - @PostMapping("/boards") 어노테이션 사용
    //  - @RequestBody로 BoardRequestDto 객체 받기
    //  - Service의 createBoard() 메서드 호출
    //  - 생성된 Board 엔티티 또는 BoardResponseDto 반환
    //  💡 학습 포인트: POST는 데이터를 생성할 때 사용하는 HTTP 메서드입니다.
    
    // TODO [4단계] 게시글 수정 API 구현하기
    //  - @PutMapping("/boards/{id}") 또는 @PatchMapping 사용
    //  - @PathVariable로 id, @RequestBody로 수정할 내용 받기
    //  - Service의 updateBoard() 메서드 호출
    //  💡 학습 포인트: 더티 체킹(Dirty Checking)으로 자동 업데이트
    
    // TODO [4단계] 게시글 삭제 API 구현하기
    //  - @DeleteMapping("/boards/{id}") 사용
    //  - Service의 deleteBoard() 메서드 호출
    //  - 삭제 성공 시 적절한 응답 반환 (예: 204 No Content)
    //  💡 학습 포인트: RESTful API의 HTTP 메서드 규칙
    
    // TODO [5단계] 입력값 검증 추가하기
    //  - @Valid 어노테이션을 @RequestBody 앞에 추가
    //  - BindingResult 또는 전역 예외 처리로 검증 오류 처리
    //  💡 예시: public ResponseDto createBoard(@Valid @RequestBody BoardRequestDto dto)
    
    // TODO [7단계] 페이징 처리된 목록 조회 API
    //  - @GetMapping("/boards") 생성
    //  - Pageable 파라미터 추가
    //  - Page<Board> 반환하여 페이지 정보 포함
    //  💡 테스트: GET /boards?page=0&size=10&sort=rgstrDt,desc
    
    // TODO [7단계] 제목 검색 API 구현하기
    //  - @GetMapping("/boards/search") 생성
    //  - @RequestParam으로 검색 키워드 받기
    //  - Repository의 쿼리 메서드 활용
    //  💡 예시: GET /boards/search?title=스프링
}
