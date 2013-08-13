package conexiondb;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Alumno extends Persona {

    private String matricula;
    
    public Alumno()
    
    {
        
    }
    public Alumno(int id)
    {
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
               
        try
        {
                Statement st = conx.createStatement();
                ResultSet res = st.executeQuery("SELECT * FROM  alumno WHERE idAlumno="+String.valueOf(id));

                if (res.next()) {

                    this.setId(res.getInt("idAlumno"));
                    this.setNombre(res.getString("nombre"));
                    this.setApellidoPaterno(res.getString("ApellidoPaterno"));
                    this.setApellidoMaterno(res.getString("ApellidoMaterno"));
                    this.setDireccion(res.getString("Direccion"));
                    this.matricula=res.getString("matricula");
                    this.setFechaNacimiento(res.getString("Fechanacimiento"));         

               }
          conx.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }     
          
    }
    
    public Alumno(String matricula,String nombre,String apellidoPaterno,String apellidoMaterno,String fechanacimiento,String direccion)
    {
        this.matricula=matricula;
        this.setNombre(nombre);
        this.setApellidoPaterno(apellidoPaterno);
        this.setApellidoMaterno(apellidoMaterno);
        this.setDireccion(direccion);
        this.setFechaNacimiento(fechanacimiento.toString());  
        
    }
      
    public String getMatricula() {
        return matricula;
    }   
   
    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
                String sqlInsert="INSERT INTO alumno VALUES (default,?,?,?,?,?,?);";
                PreparedStatement pstmt=conx.prepareStatement(sqlInsert,PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, this.matricula);
                pstmt.setString(2, this.getNombre());
                pstmt.setString(3, this.getApellidoPaterno());
                pstmt.setString(4, this.getApellidoMaterno());
                pstmt.setString(5, this.getFechaNacimiento());
                pstmt.setString(6, this.getDireccion());
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
            sql="SELECT * FROM Alumno";
        }else{
      
             sql="SELECT * FROM Alumno WHERE matricula like '%"+valor+"%'";
        }    
        System.out.println(sql);
        
        try
        {
                Statement st = conx.createStatement();
                ResultSet res = st.executeQuery(sql+" order by idAlumno");

                while (res.next()) {
                    Persona persona=new Alumno(res.getInt("idAlumno"));
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
                
                String sqlInsert="UPDATE Alumno SET matricula=?,nombre=?,ApellidoPaterno=?,ApellidoMaterno=?,Fechanacimiento=?,Direccion=? WHERE idAlumno="+String.valueOf(this.getId());
                PreparedStatement pstmt=conx.prepareStatement(sqlInsert,PreparedStatement.RETURN_GENERATED_KEYS);
                
                             
                pstmt.setString(1, this.matricula);
                pstmt.setString(2, this.getNombre());
                pstmt.setString(3, this.getApellidoPaterno());
                pstmt.setString(4, this.getApellidoMaterno());
                pstmt.setString(5, this.getFechaNacimiento());
                pstmt.setString(6, this.getDireccion());
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
                st.execute("delete from alumno where idAlumno="+this.getId());        
                conx.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            
        }
        
    }

}