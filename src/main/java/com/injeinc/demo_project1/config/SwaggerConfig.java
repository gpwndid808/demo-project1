package com.injeinc.demo_project1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO [10단계] Swagger 의존성 추가하기
//  - build.gradle에 SpringDoc 의존성 추가 필수!
//  - implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0'
//  - Gradle Refresh 후 애플리케이션 재시작
//  💡 실습: build.gradle에서 주석 해제

// TODO [10단계] @Configuration 이해하기
//  - 이 클래스가 Spring 설정 클래스임을 명시
//  - @Bean 메서드로 Spring 빈을 등록
//  💡 학습 포인트: Java Config 방식의 설정

@Configuration
public class SwaggerConfig {
    
    // TODO [10단계] OpenAPI 빈 생성하기
    //  - OpenAPI: Swagger 3.x의 명세 객체
    //  - API 문서의 메타정보를 설정합니다.
    //  💡 실습: 아래 주석을 해제하고 완성하세요
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("게시판 API")
                .version("v1.0.0")
                .description("Spring Boot + JPA로 만든 게시판 REST API 문서입니다.")
                .contact(new Contact()
                    .name("개발팀")
                    .email("dev@example.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
    
    
    // TODO [10단계] Swagger UI 접속하기
    //  - 애플리케이션 실행 후 브라우저에서 접속
    //  - URL: http://localhost:8080/swagger-ui.html
    //  - 또는: http://localhost:8080/swagger-ui/index.html
    //  💡 실습: 모든 API 엔드포인트가 표시되는지 확인
    
    // TODO [10단계] API Docs JSON 확인하기
    //  - URL: http://localhost:8080/v3/api-docs
    //  - OpenAPI 3.0 명세를 JSON 형태로 제공
    //  💡 학습 포인트: Swagger UI는 이 JSON을 기반으로 화면을 그립니다.
    
    // TODO [10단계] 보안 설정 추가하기 (선택사항)
    //  - JWT 토큰 인증을 위한 Security Scheme 추가
    //  - 추후 Spring Security 적용 시 사용
    //  💡 실습: 인증이 필요한 API에 자물쇠 아이콘 표시
    
    // TODO [10단계] 그룹화 설정 (선택사항)
    //  - API를 기능별로 그룹화
    //  - @Tag 어노테이션 활용
    //  💡 실습: 게시판, 댓글, 사용자 등으로 분류
    
    // TODO [10단계] 환경별 설정 (선택사항)
    //  - 운영 환경에서는 Swagger UI 비활성화
    //  - application-prod.properties:
    //  - springdoc.swagger-ui.enabled=false
    //  💡 보안: 운영 환경에서 API 문서를 공개하지 않도록 주의!
}
