package dev.meteor.assignmentprm.global.aop;

import dev.meteor.assignmentprm.global.error.CommonException.ValidationException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Component
@Aspect
@RequiredArgsConstructor
public class ValidationAdvice {

    @Before("@annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void apiAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {
            if (arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()){
                    throw new ValidationException(
                            bindingResult.getFieldError() != null && bindingResult.getFieldError().getDefaultMessage()
                                    == null ? "유효한 형식의 데이터가 아닙니다 다시 시도하여 주십시오. " : bindingResult.getFieldError().getDefaultMessage());
                }
            }
        }
    }



}
