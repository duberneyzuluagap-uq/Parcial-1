package co.edu.uniquindio.poo.model;

public class Hotel {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;

    private List<Huesped> listaHuespedes;
    private List<Habitacion> listaHabitaciones;
    //constructor
    public hotel (String nombreComercial, String nit, String direccion, String telefono ) {
    this.nombreComercial=nombreComercial;
    this.nit=nit;
    this.direccion=direccion;
    this.telefono=telefono;
    this.listaHabitaciones=new Arraylist<>();
    this.listaHuespedes=new ArrayList<>();
    }
    //
}