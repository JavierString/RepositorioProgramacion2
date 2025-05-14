package Tarea_Sockets_JavierContrerasLopez_JeaustinMoralesHidalgo.src;
//** Aurtores: Jeaustin Morales Hidalgo(Servidor) Javier Contreras Lopéz (Cliente).
/* Fecha: 12/05/2025.
/*Creacion del servidor  para realizar la operacion de cambiar un numero a numero binario resiviendo los datos por parte del cliente*/

import java.io.*;
import java.net.*;
public class TCP_SERVIDOR {// creamos la clase TCP_SERVIDOR
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(2000);//Creamos el socket
        System.out.println("Servidor TCP esperando conexiones...");

        while (true) {//Inicio while
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Cliente conectado desde: " + connectionSocket.getInetAddress());

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String entrada = inFromClient.readLine();

            try {// hacemos un try cach para hacer la operacion sin que se caiga el program por algún error
                int numero = Integer.parseInt(entrada);// Se hace la operacion para convertir el nmeroa numero binario
                String binario = Integer.toBinaryString(numero);
                outToClient.writeBytes(binario + '\n');
                System.out.println("Recibido: " + numero + " → Enviado: " + binario);
            } catch (NumberFormatException excepcion) {// si da error la variable, se envia una exepcion
                outToClient.writeBytes("ERROR: Entrada no válida\n");
                System.out.println("Entrada inválida: " + entrada);
            }

            connectionSocket.close();// finaliza la conexion con el cliente 
            System.out.println("Conexión con cliente finalizada.\n");
        }//Fin del while
    }//Fin del main
}//Fin de la clase

