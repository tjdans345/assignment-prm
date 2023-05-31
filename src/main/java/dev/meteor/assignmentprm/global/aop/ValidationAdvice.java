package dev.meteor.assignmentprm.global.aop;

import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeGroupRequestDTO;
import dev.meteor.assignmentprm.domain.code.repository.CodeGroupRepository;
import dev.meteor.assignmentprm.global.enum_package.error.ErrorEnum;
import dev.meteor.assignmentprm.global.error.CommonException.CommonException;
import dev.meteor.assignmentprm.global.error.CommonException.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class ValidationAdvice {

    private final CodeGroupRepository codeGroupRepository;


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

    /**
     * TODO : 데이터 구조를 바꾸거나 다형성을 이용해서 코드 중복처리 개선 처리 진행할 수 있음
     * @param joinPoint JoinPoint
     */
    @Before("@annotation(dev.meteor.assignmentprm.global.aop.annotation.CodeGroupNameDuplicateCheckAnnotation)")
    public void joinStatusCheckAnnotation(JoinPoint joinPoint) {
        log.info("중복체크 진행");
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {

            if (arg instanceof CheckDuplicateNameRequestDTO){
                CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO = (CheckDuplicateNameRequestDTO) arg;
                // 중복 체크
                if (codeGroupRepository.findByName(checkDuplicateNameRequestDTO.getCheckName()).isPresent()) {
                    throw new CommonException(ErrorEnum.ALREADY_DATA);
                }
            }
            if (arg instanceof CreateCodeGroupRequestDTO){
                CreateCodeGroupRequestDTO checkDuplicateNameRequestDTO = (CreateCodeGroupRequestDTO) arg;
                // 중복 체크
                if (codeGroupRepository.findByName(checkDuplicateNameRequestDTO.getCodeGroupName()).isPresent()) {
                    throw new CommonException(ErrorEnum.ALREADY_DATA);
                }
            }
        }
    }
}
