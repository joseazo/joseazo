/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal;

import javax.swing.JOptionPane;

public class ProyectoFinal {
public static void main(String[] args) {
          
        
        mostrarMenuPrincipal();
    }

     public static void mostrarMenuPrincipal() {
        BancoGestor gestorBanco = new BancoGestor();

        mostrarMenuPrincipal(gestorBanco);
    }

    
    public static void mostrarMenuPrincipal(BancoGestor gestor) {
        String[] opciones = {"Menú Banco", "Clientes", "Salir"};
        String titulo = "Hiperbanco - Menú Principal";
        String mensaje = "Selecciona una opción:";

        boolean continuar = true;
        while (continuar) {
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    mensaje,
                    titulo,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0: // "Menú Banco" (Opciones de administración del banco)
                    BancoMenu.mostrarMenuBancario(gestor);
                    break;
                case 1: // "Clientes" (Inicia el proceso de login de cliente)
                    JOptionPane.showMessageDialog(null, "Has seleccionado 'Clientes'. Por favor, inicia sesión.");
                    Cliente clienteLogeado = gestor.iniciarSesionCliente(); // Llama al método de login
                    if (clienteLogeado != null) {
                        JOptionPane.showMessageDialog(null, "¡Sesión iniciada como " + clienteLogeado.getNombreCompleto() + "!");
                    }
                   
                    break;
                case 2: // "Salir"
                    JOptionPane.showMessageDialog(null, "Saliendo del programa. ¡Hasta pronto!");
                    continuar = false; 
                    break;
               
                default:
                    JOptionPane.showMessageDialog(null, "Opción no reconocida. Por favor, selecciona una opción válida.");
                    break;
            }
        }
    }
}