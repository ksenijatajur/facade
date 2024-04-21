package helper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPHelper {

    public  HttpRequest CreatePostHttp(String body){

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CONSTANTS.FACADE_URI))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            return request;
    }

    public  HttpResponse<String> execute (HttpRequest request){
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
}
