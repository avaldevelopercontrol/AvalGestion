package Models;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Conection {

    public static java.sql.Connection getConexion() throws SQLException, Exception { 
        
        java.sql.Connection cnn = null;
        
        try {
            Context iniContext = new InitialContext();
            Context envContext = (Context) iniContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/cobra");
            cnn = ds.getConnection();
        } catch (SQLException sqlexception) {
            System.out.println(sqlexception.toString());
            throw sqlexception;
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return cnn;
    }
    
    public static java.sql.Connection getConexion(String resource_name) throws SQLException, Exception {
         //jdbc/patron
        java.sql.Connection cnn = null;

        try {
            Context iniContext = new InitialContext();
            Context envContext = (Context) iniContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup(resource_name);
            cnn = ds.getConnection();
        } catch (SQLException sqlexception) {
            System.out.println(sqlexception.toString());
            throw sqlexception;
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return cnn;
    }
    
}
