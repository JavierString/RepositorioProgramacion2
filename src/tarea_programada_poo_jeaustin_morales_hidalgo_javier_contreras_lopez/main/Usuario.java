package tarea_programada_poo_jeaustin_morales_hidalgo_javier_contreras_lopez.main;

public class Usuario extends Persona implements Registro {

    private String clave;

    public Usuario(String nombre, String telefono, String correo, String cedula, String clave) {
        super(nombre, telefono, correo, cedula);
        this.clave = clave;
    }

    @Override
    public String obtenerDatosUsuario() {
        return "Usuario registrado para Tránsito CR:\n"
                + "Nombre: " + nombre + "\n"
                + "Teléfono: " + telefono + "\n"
                + "Correo: " + correo + "\n"
                + "Cédula: " + cedula + "\n"
                + "Contraseña: " + clave;
    }
}

