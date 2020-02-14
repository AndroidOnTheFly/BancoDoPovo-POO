package com.banco.bancodopovo.jgi.enumeration;

public enum Cidade {
    Cajazeiras("1111-7"),
    Uiraúna("1165-7"),
    JocaClaudino("1113-4");

    private String agencia;

    Cidade (String agencia){
        this.agencia = agencia;
    }

    public String getAgencia(){
        return agencia;
    }
}