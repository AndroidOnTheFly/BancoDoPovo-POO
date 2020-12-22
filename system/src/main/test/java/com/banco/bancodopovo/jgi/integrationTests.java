package com.banco.bancodopovo.jgi;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.dao.ContaCorrenteDaoBanco;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import junit.framework.TestCase;

import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;

public class integrationTests {

    private JdbcDatabaseContainer container;
    private IDatabaseTester databaseTester;
    private ConFactory connection;

    @Before
    public void setUp() throws Exception {
        if(container == null) {
            PostgreSQLContainer postgreSQLContainer;
            postgreSQLContainer = new PostgreSQLContainer().withUsername("postgresql").withPassword("8975424").withDatabaseName("postgres");
            container = postgreSQLContainer.withInitScript("com/banco/bancodopovo/jgi/postgres/script.sql");
            container.start();
            connection = new ConFactory().setConnection(DriverManager.getConnection(container.getJdbcUrl(),"postgresql","8975424"));
        }
        configurarDBUnit();
        databaseTester.onSetup();
    }

    private void configurarDBUnit() throws ClassNotFoundException, FileNotFoundException, DataSetException {
        System.out.println(System.getProperty("user.dir"));
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("system/src/main/test/java/com/banco/bancodopovo/jgi/resources/client_testset.xml"));
        this.databaseTester = new JdbcDatabaseTester("org.postgresql.Driver", container.getJdbcUrl(),"postgresql","8975424");
        this.databaseTester.setDataSet(dataSet);
        this.databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        this.databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
    }

    @Test
    public void testarClienteCadastradoPeloCpf() {
        UsuarioDaoBanco usuarioDao = new UsuarioDaoBanco();
        usuarioDao.setConnection(connection);
        Usuario usuarioDoBanco = usuarioDao.getUsuarioBy("10882651463","cpf");
        Assert.assertEquals("10882651463",usuarioDoBanco.getCpf());
    }

    @Test
    public void testarTransacao() {
        Usuario usuario1 = new Usuario("joao pedro","10882651463","abc@hotmail.com","1997-12-18","paraiba",Cidade.Cajazeiras, TipoConta.Corrente,"123456");
        Usuario usuario2 = new Usuario("pedro arthur","12701115078","pedro@hotmail.com","1997-02-07","paraiba",Cidade.Cajazeiras,TipoConta.Corrente,"123456");
        ContaCorrente contaCorrenteUsuario1 = new ContaCorrente(usuario1,usuario1.getCidade().getAgencia());
        ContaCorrente contaCorrenteUsuario2 = new ContaCorrente(usuario2,usuario2.getCidade().getAgencia());

        contaCorrenteUsuario1.setSaldo(1500.00);
        contaCorrenteUsuario1.transferir(contaCorrenteUsuario2,250.00);

        ContaCorrenteDaoBanco contaCorrenteDao = new ContaCorrenteDaoBanco();
        contaCorrenteDao.setConnection(connection);

        contaCorrenteDao.updateConta(contaCorrenteUsuario2, contaCorrenteUsuario2.getSaldo());

        double novoSaldo = contaCorrenteDao.getConta(usuario2).getSaldo();
        Assert.assertEquals(250.00,novoSaldo,0.01);

    }

}





