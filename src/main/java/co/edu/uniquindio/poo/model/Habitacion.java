package co.edu.uniquindio.poo.model;

public class Habitacion {
private String tipoHabitacion;
private int numeroHabitacion;
private byte piso;
private int capacidadMaxima;
private double precioPorNoche;
private String estado;

//constructor
    public Habitacion(String tipoHabitacion, int numeroHabitacion, byte piso, int capacidadMaxima, String estado, double precioPorNoche){
        this.tipoHabitacion=tipoHabitacion;
        this.numeroHabitacion=numeroHabitacion;
        this.piso=piso;
        this.precioPorNoche=precioPorNoche;
        this.capacidadMaxima=capacidadMaxima;
        this.estado=estado;
    }
//getters y setters
    public String getTipoHabitacion(){return tipoHabitacion;}
    public void setTipoHabitacion(String tipoHabitacion){this.tipoHabitacion=tipoHabitacion;}

    public int getNumeroHabitacion() {return numeroHabitacion;}
    public void setNumeroHabitacion(int numeroHabitacion){this.numeroHabitacion=numeroHabitacion;}

    public byte getPiso(){return piso;}
    public void setPiso(byte piso){this.piso=piso;}

    public int getCapacidadMaxima(){return capacidadMaxima;}
    public void setCapacidadMaxima(int capacidadMaxima){this.capacidadMaxima=capacidadMaxima;}

    public double getPrecioPorNoche(){return precioPorNoche;}
    public void setPrecioPorNoche(double precioPorNoche){this.precioPorNoche=precioPorNoche;}

    public String getEstado() {return estado;}
    public void setEstado(String estado){this.estado=estado;}
}
