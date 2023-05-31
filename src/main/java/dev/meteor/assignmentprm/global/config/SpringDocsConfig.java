package dev.meteor.assignmentprm.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Slf4j
@Configuration
public class SpringDocsConfig {

    /**
     * SpringDocsConfig 설정
     * 해당 빈을 dev 서버에서만 동적으로 생성해주기 위한 설정 추가
     *
     * value : 체크 할 application.yml 의 항목
     * @param springDocsVersion Rest API Docs 버젼
     * @return OpenAPI
     */
    @Bean
    @ConditionalOnProperty(value = "api-docs-enable", havingValue = "true")
    public OpenAPI openAPI(@Value("${springdoc.version}") String springDocsVersion) {
        Info info = new Info()
                .title("이제이엠(EJM) 과제 Rest API 문서 \uD83D\uDCDD ")
                .version(springDocsVersion)
                .description("이제이엠 과제(EJM) Rest API 문서입니다. \uD83D\uDE03 잘못된 부분 또는 오류 발생 시 문의 부탁드립니다. \uD83D\uDCDE ")
                .contact(
                        new Contact()
                                .name("Meteor \uD83E\uDDD1\uD83C\uDFFB\u200D\uD83D\uDCBB ")
                                .email("tjdans345@naver.com \uD83D\uDCEE"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }



}
