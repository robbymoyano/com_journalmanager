package com.journalmanager.infra.pubsub;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.journalmanager.domain.RequestJournal;
import com.journalmanager.usecase.CommandService;

@Configuration
public class Listener {
	private static final Logger log = LoggerFactory.getLogger(Listener.class);

	@Autowired
	private CommandService service;

	private Subscriber subscriber = null;

	@Value("${pubsub.project}")
	private String projectId;

	@Value("${pubsub.journal.subscription}")
	private String journalSubscription;

	@Bean
	public void startAsync() {
		log.info("Iniciando la escucha");
		ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, journalSubscription);

		MessageReceiver receiver = (PubsubMessage message, AckReplyConsumer consumer) -> {
			// Acciones tras leer mensaje
			System.out.println("Id: " + message.getMessageId());
			System.out.println("Data: " + message.getData().toStringUtf8());
			System.out.println("Atrributes: " + message.getAttributesMap());

			try {
				RequestJournal request = deSerializar(message.getData().toStringUtf8());
				service.ingresarTransacciones(request);
			}

			catch (Exception e) {
				// el mensaje no se serializa, lo ignoramos
				log.warn("Error {}", e.getMessage());
			}
			consumer.ack();
		};

		try {
			Subscriber subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
			subscriber.startAsync().awaitRunning();
			log.info("Escuchando mensajes desde " + subscriptionName.toString());
		} catch (Exception Exception) {
			log.error("Error al iniciar listener");
			subscriber.stopAsync();
		}
	}

	private RequestJournal deSerializar(String json) throws IOException {
		return new ObjectMapper().readValue(json, RequestJournal.class);
	}

}
