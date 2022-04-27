package com.mock.ncb.provider.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.ncb.event.AbmCashOutTransfersEvent;
import com.mock.ncb.event.AbmCashOutTransfersFailedEvent;
import com.mock.ncb.event.AchCashOutEnrichedEvent;
import com.mock.ncb.event.AchTransferCompletedEvent;
import com.mock.ncb.event.BalanceNCBCashInEvent;
import com.mock.ncb.event.BalanceTransferP2PEvent;
import com.mock.ncb.event.BalanceUpdateEvent;
import com.mock.ncb.event.EcommerceCashInEvent;
import com.mock.ncb.event.FeeSavedEvent;
import com.mock.ncb.event.FirstAccountBalanceEvent;
import com.mock.ncb.event.LedgerAccountCreatedEvent;
import com.mock.ncb.event.MTopBalanceDebitedEvent;
import com.mock.ncb.event.MTopTransferEvent;
import com.mock.ncb.event.MessageCreatedEvent;
import com.mock.ncb.event.TransferApprovedEvent;
import com.mock.ncb.event.TransferCashOutEvent;
import com.mock.ncb.event.TransferRejectedEvent;
import com.mock.ncb.event.TransferTopUpEvent;
import com.mock.ncb.event.UserActivatedEvent;
import com.mock.ncb.event.UserCreatedEvent;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, CloudEvent> kafkaTemplate;
    private final KafkaProducerLeapxConfiguration kafkaTemplateLeapx;
    private final ObjectMapper objectMapper;

    public KafkaProducer(KafkaTemplate<String, CloudEvent> kafkaTemplate, KafkaProducerLeapxConfiguration kafkaTemplateLeapx) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateLeapx = kafkaTemplateLeapx;
        this.objectMapper = new ObjectMapper();
    }

    public void publishLedgerEvent(LedgerAccountCreatedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.2")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.accounts.ledger.new")
                .withSource(URI.create("dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("ACCOUNTS", cloudEvent);
        log.info("Message published!");
    }

    public void publishMessageEvent(MessageCreatedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.2")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.notify.message.new")
                .withSource(URI.create("dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("NOTIFICATIONS", cloudEvent);
        log.info("Message published!");
    }

    public void publishLeapxEvent(UserActivatedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.2")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.user.profile.activated")
                .withSource(URI.create("dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplateLeapx.getKafkaTemplateLeapx().send("USERS", cloudEvent);
        log.info("Message published!");
    }

    public void publishLeapxEvent(UserCreatedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.user.profile.activated")
                .withSource(URI.create("dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplateLeapx.getKafkaTemplateLeapx().send("USERS", cloudEvent);
        log.info("Message published!");
    }

    public void publishFirstAccountBalanceEvent(FirstAccountBalanceEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.balances.first")
                .withSource(URI.create("dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("BALANCE", cloudEvent);
        log.info("Message published!");
    }

    public void publishLedgerp2p(BalanceTransferP2PEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.2")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.p2p.transfer.balance.completed")
                .withSource(URI.create("dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("BALANCE", cloudEvent);
        log.info("Message published!");
    }

    public void publishTopUp(TransferTopUpEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.transfer.ncb.topup")
                .withSource(URI.create("dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("NCB_OPERATIONS", cloudEvent);
        log.info("Message published!");
    }

    public void transferApprove(TransferApprovedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.ecommerce.approved")
                .withSource(URI.create("/nautilus_core/ecommerce_bff"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("ACCOUNTS", cloudEvent);
        log.info("Message published!");
    }

    public void transferFailed(TransferRejectedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.ecommerce.rejected")
                .withSource(URI.create("/nautilus_core/ecommerce_bff"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("ACCOUNTS", cloudEvent);
        log.info("Message published!");
    }

    public void addBalanceETop(EcommerceCashInEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.transfer.etop")
                .withSource(URI.create("/nautilus_core/ecommerce_bff"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("TRANSFER", cloudEvent);
        log.info("Message published!");
    }

    public void addBalanceCashIn(BalanceNCBCashInEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.2")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.balance.ncb.topup")
                .withSource(URI.create("/nautilus_core/ecommerce_bff"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("BALANCE", cloudEvent);
        log.info("Message published!");
    }

    public void addEnriquecido(AchCashOutEnrichedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.ach.cashout.enriched")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("ACCOUNTS", cloudEvent);
        log.info("Message published!");
    }


    public void addCashOutNCB(TransferCashOutEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.balance.ncb.debit")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("BALANCE", cloudEvent);
        log.info("Message published!");
    }

    public void updateTransferStatus(BalanceUpdateEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.2")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.transfer.balance.update")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("BALANCE", cloudEvent);
        log.info("Message published!");
    }

    public void sendAchTransferCompleted(AchTransferCompletedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.2")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.ach.transfer.completed")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("NCB_OPERATIONS", cloudEvent);
        log.info("Message published!");
    }

    public void sendAbmCashoutEvent(AbmCashOutTransfersEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.abm.cashout.debit")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("BALANCE", cloudEvent);
        log.info("Message published!");
    }

    public void sendAbmCashoutFailedEvent(AbmCashOutTransfersFailedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.abm.cashout.debit.failed")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("BALANCE", cloudEvent);
        log.info("Message published!");
    }

    public void sendFee(FeeSavedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.mtop.fees.saved")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("TRANSFER", cloudEvent);
        log.info("Message published!");
    }

    public void sendFeeEnriched(MTopBalanceDebitedEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.mtop.balance.debited")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("BALANCE", cloudEvent);
        log.info("Message published!");
    }

    public void sendMTopBalance(MTopTransferEvent event) {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", "0.0.1")
                .withId(UUID.randomUUID().toString())
                .withType("team.nautilus.event.mtop.transfer.created")
                .withSource(URI.create("/nautilus_core/dummy"))
                .withDataContentType("application/json")
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send("TRANSFER", cloudEvent);
        log.info("Message published!");
    }
}
