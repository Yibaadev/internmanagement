package co.kozao.internanagement.utils;

import java.util.HashSet;
import java.util.Set;

public class SessionManager {

    private static final Set<String>
            connectedUsers =
            new HashSet<>();

    public static boolean isConnected(
            String login) {

        return connectedUsers.contains(login);
    }

    public static void connect(
            String login) {

        connectedUsers.add(login);
    }

    public static void disconnect(
            String login) {

        connectedUsers.remove(login);
    }

    public static Set<String>
            getConnectedUsers() {

        return connectedUsers;
    }
}