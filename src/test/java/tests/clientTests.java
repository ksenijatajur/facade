package tests;

import HttpMethods.PingParams;
import HttpMethods.Response;
import HttpMethods.SubscribeParams;
import helper.HTTPHelper;
import helper.JsonHelper;
import utils.MonitoringMock;

import java.net.http.HttpResponse;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class clientTests {

    @org.junit.Test
    public void shouldSubscribeToSystemStatus(){
        //given
        long pingTimeoutS = 3;
        long pingTimeoutMS = pingTimeoutS * 1000;

        String name = "My name";
        String role = "User";
        String message = "getSystemStatus";

        String methodSubscribe = "subscribe";
        int idSubscribe = 2;

        String methodPing = "ping";
        int idPing = 1;
        JsonHelper jsonHelper= new JsonHelper();

        //when
        SubscribeParams paramsSubscribe = new SubscribeParams(name, role, Arrays.asList(message));
        String subscribe = jsonHelper.json(methodSubscribe, paramsSubscribe, idSubscribe);


        PingParams paramsPing = new PingParams();
        String ping = jsonHelper.json(methodPing, paramsPing, idPing );

        MonitoringMock monitoringMock = new MonitoringMock();
        int clientCountBefore = monitoringMock.getClientsCount();


        long startTime = System.currentTimeMillis();
        long pingTime = startTime + pingTimeoutMS;

        HTTPHelper httpHelper = new HTTPHelper();
        HttpResponse subscriptionResponse = httpHelper.execute(httpHelper.CreatePostHttp(subscribe));


        while (subscriptionResponse.equals(null)){
            assertThat(monitoringMock.getClientsCount(), equalTo(clientCountBefore+1));
            HttpResponse pingResponse = null;
            if (System.currentTimeMillis() == pingTime){
                pingResponse = httpHelper.execute(httpHelper.CreatePostHttp(ping));
                pingTime = System.currentTimeMillis() + pingTimeoutMS;

                Response pingResponseObject = jsonHelper.jsonToResponse(pingResponse);
                assertThat(pingResponse.statusCode(), equalTo(200));
                assertThat(pingResponseObject.getJsonrpc(), equalTo("2.0"));
                assertThat(pingResponseObject.getId(), equalTo(1));
                assertThat(pingResponseObject.getResult(), equalTo(empty()));
            }
        }

        //then


        /// check the subscribe message response
        Response subscriptionResponseObject = jsonHelper.jsonToResponse(subscriptionResponse);

        assertThat(subscriptionResponse.statusCode(), equalTo(200));
        assertThat(subscriptionResponseObject.getJsonrpc(), equalTo("2.0"));
        assertThat(subscriptionResponseObject.getId(), equalTo(2));
        assertThat(subscriptionResponseObject.getResult(), equalTo("OK"));

        //when timeout is over check monitoring
        assertThat(monitoringMock.getClientsCount(), equalTo(clientCountBefore));

    }
}
