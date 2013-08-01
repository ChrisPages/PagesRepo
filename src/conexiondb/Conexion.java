/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiondb;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ernaes.trujillo
 */
public class Conexion {

    private String usuarioDB;
    private String contrasenaDB;
    private String servidorDB;
    private String baseDatos;
    private String driver;
    private String url;
    private Connection conn;
    
    
    public Conexion() {
       //this.servidorDB=servidorDB;
       this.baseDatos="proyect";
       this.usuarioDB="root";
       this.contrasenaDB="";
       this.servidorDB="localhost";//127.0.0.1
       
       url = "jdbc:mysql://"+this.servidorDB+":3306/";
       driver = "com.mysql.jdbc.Driver";

        
    }

    
public void conectate()
{
    
    try {
            Class.forName(driver).newInstance();
            this.setConn(DriverManager.getConnection(url + this.baseDatos, this.usuarioDB, this.contrasenaDB));


            //conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
}
/**
 * @return the usuarioDB
 */
public String getUsuarioDB() {
        return usuarioDB;
    }

    /**
     * @param usuarioDB the usuarioDB to set
     */
    public void setUsuarioDB(String usuarioDB) {
        this.usuarioDB = usuarioDB;
    }

    /**
     * @return the contrasenaDB
     */
    public String getContrasenaDB() {
        return contrasenaDB;
    }

    /**
     * @param contrasenaDB the contrasenaDB to set
     */
    public void setContrasenaDB(String contrasenaDB) {
        this.contrasenaDB = contrasenaDB;
    }

    /**
     * @return the servidorDB
     */
    public String getServidorDB() {
        return servidorDB;
    }

    /**
     * @param servidorDB the servidorDB to set
     */
    public void setServidorDB(String servidorDB) {
        this.servidorDB = servidorDB;
    }

    /**
     * @return the baseDatos
     */
    public String getBaseDatos() {
        return baseDatos;
    }

    /**
     * @param baseDatos the baseDatos to set
     */
    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public void cerrar()
    {
        try {
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
