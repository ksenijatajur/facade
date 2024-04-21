package HttpMethods;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(using = ParamsSerializer.class)
public class PingParams extends Params{


    public PingParams(){

    }
}
