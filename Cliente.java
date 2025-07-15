/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author azoac
 */
import java.time.LocalDate;
import java.util.Random;
import javax.swing.JOptionPane;

public class Cliente {
    private String idCliente;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String usuario;
    private String clave;
    private EstadoCliente estado;
    private Cuenta[] cuentas = new Cuenta[5]; 
    private int numeroCuentasRegistradas;
    private int[][] tarjetaAcceso;

    // Constructor
    public Cliente(String idCliente, String nombreCompleto, String telefono, String correo, String usuario) {
        this.setIdCliente(idCliente);
        this.setNombreCompleto(nombreCompleto);
        this.setTelefono(telefono);
        this.setCorreo(correo);
        this.setUsuario(usuario);
        this.clave = ""; // Clave se inicializa vacía
        this.estado = EstadoCliente.ACTIVO; // Estado por defecto es ACTIVO
        this.numeroCuentasRegistradas = 0;
        this.generarTarjetaAcceso();
    }

    // --- Getters ---
    public String getIdCliente() {
        return idCliente;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getClave() {
        return clave;
    }
    public EstadoCliente getEstado() {
        return estado;
    }

    public Cuenta[] getCuentas() {
        return cuentas;
    }

    public int getNumeroCuentasRegistradas() {
        return numeroCuentasRegistradas;
    }

    public int[][] getTarjetaAcceso() {
        return tarjetaAcceso;
    }

    // --- Setters ---
    public void setIdCliente(String idCliente) {
        if (idCliente != null && idCliente.trim().length() > 0) {
            this.idCliente = idCliente;
        } else {
            this.idCliente = "INVALIDO";
        }
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto != null && nombreCompleto.trim().length() > 0) {
            this.nombreCompleto = nombreCompleto;
        } else {
            this.nombreCompleto = "N/A";
        }
    }

    // Validación de Teléfono
    public void setTelefono(String telefono) {
    
    
        if (telefono != null && telefono.length() == 9 && telefono.charAt(4) == '-') {
            boolean allDigits = true;
            for (int i = 0; i < telefono.length(); i++) {
                if (i != 4 && !Character.isDigit(telefono.charAt(i))) {
                    allDigits = false;
                    break;
                }
            }
            if (allDigits) {
                this.telefono = telefono;
                return;
            }
        }
        this.telefono = "0000-0000"; // Valor por defecto si no cumple la validación
    }

    // Validación de Correo adaptada de la imagen
    public void setCorreo(String correo) {
        if (correo != null) {
            int atIndex = correo.indexOf('@');
            // Revisa que '@' no esté al principio o al final
            if (atIndex > 0 && atIndex < correo.length() - 1) {
                int dotIndex = correo.indexOf('.', atIndex + 1);
                // Revisa que haya un '.' después de '@' y no al final
                if (dotIndex > atIndex + 1 && dotIndex < correo.length() - 1) {
                    this.correo = correo;
                    return; // Si es válido, asigna y sale
                }
            }
        }
        this.correo = "correo.invalido@example.com"; // Valor por defecto 
    }

    public void setUsuario(String usuario) {
        if (usuario != null && usuario.trim().length() > 0) {
            this.usuario = usuario;
        } else {
            this.usuario = "defaultUser";
        }
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setEstado(EstadoCliente estado) {
        if (estado != null) {
            this.estado = estado;
        } else {
            this.estado = EstadoCliente.INACTIVO;
        }
    }

    // --- Métodos ---
    public boolean agregarCuenta(Cuenta nuevaCuenta) {
        if (numeroCuentasRegistradas < 5) { 
            this.cuentas[numeroCuentasRegistradas] = nuevaCuenta;
            numeroCuentasRegistradas++;
            return true;
        }
        return false;
    }

    private void generarTarjetaAcceso() {
        Random random = new Random();
        this.tarjetaAcceso = new int[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                this.tarjetaAcceso[i][j] = random.nextInt(10, 99); // Números entre 10 y 99
            }
        }
    }

    // Método para mostrar datos importantes en consola, con clave en asteriscos
    public void mostrarDatosImportantes() {
        System.out.println("\n--- Datos del Cliente ---");
        System.out.println("ID Cliente: " + getIdCliente());
        System.out.println("Nombre Completo: " + getNombreCompleto());
        System.out.println("Usuario: " + getUsuario());
        System.out.println("Teléfono: " + getTelefono());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Estado: " + getEstado());

        // Mostrar la clave como asteriscos
        String claveEnmascarada = "";
        if (this.clave != null) {
            for (int i = 0; i < this.clave.length(); i++) {
                claveEnmascarada += "*";
            }
        }
        System.out.println("Clave: " + claveEnmascarada);

        System.out.println("Cuentas del Cliente:");
        if (numeroCuentasRegistradas > 0) {
            for (int i = 0; i < numeroCuentasRegistradas; i++) {
                Cuenta cuenta = this.cuentas[i];
                System.out.println("  - Número: " + cuenta.getNumeroCuenta() +
                                   " | Tipo: " + cuenta.getTipoCuenta() +
                                   " | Saldo: " + cuenta.getSaldoInicial() +
                                   " | Apertura: " + cuenta.getFechaApertura());
                System.out.println("    Movimientos:\n" + cuenta.getDetallesMovimientos());
            }
        } else {
            System.out.println("  Ninguna cuenta registrada.");
        }

        System.out.println("Tarjeta de Acceso:");
        if (tarjetaAcceso != null) {
            for (int i = 0; i < 4; i++) {
                System.out.print("  ");
                for (int j = 0; j < 5; j++) {
                    if (tarjetaAcceso[i][j] < 10) {
                        System.out.print("0" + tarjetaAcceso[i][j] + " ");
                    } else {
                        System.out.print(tarjetaAcceso[i][j] + " ");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("  Tarjeta de Acceso no generada.");
        }
        System.out.println("-------------------------");
    }
}