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
import javax.swing.JOptionPane;

public class Cuenta {
 
    private String numeroCuenta;
    private Cliente duenoCuenta;
    private TipoCuenta tipoCuenta;
    private LocalDate fechaApertura;
    private double saldoInicial;
    private String[] movimientos;
    private int contadorMovimientos;

    // Constante para el número máximo de movimientos
    private static final int MAX_MOVIMIENTOS = 50;

    // Constructor
    public Cuenta(String numeroCuenta, Cliente duenoCuenta, TipoCuenta tipoCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.duenoCuenta = duenoCuenta;
        this.tipoCuenta = tipoCuenta;
        this.fechaApertura = LocalDate.now(); // Se obtiene la fecha actual del sistema
        this.saldoInicial = saldoInicial;
        this.movimientos = new String[MAX_MOVIMIENTOS];
        this.contadorMovimientos = 0;
    }

    // --- Getters ---
    public String getNumeroCuenta() { return numeroCuenta; }
    public Cliente getDuenoCuenta() { return duenoCuenta; }
    public TipoCuenta getTipoCuenta() { return tipoCuenta; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public double getSaldoInicial() { return saldoInicial; } // Nota: el nombre del getter debería ser getSaldo, no getSaldoInicial

    // Devuelve una cadena con todos los movimientos registrados.
    public String getDetallesMovimientos() {
        if (contadorMovimientos == 0) {
            return "    No hay movimientos registrados por el momento.";
        }
        StringBuilder detalles = new StringBuilder();
        for (int i = 0; i < contadorMovimientos; i++) {
            detalles.append("    - ").append(movimientos[i]).append("\n");
        }
        return detalles.toString();
    }

    // --- Setters ---
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setDuenoCuenta(Cliente duenoCuenta) {
        this.duenoCuenta = duenoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public void setSaldoInicial(double saldoInicial) { // Nota: el nombre del setter debería ser setSaldo, no setSaldoInicial
        this.saldoInicial = saldoInicial;
    }

    // --- Métodos ---

    // Agrega un nuevo movimiento al historial de la cuenta.
    public void agregarMovimiento(String descripcion) {
        if (contadorMovimientos < MAX_MOVIMIENTOS) {
            this.movimientos[contadorMovimientos] = LocalDate.now() + ": " + descripcion; // Añade la fecha al movimiento
            contadorMovimientos++;
        } else {
            System.out.println("Advertencia: Límite de movimientos alcanzado para la cuenta " + numeroCuenta);
        }
    }
}