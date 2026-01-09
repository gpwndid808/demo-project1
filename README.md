# Spring Boot CRUD 실습 프로젝트

Spring Boot + JPA 학습 프로젝트

## 📚 학습 목표

- Spring Boot 프로젝트 구조 이해
- JPA를 활용한 데이터베이스 연동
- RESTful API 설계 및 구현
- CRUD 기본 동작 원리 습득

---

## 🎯 커리큘럼 (10단계)

### 1단계: Hello Spring Boot
**핵심 키워드**: `Project Structure`, `Annotation`, `Controller`

**실습 내용**
1. 프로젝트 생성 (Spring Initializr)
2. Lombok 설정 및 테스트
3. 첫 GET API 호출 ("Hello World")

**체크리스트**
- [ ] Spring Boot 프로젝트 생성 완료
- [ ] Lombok 플러그인 설치 및 동작 확인
- [ ] `GET /hello` API 호출 성공

---

### 2단계: JPA와 DB 연동
**핵심 키워드**: `Entity`, `Repository`, `application.properties`

**실습 내용**
1. Board 엔티티 설계 (id, title, content)
2. Repository 인터페이스 생성
3. application.properties DB 설정

**체크리스트**
- [ ] `Board` 엔티티 클래스 작성
- [ ] `BoardRepository` 인터페이스 생성
- [ ] DB 연결 설정 및 테이블 자동 생성 확인

---

### 3단계: Create & Read 구현
**핵심 키워드**: `DI(의존성 주입)`, `Service 레이어`, `DTO`

**실습 내용**
1. DTO(Data Transfer Object) 분리
2. 글 작성(POST) 구현
3. 전체/단건 조회(GET) 구현

**체크리스트**
- [ ] `BoardRequestDto`, `BoardResponseDto` 생성
- [ ] `BoardService` 생성 및 DI 적용
- [ ] POST /api/boards - 게시글 작성 API
- [ ] GET /api/boards - 전체 조회 API
- [ ] GET /api/boards/{id} - 단건 조회 API

---

### 4단계: Update & Delete 구현
**핵심 키워드**: `@Transactional`, `Dirty Checking`, `REST 메서드`

**실습 내용**
1. 더티 체킹(Dirty Checking)을 이용한 수정
2. 글 삭제 구현
3. 트랜잭션의 이해

**체크리스트**
- [ ] PUT /api/boards/{id} - 게시글 수정 API
- [ ] DELETE /api/boards/{id} - 게시글 삭제 API
- [ ] `@Transactional` 어노테이션 적용
- [ ] 더티 체킹 동작 원리 이해

---

### 5단계: 예외 처리와 유효성 검사
**핵심 키워드**: `@ControllerAdvice`, `@ExceptionHandler`, `Bean Validation`

**실습 내용**
1. @Valid로 입력값 검증 (빈 제목 방지 등)
2. Global Exception Handler 만들기
3. 공통 응답 포맷(ResponseDto) 적용

**체크리스트**
- [ ] `@NotBlank`, `@Size` 등 Validation 어노테이션 적용
- [ ] `GlobalExceptionHandler` 클래스 생성
- [ ] 공통 응답 DTO 설계 (성공/실패 구분)
- [ ] 커스텀 예외 클래스 생성

---

### 6단계: JPA 연관관계 매핑 (1:N)
**핵심 키워드**: `@ManyToOne`, `@OneToMany`, `연관관계 주인`

**실습 내용**
1. Comment(댓글) 엔티티 추가
2. 게시글(1) : 댓글(N) 관계 설정
3. 댓글 작성/조회 API 개발

**체크리스트**
- [ ] `Comment` 엔티티 생성
- [ ] Board-Comment 양방향 연관관계 설정
- [ ] POST /api/boards/{id}/comments - 댓글 작성 API
- [ ] GET /api/boards/{id}/comments - 댓글 조회 API
- [ ] 연관관계 주인 개념 이해

---

### 7단계: 페이징과 쿼리 메서드
**핵심 키워드**: `Page`, `Pageable`, `Query Method`

**실습 내용**
1. 게시글 목록 페이징 처리 (Pageable)
2. 제목 검색 기능 추가
3. JPA Query Method 활용

