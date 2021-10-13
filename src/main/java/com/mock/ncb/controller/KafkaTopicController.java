package com.mock.ncb.controller;

import com.mock.ncb.dto.ResponseKafkaDto;
import com.mock.ncb.event.BalanceTransferP2PEvent;
import com.mock.ncb.event.FirstAccountBalanceEvent;
import com.mock.ncb.event.LedgerAccountCreatedEvent;
import com.mock.ncb.event.MessageCreatedEvent;
import com.mock.ncb.event.TransferTopUpEvent;
import com.mock.ncb.event.UserActivatedEvent;
import com.mock.ncb.event.UserCreatedEvent;
import com.mock.ncb.provider.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaTopicController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/push")
    public ResponseEntity<ResponseKafkaDto> bod(@RequestBody MessageCreatedEvent event) {
        kafkaProducer.publishMessageEvent(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }

    @PostMapping("/ledger")
    public ResponseEntity<ResponseKafkaDto> createLedger(@RequestBody LedgerAccountCreatedEvent event) {
        kafkaProducer.publishLedgerEvent(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }

    @PostMapping("/leapx")
    public ResponseEntity<ResponseKafkaDto> createLeapx(@RequestBody UserActivatedEvent event) {
        kafkaProducer.publishLeapxEvent(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }

    @PostMapping("/leapx-user-created")
    public ResponseEntity<ResponseKafkaDto> createLeapx(@RequestBody UserCreatedEvent event) {
        kafkaProducer.publishLeapxEvent(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }

    @PostMapping("/first-account-balance")
    public ResponseEntity<ResponseKafkaDto> createLeapx(@RequestBody FirstAccountBalanceEvent event) {
        kafkaProducer.publishFirstAccountBalanceEvent(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }

    @PostMapping("/ledger-p2p")
    public ResponseEntity<ResponseKafkaDto> publishLedgerp2p(@RequestBody BalanceTransferP2PEvent event) {
        kafkaProducer.publishLedgerp2p(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }

    @PostMapping("/topup")
    public ResponseEntity<ResponseKafkaDto> publishTopup(@RequestBody TransferTopUpEvent event) {
        kafkaProducer.publishTopUp(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }
}