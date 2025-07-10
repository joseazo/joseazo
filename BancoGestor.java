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
import java.util.Random;

public class BancoGestor {

    private Cliente cliente1;
    private Cliente cliente2;
    private int contadorClientes = 0;
    private int siguienteUsuarioNumero = 40;
    private int siguienteNumeroCuenta = 4710;

    public void agregarCliente() {
        if (contadorClientes >= 2) {
            JOptionPane.showMessageDialog(null, "Límite máximo de clientes alcanzado (2). No se pueden agregar más.");
            return;
        }

        String idCliente = "";
        boolean idValido = false;
        int confirmResult;
        do {
            idCliente = JOptionPane.showInputDialog(null, "Ingrese el ID del cliente:");
            if (idCliente == null) {
                JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
                return;
            }
            if (idCliente.trim().length() == 0) {
                confirmResult = JOptionPane.showConfirmDialog(null, "El ID del cliente no puede estar vacío. ¿Desea ingresar otro ID?");
                if (confirmResult != 0) {
                    JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
                    return;
                }
                idValido = false;
            } else if (existeIdCliente(idCliente)) {
                confirmResult = JOptionPane.showConfirmDialog(null, "Cliente ya agregado en el sistema con el ID: " + idCliente + ".\n¿Desea ingresar otro ID?");
                if (confirmResult != 0) {
                    JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
                    return;
                }
                idValido = false;
            } else {
                idValido = true;
            }
        } while (!idValido);

        String nombreCompleto = JOptionPane.showInputDialog(null, "Ingrese el nombre completo del cliente:");
        if (nombreCompleto == null || nombreCompleto.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada o nombre inválido.");
            return;
        }

        String telefono = "";
        boolean telefonoValido = false;
        do {
            telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono (Formato: 0000-0000):");
            if (telefono == null) {
                JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
                return;
            }
            if (telefono.length() == 9 && telefono.charAt(4) == '-') {
                telefonoValido = true;
            } else {
                confirmResult = JOptionPane.showConfirmDialog(null, "Formato de teléfono inválido. Use 0000-0000.\n¿Desea ingresar un teléfono válido?");
                if (confirmResult != 0) {
                    JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
                    return;
                }
                telefonoValido = false;
            }
        } while (!telefonoValido);

        String correo = "";
        boolean correoValido = false;
        do {
            correo = JOptionPane.showInputDialog(null, "Ingrese el correo electrónico:");
            if (correo == null) {
                JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
                return;
            }
            int atIndex = correo.indexOf('@');
            if (atIndex > 0 && atIndex < correo.length() - 1) {
                int dotIndex = correo.indexOf('.', atIndex + 1);
                if (dotIndex > atIndex + 1 && dotIndex < correo.length() - 1) {
                    correoValido = true;
                }
            }
            if (!correoValido) {
                confirmResult = JOptionPane.showConfirmDialog(null, "Correo inválido. Debe contener '@' y un punto después del '@'.\n¿Desea ingresar otro correo?");
                if (confirmResult != 0) {
                    JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
                    return;
                }
            }
        } while (!correoValido);

        String primerNombre = obtenerPrimerNombre(nombreCompleto);
        String usuario = primerNombre + siguienteUsuarioNumero;
        siguienteUsuarioNumero++;

        Cliente nuevoCliente = new Cliente(idCliente, nombreCompleto, telefono, correo, usuario);

        if (cliente1 == null) {
            cliente1 = nuevoCliente;
        } else if (cliente2 == null) {
            cliente2 = nuevoCliente;
        }
        contadorClientes++;

        String mensajeExito = "Cliente fue agregado exitosamente.\n\n"
                + "ID Cliente: " + nuevoCliente.getIdCliente() + "\n"
                + "Nombre Completo: " + nuevoCliente.getNombreCompleto() + "\n"
                + "Usuario: " + nuevoCliente.getUsuario() + "\n"
                + "Tarjeta de Acceso:\n";
        int[][] tarjeta = nuevoCliente.getTarjetaAcceso();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mensajeExito += tarjeta[i][j] + " ";
            }
            mensajeExito += "\n";
        }

        JOptionPane.showMessageDialog(null, mensajeExito);
    }

    public void agregarNuevaCuenta() {
        Cliente clienteSeleccionado = null;
        String idCliente;
        int confirmResult;
        boolean canProceedCliente = false;

        do {
            idCliente = JOptionPane.showInputDialog(null, "Ingrese el ID del cliente al que desea agregar una cuenta:");
            if (idCliente == null) {
                JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                return;
            }

            clienteSeleccionado = buscarClientePorId(idCliente);
            if (clienteSeleccionado == null) {
                confirmResult = JOptionPane.showConfirmDialog(null, "Cliente no encontrado. ¿Desea ingresar otro ID?");
                if (confirmResult != 0) {
                    JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                    return;
                }
                canProceedCliente = false;
            } else if (clienteSeleccionado.getNumeroCuentasRegistradas() >= 2) {
                confirmResult = JOptionPane.showConfirmDialog(null, "El cliente ya tiene el máximo de cuentas permitidas (2). ¿Desea ingresar otro ID?");
                if (confirmResult != 0) {
                    JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                    return;
                }
                canProceedCliente = false;
            } else {
                canProceedCliente = true;
            }
        } while (!canProceedCliente);

        TipoCuenta tipoCuenta = null;
        boolean tipoCuentaSeleccionado = false;
        do {
            String[] opcionesTipoCuenta = {"Cuenta corriente", "Ahorros", "Inversión", "Planilla"};
            int seleccionTipo = JOptionPane.showOptionDialog(
                    null, "Seleccione el tipo de cuenta:", "Tipo de Cuenta",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesTipoCuenta, opcionesTipoCuenta[0]);

            if (seleccionTipo == -1) {
                JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                return;
            }

            switch (seleccionTipo) {
                case 0:
                    tipoCuenta = TipoCuenta.CORRIENTE;
                    tipoCuentaSeleccionado = true;
                    break;
                case 1:
                    tipoCuenta = TipoCuenta.AHORROS;
                    tipoCuentaSeleccionado = true;
                    break;
                case 2:
                    tipoCuenta = TipoCuenta.INVERSION;
                    tipoCuentaSeleccionado = true;
                    break;
                case 3:
                    tipoCuenta = TipoCuenta.PLANILLA;
                    tipoCuentaSeleccionado = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Selección de tipo de cuenta inválida. Por favor, intente de nuevo.");
                    tipoCuentaSeleccionado = false;
                    break;
            }
        } while (!tipoCuentaSeleccionado);

        double saldoInicial = 0;
        boolean saldoValido = false;
        do {
            String saldoStr = JOptionPane.showInputDialog(null, "Ingrese el saldo inicial de la cuenta:");
            if (saldoStr == null) {
                JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                return;
            }
            try {
                saldoInicial = Double.parseDouble(saldoStr);
                if (saldoInicial < 0) {
                    confirmResult = JOptionPane.showConfirmDialog(null, "El saldo debe ser mayor o igual a cero. ¿Desea ingresar otro Saldo?");
                    if (confirmResult != 0) {
                        JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                        return;
                    }
                    saldoValido = false;
                } else {
                    saldoValido = true;
                }
            } catch (NumberFormatException e) {
                confirmResult = JOptionPane.showConfirmDialog(null, "Saldo inválido. Ingrese un número válido. ¿Desea ingresar otro saldo?");
                if (confirmResult != 0) {
                    JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                    return;
                }
                saldoValido = false;
            }
        } while (!saldoValido);

        String nuevoNumeroCuenta = String.valueOf(siguienteNumeroCuenta);
        siguienteNumeroCuenta++;

        Cuenta nuevaCuenta = new Cuenta(nuevoNumeroCuenta, clienteSeleccionado, tipoCuenta, saldoInicial);
        clienteSeleccionado.agregarCuenta(nuevaCuenta);

        JOptionPane.showMessageDialog(null, "Nueva cuenta agregada exitosamente:\n"
                + "Número de Cuenta: " + nuevaCuenta.getNumeroCuenta() + "\n"
                + "Cliente: " + nuevaCuenta.getDuenoCuenta().getNombreCompleto() + " (ID: " + nuevaCuenta.getDuenoCuenta().getIdCliente() + ")\n"
                + "Tipo de Cuenta: " + nuevaCuenta.getTipoCuenta() + "\n"
                + "Fecha de Apertura: " + nuevaCuenta.getFechaApertura() + "\n"
                + "Saldo Inicial: " + nuevaCuenta.getSaldoInicial());
    }

    public void mostrarCuentasYMovimientosEnConsola() {
        if (contadorClientes == 0) {
            System.out.println("No hay clientes registrados en el sistema, por lo tanto no hay cuentas que mostrar.");
            return;
        }

        System.out.println("\n--- Lista de Cuentas y Movimientos de Clientes ---");

        if (this.cliente1 != null) {
            System.out.println("\n--- Cliente: " + cliente1.getNombreCompleto() + " (ID: " + cliente1.getIdCliente() + ") ---");
            if (cliente1.getNumeroCuentasRegistradas() == 0) {
                System.out.println("    Este cliente no tiene cuentas registradas.");
            } else {
                if (cliente1.getCuentaObj1() != null) {
                    Cuenta cuenta = cliente1.getCuentaObj1();
                    System.out.println("    CUENTA 1 (Numero: " + cuenta.getNumeroCuenta() + ", Tipo: " + cuenta.getTipoCuenta() + ", Saldo: " + cuenta.getSaldoInicial() + "):");
                    System.out.println("      Movimientos: " + cuenta.getDetallesMovimientos());
                }
                if (cliente1.getCuentaObj2() != null) {
                    Cuenta cuenta = cliente1.getCuentaObj2();
                    System.out.println("    CUENTA 2 (Numero: " + cuenta.getNumeroCuenta() + ", Tipo: " + cuenta.getTipoCuenta() + ", Saldo: " + cuenta.getSaldoInicial() + "):");
                    System.out.println("      Movimientos: " + cuenta.getDetallesMovimientos());
                }
            }
        }

        if (this.cliente2 != null) {
            System.out.println("\n--- Cliente: " + cliente2.getNombreCompleto() + " (ID: " + cliente2.getIdCliente() + ") ---");
            if (cliente2.getNumeroCuentasRegistradas() == 0) {
                System.out.println("    Este cliente no tiene cuentas registradas.");
            } else {
                if (cliente2.getCuentaObj1() != null) {
                    Cuenta cuenta = cliente2.getCuentaObj1();
                    System.out.println("    CUENTA 1 (Numero: " + cuenta.getNumeroCuenta() + ", Tipo: " + cuenta.getTipoCuenta() + ", Saldo: " + cuenta.getSaldoInicial() + "):");
                    System.out.println("      Movimientos: " + cuenta.getDetallesMovimientos());
                }
                if (cliente2.getCuentaObj2() != null) {
                    Cuenta cuenta = cliente2.getCuentaObj2();
                    System.out.println("    CUENTA 2 (Numero: " + cuenta.getNumeroCuenta() + ", Tipo: " + cuenta.getTipoCuenta() + ", Saldo: " + cuenta.getSaldoInicial() + "):");
                    System.out.println("      Movimientos: " + cuenta.getDetallesMovimientos());
                }
            }
        }
    }

    public void mostrarClientes() {
        if (contadorClientes == 0) {
            System.out.println("No hay clientes registrados en el sistema, por lo tanto no hay clientes o cuentas que mostrar.");
            return;
        }

        System.out.println("\n--- Lista Detallada de Clientes y sus Cuentas ---");

        if (this.cliente1 != null) {
            System.out.println("\n--- Cliente: " + cliente1.getNombreCompleto() + " ---");
            cliente1.mostrarDatosCliente();
        }

        if (this.cliente2 != null) {
            System.out.println("\n--- Cliente: " + cliente2.getNombreCompleto() + " ---");
            cliente2.mostrarDatosCliente();
        }
    }

    private Cliente buscarClientePorId(String id) {
        if (cliente1 != null && cliente1.getIdCliente().equals(id)) {
            return cliente1;
        }
        if (cliente2 != null && cliente2.getIdCliente().equals(id)) {
            return cliente2;
        }
        return null;
    }

    private boolean existeIdCliente(String id) {
        if (cliente1 != null && cliente1.getIdCliente().equals(id)) {
            return true;
        }
        if (cliente2 != null && cliente2.getIdCliente().equals(id)) {
            return true;
        }
        return false;
    }

    private String obtenerPrimerNombre(String nombreCompleto) {
        int primerEspacio = nombreCompleto.indexOf(" ");
        if (primerEspacio > 0) {
            return nombreCompleto.substring(0, primerEspacio);
        }
        return nombreCompleto;
    }

    public void menuClientes() {
        if (contadorClientes == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados en el sistema");
            return;
        }

        boolean loginExitoso = false;
        while (!loginExitoso) {
            String usuario = JOptionPane.showInputDialog(null, "Ingrese su usuario:");
            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return;
            }

            Cliente cliente = buscarClientePorUsuario(usuario);
            if (cliente == null) {
                int confirm = JOptionPane.showConfirmDialog(null, "No hay ningún cliente con el usuario: " + usuario + "\n¿Desea ingresar otro usuario?");
                if (confirm != 0) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                    return;
                }
                continue;
            }

            if (cliente.getEstado() == EstadoCliente.INACTIVO) {
                int confirm = JOptionPane.showConfirmDialog(null, "Cliente está inactivo\n¿Desea ingresar otro usuario?");
                if (confirm != 0) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                    return;
                }
                continue;
            }

            int intentos = 0;
            boolean claveCorrecta = false;
            while (intentos < 3 && !claveCorrecta) {
                String clave = JOptionPane.showInputDialog(null, "Ingrese su clave:");
                if (clave == null) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                    return;
                }

                if (cliente.getClave().isEmpty()) {
                    boolean claveValida = false;
                    String nuevaClave = "";
                    do {
                        nuevaClave = JOptionPane.showInputDialog(null, "Cliente nuevo. Ingrese una nueva clave (6-10 caracteres, al menos un número y una letra):");
                        if (nuevaClave == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada");
                            return;
                        }
                        if (cliente.validarClave(nuevaClave)) {
                            String confirmClave = JOptionPane.showInputDialog(null, "Confirme la nueva clave:");
                            if (confirmClave == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada");
                                return;
                            }
                            if (nuevaClave.equals(confirmClave)) {
                                cliente.setClave(nuevaClave);
                                claveValida = true;
                            } else {
                                int confirm = JOptionPane.showConfirmDialog(null, "Las claves no coinciden\n¿Desea confirmar de nuevo la clave?");
                                if (confirm != 0) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                                    return;
                                }
                            }
                        } else {
                            int confirm = JOptionPane.showConfirmDialog(null, "La clave no cumple con las condiciones mínimas de seguridad:\n"
                                    + "- Mínimo 6 caracteres\n- Máximo 10 caracteres\n- Al menos un número\n- Al menos una letra\n¿Desea ingresar otra clave?");
                            if (confirm != 0) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada");
                                return;
                            }
                        }
                    } while (!claveValida);
                }

                if (cliente.getClave().equals(clave)) {
                    claveCorrecta = true;
                } else {
                    intentos++;
                    if (intentos < 3) {
                        int confirm = JOptionPane.showConfirmDialog(null, "Clave incorrecta\n¿Desea intentarlo de nuevo?");
                        if (confirm != 0) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada");
                            return;
                        }
                    } else {
                        cliente.setEstado(EstadoCliente.INACTIVO);
                        JOptionPane.showMessageDialog(null, "Cliente desactivado por intentos fallidos");
                        return;
                    }
                }
            }

            System.out.println("| Tarjeta de acceso | A | B | C | D | E |");
            int[][] tarjeta = cliente.getTarjetaAcceso();
            for (int i = 0; i < 4; i++) {
                System.out.print("| " + (i + 1) + " | ");
                for (int j = 0; j < 5; j++) {
                    System.out.print(tarjeta[i][j] + " | ");
                }
                System.out.println();
            }

            Random random = new Random();
            String[] posiciones = new String[3];
            String[] letras = {"A", "B", "C", "D", "E"};
            for (int i = 0; i < 3; i++) {
                int fila = random.nextInt(4) + 1;
                int columna = random.nextInt(5);
                posiciones[i] = letras[columna] + fila;
            }

            boolean accesosCorrectos = false;
            do {
                String input = JOptionPane.showInputDialog(null, "Consulte su tarjeta de acceso y digite los accesos: "
                        + String.join(" ", posiciones) + " (Formato XX-XX-XX)");
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                    return;
                }

                String[] valores = input.split("-");
                if (valores.length != 3) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Formato de acceso incorrecto (use XX-XX-XX)\n¿Desea intentarlo de nuevo?");
                    if (confirm != 0) {
                        JOptionPane.showMessageDialog(null, "Operación cancelada");
                        return;
                    }
                    continue;
                }

                boolean correctos = true;
                for (int i = 0; i < 3; i++) {
                    String pos = posiciones[i];
                    int fila = Integer.parseInt(pos.substring(1)) - 1;
                    int columna = "ABCDE".indexOf(pos.charAt(0));
                    try {
                        int valor = Integer.parseInt(valores[i].trim());
                        if (valor != tarjeta[fila][columna]) {
                            correctos = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        correctos = false;
                        break;
                    }
                }

                if (correctos) {
                    accesosCorrectos = true;
                    JOptionPane.showMessageDialog(null, "Bienvenido " + cliente.getNombreCompleto());
                    loginExitoso = true;
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Acceso incorrecto\n¿Desea intentarlo de nuevo?");
                    if (confirm != 0) {
                        JOptionPane.showMessageDialog(null, "Operación cancelada");
                        return;
                    }
                }
            } while (!accesosCorrectos);
        }
    }

    private Cliente buscarClientePorUsuario(String usuario) {
        if (cliente1 != null && cliente1.getUsuario().equals(usuario)) {
            return cliente1;
        }
        if (cliente2 != null && cliente2.getUsuario().equals(usuario)) {
            return cliente2;
        }
        return null;
    }

}
