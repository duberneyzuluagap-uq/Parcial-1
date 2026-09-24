package co.edu.uniquindio.poo.model;

public class Reserva {
private String codigoReserva;
private String fechaReserva;
private byte numeroNoches;
private String estadoReserva;
private String metodoPago;
private double valorTotal;
private byte cantidadHuespedes;

//contructor
public Reserva(String codigoReserva, byte numeroNoches, byte cantidadHuespedes, double valorTotal, String estadoReserva, String metodoPago, String fechaReserva){
    this.codigoReserva=codigoReserva;
    this.fechaReserva=fechaReserva;
    this.numeroNoches=numeroNoches;
    this.cantidadHuespedes=cantidadHuespedes;
    this.valorTotal=valorTotal;
    this.estadoReserva=estadoReserva;
    this.metodoPago=metodoPago;
}
//metodos
    public void agregarHabitacion(Habitacion habitacion){

    }
    public double calcularValorTotal(){
    return this.valorTotal;
    }
    public boolean reservaActiva(){
    return "ACTIVA".equalsIgnoreCase(this.estadoReserva);
    }
    //getters y setters
    public String getCodigoReserva(){return codigoReserva;}
    public void setCodigoReserva(String codigoReserva){this.codigoReserva=codigoReserva;}

    public String getFechaReserva(){return fechaReserva;}
    public void setFechaReserva(String fechaReserva){this.fechaReserva=fechaReserva;}

    public byte getNumeroNoches(){return numeroNoches;}
    public void setNumeroNoches(byte numeroNoches){this.numeroNoches=numeroNoches;}

    public double getValorTotal() {return valorTotal;}
    public void setValorTotal(double valorTotal){this.valorTotal=valorTotal;}

    public byte getCantidadHuespedes(){cantidadHuespedes;}
    public void setCantidadHuespedes(byte cantidadHuespedes){this.cantidadHuespedes=cantidadHuespedes}

    public String getEstadoReserva() {return estadoReserva;}
    public void setEstadoReserva(String estadoReserva) {this.estadoReserva = estadoReserva;}

    public String getMetodoPago() {return metodoPago;}
    public void setMetodoPago(String metodoPago){this.metodoPago=metodoPago}
}
