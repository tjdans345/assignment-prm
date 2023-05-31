package dev.meteor.assignmentprm.global.aop;

import dev.meteor.assignmentprm.domain.code.domain.dto.request.*;
import dev.meteor.assignmentprm.domain.code.repository.CodeGroupRepository;
import dev.meteor.assignmentprm.domain.code.repository.CodeRepository;
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
    private final CodeRepository codeRepository;


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
     * 코드 그룹 이름 중복 체크 진행 Advice
     * TODO : 데이터 구조를 바꾸거나 다형성을 이용해서 코드 중복처리 개선 처리 진행 가능성 있음 20230531
     * @param joinPoint JoinPoint
     */
    @Before("@annotation(dev.meteor.assignmentprm.global.aop.annotation.CodeGroupNameDuplicateCheckAnnotation)")
    public void codeGroupDuplicateCheckAdvice(JoinPoint joinPoint) {
        log.info("?");
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {

            if (arg instanceof CheckDuplicateNameRequestDTO){
                CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO = (CheckDuplicateNameRequestDTO) arg;
                if (codeGroupRepository.findByName(checkDuplicateNameRequestDTO.getCheckName()).isPresent()) {
                    throw new CommonException(ErrorEnum.ALREADY_DATA);
                }} else if (arg instanceof CreateCodeGroupRequestDTO){
                CreateCodeGroupRequestDTO createCodeGroupRequestDTO = (CreateCodeGroupRequestDTO) arg;
                if (codeGroupRepository.findByName(createCodeGroupRequestDTO.getCodeGroupName()).isPresent()) {
                    throw new CommonException(ErrorEnum.ALREADY_DATA);
                }} else if (arg instanceof UpdateCodeGroupRequestDTO){
                UpdateCodeGroupRequestDTO updateCodeGroupRequestDTO = (UpdateCodeGroupRequestDTO) arg;
                if (codeGroupRepository.findByName(updateCodeGroupRequestDTO.getCodeGroupName()).isPresent()) {
                    throw new CommonException(ErrorEnum.ALREADY_DATA);
                }}
        }
    }


    /**
     * 코드 이름 중복 체크 진행 Advice
     * TODO : 데이터 구조를 바꾸거나 다형성을 이용해서 코드 중복처리 개선 처리 진행 가능성 있음 20230531
     * @param joinPoint JoinPoint
     */
    @Before("@annotation(dev.meteor.assignmentprm.global.aop.annotation.CodeNameDuplicateCheckAnnotation)")
    public void codeDuplicateCheckAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {

            if (arg instanceof CheckDuplicateNameRequestDTO){
                CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO = (CheckDuplicateNameRequestDTO) arg;
                if (codeRepository.findByName(checkDuplicateNameRequestDTO.getCheckName()).isPresent()) {
                    throw new CommonException(ErrorEnum.ALREADY_DATA);
                }}else if (arg instanceof CreateCodeRequestDTO){
                CreateCodeRequestDTO checkDuplicateNameRequestDTO = (CreateCodeRequestDTO) arg;
                if (codeRepository.findByName(checkDuplicateNameRequestDTO.getCodeName()).isPresent()) {
                    throw new CommonException(ErrorEnum.ALREADY_DATA);
                }} else if (arg instanceof UpdateCodeRequestDTO){
                UpdateCodeRequestDTO updateCodeRequestDTO = (UpdateCodeRequestDTO) arg;
                if (codeRepository.findByName(updateCodeRequestDTO.getCodeName()).isPresent()) {
                    throw new CommonException(ErrorEnum.ALREADY_DATA);
                }}
        }
    }

}
