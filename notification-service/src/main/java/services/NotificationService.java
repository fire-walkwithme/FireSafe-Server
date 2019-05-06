package services;

import com.google.firebase.messaging.*;

public class NotificationService {
    private final TokenService tokService;
    public NotificationService(TokenService tokService) {
        this.tokService = tokService;
    }

    public void sendNotifications() {
        Message message = Message.builder()
                .setNotification(new Notification(
                        "Alert",
                        "Fire alert"))
                .setToken(tokService.getRegistrationTokens().get(0))
                .build();
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println(response);
        } catch (FirebaseMessagingException e) {
            System.out.println("FireBase exception");
        }

    }
}
