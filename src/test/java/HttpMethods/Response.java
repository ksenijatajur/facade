package HttpMethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response {
    @JsonProperty("jsonrpc")

    private String jsonrpc;
    @JsonProperty("id")
    private int id;
    @JsonProperty("result")
    private Object result;

    public Response (){

    }
}
