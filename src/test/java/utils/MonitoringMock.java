package utils;

import java.sql.SQLOutput;

public class MonitoringMock {
    int clientCount;

    public int getClientsCount(){
        // Facade should send the mock clientCounts and to check that we want to have this info

        return clientCount;
    }
}
