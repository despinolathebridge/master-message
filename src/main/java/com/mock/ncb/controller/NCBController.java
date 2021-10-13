package com.mock.ncb.controller;

import com.mock.ncb.dto.ResponseKafkaDto;
import com.mock.ncb.dto.TransferResponseDto;
import com.mock.ncb.event.LedgerAccountCreatedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ncb")
public class NCBController {

    @GetMapping("/bod")
    public ResponseEntity<String> bod() {
        return ResponseEntity.status(HttpStatus.OK).body("2010-10-10");
    }

    @GetMapping("/check")
    public ResponseEntity<TransferResponseDto> check() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(TransferResponseDto.builder().code("F").message(" a ").build());
    }

    @PostMapping("/simple")
    public ResponseEntity<TransferResponseDto> s1mple() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(TransferResponseDto.builder().requestid("1234").code("F").build());
    }
}
