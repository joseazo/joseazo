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
              + "1. Generar datos\n"
              + "2. Mostrar clientes\n"
              + "3. Mostrar cuentas y movimientos\n"
              + "4. Agregar nuevo cliente\n"
              + "5. Agregar nueva cuenta\n"
              + "6. Buscar cliente\n"
              + "7. Buscar cuenta\n"
              + "8. Generar reportes\n"
              + "9. Volver al menú principal"
            );
            if (opcion == null) {

                break;
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
                    case 1:
                       JOptionPane.showMessageDialog(null,"Funcion en construccion perdone el inconveniente");

                        break;
                    case 2:
                         gestor.mostrarClientes();
                        break;
                    case 3:
                        gestor.mostrarCuentasYMovimientosEnConsola();

                        break;
                    case 4:
                        gestor.agregarCliente();
 
                        break;
                    case 5:
                        gestor.agregarNuevaCuenta();

                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null,"Funcion en construccion perdone el inconveniente");

                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null,"Funcion en construccion perdone el inconveniente");

                        break;
                    case 8:
                        JOptionPane.showMessageDialog(null,"Funcion en construccion perdone el inconveniente");

                        break;
                    case 9:

                        volver = true;
                        JOptionPane.showMessageDialog(null,"Saliendo del menu bancario");
                        ProyectoFinal.mostrarMenuPrincipal();
                       
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresa un número válido");
            }
        }
    }
}
