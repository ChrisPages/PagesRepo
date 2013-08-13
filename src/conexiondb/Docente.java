/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiondb;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Chris
 */
public class Docente extends Persona{
     private String GradoEstudio;
     private String Cedula;

public Docente()
    
    {
        
    }
    public Docente(int id)
    {
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
               
        try
        {
                Statement st = conx.createStatement();
                ResultSet res = st.executeQuery("SELECT * FROM  Profesor WHERE idProfesor="+String.valueOf(id));

                if (res.next()) {

                    this.setId(res.getInt("idProfesor"));
                    this.setNombre(res.getString("nombre"));
                    this.setApellidoPaterno(res.getString("apellido_paterno"));
                    this.setApellidoMaterno(res.getString("apellido_materno"));
                    this.setDireccion(res.getString("direccion"));
                    this.GradoEstudio=res.getString("GradoEstudio");
                    this.Cedula=res.getString("Cedula");
                    this.setFechaNacimiento(res.getString("fecha_nacimiento"));         

               }
          conx.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }     
          
    }
    
    public Docente(String GradoEstudio,String Cedula,String nombre,String apellidoPaterno,String apellidoMaterno,String fechaNacimiento,String direccion)
    {
        this.setNombre(nombre);
        this.setApellidoPaterno(apellidoPaterno);
        this.setApellidoMaterno(apellidoMaterno);
        this.setDireccion(direccion);
        this.GradoEstudio=GradoEstudio;
        this.Cedula=Cedula;
        this.setFechaNacimiento(fechaNacimiento.toString());  
        
    }
      
    public String getGradoEstudio() {
        return GradoEstudio;
    }   
   
    public void setGradoEstudio(String GradoEstudio) {
        this.GradoEstudio = GradoEstudio;
    }
    /**
     * @return the Cedula
     */
    public String getCedula() {
        return Cedula;
    }

    /**
     * @param Cedula the Cedula to set
     */
    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }
    
    @Override
    public int crear()
    {
        int retorno=0;
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
        
        try
        {
                Statement st = conx.createStatement();
                String sqlInsert="INSERT INTO Profesor VALUES (default,?,?,?,?,?,?);";
                PreparedStatement pstmt=conx.prepareStatement(sqlInsert,PreparedStatement.RETURN_GENERATED_KEYS);
                
                pstmt.setString(1, this.getNombre());
                pstmt.setString(2, this.getApellidoPaterno());
                pstmt.setString(3, this.getApellidoMaterno());
                pstmt.setString(4, this.getFechaNacimiento());
                pstmt.setString(5, this.getDireccion());
                pstmt.setString(7, this.Cedula);
                int n=pstmt.executeUpdate();
                
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Guardado con Exito");
                }
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs != null && rs.next()) {
                     this.setId( rs.getInt(1));
                }
                else
                {
                    retorno=-1;
                }
               
                
                conx.close();
        }catch(SQLException | HeadlessException e)
        {
            System.out.println(e.getMessage());
            retorno=-1;
        }
        
        return retorno;
    }
    
    
    @Override
     public ArrayList<Persona> listado(String valor) {
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
        ArrayList<Persona> resultado=new ArrayList();
        
        String sql="";
        
        if(valor.equals(""))
        {
            sql="SELECT * FROM Profesor";
        }else{
      
             sql="SELECT * FROM Profesor WHERE idProfesor like '%"+valor+"%'";
        }    
        System.out.println(sql);
        
        try
        {
                Statement st = conx.createStatement();
                ResultSet res = st.executeQuery(sql+" order by idProfesor");

                while (res.next()) {
                    Persona persona=new Alumno(res.getInt("idProfesor"));
                    resultado.add(persona);

               }
          conx.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }     
        
        return resultado;
     }
    //inicio de update
    
 
      public int actualiza() {
        int retorno=0;
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
            
        try
        {
            
             System.out.println(this.getId());
                Statement st = conx.createStatement();
                
                String sqlInsert="UPDATE Profesor SET nombre=?,apellidoPat=?,apellidoMat=?,fechaNac=?,direccion=?,cedula=?,carrera=? WHERE idalumno="+String.valueOf(this.getId());
                PreparedStatement pstmt=conx.prepareStatement(sqlInsert,PreparedStatement.RETURN_GENERATED_KEYS);
                
                
                pstmt.setString(1, this.getNombre());
                pstmt.setString(2, this.getApellidoPaterno());
                pstmt.setString(3, this.getApellidoMaterno());
                pstmt.setString(4, this.getFechaNacimiento());
                pstmt.setString(5, this.getDireccion());
                pstmt.setString(6, this.getCedula());
                
                
                pstmt.executeUpdate(); 
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs != null && rs.next()) {
                     this.setId( rs.getInt(1));
                }
                else
                {
                    retorno=-1;
                }
               
                
                conx.close();
        }catch(Exception e)
        {
            System.out.println("Actualizar Error");
            System.out.println(e.getMessage());
            retorno=-1;
        }
        
        return retorno;
             
        }  
    //fin del update
    
    
    
   @Override
    public void eliminar() {
        
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
               
        try
        {
                Statement st = conx.createStatement();                
                st.execute("delete from Profesor where idProfesor="+this.getId());        
                conx.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            
        }
        
    }

  }