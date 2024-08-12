package com.pitchmanagement.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pitchmanagement.dto.CommentDTO;
import com.pitchmanagement.model.request.CommentRequest;
import com.pitchmanagement.model.response.BaseResponse;
import com.pitchmanagement.service.CommentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/comment")
@Validated
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("{id}")
    public ResponseEntity<?> getCommentByPitch(
            @Min(value = 1, message = "pitch id must be greater than 0") @PathVariable("id") Integer pitch_id,
            @RequestParam(name = "offset", defaultValue = "1", required = false) Integer offset,
            @RequestParam(name = "limit", defaultValue = "5", required = false) Integer limit) {
        try {
            List<CommentDTO> lst = commentService.GetCommentByPitch(pitch_id, offset, limit);
            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(lst)
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("failed: " + e)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody CommentRequest commentRequest) {
        try {
            commentService.insert(commentRequest);
            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.CREATED.value())
                    .message("success")
                    // .data(lst)
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("failed: " + e)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody CommentRequest commentRequest) {
        try {
            commentService.update(commentRequest);
            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.CREATED.value())
                    .message("success")
                    // .data(lst)
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("failed: " + e)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            commentService.delete(id);
            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.NO_CONTENT.value())
                    .message("success")
                    // .data(lst)
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("failed: " + e)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
