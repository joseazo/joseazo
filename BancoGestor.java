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
    private Cliente[] clientes = new Cliente[30]; // Tamaño del arreglo hardcodeado
    private int contadorClientes;
    private int siguienteUsuarioNumero = 40;
    private int siguienteNumeroCuenta = 4710;

    public BancoGestor() {
        this.contadorClientes = 0;
    }

    // Método para agregar clientes predefinidos (solo para pruebas iniciales)
    public void setCliente(int index, Cliente cliente) {
        if (index >= 0 && index < 30) { // Usando el literal 30
            this.clientes[index] = cliente;
            if (index >= contadorClientes) {
                contadorClientes = index + 1; // Ajusta el contador si se agrega en un índice mayor
            }
        }
    }
    public Cliente getCliente(int index) {
        if (index >= 0 && index < contadorClientes) {
            return clientes[index];
        }
        return null;
    }

    public int getContadorClientes() {
        return contadorClientes;
    }
    
    public int getSiguienteNumeroCuenta() {
        return siguienteNumeroCuenta;
    }

    public Cliente buscarClientePorId(String idCliente) {
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] != null && clientes[i].getIdCliente().equals(idCliente)) {
                return clientes[i];
            }
        }
        return null;
    }

    public Cliente buscarClientePorUsuario(String usuario) {
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] != null && clientes[i].getUsuario().equals(usuario)) {
                return clientes[i];
            }
        }
        return null;
    }

    private boolean existeIdCliente(String idCliente) {
        return buscarClientePorId(idCliente) != null;
    }

    private String obtenerPrimerNombre(String nombreCompleto) {
        if (nombreCompleto != null && nombreCompleto.contains(" ")) {
            return nombreCompleto.substring(0, nombreCompleto.indexOf(" "));
        }
        return nombreCompleto; // Si no hay espacio, se asume que todo es el primer nombre
    }

    public void agregarCliente() {
        if (contadorClientes >= 30) { // Usando el literal 30
            JOptionPane.showMessageDialog(null, "Límite máximo de clientes alcanzado. No se pueden agregar más."); 
            return;
        }

        String idCliente;
        boolean idValido = false;
        do {
            idCliente = JOptionPane.showInputDialog(null, "Ingrese el ID del cliente:");
            if (idCliente == null) { // Si el usuario presiona Cancelar o cierra el diálogo
                JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
                return;
            }
            if (idCliente.trim().length() == 0) {
                int confirmResult = JOptionPane.showConfirmDialog(null, "¿Desea ingresar otro ID?");
                if (confirmResult != 0) { return; } 
            } else if (existeIdCliente(idCliente)) {
                int confirmResult = JOptionPane.showConfirmDialog(null, "Cliente ya agregado en el sistema con el ID: " + idCliente + ".\n¿Desea ingresar otro ID?");
                if (confirmResult != 0) { return; } 
            } else {
                idValido = true;
            }
        } while (!idValido);

        String nombreCompleto = JOptionPane.showInputDialog(null, "Ingrese el nombre completo del cliente:");
        if (nombreCompleto == null || nombreCompleto.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada o nombre inválido.");
            return;
        }

        String telefono;
        boolean telefonoValido = false;
        do {
            telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono (Formato: 0000-0000):");
            if (telefono == null) { JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.");
            } 
            
            // Validación de teléfono 
            if (telefono.length() == 9 && telefono.charAt(4) == '-') {
                boolean allDigits = true;
                for (int i = 0; i < telefono.length(); i++) {
                    char c = telefono.charAt(i);
                    if (i != 4 && (c < '0' || c > '9')) { // Ignorar el guion y verificar que sean dígitos
                        allDigits = false;
                        break;
                    }
                }
                if (allDigits) {
                    telefonoValido = true;
                }
            }
            
            if (!telefonoValido) {
                int confirmResult = JOptionPane.showConfirmDialog(null, "Formato de teléfono inválido. Use 0000-0000.\n¿Desea ingresar un teléfono válido?");
                if (confirmResult != 0) { return; 
                } 
            }
        } while (!telefonoValido);

        String correo;
        boolean correoValido = false;
        do {
            correo = JOptionPane.showInputDialog(null, "Ingrese el correo electrónico:");
            if (correo == null) { JOptionPane.showMessageDialog(null, "Operación de agregar cliente cancelada.", "Cancelado", 1); return; } // INFORMATION_MESSAGE
            
            int atIndex = correo.indexOf('@');
            if (atIndex > 0 && atIndex < correo.length() - 1) { // '@' no al principio ni al final
                int dotIndex = correo.indexOf('.', atIndex + 1); // Buscar '.' después de '@'
                if (dotIndex > atIndex + 1 && dotIndex < correo.length() - 1) { // '.' después de '@' y no al final
                    correoValido = true;
                }
            }
            
            if (!correoValido) {
                int confirmResult = JOptionPane.showConfirmDialog(null, "Correo inválido. Debe contener '@' y un punto después del '@'.\n¿Desea ingresar otro correo?");
                if (confirmResult != 0) { return; 
                } 
                
            }
        } while (!correoValido);

        String primerNombre = obtenerPrimerNombre(nombreCompleto);
        
        String usuarioSinEspacios = "";
        String lowerCasePrimerNombre = primerNombre.toLowerCase();
        for (int i = 0; i < lowerCasePrimerNombre.length(); i++) {
            char c = lowerCasePrimerNombre.charAt(i);
            if (c != ' ') { // Si no es un espacio, añadirlo
                usuarioSinEspacios = usuarioSinEspacios + c;
            }
        }
        String usuario = usuarioSinEspacios + siguienteUsuarioNumero;
        siguienteUsuarioNumero++;

        Cliente nuevoCliente = new Cliente(idCliente, nombreCompleto, telefono, correo, usuario);
        this.clientes[contadorClientes] = nuevoCliente;
        contadorClientes++;

        String mensajeExito = "Cliente fue agregado exitosamente.\n\n" +
                              "ID Cliente: " + nuevoCliente.getIdCliente() + "\n" +
                              "Nombre Completo: " + nuevoCliente.getNombreCompleto() + "\n" +
                              "Usuario: " + nuevoCliente.getUsuario() + "\n";
        mensajeExito = mensajeExito + "Tarjeta de Acceso:\n";
        int[][] tarjeta = nuevoCliente.getTarjetaAcceso();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (tarjeta[i][j] < 10) {
                    mensajeExito = mensajeExito + "0" + tarjeta[i][j] + " ";
                } else {
                    mensajeExito = mensajeExito + tarjeta[i][j] + " ";
                }
            }
            mensajeExito = mensajeExito + "\n";
        }
        JOptionPane.showMessageDialog(null, mensajeExito + "Cliente Agregado");
    }

    public void agregarNuevaCuenta() {
        if (contadorClientes == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados en el sistema para agregar cuentas.");
            return;
        }

        Cliente clienteSeleccionado = null;
        String idCliente;
        boolean clienteEncontrado = false;

        do {
            idCliente = JOptionPane.showInputDialog(null, "Ingrese el ID del cliente al que desea agregar una cuenta:");
            if (idCliente == null) {
                JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                return;
            }
            clienteSeleccionado = buscarClientePorId(idCliente);
            if (clienteSeleccionado == null) {
                int confirmResult = JOptionPane.showConfirmDialog(null, "¿Desea ingresar otro ID?" + "Cliente no encontrado");
                if (confirmResult != 0) { return; } 
            } else {
                clienteEncontrado = true;
            }
        } while (!clienteEncontrado);

        // Validar si el cliente ya tiene 5 cuentas
        while (clienteSeleccionado.getNumeroCuentasRegistradas() >= 5) {// Usando el literal 5
            int confirmResult = JOptionPane.showConfirmDialog(null, "El cliente ya tiene el número máximo de cuentas (5).\n¿Desea ingresar otro ID de cliente?");
            if (confirmResult != 0) { 
                JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                return;
            }
            
            // Si elige "Ingresar otro ID", se vuelve a pedir el ID
            idCliente = JOptionPane.showInputDialog(null, "Ingrese el ID del cliente al que desea agregar una cuenta:");
            if (idCliente == null) {
                JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                return;
            }
            clienteSeleccionado = buscarClientePorId(idCliente);
            if (clienteSeleccionado == null) {
                int confirmResult2 = JOptionPane.showConfirmDialog(null, "Cliente no encontrado. ¿Desea ingresar otro ID?");
                if (confirmResult2 != 0) { return; } 
            }
        }

        String numeroCuenta = "" + siguienteNumeroCuenta++; 
        TipoCuenta tipoCuenta = null;
        double saldoInicial = 0;
        boolean datosCuentaValidos = false;

        // Seleccionar tipo de cuenta
        String[] tipos = {"CORRIENTE", "AHORROS", "INVERSION", "PLANILLA"};
        int tipoSeleccion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de cuenta:", "Tipo de Cuenta",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

        if (tipoSeleccion == -1) { 
            JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
            return;
        }

        switch (tipoSeleccion) {
            case 0:
                tipoCuenta = TipoCuenta.CORRIENTE;
                break;
            case 1:
                tipoCuenta = TipoCuenta.AHORROS;
                break;
            case 2:
                tipoCuenta = TipoCuenta.INVERSION;
                break;
            case 3:
                tipoCuenta = TipoCuenta.PLANILLA;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Selección de tipo de cuenta no válida. Asignando AHORROS por defecto.");
                tipoCuenta = TipoCuenta.AHORROS; // Asignar un valor por defecto 
                break;
        }


        // Solicitar y validar saldo inicial 
        while (!datosCuentaValidos) {
            String saldoStr = JOptionPane.showInputDialog(null, "Ingrese el saldo inicial de la cuenta:");
            if (saldoStr == null) {
                JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                return;
            }
            
            boolean currentSaldoAttemptValid = false;
            if (saldoStr.trim().length() == 0) {
                 int confirmResult = JOptionPane.showConfirmDialog(null, "El saldo inicial no puede estar vacío. ¿Desea ingresar otro Saldo?", "Saldo Inválido", 0, 0); // YES_NO_OPTION, ERROR_MESSAGE
                if (confirmResult != 0) { return; } 
            } else {
                boolean esNumero = true;
                boolean puntoEncontrado = false;
                for (int i = 0; i < saldoStr.length(); i++) {
                    char c = saldoStr.charAt(i);
                    if (c == '-' && i == 0) {
                         
                    
                    if (c == '.' && !puntoEncontrado) { 
                        puntoEncontrado = true;
                         
                    
                    if (c < '0' || c > '9') { 
                        esNumero = false;
                        break;
                    }
                    }
                    }
                }

                if (esNumero) {
                    saldoInicial = Double.parseDouble(saldoStr);
                    if (saldoInicial < 0) {
                        int confirmResult = JOptionPane.showConfirmDialog(null, "El saldo inicial no puede ser negativo. ¿Desea ingresar otro Saldo?");
                        if (confirmResult != 0) { 
                            JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                            return;
                        }
                    } else {
                        currentSaldoAttemptValid = true;
                    }
                } else {
                    int confirmResult = JOptionPane.showConfirmDialog(null, "Saldo inicial inválido. Por favor, ingrese un número válido.");
                    if (confirmResult != 0) { 
                        JOptionPane.showMessageDialog(null, "Operación de agregar nueva cuenta cancelada.");
                        return;
                    }
                }
            }
            if (currentSaldoAttemptValid) {
                datosCuentaValidos = true;
            }
        }

        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, clienteSeleccionado, tipoCuenta, saldoInicial);
        if (clienteSeleccionado.agregarCuenta(nuevaCuenta)) {
            nuevaCuenta.agregarMovimiento("Apertura de cuenta con saldo inicial de " + saldoInicial);
            JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente al cliente " + clienteSeleccionado.getNombreCompleto() +
                                            "\nNúmero de Cuenta: " + numeroCuenta +
                                            "\nTipo: " + tipoCuenta +
                                            
                                            "\nSaldo Inicial: " + saldoInicial + "\nCuenta Agregada"); 
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar la cuenta. El cliente ya tiene el límite máximo de cuentas");
        }
    }

    public void mostrarClientes() {
        if (contadorClientes == 0) {
            System.out.println("No hay clientes registrados en el sistema.");
            JOptionPane.showMessageDialog(null, "No hay clientes registrados en el sistema.");
            return;
        }

        System.out.println("\n--- LISTADO COMPLETO DE CLIENTES Y SUS CUENTAS ---");
        for (int i = 0; i < contadorClientes; i++) {
            Cliente cliente = clientes[i];
            System.out.println("\n========== Detalle del Cliente " + (i + 1) + " ==========");
            cliente.mostrarDatosImportantes(); // Este método ahora imprime a la consola todos los datos importantes
            System.out.println("==================================================");
        }
    }
 public void mostrarCuentasYMovimientosEnConsola() {
        if (contadorClientes == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados en el sistema.");
            return;
        }

        Cliente clienteSeleccionado = null;
        String idCliente = null; 
        boolean clienteEncontrado = false;
        boolean operacionContinuar = true; 

        while (!clienteEncontrado && operacionContinuar) {
            String[] opcionesEntrada = {"Ingresar ID", "Cancelar"};
            int seleccionEntrada = JOptionPane.showOptionDialog(null,
                                    "¿Desea ingresar el ID de un cliente para ver sus cuentas y movimientos?",
                                    "Ver Cuentas y Movimientos",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    opcionesEntrada,
                                    opcionesEntrada[0]);

            if (seleccionEntrada == 0) { // Si el usuario selecciona "Ingresar ID"
                idCliente = JOptionPane.showInputDialog(null, "Ingrese el ID del cliente:");
                if (idCliente == null) { // Si el usuario cancela el input del ID
                    JOptionPane.showMessageDialog(null, "Operación de ingreso de ID cancelada.");
                    operacionContinuar = false; 
                    return; 
                }

                if (idCliente.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "El ID no puede estar vacío. Inténtelo de nuevo."); 
                    
                } else {
                    clienteSeleccionado = buscarClientePorId(idCliente);
                    if (clienteSeleccionado == null) {
                        int confirmResult = JOptionPane.showConfirmDialog(null, "¿Desea ingresar otro ID?"); 
                        if (confirmResult != 0) { 
                            operacionContinuar = false; 
                            return; 
                        }
                       
                    } else {
                        clienteEncontrado = true; 
                    }
                }
            } else { 
                JOptionPane.showMessageDialog(null, "Operación cancelada.", "Cancelado", 1); 
                operacionContinuar = false; 
                return; 
            }
        }

       
        if (clienteEncontrado) {
            System.out.println("\n--- CUENTAS Y MOVIMIENTOS DEL CLIENTE: " + clienteSeleccionado.getNombreCompleto() + " ---");
            clienteSeleccionado.mostrarDatosImportantes();
            System.out.println("-----------------------------------------------------");
        }
    }
    public Cliente iniciarSesionCliente() {
        Cliente clienteActual = null;
        String usuarioIngresado;
        String claveIngresada;
        boolean usuarioValido = false;

        while (!usuarioValido) {
            usuarioIngresado = JOptionPane.showInputDialog(null, "Ingrese su usuario:");
            if (usuarioIngresado == null) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.");
                return null;
            }
            
            boolean currentAttemptValid = false;
            if (usuarioIngresado.trim().length() == 0) { 
                JOptionPane.showMessageDialog(null, "El usuario no puede estar vacío. Inténtelo de nuevo.");
            } else {
                clienteActual = buscarClientePorUsuario(usuarioIngresado);

                if (clienteActual == null) {
                    JOptionPane.showMessageDialog(null, "No hay ningún cliente con el usuario: " + usuarioIngresado);
                } else if (clienteActual.getEstado() == EstadoCliente.INACTIVO) {
                    JOptionPane.showMessageDialog(null, "El cliente '" + usuarioIngresado + "' está inactivo. Contacte a soporte.");
                } else {
                    currentAttemptValid = true; 
                }
            }
            if (currentAttemptValid) {
                usuarioValido = true; 
            }
        }

        // Si la clave está vacía, es la primera vez que inicia sesión
        if (clienteActual.getClave() == null || clienteActual.getClave().length() == 0) {
            JOptionPane.showMessageDialog(null, "Es su primera vez iniciando sesión. Debe crear su contraseña.");
            boolean claveEstablecida = false;
            while (!claveEstablecida) {
                String nuevaClave1 = JOptionPane.showInputDialog(null, "Ingrese su nueva contraseña:");
                if (nuevaClave1 == null) {
                    JOptionPane.showMessageDialog(null, "Creación de contraseña cancelada. Inicio de sesión abortado.");
                    return null;
                }
                
                boolean currentClaveAttemptValid = false;
                if (nuevaClave1.trim().length() == 0) { 
                    JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía. Inténtelo de nuevo.");
                } else {
                    String nuevaClave2 = JOptionPane.showInputDialog(null, "Confirme su nueva contraseña:");
                    if (nuevaClave2 == null) {
                        JOptionPane.showMessageDialog(null, "Confirmación de contraseña cancelada. Inicio de sesión abortado.");
                        return null;
                    }

                    if (nuevaClave1.equals(nuevaClave2)) {
                        clienteActual.setClave(nuevaClave1);
                        JOptionPane.showMessageDialog(null, "Contraseña creada exitosamente. Iniciando sesión.");
                        currentClaveAttemptValid = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden. Inténtelo de nuevo"); 
                    }
                }
                if (currentClaveAttemptValid) {
                    claveEstablecida = true; 
                }
            }
        }

        // Pedir la clave de acceso si ya está establecida
        boolean claveValida = false;
        while (!claveValida) {
            claveIngresada = JOptionPane.showInputDialog(null, "Ingrese su clave de acceso:");
            if (claveIngresada == null) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado."); 
                return null;
            }
            
            boolean currentLoginClaveAttemptValid = false;
            if (claveIngresada.trim().length() == 0) { 
                JOptionPane.showMessageDialog(null, "La clave no puede estar vacía. Inténtelo de nuevo.");
            } else {
                if (claveIngresada.equals(clienteActual.getClave())) {
                    currentLoginClaveAttemptValid = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Clave incorrecta. Inténtelo de nuevo."); 
                }
            }
            if (currentLoginClaveAttemptValid) {
                claveValida = true; 
            }
        }

        JOptionPane.showMessageDialog(null, "¡Bienvenido al sistema, " + clienteActual.getNombreCompleto() + "!"); 
        return clienteActual;
    }
}