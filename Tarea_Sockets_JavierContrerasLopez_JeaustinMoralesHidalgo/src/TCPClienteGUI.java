package Tarea_Sockets_JavierContrerasLopez_JeaustinMoralesHidalgo.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/*
 Javier Contreras López (Cliente) C4E402 
 Jeaustin Morales Hidalgo (Servidor) C4H694

 fecha: 12/05/2025
 */

/* 
 TCP de parte del cliente, adaptado en interfaz gráfica usando JFrame para una mejor experiencia visual.
 
 El código fuente anterior utilizaba JOptionPane para entrada/salida de datos. 
 Esta versión implementa una ventana gráfica más completa con campo de texto, botón de envío 
 y un área para mostrar múltiples respuestas del servidor.
 
 La funcionalidad se mantiene: el cliente ingresa un número decimal, se envía al servidor, 
 y el servidor responde con el valor en notación binaria.
*/

public class TCPClienteGUI extends JFrame {

    // Componentes visuales
    private JTextField campoEntrada;
    private JTextArea areaSalida;
    private JButton botonEnviar, botonSalir;

    public TCPClienteGUI() {
        // Configuración de la ventana
        setTitle("Cliente TCP - Convertidor Decimal a Binario");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campo de entrada
        campoEntrada = new JTextField();

        // Botones
        botonEnviar = new JButton("Enviar");
        botonSalir = new JButton("Salir");

        // Panel de entrada
        JPanel entradaPanel = new JPanel(new BorderLayout(5, 5));
        entradaPanel.add(new JLabel("Ingrese un número decimal:"), BorderLayout.NORTH);
        entradaPanel.add(campoEntrada, BorderLayout.CENTER);

        // Panel de botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.add(botonEnviar);
        botonesPanel.add(botonSalir);

        // Área de salida
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setLineWrap(true);
        areaSalida.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(areaSalida);

        // Agrega todo al panel principal
        panel.add(entradaPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Agrega el panel al JFrame
        add(panel);

        // acción del botón Enviar
        botonEnviar.addActionListener(e -> enviarNumero());

        // esta es la acción del botón Salir
        botonSalir.addActionListener(e -> {
            setVisible(false);
            dispose();
            System.exit(0);
        });
    }

    // Método que maneja la conexión TCP y el envío del número
    private void enviarNumero() {
        String entrada = campoEntrada.getText().trim();

        if (entrada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número.");
            return;
        }

        try {
            // Se crea un objeto cliente de tipo Socket con su hostname y puerto
            Socket clientSocket = new Socket("192.168.0.3", 4357);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Se envía el número ingresado al servidor
            outToServer.writeBytes(entrada + "\n");

            // Se lee y muestra la respuesta del servidor (número en binario)
            String respuestaServidor = inFromServer.readLine();
            areaSalida.append("Decimal: " + entrada + " -> Binario: " + respuestaServidor + "\n");

            // Se cierra la conexión con el servidor
            clientSocket.close();

        } catch (IOException ex) {
            // Caso de excepción si ocurre error en la conexión
            JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
        }
    }
}
