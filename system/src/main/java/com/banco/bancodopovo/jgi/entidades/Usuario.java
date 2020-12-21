package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;
import java.util.Objects;

/**
 * Classe responsável por representar um usuário
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */

public class Usuario{

    private String nome;
    private String cpf;
    private String email;
    private String nascimento;
    private String estado;
    private Cidade cidade;
    private TipoConta tipoConta;
    private String senha;

    /** construtor para definir os valores inicias de dados relacionados a um objeto do tipo usuário */

    public Usuario(String nome, String cpf, String email, String nascimento, String estado, Cidade cidade,  TipoConta tipoConta, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.nascimento = nascimento;
        this.estado = estado;
        this.cidade = cidade;
        this.tipoConta = tipoConta;
        this.senha = senha;
    }
    /** getters e setter */
    public String getNome() {
        return nome;
    }
    /** getters e setter */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /** getters e setter */
    public String getCpf() {
        return cpf;
    }
    /** getters e setter */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    /** getters e setter */
    public String getEmail() {
        return email;
    }
    /** getters e setter */
    public void setEmail(String email) {
        this.email = email;
    }
    /** getters e setter */
    public String getNascimento() {
        return nascimento;
    }
    /** getters e setter */
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    /** getters e setter */
    public String getEstado() {
        return estado;
    }
    /** getters e setter */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /** getters e setter */
    public Cidade getCidade() {
        return cidade;
    }
    /** getters e setter */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    /** getters e setter */
    public TipoConta getTipoConta() {
        return tipoConta;
    }
    /** getters e setter */
    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
    /** getters e setter */
    public String getSenha() {
        return senha;
    }
    /** getters e setter */
    public void setSenha(String senha) {
        this.senha = senha;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) &&
                Objects.equals(cpf, usuario.cpf) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(nascimento, usuario.nascimento) &&
                Objects.equals(estado, usuario.estado) &&
                cidade == usuario.cidade  &&
                tipoConta == usuario.tipoConta &&
                Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, email, nascimento, estado, cidade, tipoConta, senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", nascimento=" + nascimento +
                ", estado='" + estado + '\'' +
                ", cidade=" + cidade +
                ", tipoConta=" + tipoConta + '\'' +
                ", senha='" + senha+
                '}';
    }
}
