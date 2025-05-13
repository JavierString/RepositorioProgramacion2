package Tarea_Sockets_JavierContrerasLopez_JeaustinMoralesHidalgo.src;
import java.io.*;
import java.net.*;
import javax.swing.*;


/*
 Javier Contreras López (Cliente) C4E402 
 Jeaustin Morales Hidalgo (Servidor) C4H694

 fecha: 12/05/2025
 */


public class TCP_Cliente {

    /*TCP de parte del cliente, adaptado en interfaz gráfica por JOptionPane para la entreda de datos 
      
      El código fuente antes devolvia un String del usuario convertido a mayúsculas, este ahora devuelve un-
      número decimal enviado por el usuario y el server le devuelve el numero convertido en notación binaria 
     
    */


     public static void main(String[] args){
        //variable String para la entrada de datos, en el cual tiene que ingresar un numero 
         String entrada = JOptionPane.showInputDialog(null, "Ingrese un número (o 'salir'):", "Cliente TCP", JOptionPane.QUESTION_MESSAGE);

         /* Este bloque de código verifica si la entrada es nula o si se escribe "salir" sin importar mayusculas o minusculas-
          Si es así entonces sale el mensaje de cliente finalizado */
        if (entrada == null || entrada.equalsIgnoreCase("salir")) {
            JOptionPane.showMessageDialog(null, "Cliente finalizado.");
            return;
        }

        try {
            //se crea un objeto cliente de tipo Socker con su hostname y en el puerto 7777
            Socket clientSocket = new Socket("DESKTOP-4T1NPFT", 7777);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.writeBytes(entrada + "\n");
            String respuestaServidor = inFromServer.readLine();
            //imprime la respuesta del servidor el cual es el numero en binario
            JOptionPane.showMessageDialog(null, "Respuesta del servidor: " + respuestaServidor);

            //finaliza la conexión del cliente mediante este metodo
            clientSocket.close();
            //caso de excepcion por si acasoel numero de puerto no es igual
        } catch (IOException excepcion) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + excepcion.getMessage());
    }





 }


}
