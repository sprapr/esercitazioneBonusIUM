package com.example.esercitazionebonus;

public class User {

    private String username,password,citta,dataNascita;

    public User(String u, String pwd, String citta, String data){
        this.username = u;
        this.password = pwd;
        this.citta = citta;
        this.dataNascita = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }
}
