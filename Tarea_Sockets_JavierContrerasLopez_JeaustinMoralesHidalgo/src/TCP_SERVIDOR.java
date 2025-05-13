package Tarea_Sockets_JavierContrerasLopez_JeaustinMoralesHidalgo.src;


    import java.io.*;
import java.net.*;
public class TCP_SERVIDOR {// creamos la clase TCP_SERVIDOR
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(7777);//Creamos el socket
        System.out.println("Servidor TCP esperando conexiones...");

        while (true) {
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
        }
    }
}

