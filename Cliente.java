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

public class Cliente {

    private String idCliente;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String usuario;
    private String clave;
    private EstadoCliente estado;
    private Cuenta cuentaObj1;
    private Cuenta cuentaObj2;
    private int numeroCuentasRegistradas;
    private int[][] tarjetaAcceso;

    // Constructor 
    public Cliente(String idCliente, String nombreCompleto, String telefono, String correo, String usuario) {
        this.setIdCliente(idCliente);
        this.setNombreCompleto(nombreCompleto);
        this.setTelefono(telefono);
        this.setCorreo(correo);
        this.setUsuario(usuario);
        this.clave = "";
        this.estado = EstadoCliente.ACTIVO;
        this.numeroCuentasRegistradas = 0;
        this.generarTarjetaAcceso();
    }

    // Getters
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

    public Cuenta getCuentaObj1() {
        return cuentaObj1;
    }

    public Cuenta getCuentaObj2() {
        return cuentaObj2;
    }

    public int getNumeroCuentasRegistradas() {
        return numeroCuentasRegistradas;
    }

    public int[][] getTarjetaAcceso() {
        return tarjetaAcceso;
    }

    // Setters
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

    public void setTelefono(String telefono) {
        if (telefono != null && telefono.length() == 9 && telefono.charAt(4) == '-') {
            this.telefono = telefono;
        } else {
            this.telefono = "0000-0000";
        }
    }

    public void setCorreo(String correo) {
        if (correo != null) {
            int atIndex = correo.indexOf('@');
            if (atIndex > 0 && atIndex < correo.length() - 1) {
                int dotIndex = correo.indexOf('.', atIndex + 1);
                if (dotIndex > atIndex + 1 && dotIndex < correo.length() - 1) {
                    this.correo = correo;
                    return;
                }
            }
        }
        this.correo = "invalido@dominio.com";
    }

    public void setUsuario(String usuario) {
        if (usuario != null && usuario.trim().length() > 0) {
            this.usuario = usuario;
        } else {
            this.usuario = "defaultUser";
        }
    }

    public void setClave(String clave) {
        if (validarClave(clave)) {
            this.clave = clave;
        } else {
            this.clave = "";
        }
    }

    public void setEstado(EstadoCliente estado) {
        if (estado != null) {
            this.estado = estado;
        } else {
            this.estado = EstadoCliente.INACTIVO;
        }
    }

    // Método para validar clave
    public boolean validarClave(String clave) {
        if (clave == null || clave.length() < 6 || clave.length() > 10) {
            return false;
        }
        boolean tieneNumero = false;
        boolean tieneLetra = false;
        for (char c : clave.toCharArray()) {
            if (Character.isDigit(c)) {
                tieneNumero = true;
            }
            if (Character.isLetter(c)) {
                tieneLetra = true;
            }
        }
        return tieneNumero && tieneLetra;
    }

    // Métodos
    public boolean agregarCuenta(Cuenta nuevaCuenta) {
        if (numeroCuentasRegistradas < 2) {
            if (cuentaObj1 == null) {
                cuentaObj1 = nuevaCuenta;
            } else if (cuentaObj2 == null) {
                cuentaObj2 = nuevaCuenta;
            }
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
                this.tarjetaAcceso[i][j] = random.nextInt(10, 90);
            }
        }
    }

    public void mostrarDatosImportantes() {
        String cuentasStr = "";
        if (numeroCuentasRegistradas > 0) {
            if (cuentaObj1 != null) {
                cuentasStr += cuentaObj1.getNumeroCuenta();
            }
            if (cuentaObj2 != null) {
                cuentasStr += ", " + cuentaObj2.getNumeroCuenta();
            }
        } else {
            cuentasStr = "Ninguna";
        }

        // Mostrar tarjeta de acceso en consola
        System.out.println("| Tarjeta de acceso | A | B | C | D | E |");
        for (int i = 0; i < 4; i++) {
            System.out.print("| " + (i + 1) + " | ");
            for (int j = 0; j < 5; j++) {
                System.out.print(tarjetaAcceso[i][j] + " | ");
            }
            System.out.println();
        }

        JOptionPane.showMessageDialog(null, "--- Datos del Cliente ---\n"
                + "ID Cliente: " + getIdCliente() + "\n"
                + "Nombre Completo: " + getNombreCompleto() + "\n"
                + "Usuario: " + getUsuario() + "\n"
                + "Números de Cuentas: [" + cuentasStr + "]");
    }

    public void mostrarDatosCliente() {
        System.out.println("\n--- Datos del Cliente ---");
        System.out.println("ID Cliente: " + getIdCliente());
        System.out.println("Nombre Completo: " + getNombreCompleto());
        System.out.println("Usuario: " + getUsuario());
        System.out.println("Teléfono: " + getTelefono());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Estado: " + getEstado());
        System.out.println("Clave: " + "*".repeat(clave.length()));

        String cuentasStr = "";
        if (numeroCuentasRegistradas > 0) {
            if (cuentaObj1 != null) {
                cuentasStr = cuentaObj1.getNumeroCuenta()
                        + " (" + cuentaObj1.getTipoCuenta()
                        + ") Saldo: " + cuentaObj1.getSaldoInicial();
            }
            if (cuentaObj2 != null) {
                if (cuentasStr.length() > 0) {
                    cuentasStr += ", ";
                }
                cuentasStr += cuentaObj2.getNumeroCuenta()
                        + " (" + cuentaObj2.getTipoCuenta()
                        + ") Saldo: " + cuentaObj2.getSaldoInicial();
            }
        } else {
            cuentasStr = "Ninguna";
        }
        System.out.println("Números de Cuentas: [" + cuentasStr + "]");

        System.out.println("Tarjeta de Acceso:");
        System.out.println("| Tarjeta de acceso | A | B | C | D | E |");
        for (int i = 0; i < 4; i++) {
            System.out.print("| " + (i + 1) + " | ");
            for (int j = 0; j < 5; j++) {
                System.out.print(tarjetaAcceso[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

}
