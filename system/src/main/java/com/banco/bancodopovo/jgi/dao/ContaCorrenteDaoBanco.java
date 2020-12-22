package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.interfaceDao.ContaDao;
import com.banco.bancodopovo.jgi.modelo.Conta;

import java.sql.ResultSet;

/**
 * Classe responsável por armazenar métodos de acesso de dados relacionados à uma conta corrente
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public class ContaCorrenteDaoBanco implements ContaDao {

    private ConFactory connection;

    public ContaCorrenteDaoBanco(){
        connection = new ConFactory();
    }

    public void setConnection(ConFactory conexao) {
        connection = conexao;
    }
    /** método responsável por inserir uma conta corrente no banco de dados */
    @Override
    public boolean insertConta(Conta conta) {
        ContaCorrente c = (ContaCorrente) conta;
        String sql = "insert into contacorrente (numconta,agencia,saldo,cpf) values (" + "'" + c.getNumContaCorrent()
                + "'," + "'" + c.getAgencia() + "'," + "'" + c.getSaldo() + "'," + "'" + c.getUsuario().getCpf() +"')";
        int connectionResult = connection.executeSQL(sql,true);
        if(connectionResult > 0){
            return true;
        }
        return false;
    }
    /** método responsável por selecionar dados de uma conta corrente armazenada no banco de dados */
    @Override
    public ContaCorrente getConta(Usuario cliente) {
        String sql = "SELECT * from contacorrente WHERE cpf = '"+cliente.getCpf()+"'";
        ResultSet result = connection.getQueryResult(sql,true);

        String numconta = "",agencia = "";
        double saldo = 0.0;

        try{
            while(result.next()){
                System.out.println("HEY!!!");
                numconta = result.getString("numconta");
                agencia = result.getString("agencia");
                saldo = result.getDouble("saldo");
            }
            ContaCorrente conta = new ContaCorrente(cliente,agencia);
            conta.setConta(numconta);
            conta.setSaldo(saldo);

            return conta;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    /** método responsável por atualizar informações de uma conta corrente armazenada no banco de dados */
    @Override
    public boolean updateConta(Conta conta,double novoSaldo) {

        ContaCorrente c = (ContaCorrente) conta;
        String sql = "update contacorrente set saldo = " + novoSaldo + " where cpf = " + "'"
                + c.getUsuario().getCpf() + "'";
        int connectionResult = connection.executeSQL(sql,false);
        if(connectionResult > 0){
            return true;
        }
        return false;

    }

    /** método responsável por deletar uma conta corrente armazenada no banco de dados */
    @Override
    public boolean deleteConta(Conta conta) {
        String sql = "DELETE FROM contacorrente WHERE numconta = '" + ((ContaCorrente)conta).getNumContaCorrent() + "'";
        int result = connection.executeSQL(sql,true);
        if(result > 0)
            return true;
        return false;
    }

    /** método responsável por selecionar uma conta através do cpf de um usuário */
    @Override
    public Conta getContaByCpf(String cpf) {
        Usuario transferUser = new UsuarioDaoBanco().getUsuarioBy(cpf,"cpf");
        if(transferUser == null)
            return null;
        ContaCorrente contaDestino = getConta(transferUser);
        return (contaDestino == null ? null : contaDestino);
    }
}
