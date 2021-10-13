package com.mock.ncb.provider.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.ncb.event.BalanceTransferP2PEvent;
import com.mock.ncb.event.FirstAccountBalanceEvent;
import com.mock.ncb.event.LedgerAccountCreatedEvent;
import com.mock.ncb.event.MessageCreatedEvent;
import com.mock.ncb.event.TransferApprovedEvent;
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
}
