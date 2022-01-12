package br.com.dominio.classes.Connection;

public class Key {
    private String user;
    private String password;
    public Key(String user, String password){
        this.user=user;
        this.password=password;
    }
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
}
