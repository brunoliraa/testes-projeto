import br.edu.ifpb.pw1.projeto.DAO.*;
import br.edu.ifpb.pw1.projeto.DAOBD.UserDAOBD;
import br.edu.ifpb.pw1.projeto.model.*;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.servlet.ServletException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDAOTest {
    private UserDAO userDAO;
    private IDatabaseTester databaseTester;
    private Connection connection;
    private Conexao conexao;



    private void configureDBUnit() throws DataSetException, FileNotFoundException, ClassNotFoundException  {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(("src/test/resources/user_testset.xml")));
        this.databaseTester = new JdbcDatabaseTester("org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/SimuladorInvestimentos", "postgres", "93086585");
        this.databaseTester.setDataSet(dataSet);
        this.databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        this.databaseTester.setTearDownOperation(DatabaseOperation.DELETE);

    }

    //Cadastro
    @Test
    public void CadastrarUserValido() throws Exception {
        userDAO = new UserDAOBD();
        User user = new User();
        user.setNome("user3");
        user.setEmail("user4@email.com");
        user.setSenha("123456");
        Long id = new Long(userDAO.buscarTodos().size());
        user.setId(id);

        CarteiraDAO carteiraDAO = DaoFactory.criarCarteiraDAO();
        Carteira carteira = carteiraDAO.cadastrarCarteira();
        user.setCarteira(carteira);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2001", formatter);
        user.setNascimento(date);

        Assert.assertTrue(userDAO.cadastrarUser(user));
    }

    @Test
    public void CadastrarUserEmailJaExistente() throws Exception {
        userDAO = new UserDAOBD();
        User user = new User();
        user.setNome("user2");
        user.setEmail("user4@email.com");
        user.setSenha("123456");
        Long id = new Long(userDAO.buscarTodos().size());
        user.setId(id);
        CarteiraDAO carteiraDAO = DaoFactory.criarCarteiraDAO();
        Carteira carteira = carteiraDAO.cadastrarCarteira();
        user.setCarteira(carteira);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2001", formatter);
        user.setNascimento(date);


       Assert.assertFalse(userDAO.cadastrarUser(user));
    }

    @Test
    public void CadastrarUserApenasCamposObrigatorios() throws Exception {
        userDAO = new UserDAOBD();
        User user = new User();
        user.setNome("user6");
        user.setEmail("user6@email.com");
        user.setSenha("123456");
        Long id = new Long(userDAO.buscarTodos().size());
        user.setId(id);
        CarteiraDAO carteiraDAO = DaoFactory.criarCarteiraDAO();
        Carteira carteira = carteiraDAO.cadastrarCarteira();
        user.setCarteira(carteira);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2001", formatter);
        user.setNascimento(date);

        //todos os campos sao obrigatórios
        Assert.assertTrue(userDAO.cadastrarUser(user));
    }


    @Test
    public void CadastrarUserCamposNaoPreenchidos() throws Exception {
        userDAO = new UserDAOBD();
        User user = new User();
        user.setNome("user2");
        user.setEmail("user7@email.com");
        user.setSenha("123456");
        Long id = new Long(userDAO.buscarTodos().size());
        user.setId(id);
        CarteiraDAO carteiraDAO = DaoFactory.criarCarteiraDAO();
        Carteira carteira = carteiraDAO.cadastrarCarteira();
        user.setCarteira(carteira);


        Assert.assertFalse(userDAO.cadastrarUser(user));
    }



    //Login

    @Test
    public void LoginUserCadastrado() throws Exception {
        userDAO = new UserDAOBD();
        User user = new User();
        user.setEmail("user@email.com");
        user.setSenha("123456");
        Assert.assertTrue(userDAO.autenticarUser(user.getEmail(),user.getSenha()).isPresent());
    }


    @Test
    public void LoginUserApenasEmail() throws Exception {
        userDAO = new UserDAOBD();
        User user = new User();
        user.setEmail("user@email.com");
        Assert.assertFalse(userDAO.autenticarUser(user.getEmail(),user.getSenha()).isPresent());
    }

    @Test
    public void LoginUserCamposEmbranco() throws Exception {
        userDAO = new UserDAOBD();
        User user = new User();
        Assert.assertFalse(userDAO.autenticarUser(user.getEmail(),user.getSenha()).isPresent());

    }

    @Test
    public void LoginUserEmailOuSenhaIncorretos() throws Exception {
        userDAO = new UserDAOBD();
        User user = new User();
        user.setEmail("trab@email.com");
        user.setSenha("123456");
        Assert.assertFalse(userDAO.autenticarUser(user.getEmail(),user.getSenha()).isPresent());
    }


    //Comprar Ativo

    @Test
    public void ComprarComSaldoSuficiente() throws Exception {
        List<AtivoJson> ativosJson = new ArrayList<>();
        ConexaoJsonApi conexaoJsonApi = new ConexaoJsonApi();
        ativosJson= conexaoJsonApi.buscarAtivos();
        User user = new User();
        user.setNome("fishboy");
        user.setEmail("fishboy@email.com");
        user.setSenha("123456");
        user.setId((long) 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2001", formatter);
        user.setNascimento(date);

        String idAtivo = "BBAS3.SA";
        BigDecimal quantidade = new BigDecimal(1);

        Carteira carteira = new Carteira();
        BigDecimal valor = new BigDecimal(1000);
        carteira.setValorCaixa(valor);
        carteira.setId((long) 2);
        user.setCarteira(carteira);

        AtivoJson ativoJson = conexaoJsonApi.buscarAtivoJson(ativosJson,idAtivo).orElseThrow(() -> new ServletException());
        Ativo ativo = new Ativo();


        ativo.setPrecoDeCompra(ativoJson.getPrice());
        ativo.setNome(ativoJson.getNameAtivo());
        ativo.setQuantidade(quantidade);
        ativo.setDisponibilidade(Disponibilidade.DISPONIVEL);



        AtivoDAO ativoDAO= DaoFactory.criarAtivoDAO();
        ativo = ativoDAO.CadastrarAtivo(ativo,carteira.getId());

        CarteiraDAO carteiraDAO = DaoFactory.criarCarteiraDAO();
        carteiraDAO.atualizarSaldo(carteira);
        user.setCarteira(carteira);


        Transacao transacao = new Transacao();
        transacao.setValor(ativo.getPrecoDeCompra().multiply(ativo.getQuantidade()));
        transacao.setAtivo(ativo);
        transacao.setUser(user);
        Long id = ativo.getId();
        transacao.setId(id);
        LocalDate localDate = LocalDate.now();
        transacao.setData(localDate);


        TransacaoDAO transacaoDAO = DaoFactory.criarTransacaoDAO();
        transacaoDAO.cadastrarTransacao(transacao);

        //Thread.currentThread().sleep(4000);

        //Assert.assertTrue(transacaoDAO.buscarTransacao(transacao.getId()).isPresent());
        Assert.assertTrue(transacaoDAO.buscarTodos(user.getId()).stream().filter(Transacao ->transacao.getId().equals(id)).findFirst().isPresent());


    }





    @Test
    public void comprarComSaldoInsuficiente() throws Exception {
        List<AtivoJson> ativosJson = new ArrayList<>();
        ConexaoJsonApi conexaoJsonApi = new ConexaoJsonApi();
        ativosJson= conexaoJsonApi.buscarAtivos();
        User user = new User();
        user.setNome("user");
        user.setEmail("user@email.com");
        user.setSenha("123456");
        user.setId((long) 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2001", formatter);
        user.setNascimento(date);

        String idAtivo = "BBAS3.SA";
        BigDecimal quantidade = new BigDecimal(1);

        Carteira carteira = new Carteira();
        BigDecimal valor = new BigDecimal(0);
        carteira.setValorCaixa(valor);
        carteira.setId((long) 1);
        user.setCarteira(carteira);

        AtivoJson ativoJson = conexaoJsonApi.buscarAtivoJson(ativosJson,idAtivo).orElseThrow(() -> new ServletException());
        Ativo ativo = new Ativo();


        ativo.setPrecoDeCompra(ativoJson.getPrice());
        ativo.setNome(ativoJson.getNameAtivo());
        ativo.setQuantidade(quantidade);
        ativo.setDisponibilidade(Disponibilidade.DISPONIVEL);



        AtivoDAO ativoDAO= DaoFactory.criarAtivoDAO();
        ativo = ativoDAO.CadastrarAtivo(ativo,carteira.getId());

        CarteiraDAO carteiraDAO = DaoFactory.criarCarteiraDAO();
        carteiraDAO.atualizarSaldo(carteira);
        user.setCarteira(carteira);


        Transacao transacao = new Transacao();
        transacao.setValor(ativo.getPrecoDeCompra().multiply(ativo.getQuantidade()));
        transacao.setAtivo(ativo);
        transacao.setUser(user);
        Long id = ativo.getId();
        transacao.setId(id);
        LocalDate localDate = LocalDate.now();
        transacao.setData(localDate);


        TransacaoDAO transacaoDAO = DaoFactory.criarTransacaoDAO();
        transacaoDAO.cadastrarTransacao(transacao);

        //vai falhar pq ta comprando com saldo insuficiente
        Assert.assertFalse(transacaoDAO.buscarTodos(user.getId()).stream().filter(Transacao ->transacao.getId().equals(id)).findFirst().isPresent());


    }




}
