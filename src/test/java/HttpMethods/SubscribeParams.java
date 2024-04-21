package HttpMethods;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class SubscribeParams extends Params {
    @JsonProperty("clientName")
    private String clientName;

    @JsonProperty("clientRole")
    private String clientRole;

    @JsonProperty("message")
    private List<String> message;



    public SubscribeParams(String name, String role, List<String> messages) {
        clientName = name;
        clientRole= role;
        message = messages;
    }







}
