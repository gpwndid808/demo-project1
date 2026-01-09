# 단계별 학습 가이드

각 단계별 상세 안내

---

## 1단계: Hello Spring Boot

### 목표
- Spring Boot 프로젝트 구조 파악
- Lombok 설정 및 첫 API 작성

### 체크포인트
1. **프로젝트 실행 확인**
   ```bash
   ./gradlew bootRun
   ```
   - 콘솔에서 "Started DemoProject1Application" 메시지 확인

2. **Lombok 플러그인 설치**
   - IntelliJ: Settings > Plugins > Lombok 검색 후 설치
   - Eclipse: https://projectlombok.org/setup/eclipse
   - VS Code: Extension에서 Lombok 검색

3. **첫 API 만들기**
   - `DemoController.java` 열기
   - 아래 메서드 추가:
   ```java
   @GetMapping("/hello")
   public String hello() {
       return "Hello, Spring Boot!";
   }
   ```
   - 실행 후 브라우저에서 http://localhost:8080/hello 접속
   - "Hello, Spring Boot!" 메시지 확인

4. **Lombok 적용 연습**
   - `Board.java`에서 getter 메서드들을 `@Getter`로 대체
   - `@Builder`, `@NoArgsConstructor` 추가해보기

### 학습 자료
- [Spring Boot 시작하기](https://spring.io/quickstart)
- [Lombok 공식 문서](https://projectlombok.org/)

---

## 2단계: JPA와 DB 연동

### 목표
- Entity, Repository 개념 이해
- 데이터베이스 연결 및 테이블 자동 생성

### 체크포인트
1. **데이터베이스 준비**
   
   **옵션 A: MySQL 사용**
   ```sql
   CREATE DATABASE test_board;
   ```
   - `application.properties`에서 접속 정보 확인

   **옵션 B: H2 사용 (추천 - 간편함)**
   - `application.properties` 수정:
   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driver-class-name=org.h2.Driver
   spring.h2.console.enabled=true
   ```
   - `build.gradle`에 의존성 추가:
   ```gradle
   runtimeOnly 'com.h2database:h2'
   ```

2. **Entity 확인**
   - `Board.java` 열기
   - `@Entity`, `@Id`, `@GeneratedValue` 어노테이션 확인
   - 필드와 DB 컬럼의 매핑 이해

3. **애플리케이션 실행 후 테이블 생성 확인**
   - 콘솔에서 Hibernate DDL 로그 확인:
   ```
   Hibernate: create table board (...)
   ```

4. **H2 Console 접속 (H2 사용 시)**
   - http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:testdb
   - board 테이블 확인: `SELECT * FROM board;`

5. **Repository 이해**
   - `BoardRepository.java` 열기
   - `JpaRepository` 상속 확인
   - 자동으로 제공되는 메서드들 파악

### 실습 과제
- `boardId` 타입을 `String`에서 `Long`으로 변경해보기
- `@Column` 어노테이션 사용해보기
  ```java
  @Column(name = "title", nullable = false, length = 100)
  private String boardTitle;
  ```

### 학습 자료
- [JPA 기본 개념](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Hibernate DDL 옵션](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql)

---

## 3단계: Create & Read 구현

### 목표
- DTO 작성 및 Entity와 분리
- POST, GET API 구현
- Service 계층 이해

### 체크포인트
1. **DTO 클래스 확인**
   - `dto/BoardRequestDto.java` 확인
   - `dto/BoardResponseDto.java` 확인
   - DTO와 Entity의 차이점 이해

2. **Service 메서드 구현**
   - `DemoServiceImpl.java` 열기
   - 단건 조회 메서드 추가:
   ```java
   @Override
   public BoardResponseDto findById(String id) {
       Board board = boardRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다: " + id));
       return new BoardResponseDto(board);
   }
   ```

3. **게시글 작성 메서드 구현**
   ```java
   @Transactional
   @Override
   public BoardResponseDto createBoard(BoardRequestDto dto) {
       Board board = dto.toEntity();
       Board savedBoard = boardRepository.save(board);
       return new BoardResponseDto(savedBoard);
   }
   ```

4. **Controller에 API 추가**
   - `DemoController.java` 열기
   - POST API 추가:
   ```java
   @PostMapping("/api/boards")
   public BoardResponseDto createBoard(@RequestBody BoardRequestDto dto) {
       return demoService.createBoard(dto);
   }
   ```
   
   - 단건 조회 API 추가:
   ```java
   @GetMapping("/api/boards/{id}")
   public BoardResponseDto getBoard(@PathVariable String id) {
       return demoService.findById(id);
   }
   ```

5. **Postman으로 테스트**
   
   **POST 요청**
   - URL: http://localhost:8080/api/boards
   - Method: POST
   - Body (JSON):
   ```json
   {
       "boardTitle": "첫 번째 게시글",
       "boardCn": "안녕하세요!",
       "rgstrUsrId": "user001"
   }
   ```

   **GET 요청**
   - URL: http://localhost:8080/api/boards/1
   - Method: GET

### 실습 과제
- 전체 조회 API를 DTO로 변환하여 반환하도록 수정
- `toEntity()`, `from()` 메서드 구현해보기

### 학습 자료
- [DTO vs Entity](https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application)
- [@RequestBody와 JSON](https://spring.io/guides/gs/rest-service/)

---

## 4단계: Update & Delete 구현

### 목표
- 더티 체킹(Dirty Checking) 이해
- PUT, DELETE API 구현
- @Transactional 활용

### 체크포인트
1. **Entity에 수정 메서드 추가**
   - `Board.java` 열기
   - update 메서드 추가:
   ```java
   public void update(String title, String content) {
       this.boardTitle = title;
       this.boardCn = content;
   }
   ```

2. **Service에 수정 메서드 구현**
   ```java
   @Transactional
   @Override
   public BoardResponseDto updateBoard(String id, BoardUpdateDto dto) {
       Board board = boardRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다: " + id));
       
       // 더티 체킹: 엔티티 필드만 변경하면 자동으로 UPDATE 쿼리 실행!
       board.update(dto.getBoardTitle(), dto.getBoardCn());
       
       // save() 호출 불필요!
       return new BoardResponseDto(board);
   }
   ```

3. **더티 체킹 확인**
   - 애플리케이션 실행 후 수정 API 호출
   - 콘솔에서 UPDATE 쿼리 자동 실행 확인
   - `save()` 메서드를 호출하지 않았는데도 업데이트됨!

4. **Controller에 수정 API 추가**
   ```java
   @PutMapping("/api/boards/{id}")
   public BoardResponseDto updateBoard(
           @PathVariable String id,
           @RequestBody BoardUpdateDto dto) {
       return demoService.updateBoard(id, dto);
   }
   ```

5. **Service에 삭제 메서드 구현**
   ```java
   @Transactional
   @Override
   public void deleteBoard(String id) {
       if (!boardRepository.existsById(id)) {
           throw new RuntimeException("게시글을 찾을 수 없습니다: " + id);
       }
       boardRepository.deleteById(id);
   }
   ```

6. **Controller에 삭제 API 추가**
   ```java
   @DeleteMapping("/api/boards/{id}")
   public void deleteBoard(@PathVariable String id) {
       demoService.deleteBoard(id);
   }
   ```

7. **Postman으로 테스트**
   
   **PUT 요청**
   - URL: http://localhost:8080/api/boards/1
   - Method: PUT
   - Body:
   ```json
   {
       "boardTitle": "수정된 제목",
       "boardCn": "수정된 내용"
   }
   ```

   **DELETE 요청**
   - URL: http://localhost:8080/api/boards/1
   - Method: DELETE

### 실습 과제
- `@Transactional(readOnly = false)`를 명시적으로 작성해보기
- 트랜잭션 없이 update 메서드를 실행하면 어떻게 되는지 테스트
- 수정일시(mdfcnDt)를 자동으로 갱신하도록 개선

### 핵심 개념: 더티 체킹
```
1. @Transactional 메서드 시작 → 트랜잭션 시작
2. findById()로 엔티티 조회 → 영속성 컨텍스트에 저장
3. 엔티티 필드 변경 (update 메서드 호출)
4. 메서드 종료 → 트랜잭션 커밋 직전
5. JPA가 영속성 컨텍스트의 엔티티 변경사항 감지 (더티 체킹)
6. 자동으로 UPDATE 쿼리 실행!
```

### 학습 자료
- [JPA 영속성 컨텍스트](https://www.baeldung.com/jpa-entity-lifecycle-events)
- [더티 체킹 원리](https://www.baeldung.com/spring-data-jpa-save-saveandflush)
- [@Transactional 이해](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction)

---

## 5단계: 예외 처리와 유효성 검사

### 목표
- Bean Validation으로 입력값 검증
- 전역 예외 처리 (Global Exception Handler)
- 공통 응답 포맷 적용

### 체크포인트
1. **Validation 의존성 추가**
   - `build.gradle` 수정:
   ```gradle
   implementation 'org.springframework.boot:spring-boot-starter-validation'
   ```
   - Gradle 새로고침

2. **DTO에 Validation 어노테이션 추가**
   - `BoardRequestDto.java` 수정:
   ```java
   import jakarta.validation.constraints.*;
   
   public class BoardRequestDto {
       @NotBlank(message = "제목은 필수입니다.")
       @Size(min = 1, max = 100, message = "제목은 1~100자 이내여야 합니다.")
       private String boardTitle;
       
       @NotBlank(message = "내용은 필수입니다.")
       private String boardCn;
       
       // ...
   }
   ```

3. **Controller에 @Valid 추가**
   ```java
   @PostMapping("/api/boards")
   public BoardResponseDto createBoard(@Valid @RequestBody BoardRequestDto dto) {
       return demoService.createBoard(dto);
   }
   ```

4. **커스텀 예외 클래스 생성**
   - `exception/BoardNotFoundException.java`:
   ```java
   public class BoardNotFoundException extends RuntimeException {
       public BoardNotFoundException(String id) {
           super("게시글을 찾을 수 없습니다: " + id);
       }
   }
   ```

5. **Global Exception Handler 생성**
   - `exception/GlobalExceptionHandler.java`:
   ```java
   @RestControllerAdvice
   public class GlobalExceptionHandler {
       
       @ExceptionHandler(BoardNotFoundException.class)
       public ResponseEntity<ErrorResponse> handleBoardNotFound(BoardNotFoundException e) {
           ErrorResponse response = new ErrorResponse("NOT_FOUND", e.getMessage());
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }
       
       @ExceptionHandler(MethodArgumentNotValidException.class)
       public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
           String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
           ErrorResponse response = new ErrorResponse("VALIDATION_ERROR", message);
           return ResponseEntity.badRequest().body(response);
       }
   }
   ```

6. **공통 응답 DTO 생성**
   - `dto/ErrorResponse.java`:
   ```java
   @Getter
   @AllArgsConstructor
   public class ErrorResponse {
       private String code;
       private String message;
   }
   ```

7. **테스트**
   - 빈 제목으로 POST 요청 → 400 Bad Request
   - 없는 ID로 조회 → 404 Not Found
   - 에러 메시지 확인

### 실습 과제
- `@Min`, `@Max`, `@Email` 등 다른 Validation 어노테이션 사용해보기
- 성공 응답도 공통 포맷으로 감싸기
- 타임스탬프를 포함한 에러 응답 만들기

### 학습 자료
- [Bean Validation](https://beanvalidation.org/)
- [@ControllerAdvice](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)

---

## 6단계: JPA 연관관계 매핑 (1:N)

### 목표
- Comment(댓글) 엔티티 추가
- 게시글과 댓글의 1:N 관계 설정
- 연관관계 주인 개념 이해

### 체크포인트
1. **Comment 엔티티 생성**
   - `entity/Comment.java`:
   ```java
   @Entity
   @Getter
   @NoArgsConstructor(access = AccessLevel.PROTECTED)
   public class Comment {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       
       private String content;
       private String writer;
       
       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "board_id")
       private Board board;
       
       @Builder
       public Comment(String content, String writer, Board board) {
           this.content = content;
           this.writer = writer;
           this.board = board;
       }
   }
   ```

2. **Board 엔티티에 양방향 관계 추가**
   ```java
   @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Comment> comments = new ArrayList<>();
   
   // 연관관계 편의 메서드
   public void addComment(Comment comment) {
       comments.add(comment);
   }
   ```

3. **CommentRepository 생성**
   ```java
   public interface CommentRepository extends JpaRepository<Comment, Long> {
       List<Comment> findByBoardId(String boardId);
   }
   ```

4. **CommentService 및 Controller 구현**
   - 댓글 작성 API: `POST /api/boards/{boardId}/comments`
   - 댓글 조회 API: `GET /api/boards/{boardId}/comments`

5. **N+1 문제 확인 및 해결**
   - 게시글 목록 조회 시 댓글도 함께 조회하면?
   - 콘솔에서 SELECT 쿼리가 여러 번 실행되는지 확인
   - 해결: `@EntityGraph` 또는 `fetch join` 사용

### 실습 과제
- 양방향 연관관계와 단방향 연관관계의 차이 비교
- `cascade` 옵션 실험 (ALL, PERSIST, REMOVE)
- `orphanRemoval = true` 동작 확인

### 핵심 개념
- **연관관계 주인**: 외래키를 관리하는 쪽 (@ManyToOne 쪽)
- **mappedBy**: 주인이 아닌 쪽에 표시
- **FetchType.LAZY**: 지연 로딩 (성능 최적화)

### 학습 자료
- [JPA 연관관계](https://www.baeldung.com/jpa-one-to-many)
- [N+1 문제 해결](https://www.baeldung.com/jpa-eager-vs-lazy-loading)

---

## 7단계: 페이징과 쿼리 메서드

### 목표
- 페이징 처리로 대량 데이터 효율적으로 조회
- JPA Query Method 활용
- 검색 기능 구현

### 체크포인트
1. **Repository에 쿼리 메서드 추가**
   - `BoardRepository.java`:
   ```java
   // 제목으로 검색
   List<Board> findByBoardTitleContaining(String keyword);
   
   // 제목으로 검색 + 페이징
   Page<Board> findByBoardTitleContaining(String keyword, Pageable pageable);
   
   // 전체 조회 + 페이징
   Page<Board> findAll(Pageable pageable);
   ```

2. **Service에 페이징 메서드 구현**
   ```java
   public Page<BoardResponseDto> findAllWithPaging(Pageable pageable) {
       Page<Board> boardPage = boardRepository.findAll(pageable);
       return boardPage.map(BoardResponseDto::from);
   }
   ```

3. **Controller에 페이징 API 추가**
   ```java
   @GetMapping("/api/boards")
   public Page<BoardResponseDto> getBoards(
           @PageableDefault(size = 10, sort = "rgstrDt", direction = Sort.Direction.DESC) 
           Pageable pageable) {
       return boardService.findAllWithPaging(pageable);
   }
   ```

4. **검색 API 구현**
   ```java
   @GetMapping("/api/boards/search")
   public Page<BoardResponseDto> searchBoards(
           @RequestParam String keyword,
           Pageable pageable) {
       return boardService.searchByTitle(keyword, pageable);
   }
   ```

5. **Postman으로 테스트**
   - `GET /api/boards?page=0&size=5&sort=rgstrDt,desc`
   - `GET /api/boards/search?keyword=스프링&page=0&size=10`
   
   응답 예시:
   ```json
   {
       "content": [...],
       "pageable": {...},
       "totalElements": 100,
       "totalPages": 10,
       "number": 0,
       "size": 10
   }
   ```

6. **쿼리 메서드 네이밍 규칙 학습**
   - `findBy` + 필드명 + 키워드
   - `Containing`, `StartingWith`, `EndingWith`
   - `And`, `Or`
   - `OrderBy` + 필드명 + `Asc/Desc`

### 실습 과제
- 작성자로 검색하는 메서드 추가
- 제목과 내용 모두 검색하는 메서드 (OR 조건)
- `@Query` 어노테이션으로 JPQL 작성해보기

### 학습 자료
- [Spring Data JPA Query Methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
- [Pagination](https://www.baeldung.com/spring-data-jpa-pagination-sorting)

---

## 8단계: JPA Auditing & 리팩토링

### 목표
- 생성/수정 시간 자동 관리
- 공통 필드 추상화
- 코드 중복 제거

### 체크포인트
1. **JPA Auditing 활성화**
   - `DemoProject1Application.java`:
   ```java
   @EnableJpaAuditing
   @SpringBootApplication
   public class DemoProject1Application {
       // ...
   }
   ```

2. **BaseTimeEntity 생성**
   - `entity/BaseTimeEntity.java`:
   ```java
   @MappedSuperclass
   @EntityListeners(AuditingEntityListener.class)
   @Getter
   public abstract class BaseTimeEntity {
       
       @CreatedDate
       @Column(updatable = false)
       private LocalDateTime createdDate;
       
       @LastModifiedDate
       private LocalDateTime modifiedDate;
   }
   ```

3. **Board와 Comment가 상속받도록 수정**
   ```java
   @Entity
   public class Board extends BaseTimeEntity {
       // rgstrDt, mdfcnDt 필드 제거
       // ...
   }
   ```

4. **테스트**
   - 게시글 생성 → createdDate 자동 설정 확인
   - 게시글 수정 → modifiedDate 자동 갱신 확인
   - DB에서 실제 값 확인

5. **리팩토링 포인트 찾기**
   - 중복되는 예외 처리 코드
   - 반복되는 DTO 변환 로직
   - Service에서 Repository 직접 호출하는 패턴
   
6. **공통 메서드 추출**
   ```java
   // Service에 공통 메서드
   private Board findBoardById(String id) {
       return boardRepository.findById(id)
           .orElseThrow(() -> new BoardNotFoundException(id));
   }
   ```

### 실습 과제
- 작성자/수정자 정보도 자동 관리 (@CreatedBy, @LastModifiedBy)
- AuditorAware 구현하여 현재 사용자 ID 자동 주입
- 삭제 여부 필드 추가 (소프트 삭제)

### 학습 자료
- [JPA Auditing](https://www.baeldung.com/database-auditing-jpa)
- [MappedSuperclass](https://www.baeldung.com/jpa-entities#mappedsuperclass)

---

## 9단계: 테스트 코드 작성 (기초)

### 목표
- Repository 계층 테스트
- Service 계층 단위 테스트
- given-when-then 패턴 이해

### 체크포인트
1. **Repository 테스트 생성**
   - `test/.../repository/BoardRepositoryTest.java`:
   ```java
   @DataJpaTest
   class BoardRepositoryTest {
       
       @Autowired
       private BoardRepository boardRepository;
       
       @Test
       @DisplayName("게시글 저장 테스트")
       void saveBoard() {
           // given
           Board board = Board.builder()
               .boardTitle("테스트 제목")
               .boardCn("테스트 내용")
               .build();
           
           // when
           Board savedBoard = boardRepository.save(board);
           
           // then
           assertThat(savedBoard.getBoardId()).isNotNull();
           assertThat(savedBoard.getBoardTitle()).isEqualTo("테스트 제목");
       }
       
       @Test
       @DisplayName("제목으로 검색 테스트")
       void findByTitleContaining() {
           // given
           boardRepository.save(Board.builder()
               .boardTitle("스프링 부트 학습")
               .boardCn("내용")
               .build());
           
           // when
           List<Board> result = boardRepository.findByBoardTitleContaining("스프링");
           
           // then
           assertThat(result).hasSize(1);
           assertThat(result.get(0).getBoardTitle()).contains("스프링");
       }
   }
   ```

2. **Service 테스트 생성**
   - `test/.../service/BoardServiceTest.java`:
   ```java
   @SpringBootTest
   @Transactional
   class BoardServiceTest {
       
       @Autowired
       private BoardService boardService;
       
       @Test
       @DisplayName("게시글 작성 테스트")
       void createBoard() {
           // given
           BoardRequestDto dto = new BoardRequestDto(
               "테스트 제목", "테스트 내용", "user001"
           );
           
           // when
           BoardResponseDto result = boardService.createBoard(dto);
           
           // then
           assertThat(result).isNotNull();
           assertThat(result.getBoardTitle()).isEqualTo("테스트 제목");
       }
       
       @Test
       @DisplayName("존재하지 않는 게시글 조회 시 예외 발생")
       void findByIdNotFound() {
           // given
           String notExistId = "999";
           
           // when & then
           assertThatThrownBy(() -> boardService.findById(notExistId))
               .isInstanceOf(BoardNotFoundException.class);
       }
   }
   ```

3. **테스트 실행**
   - IDE에서 테스트 클래스 우클릭 → Run Test
   - 또는 `./gradlew test` 명령 실행
   - 모든 테스트가 통과하는지 확인

4. **테스트 격리 확인**
   - 각 테스트는 독립적으로 실행되어야 함
   - `@Transactional`로 테스트 후 롤백
   - 테스트 순서에 관계없이 성공해야 함

### 실습 과제
- 수정/삭제 테스트 작성
- MockMvc를 사용한 Controller 테스트 작성
- 페이징 테스트 작성

### 핵심 개념
- **@DataJpaTest**: JPA 관련 컴포넌트만 로드 (가벼운 테스트)
- **@SpringBootTest**: 전체 애플리케이션 컨텍스트 로드
- **given-when-then**: 테스트 구조화 패턴

### 학습 자료
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [AssertJ](https://assertj.github.io/doc/)

---

## 10단계: 문서화 및 마무리

### 목표
- Swagger UI로 API 문서 자동 생성
- JAR 빌드 및 배포 준비
- 프로젝트 정리

### 체크포인트
1. **SpringDoc 의존성 추가**
   - `build.gradle`:
   ```gradle
   implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0'
   ```

2. **Swagger 설정 (선택사항)**
   - `config/SwaggerConfig.java`:
   ```java
   @Configuration
   public class SwaggerConfig {
       @Bean
       public OpenAPI openAPI() {
           return new OpenAPI()
               .info(new Info()
                   .title("게시판 API")
                   .version("v1.0")
                   .description("Spring Boot 학습용 게시판 API 문서"));
       }
   }
   ```

3. **Controller에 API 설명 추가**
   ```java
   @Tag(name = "게시판", description = "게시판 API")
   @RestController
   public class BoardController {
       
       @Operation(summary = "게시글 목록 조회", description = "페이징 처리된 게시글 목록을 조회합니다")
       @ApiResponses({
           @ApiResponse(responseCode = "200", description = "조회 성공"),
           @ApiResponse(responseCode = "400", description = "잘못된 요청")
       })
       @GetMapping("/api/boards")
       public Page<BoardResponseDto> getBoards(Pageable pageable) {
           // ...
       }
   }
   ```

4. **Swagger UI 접속**
   - 애플리케이션 실행
   - 브라우저에서 http://localhost:8080/swagger-ui.html 접속
   - 모든 API 엔드포인트 확인
   - Swagger UI에서 직접 API 테스트

5. **JAR 빌드**
   ```bash
   # Windows
   .\gradlew clean build
   
   # Mac/Linux
   ./gradlew clean build
   ```
   
   - `build/libs/` 폴더에 JAR 파일 생성 확인

6. **JAR 실행**
   ```bash
   java -jar build/libs/demo-project1-0.0.1-SNAPSHOT.jar
   ```
   
   - 애플리케이션이 정상적으로 실행되는지 확인

7. **프로파일 설정 (선택사항)**
   - `application-dev.properties` (개발용)
   - `application-prod.properties` (운영용)
   - 실행 시 프로파일 지정:
   ```bash
   java -jar -Dspring.profiles.active=prod app.jar
   ```

8. **README 업데이트**
   - API 엔드포인트 목록 정리
   - 실행 방법 문서화
   - 배운 내용 정리

### 실습 과제
- Docker 이미지 만들기 (Dockerfile 작성)
- 환경변수로 DB 접속 정보 관리
- 로깅 설정 추가 (logback-spring.xml)

### 체크리스트
- [ ] Swagger UI 접근 가능
- [ ] 모든 API가 Swagger에 표시됨
- [ ] JAR 빌드 성공
- [ ] JAR 파일 실행 가능
- [ ] API 문서화 완료

### 학습 자료
- [SpringDoc](https://springdoc.org/)
- [Spring Boot Profiles](https://www.baeldung.com/spring-profiles)

---

## 전체 학습 완료! 🎉

10단계를 모두 완료하셨다면:
- ✅ Spring Boot + JPA를 활용한 REST API 개발
- ✅ CRUD 기본 동작 완벽 이해
- ✅ JPA 핵심 개념 (Entity, Repository, 연관관계, 더티 체킹)
- ✅ 예외 처리 및 입력값 검증
- ✅ 페이징 및 검색 기능
- ✅ 테스트 코드 작성
- ✅ API 문서화

**축하합니다! 이제 실무 프로젝트를 시작할 준비가 되었습니다!** 🚀

---

## 트러블슈팅

### 자주 발생하는 문제

**데이터베이스 연결 오류**
- MySQL 실행 여부 확인
- `application.properties`의 username/password 확인
- 포트 번호 확인 (기본 3306)

**Entity를 JSON으로 직접 반환 시 오류**
- 양방향 연관관계 시 무한 순환 참조 발생 가능
- 해결: DTO를 사용하여 필요한 필드만 반환

**@Transactional 없이 update가 안 돼요**
- 더티 체킹은 @Transactional 내에서만 동작
- 메서드에 @Transactional 추가

**ID를 Long으로 변경했는데 오류가 나요**
- Repository의 제네릭 타입도 변경: `JpaRepository<Board, Long>`
- Controller의 @PathVariable 타입도 변경: `@PathVariable Long id`

---

## 코딩 컨벤션 (권장사항)

### 1. 메서드명 규칙
- 조회: `find`, `get`, `retrieve`
- 생성: `create`, `save`
- 수정: `update`, `modify`
- 삭제: `delete`, `remove`

### 2. 변수명 규칙
- 카멜 케이스: `boardTitle`, `userId`
- 상수는 대문자: `MAX_LENGTH`

### 3. 패키지 구조
```
controller  - API 엔드포인트
service     - 비즈니스 로직
repository  - DB 접근
entity      - JPA 엔티티
dto         - 데이터 전송 객체
exception   - 예외 클래스
```

### 4. 커밋 메시지
```
feat: 새로운 기능 추가
fix: 버그 수정
docs: 문서 수정
refactor: 코드 리팩토링
test: 테스트 코드
```

---

