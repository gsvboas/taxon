package br.ufscar.dc.pibd.dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.InetAddress;

abstract public class GenericDAO {

    public GenericDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        /* Conexão banco de dados postgresql */
        String ip;
        try {
            String dockerDatabaseServiceName = "taxon-pg";
            ip = InetAddress.getByName(dockerDatabaseServiceName).getHostAddress();
        }catch(UnknownHostException e){
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection("jdbc:postgresql://" + ip + "/postgres", "admin", "abcdefgh");
    }
}