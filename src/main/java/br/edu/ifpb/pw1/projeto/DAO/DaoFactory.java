package br.edu.ifpb.pw1.projeto.DAO;

import br.edu.ifpb.pw1.projeto.DAOBD.AtivoDAOBD;
import br.edu.ifpb.pw1.projeto.DAOBD.CarteiraDAOBD;
import br.edu.ifpb.pw1.projeto.DAOBD.TransacaoDAOBD;
import br.edu.ifpb.pw1.projeto.DAOBD.UserDAOBD;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

    private static String TIPO_DAO;

    static {
        InputStream  inputStream = DaoFactory.class.getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TIPO_DAO = properties.getProperty("dao.tipo");
    }

    public static UserDAO criarUserDAO() throws SQLException {
        UserDAO userDAO = null;
        switch (TIPO_DAO) {
            case "BANCO":
                userDAO = new UserDAOBD();
                break;
        }
        return userDAO;
    }
    public static AtivoDAO criarAtivoDAO() throws SQLException {
        AtivoDAO ativoDAO = null;
        switch (TIPO_DAO) {
            case "BANCO":
                ativoDAO = new AtivoDAOBD();
                break;
        }
        return ativoDAO;
    }
    public static CarteiraDAO criarCarteiraDAO() throws SQLException {
        CarteiraDAO carteiraDAO = null;
        switch (TIPO_DAO) {
            case "BANCO":
                carteiraDAO = new CarteiraDAOBD();
                break;
        }
        return carteiraDAO;
    }

    public static TransacaoDAO criarTransacaoDAO() throws SQLException {
        TransacaoDAO transacaoDAO = null;
        switch (TIPO_DAO) {
            case "BANCO":
                transacaoDAO = new TransacaoDAOBD();
                break;
        }
        return transacaoDAO;
    }

}
