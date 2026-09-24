package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Reserva {
    private String codigoReserva;
    private String fechaReserva;
    private byte numeroNoches;
    private String estadoReserva; // Pendiente, Confirmada, Finalizada
    private String metodoPago;    // Efectivo, Tarjeta, Transferencia bancaria
    private double valorTotal;
    private byte cantidadHuespedes;
    private ArrayList<Habitacion> habitaciones;
    private Huesped huesped;

    // Constructor completo con huésped
    public Reserva(String codigoReserva, String fechaReserva, byte numeroNoches, 
                   byte cantidadHuespedes, String estadoReserva, String metodoPago, Huesped huesped) {
        this.codigoReserva = codigoReserva;
        this.fechaReserva = fechaReserva;
        this.numeroNoches = numeroNoches;
        this.cantidadHuespedes = cantidadHuespedes;
        this.estadoReserva = estadoReserva;
        this.metodoPago = metodoPago;
        this.huesped = huesped;
        this.habitaciones = new ArrayList<>();
        this.valorTotal = 0;
    }

    // Constructor secundario por compatibilidad
    public Reserva(String codigoReserva, byte numeroNoches, byte cantidadHuespedes, 
                   double valorTotal, String estadoReserva, String metodoPago, String fechaReserva) {
        this.codigoReserva = codigoReserva;
        this.fechaReserva = fechaReserva;
        this.numeroNoches = numeroNoches;
        this.cantidadHuespedes = cantidadHuespedes;
        this.valorTotal = valorTotal;
        this.estadoReserva = estadoReserva;
        this.metodoPago = metodoPago;
        this.habitaciones = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        if (habitacion != null) {
            this.habitaciones.add(habitacion);
            if ("Confirmada".equalsIgnoreCase(this.estadoReserva)) {
                habitacion.actualizarEstado("Reservada");
            }
            calcularValorTotal();
        }
    }

    public double calcularValorTotal() {
        double sumaPreciosNoche = 0;
        if (habitaciones != null) {
            for (int i = 0; i < habitaciones.size(); i++) {
                Habitacion h = habitaciones.get(i);
                sumaPreciosNoche = sumaPreciosNoche + h.getPrecioPorNoche();
            }
        }
        this.valorTotal = sumaPreciosNoche * this.numeroNoches;
        return this.valorTotal;
    }

    // Punto 4: Algoritmo clásico para número capicúa
    public boolean esEspecial() {
        if (codigoReserva == null || codigoReserva.equals("")) {
            return false;
        }
        String codigo = codigoReserva.trim();
        int n = codigo.length();
        for (int i = 0; i < n / 2; i++) {
            if (codigo.charAt(i) != codigo.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean reservaActiva() {
        return "Confirmada".equalsIgnoreCase(this.estadoReserva) || "Pendiente".equalsIgnoreCase(this.estadoReserva);
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public byte getNumeroNoches() {
        return numeroNoches;
    }

    public void setNumeroNoches(byte numeroNoches) {
        this.numeroNoches = numeroNoches;
        calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public byte getCantidadHuespedes() {
        return cantidadHuespedes;
    }

    public void setCantidadHuespedes(byte cantidadHuespedes) {
        this.cantidadHuespedes = cantidadHuespedes;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
        if ("Confirmada".equalsIgnoreCase(estadoReserva) && habitaciones != null) {
            for (int i = 0; i < habitaciones.size(); i++) {
                habitaciones.get(i).actualizarEstado("Reservada");
            }
        }
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
        calcularValorTotal();
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    @Override
    public String toString() {
        String nombreHuesped = (huesped != null) ? huesped.getNombre() : "Sin asignar";
        int cantHab = (habitaciones != null) ? habitaciones.size() : 0;
        String especial = esEspecial() ? " (ESPECIAL/CAPICÚA)" : "";
        return "Reserva [" + codigoReserva + especial + "] | Fecha: " + fechaReserva + 
               " | Huésped: " + nombreHuesped + " | Noches: " + numeroNoches + 
               " | Hab: " + cantHab + " | Total: $" + valorTotal + 
               " | Estado: " + estadoReserva + " | Pago: " + metodoPago;
    }
}
