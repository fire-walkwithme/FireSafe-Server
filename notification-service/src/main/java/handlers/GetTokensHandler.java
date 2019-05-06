package handlers;

import com.google.gson.Gson;
import services.NotificationService;
import services.TokenService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class GetTokensHandler implements Route {
    private final TokenService tokServce;

    public GetTokensHandler(TokenService tokServce) {
        this.tokServce = tokServce;
    }

    @Override
    public Object handle(Request request, Response response) {
        NotificationService notifService = new NotificationService(tokServce);
        response.header("Content-Type", "application/json;charset=utf-8");
        List<String> tokens = tokServce.getRegistrationTokens();
        notifService.sendNotifications();
        return new Gson().toJson(tokens);
    }
}
