package com.banco.bancodopovo.jgi.enumeration;


/**
 * Classe enum responsável por definir os possíveis tipos de conta permitidas de acordo com a cidade
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public enum TipoConta {

    Corrente(1),
    Poupanca(2),
    Mista(3);

    private int tipo;

    TipoConta (int tipo){
        this.tipo = tipo;
    }

    /** método responsável por pegar o tipo de conta atribuido aos tipos de constantes do enum */
    public int getTipo(){
        return this.tipo;
    }


}
