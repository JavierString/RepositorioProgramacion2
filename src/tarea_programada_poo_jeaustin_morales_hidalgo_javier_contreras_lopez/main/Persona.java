package tarea_programada_poo_jeaustin_morales_hidalgo_javier_contreras_lopez.main;

public class Persona {
    //atributos protegidos de la clase persona 
    protected String nombre;
    protected String telefono;
    protected String correo;
    protected String cedula;
    
    //constructor de persona
    public Persona(String nombre, String telefono, String correo, String cedula) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.cedula = cedula;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
}
