package com.banco.bancodopovo.jgi.enumeration;

public enum TipoConta {

    Corrente(1),
    Poupanpaça(2),
    Mista(3);

    private int tipo;

    TipoConta (int tipo){
        this.tipo = tipo;
    }

    public int getTipo(){
        return tipo;
    }


}
