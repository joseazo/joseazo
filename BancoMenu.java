/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;


/**
 *
 * @author azoac
 */
import javax.swing.JOptionPane;

public class BancoMenu {

 public static void mostrarMenuBancario(BancoGestor gestor) {

      boolean volver = false;

        while (!volver) {
            String opcion = JOptionPane.showInputDialog(
                "Menú Bancario:\n"
              + "1. Agregar nuevo cliente\n"
              + "2. Agregar nueva cuenta a cliente\n"
              + "3. Mostrar todos los clientes y sus cuentas\n"
              + "4. Mostrar cuentas y movimientos de un cliente específico\n"
              + "5. Volver al menú principal"
            );

            if (opcion == null) { // Si el usuario cancela o cierra
                volver = true; // Vuelve al menú principal
                continue;
            }

            boolean esNumero = true;
            for (int i = 0; i < opcion.length(); i++) {
                char c = opcion.charAt(i);
                if (c < '0' || c > '9') {
                    esNumero = false;
                    break;
                }
            }

            if (esNumero) {
                int op = Integer.parseInt(opcion);
                switch (op) {
                    case 1: // Agregar nuevo cliente
                        gestor.agregarCliente();
                        break;
                    case 2: // Agregar nueva cuenta a cliente
                        gestor.agregarNuevaCuenta();
                        break;
                    case 3: // Mostrar todos los clientes y sus cuentas
                        gestor.mostrarClientes();
                        break;
                    case 4: // Mostrar cuentas y movimientos de un cliente específico (pide ID)
                        gestor.mostrarCuentasYMovimientosEnConsola();
                        break;
                    case 5: // Volver al menú principal
                        volver = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, ingrese un número entre 1 y 5.", "Opción Inválida", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresa un número válido para la opción.", "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}