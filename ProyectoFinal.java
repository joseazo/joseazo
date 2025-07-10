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
        BancoGestor gestor = new BancoGestor();
        boolean salir = false;

        while (!salir) {
            String[] opciones = {"BANCO", "CLIENTES", "SALIR"};
            int opcion = JOptionPane.showOptionDialog(null, "Bienvenido a HiperBanco", "Inicio del Sistema",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0: // BANCO
                    BancoMenu.mostrarMenuBancario(gestor);
                    break;
                case 1: // CLIENTES
                    gestor.menuClientes();
                    break;
                case 2: // SALIR
                    salir = true;
                    JOptionPane.showMessageDialog(null, "Gracias por usar HiperBanco");
                    break;
                default:
                    salir = true;
                    break;
            }
        }
    }
}
