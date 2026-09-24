package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Huesped {
    private String documento;
    private String nombre;
    private byte edad;
    private String telefono;
    private String ciudad;
    private ArrayList<Reserva> reservas;

    public Huesped(String documento, String nombre, byte edad, String telefono, String ciudad) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.reservas = new ArrayList<>();
    }

    public Huesped(String documento, String nombre, byte edad, String telefono,
                   String ciudad, ArrayList<Reserva> reservas) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudad = ciudad;
        if (reservas != null) {
            this.reservas = reservas;
        } else {
            this.reservas = new ArrayList<>();
        }
    }

    public void agregarReserva(Reserva reserva) {
        if (reserva != null && !this.reservas.contains(reserva)) {
            this.reservas.add(reserva);
        }
    }

    // Punto 1: Mostrar nombre, documento, ciudad y reservas
    public String obtenerInformacionCompleta() {
        String info = "=== INFORMACIÓN DEL HUÉSPED ===\n" +
                      "Nombre: " + nombre + "\n" +
                      "Documento: " + documento + "\n" +
                      "Ciudad: " + ciudad + "\n" +
                      "Teléfono: " + telefono + "\n" +
                      "Edad: " + edad + " años\n" +
                      "--- Reservas Realizadas (" + reservas.size() + ") ---\n";
        
        if (reservas.isEmpty()) {
            info = info + "No tiene reservas registradas.\n";
        } else {
            for (int i = 0; i < reservas.size(); i++) {
                Reserva r = reservas.get(i);
                info = info + "- Código: " + r.getCodigoReserva() + 
                              " | Fecha: " + r.getFechaReserva() + 
                              " | Noches: " + r.getNumeroNoches() + 
                              " | Total: $" + r.getValorTotal() + 
                              " | Estado: " + r.getEstadoReserva() + "\n";
            }
        }
        return info;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return nombre + " (Doc: " + documento + ", Tel: " + telefono + ", Ciudad: " + ciudad + ")";
    }
}
