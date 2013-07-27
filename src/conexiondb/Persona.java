

package conexiondb;

import java.util.ArrayList;



public class Persona {

  public String nombre;
 public String apellidoPaterno;
  private String apellidoMaterno;
  private String fechaNacimiento;
  private String direccion;
  private Integer id;

  
  
  
  public int crear() {
      return 0;
  }

  public void consultar() {
  }

  public void actualizar() {
  }

  public void eliminar() {
  }

  public ArrayList<Persona> listado(String valor) {
        return null;
  }
  
    public String getNombre() {
        return nombre;
    }

   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    
    public String getDireccion() {
        return direccion;
    }

   
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    public Integer getId() {
        return id;
    }

  
    public void setId(Integer id) {
        this.id = id;
    }

}