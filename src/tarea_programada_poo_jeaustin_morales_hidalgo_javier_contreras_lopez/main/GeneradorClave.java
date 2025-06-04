package tarea_programada_poo_jeaustin_morales_hidalgo_javier_contreras_lopez.main;

import java.util.Random;
//clase sumamente importante ya que es la que crea la clave unica para cada usuario
public class GeneradorClave {
 public static String generarClave(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder clave = new StringBuilder();
        Random rand = new Random();
        
        //for que va escribiendo caracteres random que esté, dento de la variable caracteres
        for (int i = 0; i < longitud; i++) {
            clave.append(caracteres.charAt(rand.nextInt(caracteres.length())));
        }

        return clave.toString();
    }
}
