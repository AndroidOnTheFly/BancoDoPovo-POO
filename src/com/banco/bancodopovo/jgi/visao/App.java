package com.banco.bancodopovo.jgi.visao;

import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {


       Usuario usuario = new Usuario("André", "111.111.111-01", "andre@ifpb.com",
              LocalDate.now(), "PB", Cidade.Cajazeiras, TipoConta.Corrente, "123456");
        System.out.println(usuario.toString());

        System.out.println(usuario.getCidade().getAgencia());


        ContaCorrente contaCorrente = new ContaCorrente(usuario, usuario.getCidade().getAgencia() );
        System.out.println(contaCorrente);
        contaCorrente.depositar(200);
        System.out.println(contaCorrente);

        //launch(args);


    }
}