package br.com.michaelcruz.desafiopicpay.service;

import br.com.michaelcruz.desafiopicpay.client.NotificationClient;
import br.com.michaelcruz.desafiopicpay.entities.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationClient notificationClient;

    public void sendNoitifcation(Transfer transfer){

        try {
            logger.info("Sending notification...");
            var response = notificationClient.sendNotification((transfer));

            if(response.getStatusCode().isError()){
                logger.error("Error while sending notification, status code isn't OK");
            }

        } catch (Exception e) {
            logger.error("Error while sending notification", e);
        }
    }
}

