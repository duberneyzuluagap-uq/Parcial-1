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
    //metodos
    public boolean consultarNuevoHuespedPorTelefono(String telefono){
        for(Huesped h: listaHuespedes){
            if(h.getTelefono().equals(telefono)){
                return true;
            }
        }
        return false;
    }
    public void agregarHuesped(Huesped huesped){
        this.listaHuespedes.add(huesped);
    }
    public void agregarHabitacion(Habitacion habitacion){
        this.listaHabitaciones.add(habitacion);
    }
    public void realizarReserva(Reserva reserva){

    }
    public void cancelarReserva(String codigoReserva){

    }
    //getters y setters
    public String getNombreComercial(){return nombreComercial;}
    public void setNombreComercial(String nombreComercial){this.nombreComercial=nombreComercial;}

    public String getNit(){return nit;}
    public void setNit(String nit){this.nit=nit;}

    public String getDireccion(){return direccion;}
    public void setDireccion(String direccion){this.direccion=direccion;}

    public String getTelefono(){return telefono;}
    public void setTelefono(String telefono){this.telefono=telefono;}

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public List<Huesped> getListaHuespedes() {
        return listaHuespedes;
    }
}