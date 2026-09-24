package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;

    private List<Huesped> listaHuespedes;
    private List<Habitacion> listaHabitaciones;
    private List<Reserva> listaReservas;

    private String[][] matrizOcupacion;
    public static final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    // Constructor
    public Hotel(String nombreComercial, String nit, String direccion, String telefono) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.listaHabitaciones = new ArrayList<>();
        this.listaHuespedes = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
        this.matrizOcupacion = new String[0][7];
    }

    // ==========================================
    // MÉTODOS DE GESTIÓN Y BÚSQUEDA BÁSICA
    // ==========================================

    public void agregarHuesped(Huesped huesped) {
        if (huesped != null) {
            this.listaHuespedes.add(huesped);
        }
    }

    public void agregarHabitacion(Habitacion habitacion) {
        if (habitacion != null) {
            this.listaHabitaciones.add(habitacion);
        }
    }

    public void realizarReserva(Reserva reserva) {
        if (reserva != null) {
            this.listaReservas.add(reserva);
            if (reserva.getHuesped() != null) {
                reserva.getHuesped().agregarReserva(reserva);
            }
        }
    }

    public void cancelarReserva(String codigoReserva) {
        for (int i = 0; i < listaReservas.size(); i++) {
            Reserva r = listaReservas.get(i);
            if (r.getCodigoReserva().equalsIgnoreCase(codigoReserva)) {
                r.setEstadoReserva("Cancelada");
                for (int j = 0; j < r.getHabitaciones().size(); j++) {
                    r.getHabitaciones().get(j).actualizarEstado("Disponible");
                }
                break;
            }
        }
    }

    public boolean consultarNuevoHuespedPorTelefono(String telefono) {
        return consultarHuespedPorTelefono(telefono) != null;
    }

    public Huesped buscarHuespedPorDocumento(String documento) {
        if (documento == null) return null;
        for (int i = 0; i < listaHuespedes.size(); i++) {
            Huesped h = listaHuespedes.get(i);
            if (h.getDocumento() != null && h.getDocumento().equals(documento.trim())) {
                return h;
            }
        }
        return null;
    }

    public Habitacion buscarHabitacionPorNumero(int numero) {
        for (int i = 0; i < listaHabitaciones.size(); i++) {
            Habitacion h = listaHabitaciones.get(i);
            if (h.getNumeroHabitacion() == numero) {
                return h;
            }
        }
        return null;
    }

    // ==========================================
    // PUNTO 1: CONSULTAR HUÉSPED POR TELÉFONO
    // ==========================================

    public Huesped consultarHuespedPorTelefono(String telefono) {
        if (telefono == null) return null;
        for (int i = 0; i < listaHuespedes.size(); i++) {
            Huesped h = listaHuespedes.get(i);
            if (h.getTelefono() != null && h.getTelefono().trim().equals(telefono.trim())) {
                return h;
            }
        }
        return null;
    }

    // ==========================================
    // PUNTO 2: CONTROL DE DISPONIBILIDAD Y PRECIOS
    // ==========================================

    public int contarHabitacionesPorEstado(String estado) {
        int contador = 0;
        for (int i = 0; i < listaHabitaciones.size(); i++) {
            Habitacion h = listaHabitaciones.get(i);
            if (h.getEstado() != null && h.getEstado().equalsIgnoreCase(estado.trim())) {
                contador++;
            }
        }
        return contador;
    }

    public int getCantidadHabitacionesDisponibles() {
        return contarHabitacionesPorEstado("Disponible");
    }

    public int getCantidadHabitacionesOcupadas() {
        return contarHabitacionesPorEstado("Ocupada");
    }

    public int getCantidadHabitacionesMantenimiento() {
        return contarHabitacionesPorEstado("Mantenimiento");
    }

    public Habitacion getHabitacionMayorPrecio() {
        if (listaHabitaciones.isEmpty()) return null;
        Habitacion mayor = listaHabitaciones.get(0);
        for (int i = 1; i < listaHabitaciones.size(); i++) {
            Habitacion h = listaHabitaciones.get(i);
            if (h.getPrecioPorNoche() > mayor.getPrecioPorNoche()) {
                mayor = h;
            }
        }
        return mayor;
    }

    public Habitacion getHabitacionMenorPrecio() {
        if (listaHabitaciones.isEmpty()) return null;
        Habitacion menor = listaHabitaciones.get(0);
        for (int i = 1; i < listaHabitaciones.size(); i++) {
            Habitacion h = listaHabitaciones.get(i);
            if (h.getPrecioPorNoche() < menor.getPrecioPorNoche()) {
                menor = h;
            }
        }
        return menor;
    }

    public String generarReporteDisponibilidad() {
        String reporte = "=== CONTROL DE DISPONIBILIDAD DE HABITACIONES ===\n" +
                         "- Habitaciones Disponibles: " + getCantidadHabitacionesDisponibles() + "\n" +
                         "- Habitaciones Ocupadas: " + getCantidadHabitacionesOcupadas() + "\n" +
                         "- Habitaciones Reservadas: " + contarHabitacionesPorEstado("Reservada") + "\n" +
                         "- Habitaciones en Mantenimiento: " + getCantidadHabitacionesMantenimiento() + "\n\n";

        Habitacion mayor = getHabitacionMayorPrecio();
        Habitacion menor = getHabitacionMenorPrecio();

        if (mayor != null) {
            reporte = reporte + "- Habitación con MAYOR precio: Hab #" + mayor.getNumeroHabitacion() + 
                      " (" + mayor.getTipoHabitacion() + ") - $" + mayor.getPrecioPorNoche() + " / noche\n";
        }
        if (menor != null) {
            reporte = reporte + "- Habitación con MENOR precio: Hab #" + menor.getNumeroHabitacion() + 
                      " (" + menor.getTipoHabitacion() + ") - $" + menor.getPrecioPorNoche() + " / noche\n";
        }
        return reporte;
    }

    // ==========================================
    // PUNTO 3: MATRIZ DE OCUPACIÓN DEL HOTEL
    // ==========================================

    public void inicializarMatrizOcupacion(String[][] matriz) {
        this.matrizOcupacion = matriz;
    }

    public int contarOcupadasPorDia(int colDia) {
        int ocupadas = 0;
        if (matrizOcupacion == null || colDia < 0 || colDia >= DIAS_SEMANA.length) {
            return 0;
        }
        for (int i = 0; i < matrizOcupacion.length; i++) {
            if ("O".equalsIgnoreCase(matrizOcupacion[i][colDia])) {
                ocupadas++;
            }
        }
        return ocupadas;
    }

    public String getDiaMayorOcupacion() {
        if (matrizOcupacion == null || matrizOcupacion.length == 0) return "No hay datos";
        int maxOcupadas = -1;
        int mejorIndice = 0;
        for (int j = 0; j < DIAS_SEMANA.length; j++) {
            int ocupadas = contarOcupadasPorDia(j);
            if (ocupadas > maxOcupadas) {
                maxOcupadas = ocupadas;
                mejorIndice = j;
            }
        }
        return DIAS_SEMANA[mejorIndice] + " (" + maxOcupadas + " ocupadas)";
    }

    public String getDiaMenorOcupacion() {
        if (matrizOcupacion == null || matrizOcupacion.length == 0) return "No hay datos";
        int minOcupadas = 999999;
        int menorIndice = 0;
        for (int j = 0; j < DIAS_SEMANA.length; j++) {
            int ocupadas = contarOcupadasPorDia(j);
            if (ocupadas < minOcupadas) {
                minOcupadas = ocupadas;
                menorIndice = j;
            }
        }
        return DIAS_SEMANA[menorIndice] + " (" + minOcupadas + " ocupadas)";
    }

    public int getCantidadTotalOcupadasSemana() {
        if (matrizOcupacion == null) return 0;
        int total = 0;
        for (int i = 0; i < matrizOcupacion.length; i++) {
            for (int j = 0; j < matrizOcupacion[i].length; j++) {
                if ("O".equalsIgnoreCase(matrizOcupacion[i][j])) {
                    total++;
                }
            }
        }
        return total;
    }

    public String obtenerMatrizOcupacionComoTexto() {
        if (matrizOcupacion == null || matrizOcupacion.length == 0) {
            return "Matriz de ocupación vacía.";
        }
        String texto = "Habitación\tLun\tMar\tMié\tJue\tVie\tSáb\tDom\n";
        texto = texto + "------------------------------------------------------------------------------------\n";

        for (int i = 0; i < matrizOcupacion.length; i++) {
            String etiqueta;
            if (i < listaHabitaciones.size()) {
                etiqueta = "Hab " + listaHabitaciones.get(i).getNumeroHabitacion();
            } else {
                etiqueta = "Habitación " + (i + 1);
            }
            texto = texto + etiqueta + "\t";
            for (int j = 0; j < matrizOcupacion[i].length; j++) {
                texto = texto + matrizOcupacion[i][j] + "\t";
            }
            texto = texto + "\n";
        }
        texto = texto + "\nConvención: O = Ocupada, D = Disponible\n";
        return texto;
    }

    // ==========================================
    // PUNTO 4: NÚMERO ESPECIAL DE RESERVA (CAPICÚA)
    // ==========================================

    public List<Reserva> obtenerReservasEspeciales() {
        List<Reserva> especiales = new ArrayList<>();
        for (int i = 0; i < listaReservas.size(); i++) {
            Reserva r = listaReservas.get(i);
            if (r.esEspecial()) {
                especiales.add(r);
            }
        }
        return especiales;
    }

    // ==========================================
    // PUNTO 5: INGRESOS DEL HOTEL POR FECHA
    // ==========================================

    public double calcularIngresosPorFecha(String fechaConsultada) {
        if (fechaConsultada == null) return 0.0;
        double total = 0.0;
        for (int i = 0; i < listaReservas.size(); i++) {
            Reserva r = listaReservas.get(i);
            if (r.getFechaReserva() != null && r.getFechaReserva().trim().equals(fechaConsultada.trim())) {
                total = total + r.getValorTotal();
            }
        }
        return total;
    }

    public List<Reserva> obtenerReservasPorFecha(String fechaConsultada) {
        List<Reserva> lista = new ArrayList<>();
        if (fechaConsultada == null) return lista;
        for (int i = 0; i < listaReservas.size(); i++) {
            Reserva r = listaReservas.get(i);
            if (r.getFechaReserva() != null && r.getFechaReserva().trim().equals(fechaConsultada.trim())) {
                lista.add(r);
            }
        }
        return lista;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<Habitacion> getListaHabitaciones() { return listaHabitaciones; }
    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) { this.listaHabitaciones = listaHabitaciones; }

    public List<Huesped> getListaHuespedes() { return listaHuespedes; }
    public void setListaHuespedes(List<Huesped> listaHuespedes) { this.listaHuespedes = listaHuespedes; }

    public List<Reserva> getListaReservas() { return listaReservas; }
    public void setListaReservas(List<Reserva> listaReservas) { this.listaReservas = listaReservas; }

    public String[][] getMatrizOcupacion() { return matrizOcupacion; }
    public void setMatrizOcupacion(String[][] matrizOcupacion) { this.matrizOcupacion = matrizOcupacion; }
}