package dev.meteor.assignmentprm.domain.code.controller;


import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.service.CodeService;
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
@RequestMapping("/api/v1/code")
@RestController
public class CodeApiController {

    private final CodeService codeService;

    // 코드 리스트 조회
    @GetMapping
    public HttpEntity<ResponseEntity<?>> getCodeList(
            @PageableDefault(sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {
       return null;
    }

    // 코드 상세 조회
    @GetMapping("/{idx}")
    public HttpEntity<ResponseEntity<?>> getCodeDetail(@PathVariable Long idx) {
       return null;
    }

    // 코드 생성
    @PostMapping
    public HttpEntity<ResponseEntity<?>> createCode(
            @Valid @RequestBody Object o, BindingResult bindingResult) {
       return null;
    }

    // 코드 이름 중복 조회
    @GetMapping("/check_duplicate")
    public HttpEntity<ResponseEntity<?>> checkDuplicateCodeName(
            @Valid @ModelAttribute CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO
            , BindingResult bindingResult) {
       return null;
    }

    // 코드 그룹 수정
    @PutMapping
    public HttpEntity<ResponseEntity<?>> updateCode(
            @Valid @RequestBody Object o, BindingResult bindingResult) {

       return null;
    }

    // 코드 그룹 삭제
    @DeleteMapping("/{idx}")
    public HttpEntity<ResponseEntity<?>> deleteCode(@PathVariable Long idx) {
        
       return null;
    }


}
