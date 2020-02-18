package com.banco.bancodopovo.jgi.enumeration;

/**
 * Classe enum responsável por definir os possíveis tipos de agência permitidas de acordo com a cidade
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public enum Cidade {

    Cajazeiras("1111-7"),
    Uiraúna("1165-7"),
    JocaClaudino("1113-4");

    private String agencia;

    Cidade (String agencia){
        this.agencia = agencia;
    }

    /** método responsável por pegar o valor de agência atribuido aos tipos de constantes do enum */
    public String getAgencia(){
        return agencia;
    }
}