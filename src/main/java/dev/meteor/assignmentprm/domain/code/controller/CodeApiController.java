package dev.meteor.assignmentprm.domain.code.controller;


import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.UpdateCodeRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.response.CodeResponseDTO;
import dev.meteor.assignmentprm.domain.code.service.CodeService;
import dev.meteor.assignmentprm.global.aop.annotation.ApiDocumentResponseAnnotation;
import dev.meteor.assignmentprm.global.aop.annotation.CodeNameDuplicateCheckAnnotation;
import dev.meteor.assignmentprm.global.common.domain.dto.response.ResponseDTO;
import dev.meteor.assignmentprm.global.enum_package.response.ResponseEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/v1/code")
@RestController
public class CodeApiController {

    private final CodeService codeService;

    /**
     * 코드 리스트 조회 ver.1
     * TODO rfd : 무한 스크롤 방식 , 타입 별, 조건 별에 따른 업데이트 가능성 有
     *
     * @param pageable Pageable
     * @return CodeResponseDTO
     */
    @ApiDocumentResponseAnnotation
    @Operation(summary = "코드 리스트 조회 API", description = "코드 리스트 조회 처리")
    @GetMapping
    public HttpEntity<ResponseDTO<Page<CodeResponseDTO>>> getCodeList(
            @PageableDefault(sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.FIND_SUCCESS,
                        codeService.getCodeList(pageable)
                ));
    }


    /**
     * 특정 코드 그룹을 가지고 있는 코드 리스트 조회
     * ex) 16번 코드 그룹의 번호를 들고있는 코드 리스트를 조회할 때
     * @param pageable Pageable
     * @param idx Long (코드 그룹 인덱스)
     * @return Page<CodeResponseDTO>
     */
    @ApiDocumentResponseAnnotation
    @Operation(summary = "특정 코드 그룹을 가지고 있는 코드 리스트 조회 API", description = "특정 코드 그룹인덱스를 들고있는 코드 리스트를 출력해줍니다")
    @GetMapping("/code_group_contain/{idx}")
    public HttpEntity<ResponseDTO<Page<CodeResponseDTO>>> getCodeList(
            @PageableDefault(sort = "idx", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long idx) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.FIND_SUCCESS,
                        codeService.getCodeContainCodeGroup(pageable, idx)
                ));
    }

    /**
     * 코드 상세 조회 API
     *
     * @param idx Long (코드 인덱스)
     * @return CodeResponseDTO
     */
    @ApiDocumentResponseAnnotation
    @Operation(summary = "코드 상세 조회 API", description = "코드 상세 조회 처리")
    @GetMapping("/{idx}")
    public HttpEntity<ResponseDTO<CodeResponseDTO>> getCodeDetail(@PathVariable Long idx) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.FIND_SUCCESS,
                        codeService.getCodeDetail(idx)
                ));
    }

    //

    /**
     * 코드 생성 Api
     *
     * @param createCodeRequestDTO CreateCodeRequestDTO
     * @param bindingResult        BindingResult
     * @return CodeResponseDTO
     */
    @CodeNameDuplicateCheckAnnotation
    @ApiDocumentResponseAnnotation
    @Operation(summary = "코드 생성 API", description = "코드 생성 처리")
    @PostMapping
    public HttpEntity<ResponseDTO<CodeResponseDTO>> createCode(
            @Valid @RequestBody CreateCodeRequestDTO createCodeRequestDTO, BindingResult bindingResult) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.CREATE_SUCCESS,
                        codeService.createCode(createCodeRequestDTO)));
    }


    /**
     * 코드 이름 중복 조회
     *
     * @param checkDuplicateNameRequestDTO CheckDuplicateNameRequestDTO
     * @param bindingResult                BindingResult
     * @return Boolean
     */
    @CodeNameDuplicateCheckAnnotation
    @ApiDocumentResponseAnnotation
    @Operation(summary = "코드이름 중복 조회 API", description = "코드이름 중복 조회 처리")
    @GetMapping("/check_duplicate")
    public HttpEntity<ResponseDTO<Boolean>> checkDuplicateCodeName(
            @Valid @ModelAttribute CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO
            , BindingResult bindingResult) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.CHECK_SUCCESS,
                        codeService.checkDuplicateCodeName(checkDuplicateNameRequestDTO)
                ));
    }

    /**
     * 코드 수정 Api
     *
     * @param updateCodeRequestDTO UpdateCodeRequestDTO
     * @param bindingResult        BindingResult
     * @return CodeResponseDTO
     */
    @CodeNameDuplicateCheckAnnotation
    @ApiDocumentResponseAnnotation
    @Operation(summary = "코드 수정 API", description = "코드 수정 처리")
    @PutMapping
    public HttpEntity<ResponseDTO<CodeResponseDTO>> updateCode(
            @Valid @RequestBody UpdateCodeRequestDTO updateCodeRequestDTO, BindingResult bindingResult) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.UPDATE_SUCCESS,
                        codeService.updateCode(updateCodeRequestDTO)
                ));
    }


    /**
     * 코드 그룹 삭제 처리
     *
     * @param idx Long
     * @return String (결과 메시지)
     */
    @ApiDocumentResponseAnnotation
    @Operation(summary = "코드 삭제 API", description = "코드 처리")
    @DeleteMapping("/{idx}")
    public HttpEntity<ResponseDTO<String>> deleteCode(@PathVariable Long idx) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.DELETE_SUCCESS,
                        codeService.deleteCode(idx)
                ));
    }


}
