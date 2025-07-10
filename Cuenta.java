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

    // Constructor
    public Cuenta(String numeroCuenta, Cliente duenoCuenta, TipoCuenta tipoCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.duenoCuenta = duenoCuenta;
        this.tipoCuenta = tipoCuenta;
        this.fechaApertura = LocalDate.now();
        if (saldoInicial >= 0) {
            this.saldoInicial = saldoInicial;
        } else {
            this.saldoInicial = 0;
            JOptionPane.showMessageDialog(null, "El saldo inicial no puede ser negativo. Se estableció en 0.");
        }
        this.movimientos = new String[50];
        this.contadorMovimientos = 0;
    }

    // Getters
    public String getNumeroCuenta() { return numeroCuenta; }
    public Cliente getDuenoCuenta() { return duenoCuenta; }
    public TipoCuenta getTipoCuenta() { return tipoCuenta; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public double getSaldoInicial() { return saldoInicial; }

    public String getDetallesMovimientos() {
        if (contadorMovimientos == 0) {
            return "No hay movimientos registrados por el momento.";
        }
        String detalles = "";
        for (int i = 0; i < contadorMovimientos; i++) {
            detalles = detalles + "- " + movimientos[i] + "\n";
        }
        return detalles;
    }

    // Setters
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

    public void setSaldoInicial(double saldoInicial) {
        if (saldoInicial >= 0) {
            this.saldoInicial = saldoInicial;
        } else {
            JOptionPane.showMessageDialog(null, "El saldo no puede ser negativo.");
        }
    }

    // Métodos
    public void agregarMovimiento(String descripcion) {
        if (contadorMovimientos < 50) {
            this.movimientos[contadorMovimientos] = descripcion;
            this.contadorMovimientos++;
        } else {
            JOptionPane.showMessageDialog(null, "Se ha alcanzado el límite de 50 movimientos para esta cuenta.");
        }
    }
}
