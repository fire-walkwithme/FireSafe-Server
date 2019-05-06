import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import handlers.GetTokensHandler;
import handlers.PostTokenHandler;
import services.TokenService;
import spark.Spark;

public final class Main {

    public static void main(String[] args) throws Exception {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://firesafe-4c3b3.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);

        TokenService tokService = TokenService.getInstance();
        PostTokenHandler postHndl = new PostTokenHandler(tokService);
        GetTokensHandler getHndl = new GetTokensHandler(tokService);

        Spark.port(8082);
        Spark.threadPool(26, 10, 2000);
        Spark.post("/tokens", postHndl);
        Spark.get("/tokens", getHndl);

    }
}