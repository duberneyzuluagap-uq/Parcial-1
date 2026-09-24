package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

/*






 */
public class Huesped {
    private String documento;
    private String nombre;
    private byte edad;
    private String telefono;
    private String ciudad;
    private ArrayList<Reserva> reservas;

    public Huesped(String documento, String nombre, byte edad, String telefono,
                   String ciudad, ArrayList<Reserva> reservas) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.reservas = reservas;
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
}