**체크리스트**
- [ ] GET /api/boards?page=0&size=10 - 페이징 적용
- [ ] `findByTitleContaining` 쿼리 메서드 작성
- [ ] GET /api/boards/search?keyword=검색어 - 검색 API
- [ ] Page 객체 응답 DTO로 변환

---

### 8단계: JPA Auditing & 리팩토링
**핵심 키워드**: `@EnableJpaAuditing`, `@MappedSuperclass`, `상속`

**실습 내용**
1. 생성시간/수정시간 자동화 (BaseTimeEntity)
2. 반복되는 코드 정리 및 구조 개선

**체크리스트**
- [ ] `BaseTimeEntity` 추상 클래스 생성
- [ ] `@CreatedDate`, `@LastModifiedDate` 적용
- [ ] `@EnableJpaAuditing` 설정
- [ ] Board, Comment가 BaseTimeEntity 상속
- [ ] 코드 중복 제거 및 구조 개선

---

### 9단계: 테스트 코드 작성 (기초)
**핵심 키워드**: `JUnit`, `MockMvc`, `@SpringBootTest`

**실습 내용**
1. Service 계층 단위 테스트 작성
2. Repository 테스트

**체크리스트**
- [ ] `BoardServiceTest` 작성
- [ ] `BoardRepositoryTest` 작성
- [ ] given-when-then 패턴 적용
- [ ] 테스트 격리 (@Transactional)

---

### 10단계: 문서화 및 마무리
**핵심 키워드**: `Swagger`, `Profile 설정`, `Build`

**실습 내용**
1. Swagger(SpringDoc) 적용하여 API 문서 자동화
2. Jar 빌드 및 실행

**체크리스트**
- [ ] SpringDoc 의존성 추가
- [ ] Swagger UI 접근 가능 (http://localhost:8080/swagger-ui.html)
- [ ] API 설명 및 예시 추가
- [ ] `./gradlew build` 성공
- [ ] `java -jar build/libs/*.jar` 실행 성공

---

## 🛠️ 기술 스택

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- Gradle
- H2 Database (개발용) / MySQL or PostgreSQL (선택)
- Lombok
- SpringDoc (Swagger)

---

## 📁 프로젝트 구조

```
src/main/java/com/injeinc/demo_project1/
├── DemoProject1Application.java    # 메인 애플리케이션
├── controller/                      # API 엔드포인트
│   └── BoardController.java
├── service/                         # 비즈니스 로직
│   ├── BoardService.java
│   └── BoardServiceImpl.java
├── repository/                      # DB 접근 계층
│   └── BoardRepository.java
├── entity/                          # JPA 엔티티
│   ├── Board.java
│   ├── Comment.java
│   └── BaseTimeEntity.java
├── dto/                            # 데이터 전송 객체
│   ├── request/
│   │   ├── BoardCreateRequest.java
│   │   └── BoardUpdateRequest.java
│   └── response/
│       ├── BoardResponse.java
│       └── CommonResponse.java
└── exception/                      # 예외 처리
    ├── GlobalExceptionHandler.java
    └── CustomException.java
```

---

## 📖 학습 가이드

### 각 단계별 진행 방법

1. **README의 해당 단계 내용 읽기**
2. **코드의 TODO 주석 찾기** - 각 파일에 `[N단계]` 형식으로 표시
3. **실습 진행** - TODO에 따라 코드 작성
4. **체크리스트 확인** - 모든 항목 완료 시 다음 단계 진행
5. **필요시 코드 리뷰 요청**


## 🤝 코드 리뷰 체크포인트

각 단계 완료 후 아래 항목을 확인하세요:

- [ ] 코드가 정상적으로 실행되는가?
- [ ] API가 예상한 대로 동작하는가?
- [ ] 코드에 주석이 적절히 작성되어 있는가?
- [ ] 변수명과 메서드명이 의미를 잘 전달하는가?
- [ ] 예외 상황을 처리했는가?
- [ ] 중복 코드는 없는가?

---

## 📝 참고 자료

- [Baeldung - Spring Boot Tutorials](https://www.baeldung.com/spring-boot)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [JPA 프로그래밍 입문](https://www.inflearn.com/)

---

