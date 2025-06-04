package tarea_programada_poo_jeaustin_morales_hidalgo_javier_contreras_lopez.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ManejadorUsuarios {
 /*
    Clase que va a permitir hacer una simulacion de "soporte" que va a permitir actualizar datos de usuario,
    eliminar y consultar usuarios
   
    */
    
    private static  String Archivo = "usuarios_transito_cr.txt";

        //método que permite guardar los usuarios en archivo
    public static void guardarUsuario(Usuario usuario) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Archivo, true))) {
            writer.write(usuario.obtenerDatosUsuario());
            writer.newLine();
            writer.write("---------------------------------------------------------");
            writer.newLine();
        }
    }
    //metodo que devuelve una lista en la que se van a leer y agregar los archivos
    public static List<String> consultarUsuarios() throws IOException {
        List<String> usuarios = new ArrayList<>();
        try (BufferedReader entrada = new BufferedReader(new FileReader(Archivo))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                usuarios.add(linea);
            }
        }
        return usuarios;
    }
    //metodo que lee a los usuarios registrados y los convierte a String
    public static String leerUsuariosComoTexto() throws IOException {
        return new String(Files.readAllBytes(Paths.get(Archivo)));
    }
//metodo importante, ya que es el que permite actualizar la información de un usuario
   public static boolean actualizarUsuario(String cedula, String nuevoNombre, String nuevoTelefono, String nuevoCorreo) throws IOException {
    List<String> lineas = Files.readAllLines(Paths.get(Archivo));
    StringBuilder nuevoContenido = new StringBuilder();
    boolean actualizado = false;

    for (int i = 0; i < lineas.size(); ) {
        //esta linea detecta un bloque de usuario, y comienza a leer el bloque
        if (lineas.get(i).startsWith("Usuario registrado")) {
            StringBuilder bloqueUsuario = new StringBuilder();
            int j = i;

           //esta linea hace que se acumule el bloque de cada usuario separado por guiones
            while (j < lineas.size() && !lineas.get(j).startsWith("---------------------------------------------------------")) {
                bloqueUsuario.append(lineas.get(j)).append("\n");
                j++;
            }

           
            if (j < lineas.size()) {
                bloqueUsuario.append(lineas.get(j)).append("\n");
                j++;
            }
            //bloque que encuentra el usuario y le genera otra clave
            if (bloqueUsuario.toString().contains("Cédula: " + cedula)) {
                String nuevaClave = GeneradorClave.generarClave(8);
                nuevoContenido.append("Usuario registrado para Tránsito CR:\n")
                        .append("Nombre: ").append(nuevoNombre).append("\n")
                        .append("Teléfono: ").append(nuevoTelefono).append("\n")
                        .append("Correo: ").append(nuevoCorreo).append("\n")
                        .append("Cédula: ").append(cedula).append("\n")
                        .append("Contraseña: ").append(nuevaClave).append("\n")
                        .append("---------------------------------------------------------\n");
                actualizado = true;
                i = j; 
            } else {
                nuevoContenido.append(bloqueUsuario);
                i = j;
            }
        } else {
            nuevoContenido.append(lineas.get(i)).append("\n");
            i++;
        }
    }

    Files.write(Paths.get(Archivo), nuevoContenido.toString().getBytes());
    return actualizado;
}


   
   //Método que elimina un usuario con base a su cedula
    public static boolean eliminarUsuario(String cedula) throws IOException {
       //arraylist que permite leer linea por linea el archivo y lo guarda en una lista
        List<String> lineas = Files.readAllLines(Paths.get(Archivo));
        StringBuilder nuevoContenido = new StringBuilder();
        boolean eliminado = false;
        //este for permite recorrer las lineas del archivo con base a su longitud, sin incrementar
        for (int i = 0; i < lineas.size();) {
            if (lineas.get(i).startsWith("Usuario registrado")) {
                StringBuilder bloqueUsuario = new StringBuilder();
                int j = i;

                while (j < lineas.size() && !lineas.get(j).startsWith("---------------------------------------------------------")) {
                    bloqueUsuario.append(lineas.get(j)).append("\n");
                    j++;
                }

                if (j < lineas.size()) {
                    bloqueUsuario.append(lineas.get(j)).append("\n");
                    j++;
                }

                if (bloqueUsuario.toString().contains("Cédula: " + cedula)) {
                    eliminado = true;
                    i = j; 
                } else {
                    nuevoContenido.append(bloqueUsuario);
                    i = j;
                }

            } else {
                nuevoContenido.append(lineas.get(i)).append("\n");
                i++;
            }
        }

        Files.write(Paths.get(Archivo), nuevoContenido.toString().getBytes());
        return eliminado;
    }
}
