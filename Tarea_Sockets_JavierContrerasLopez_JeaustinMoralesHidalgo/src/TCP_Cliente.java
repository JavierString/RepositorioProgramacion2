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

public class TCP_Cliente extends JFrame {

  // Declaración de los componentes visuales principales de la ventana
  private JTextField campoEntrada;
  private JTextArea areaSalida;
  private JButton botonEnviar, botonSalir;

  public TCP_Cliente() {
    // Configuración inicial del JFrame (título, tamaño, cierre, ubicación)
    setTitle("Cliente TCP - Convertidor Decimal a Binario");
    setSize(400, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Centra la ventana en pantalla

    // Se crea el panel principal con márgenes internos
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Campo de texto para ingresar el número decimal
    campoEntrada = new JTextField();

    // Botones de enviar y salir
    botonEnviar = new JButton("Enviar");
    botonSalir = new JButton("Salir");

    // Panel para el campo de entrada con su etiqueta
    JPanel entradaPanel = new JPanel(new BorderLayout(5, 5));
    entradaPanel.add(new JLabel("Ingrese un número decimal:"), BorderLayout.NORTH);
    entradaPanel.add(campoEntrada, BorderLayout.CENTER);

    // Panel que contiene los botones
    JPanel botonesPanel = new JPanel();
    botonesPanel.add(botonEnviar);
    botonesPanel.add(botonSalir);

    // Área de texto para mostrar las respuestas del servidor
    areaSalida = new JTextArea();
    areaSalida.setEditable(false);
    areaSalida.setLineWrap(true);
    areaSalida.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(areaSalida);

    // Se agregan los componentes al panel principal
    panel.add(entradaPanel, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(botonesPanel, BorderLayout.SOUTH);

    // Se agrega el panel principal al JFrame
    add(panel);

    /*
     * Acción que ocurre al presionar el botón enviar:
     * - Se obtiene el número decimal del campo de texto
     * - Se establece conexión con el servidor
     * - Se envía el número
     * - Se recibe la respuesta en binario
     * - Se muestra en el área de salida
     */
    botonEnviar.addActionListener(e -> enviarNumero());

    // Acción que finaliza el programa al presionar el botón salir
    botonSalir.addActionListener(e -> System.exit(0));
  }

  // Método que maneja el envío del número y la conexión al servidor
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

  // Método principal que inicia la ventana del cliente TCP
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      TCP_Cliente cliente = new TCP_Cliente();
      cliente.setVisible(true);
    });
  }
}
