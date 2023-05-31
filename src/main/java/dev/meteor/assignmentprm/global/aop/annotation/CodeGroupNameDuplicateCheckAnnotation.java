package dev.meteor.assignmentprm.global.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeGroupNameDuplicateCheckAnnotation {

    /**
     * 코드 그룹 이름 중복체크가 필요한 메서드 단위에서 사용하는 CustomAnnotation
     * 이 어노테이션이 붙어있으면 ValidationAdvice 에서 AOP 를 활용하여 중복 체크 진행
     */

}
