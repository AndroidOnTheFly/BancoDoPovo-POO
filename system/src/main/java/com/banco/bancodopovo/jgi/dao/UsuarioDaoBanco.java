package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.interfaceDao.UsuarioDao;
import com.banco.bancodopovo.jgi.validations.Validations;
import java.sql.ResultSet;

/**
 * Classe responsável por armazenar métodos de acesso de dados relacionados à um usuário
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public class UsuarioDaoBanco implements UsuarioDao {


    private ConFactory connection;

    public UsuarioDaoBanco(){
       connection = new ConFactory();
    }

    /** método responsável por inserir um usuário no banco de dados */
    @Override
    public boolean insertUsuario(Usuario usuario){

        String sql = "insert into cliente (cpf,nome,email,cidade,estado,nascimento,tipoconta,senha) values ("
                + "'" + usuario.getCpf() + "'," + "'" + usuario.getNome() + "'," + "'" + usuario.getEmail()
                + "'," + "'" + usuario.getCidade() + "'," + "'" + usuario.getEstado() + "'," +
                "'" + usuario.getNascimento() + "',"
                + "'" + usuario.getTipoConta() + "'," + "'" + usuario.getSenha() + "')";

        int connectionResult = connection.executeSQL(sql,true);
        if(connectionResult > 0){
            return true;
        }
        return false;


    }
    /** método responsável por atualizar informações de um usuário no banco de dados */
    @Override
    public boolean updateUsuario(Usuario usuario) {

        String sql = "update cliente set nome = '" + usuario.getNome() + "'," + "email = '" + usuario.getEmail()
                +"',cidade = '"+ usuario.getCidade().name() + "',senha = '" + usuario.getSenha() + "'" + " where cpf = '" +
                usuario.getCpf() + "'";

        int connectionResult = connection.executeSQL(sql,false);
        if(connectionResult > 0){
            return true;
        }
        return false;
    }
    /** método responsável deletar um usuário no banco de dados */
    @Override
    public boolean deleteUsuario(Usuario usuario) {
        String sql = "DELETE FROM cliente WHERE cpf = '" + usuario.getCpf() + "'";
        int result = connection.executeSQL(sql,true);
        if(result > 0)
            return true;
        return false;
    }
    /** método responsável por selecionar um usuário no banco de dados através do seu cpf ou email */
    @Override
    public Usuario getUsuarioBy(String data,String bySearch) {

        ResultSet result = connection.getQueryResult("SELECT * from cliente WHERE " + bySearch + "= '" + data + "'",false);

        int countRow = 0;
        String cpf = "", nome = "", email = "", cidade = "", estado = "",nascimento = "", tipoconta = "",senha = "",cc = "", cp = "";

        try{
            while(result.next()){

                countRow ++;

                cpf = result.getString("cpf");
                nome = result.getString("nome");
                email = result.getString("email");
                cidade =result.getString("cidade");
                estado = result.getString("estado");
                nascimento = result.getString("nascimento");
                tipoconta = result.getString("tipoconta");
                senha = result.getString("senha");

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if(countRow == 1){
            if(tipoconta.equals("mista")){
                cc = "corrente";
                cp = "poupança";
            }
            return (new Usuario(nome,cpf,
                    email,nascimento,estado,Validations.validarCidade(cidade), Validations.validarTipoConta(cc,cp),senha));
        }
        return null;
    }


}
