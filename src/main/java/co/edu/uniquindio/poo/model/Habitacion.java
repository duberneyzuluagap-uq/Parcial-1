package co.edu.uniquindio.poo.model;

public class Habitacion {
    private int numeroHabitacion;
    private String tipoHabitacion; //Individual, Doble o Suite
    private byte piso;
    private int capacidadMaxima;
    private double precioPorNoche;
    private String estado; // "Disponible", "Reservada", "Ocupada", "Mantenimiento"

    public Habitacion(int numeroHabitacion, String tipoHabitacion, byte piso, int capacidadMaxima, String estado, double precioPorNoche) {
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.piso = piso;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
        this.precioPorNoche = precioPorNoche;
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public byte getPiso() {
        return piso;
    }

    public void setPiso(byte piso) {
        this.piso = piso;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    @Override
    public String toString() {
        return "Habitación " + numeroHabitacion + 
               " [Tipo: " + tipoHabitacion + 
               ", Piso: " + piso + 
               ", Capacidad: " + capacidadMaxima + " pers." + 
               ", Precio/Noche: $" + precioPorNoche + 
               ", Estado: " + estado + "]";
    }
}
