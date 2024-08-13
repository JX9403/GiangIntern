package com.pitchmanagement.controller;

import com.pitchmanagement.model.request.UpdateUserRequest;
import com.pitchmanagement.model.response.BaseResponse;
import com.pitchmanagement.model.response.RegisterResponse;
import com.pitchmanagement.model.response.UserResponse;
import com.pitchmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByEmail(
            @PathVariable("id") Long id
    ){
        try {
            UserResponse userResponse = userService.getUserById(id);

            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.OK.value())
                    .data(userResponse)
                    .message("Thông tin người dùng !")
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message(e.getMessage())
                            .build());
        }
    }

    @PreAuthorize("ROLE_USER")
    @PutMapping
    public ResponseEntity<?> updateUser(
            @ModelAttribute @Valid UpdateUserRequest updateUserRequest,
            BindingResult result
            ){
        try {
            if (result.hasErrors()) {
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                BaseResponse response = BaseResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Lỗi thông tin đầu vào!!!")
                        .data(errorMessages)
                        .build();
                return ResponseEntity.badRequest().body(response);
            }
            UserResponse userResponse = userService.updateUser(updateUserRequest);

            BaseResponse response = BaseResponse.builder()
                    .status(HttpStatus.OK.value())
                    .data(userResponse)
                    .message("Thông tin người dùng !")
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message(e.getMessage())
                            .build());
        }
    }

}
