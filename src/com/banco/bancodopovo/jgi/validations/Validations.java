package com.banco.bancodopovo.jgi.validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class Validations {

    public static int validarCPF(String cpf, boolean inicio) {

        String numeriCpf = cpf.replaceAll("-?[^\\d]","");

        if (numeriCpf.matches("\\d{11}")) {
            boolean excecao = IntStream.range(0, 9).boxed().filter(num -> (((11111111111L*num) + "").equals(numeriCpf))).findFirst().orElse(-1) != -1;
            int soma = 0, peso = inicio ? 10 : 11;
            //somando os digitos por um peso decrescente
            for (int i = 0; i < (inicio ? 9 : 10); i++) {
                soma += Integer.parseInt(numeriCpf.charAt(i) + "") * peso--;
            }
            //calculando o resto
            peso = soma * 10 % 11 == 10 ? 0 : soma * 10 % 11;
            //se inicio = true a validação ocorre no index 9 do array, se não no index 11
            return excecao || !(peso + "").equals(numeriCpf.charAt(inicio ? 9 : 10) + "") ? 0 : inicio ? validarCPF(numeriCpf, false) : 1;
        } else {
            return 3;
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


}
