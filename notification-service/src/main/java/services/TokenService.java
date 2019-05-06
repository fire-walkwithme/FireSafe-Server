package services;

import java.util.ArrayList;
import java.util.List;

public class TokenService {
    private static TokenService INSTANCE = null;
    private static List<String> registrationTokens = new ArrayList<>();

    private TokenService() {}

    public static TokenService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new TokenService();
        return INSTANCE;
    }

    public void registerToken(String tok) {
        registrationTokens.add(tok);
    }

    public void unregisterToken(String tok) {
        registrationTokens.removeIf(item -> item.equals(tok));
    }

    public List<String> getRegistrationTokens() {
        return registrationTokens;
    }


}
