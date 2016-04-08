package com.swchung.softchain;

/**
 * Created by icete on 2016-04-06.
 */
public class NetworkManager {
    private static NetworkManager ourInstance = new NetworkManager();

    public static NetworkManager getInstance() {
        return ourInstance;
    }

    private NetworkManager() {
    }
}
