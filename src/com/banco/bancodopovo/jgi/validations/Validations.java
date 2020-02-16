package com.banco.bancodopovo.jgi.validations;

import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.stream.IntStream;

public class Validations {

    public static boolean isCPF(String CPF) {

        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public static boolean validarNome(String nome){
        boolean v = nome.matches(".*\\d.*");
        if(!v && nome.length() > 5){
            return true;
        }
        return false;
    }

    public static boolean validarEmail(String email){
       return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }

    public static int validarSenha(String senha, String confirm){
        String passObj = new String(senha);
        String confirmPassObj = new String(confirm);
        if(senha.length() < 6){
            return 0;
        }
        if(passObj.equals(confirmPassObj)){
            return 1;
        }
        return 2;
    }

    public static String validarDate(LocalDate date){
        if(date == null){
            return null;
        }else{
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public static Cidade validarCidade(String city){
        Cidade cidade;
        if(Cidade.Cajazeiras.name() == city)
            cidade = Cidade.Cajazeiras;
        else if(Cidade.JocaClaudino.name() == city)
            cidade = Cidade.JocaClaudino;
        else if(Cidade.Uiraúna.name() == city)
            cidade = Cidade.Uiraúna;
        else
            cidade = null;

        return cidade;
    }

    public static TipoConta validarTipoConta(String cc, String cp){
        TipoConta tipo;
        if(cc.length() != 0 && cp.length() == 0)
            tipo = TipoConta.Corrente;
        else if(cc.length() == 0 && cp.length() != 0)
            tipo = TipoConta.Poupanpaça;
        else if(cc.length() != 0 && cp.length() != 0)
            tipo = TipoConta.Mista;
        else
            tipo = null;

        return tipo;
    }


}
