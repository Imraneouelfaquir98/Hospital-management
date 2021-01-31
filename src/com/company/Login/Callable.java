package com.company.Login;

@FunctionalInterface
public interface Callable {
    void setResult(boolean loggedIn, String username);

}
