package com.pitchmanagement.controller;

import com.pitchmanagement.model.request.BillRequest;
import com.pitchmanagement.model.response.BaseResponse;
import com.pitchmanagement.model.response.BillDayResponse;
import com.pitchmanagement.model.response.BillPitchResponse;
import com.pitchmanagement.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("bill")
@RequiredArgsConstructor
public class BillController {

    @Autowired
    private final BillService billService;

    @PostMapping("/admin")
    public ResponseEntity<BaseResponse> addBill(@RequestBody BillRequest bill) {
        BillRequest myBill = billService.addBill(bill);
        BaseResponse response = BaseResponse
                .builder()
                .status(HttpStatus.ACCEPTED)
                .data(myBill)
                .message("success")
                .build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/admin/billday")
    public ResponseEntity<BaseResponse> getBillDayByMonth(@RequestParam  int month, @RequestParam int year) {
        List<BillDayResponse> myBill = billService.getBillDayByMonth(month, year);
        BaseResponse response = BaseResponse
                .builder()
                .status(HttpStatus.ACCEPTED)
                .data(myBill)
                .message("success")
                .build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/admin/billpitch")
    public ResponseEntity<BaseResponse> getBillPitchByMonth(@RequestParam  int month, @RequestParam int year) {
        List<BillPitchResponse> myBill = billService.getBillPitchByMonth(month, year);
        BaseResponse response = BaseResponse
                .builder()
                .status(HttpStatus.ACCEPTED)
                .data(myBill)
                .message("success")
                .build();

        return ResponseEntity.ok().body(response);
    }
}
