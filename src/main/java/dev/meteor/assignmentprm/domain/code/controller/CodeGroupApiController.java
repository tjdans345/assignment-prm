package dev.meteor.assignmentprm.domain.code.controller;


import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeRequestDTO;
import dev.meteor.assignmentprm.domain.code.service.CodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;

@RequiredArgsConstructor
@RequestMapping("/api/v1/code_group")
@RestController
public class CodeGroupApiController {

    private final CodeGroupService codeGroupService;

    @GetMapping
    public HttpEntity<ResponseEntity<?>> getCodeGroupList(
            @PageableDefault(sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {

        codeGroupService.getCodeGroupList(pageable);
        return null;
    }


    // 코드 그룹 상세 조회
    @GetMapping("/{idx}")
    public HttpEntity<ResponseEntity<?>> getCodeGroupDetail(@PathVariable Long idx) {

        codeGroupService.getCodeGroupDetail(idx);

       return null;
    }

    @PostMapping
    // 코드 그룹 생성
    public HttpEntity<ResponseEntity<?>> createCodeGroup(
            @Valid @RequestBody CreateCodeRequestDTO createCodeRequestDTO
            , BindingResult bindingResult) {
        codeGroupService.createCodeGroup(createCodeRequestDTO);
       return null;
    }

    @GetMapping("/check_duplicate")
    // 코드 그룹 이름 중복 조회
    public HttpEntity<ResponseEntity<?>> checkDuplicateCodeGroupName(
            @Valid @ModelAttribute CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO
            , BindingResult bindingResult) {

        codeGroupService.checkDuplicateCodeGroupName(checkDuplicateNameRequestDTO);
       return null;
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
