package HttpMethods;



import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

    @JsonProperty("jsonrpc")
    private String jsonrpc;

    @JsonProperty("method")
    private String method;

    @JsonProperty("params")
    private Params params;

    @JsonProperty("id")
    private int id; // this ID probably should be automatic ?

     public Request(String jsonrpc, String method, Params params, int id){
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.params = params;
        this.id = id;


     }



}
