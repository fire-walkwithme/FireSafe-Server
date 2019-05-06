package handlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import services.TokenService;
import spark.Request;
import spark.Response;
import spark.Route;


public final class PostTokenHandler implements Route {
    private final TokenService tokService;

    public PostTokenHandler(TokenService tokService) {
        this.tokService = tokService;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.header("Content-Type", "application/json;charset=utf-8");
        JsonObject token = new Gson().fromJson(request.body(), JsonElement.class).getAsJsonObject();;
        tokService.registerToken(token.get("token").getAsString());
        return new Gson()
                .toJson("{status: SUCCESS}");
    }
}
