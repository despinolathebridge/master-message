package com.mock.ncb.controller;

import com.mock.ncb.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile_top_ups")
public class MTopController {

    @PostMapping("/{mtop_id}/orders")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage dummyError(@PathVariable("mtop_id") String mTopId) {
        return ErrorMessage.builder()
                .errorCode("MTU-007")
                .errorDescription("dummy")
                .build();
    }
}
