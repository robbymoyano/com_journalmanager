package com.journalmanager.infra.servicebus;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.journalmanager.domain.RequestJournal;
import com.journalmanager.usecase.CommandService;
import com.microsoft.azure.servicebus.ClientFactory;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageReceiver;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

@Configuration
public class Listener {
	private static final Logger log = LoggerFactory.getLogger(Listener.class);

	@Value("${bus.journal.stringConnection}")
	private String posvStringConnection;

	@Value("${bus.journal.entityPath}")
	private String posvEntityPath;

	@Autowired
	private CommandService service;

	@Bean
	public void queuePosvRead() throws InterruptedException, ServiceBusException {
		log.info("Inicia Bean de conexion a cola Journal");
		ConnectionStringBuilder conexion = new ConnectionStringBuilder(posvStringConnection, posvEntityPath);
		IMessageReceiver messageReceiver = ClientFactory.createMessageReceiverFromConnectionStringBuilder(conexion,
				ReceiveMode.RECEIVEANDDELETE);
		CompletableFuture<String> currentTask = new CompletableFuture<String>();

		try {
			CompletableFuture.runAsync(() -> {
				while (!currentTask.isCancelled()) {
					try {
						IMessage message = messageReceiver.receive();
						if (message != null) {
							log.info("INI Listener");

							byte[] body = message.getMessageBody().getBinaryData().get(0);
							String bodyText = new String(body);
							log.info("Mensaje: count:{} {} {}", message.getDeliveryCount(), message.getMessageId(),
									bodyText);
							RequestJournal request = null;
							try {
								request = deSerializar(bodyText);
								service.ingresarTransacciones(request);
							} catch (IOException e) {
								log.warn("Deserializacion {}", e.getMessage());
							} catch (Exception e) {
								log.error("Error interno: {}", e.getMessage());
							}

						}

					} catch (Exception e) {
						log.error("Error al recibir el mensaje", e);
						currentTask.completeExceptionally(e);
						log.error("FIN Listener", e);
					}
				}
				currentTask.complete("Finalizado");
			});
		} catch (Exception e) {
			log.error("Error en el proceso de escuchar posv: {}", e);
			currentTask.completeExceptionally(e);
		}
	}

	private RequestJournal deSerializar(String json) throws IOException {
		return new ObjectMapper().readValue(json, RequestJournal.class);
	}

}
