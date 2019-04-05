package top.zerotop.websocket;

import java.io.Serializable;
import java.security.Principal;

public final class User implements Serializable, Principal {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}