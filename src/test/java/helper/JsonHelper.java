package helper;

import HttpMethods.Params;
import HttpMethods.Response;
import HttpMethods.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;


public class JsonHelper {


    public String json(String method, Params params, int id) {

        Request request = new Request(
                "2.0",
                method,
                params,
                id);


        ObjectMapper mapper = new ObjectMapper();
        String json = null;

        try {
            json = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        System.out.println(json);
        return json;
    }

    public Response jsonToResponse(HttpResponse<String> response){
        ObjectMapper objectMapper = new ObjectMapper();
        Response responseObject = null;
        try {
           responseObject = objectMapper.readValue(response.body(), Response.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return responseObject;
    }

}
