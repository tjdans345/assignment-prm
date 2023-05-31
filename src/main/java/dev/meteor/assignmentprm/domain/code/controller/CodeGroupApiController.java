package dev.meteor.assignmentprm.domain.code.controller;


import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeGroupRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.response.CodeGroupResponseDTO;
import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.service.CodeGroupService;
import dev.meteor.assignmentprm.global.aop.annotation.ApiDocumentResponseAnnotation;
import dev.meteor.assignmentprm.global.aop.annotation.CodeGroupNameDuplicateCheckAnnotation;
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
@RequestMapping("/api/v1/code_group")
@RestController
public class CodeGroupApiController {

    private final CodeGroupService codeGroupService;

    @GetMapping
    public HttpEntity<ResponseDTO<Page<CodeGroupEntity>>> getCodeGroupList(
            @PageableDefault(sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.TEST_CODE,
                        codeGroupService.getCodeGroupList(pageable)
                ));
    }


    // 코드 그룹 상세 조회
    @GetMapping("/{idx}")
    public HttpEntity<?> getCodeGroupDetail(@PathVariable Long idx) {

        codeGroupService.getCodeGroupDetail(idx);

        return null;
    }

    /**
     * 코드 그룹 생성
     * @param createCodeGroupRequestDTO CreateCodeGroupRequestDTO
     * @param bindingResult BindingResult
     * @return CodeGroupResponseDTO
     */
    @CodeGroupNameDuplicateCheckAnnotation
    @ApiDocumentResponseAnnotation
    @Operation(summary = "코드 그룹 생성 API", description = "코드 그룹 생성 처리")
    @PostMapping
    public HttpEntity<ResponseDTO<CodeGroupResponseDTO>> createCodeGroup(
            @Valid @RequestBody CreateCodeGroupRequestDTO createCodeGroupRequestDTO, BindingResult bindingResult) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.CREATE_SUCCESS,
                        codeGroupService.createCodeGroup(createCodeGroupRequestDTO)
                ));
    }

    /**
     * 코드 그룹 이름 중복 조회
     * @param checkDuplicateNameRequestDTO CheckDuplicateNameRequestDTO
     * @param bindingResult BindingResult
     * @return Boolean
     */
    @CodeGroupNameDuplicateCheckAnnotation
    @ApiDocumentResponseAnnotation
    @Operation(summary = "코드 그룹 이름 중복 조회 API", description = "코드 그룹 이름 중복 조회 처리")
    @GetMapping("/check_duplicate")
    public HttpEntity<ResponseDTO<Boolean>> checkDuplicateCodeGroupName(
            @ModelAttribute CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO
            , BindingResult bindingResult) {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(
                        ResponseEnum.CHECK_SUCCESS,
                        codeGroupService.checkDuplicateCodeGroupName(checkDuplicateNameRequestDTO)
                ));
    }


    @PutMapping
    // 코드 그룹 수정
    public HttpEntity<ResponseEntity<?>> updateCodeGroup(
            @Valid @RequestBody Object O, BindingResult bindingResult
    ) {

        codeGroupService.updateCodeGroup();
        return null;
    }

    @DeleteMapping("/{idx}")
    // 코드 그룹 삭제
    public HttpEntity<ResponseEntity<?>> deleteCodeGroup(@PathVariable Long idx) {
        codeGroupService.deleteCodeGroup(idx);
        return null;
    }


}
