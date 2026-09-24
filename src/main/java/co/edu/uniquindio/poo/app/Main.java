package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Habitacion;
import co.edu.uniquindio.poo.model.Hotel;
import co.edu.uniquindio.poo.model.Huesped;
import co.edu.uniquindio.poo.model.Reserva;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        Hotel hotelStayPlus = new Hotel("StayPlus", "192.168.101.4",
                "Av. Bolivar calle 4", "3181101943");

        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de manejo del hotel");

        int opcion = 0;

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "--- MENU HOTEL STAYPLUS ---\n\n" +
                    "1. Registrar Huesped\n" +
                    "2. Registrar Habitacion\n" +
                    "3. Registrar Reserva\n" +
                    "4. Consultar Huesped por Telefono\n" +
                    "5. Control de Disponibilidad de Habitaciones\n" +
                    "6. Analizar Matriz de Ocupacion Semanal\n" +
                    "7. Verificar Reservas Especiales\n" +
                    "8. Consultar Ingresos por Fecha\n" +
                    "9. Salir\n\n" +
                    "Ingrese una opcion:"));

            switch (opcion) {
                case 1:
                    opcionAgregarHuesped(hotelStayPlus);
                    break;
                case 2:
                    opcionAgregarHabitacion(hotelStayPlus);
                    break;
                case 3:
                    opcionAgregarReserva(hotelStayPlus);
                    break;
                case 4:
                    opcionConsultarHuesped(hotelStayPlus);
                    break;
                case 5:
                    opcionControlDisponibilidad(hotelStayPlus);
                    break;
                case 6:
                    opcionMatrizOcupacion(hotelStayPlus);
                    break;
                case 7:
                    opcionReservasCapicua(hotelStayPlus);
                    break;
                case 8:
                    opcionConsultarIngresos(hotelStayPlus);
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida.");
            }

        } while (opcion != 9);
    }

    public static void opcionAgregarHuesped(Hotel hotelStayPlus) {
        String doc = JOptionPane.showInputDialog("Documento del huesped:");
        String nom = JOptionPane.showInputDialog("Nombre completo:");
        byte edad = Byte.parseByte(JOptionPane.showInputDialog("Edad:"));
        String tel = JOptionPane.showInputDialog("Telefono:");
        String ciudad = JOptionPane.showInputDialog("Ciudad:");

        Huesped nuevoHuesped = new Huesped(doc, nom, edad, tel, ciudad);
        hotelStayPlus.agregarHuesped(nuevoHuesped);

        JOptionPane.showMessageDialog(null, "Huesped guardado.");
    }

    public static void opcionAgregarHabitacion(Hotel hotelStayPlus) {
        int numHab = Integer.parseInt(JOptionPane.showInputDialog("Numero de habitacion:"));
        String tipo = JOptionPane.showInputDialog("Tipo (Individual, Doble, Suite):");
        byte piso = Byte.parseByte(JOptionPane.showInputDialog("Piso:"));
        byte capacidad = Byte.parseByte(JOptionPane.showInputDialog("Capacidad personas:"));
        String estado = JOptionPane.showInputDialog("Estado (Disponible, Reservada, Ocupada, Mantenimiento):");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio por noche:"));

        Habitacion nuevaHabitacion = new Habitacion(numHab, tipo, piso, capacidad, estado, precio);
        hotelStayPlus.agregarHabitacion(nuevaHabitacion);

        hotelStayPlus.actualizarTamanoMatrizOcupacion();

        JOptionPane.showMessageDialog(null, "Habitacion guardada.");
    }

    public static void opcionAgregarReserva(Hotel hotelStayPlus) {
        String telHuesped = JOptionPane.showInputDialog("Telefono del huesped:");
        Huesped huesped = hotelStayPlus.consultarHuespedPorTelefono(telHuesped);

        if (huesped == null) {
            JOptionPane.showMessageDialog(null, "El huesped no existe.");
            return;
        }

        String codReserva = JOptionPane.showInputDialog("Codigo de reserva:");
        String fechaReserva = JOptionPane.showInputDialog("Fecha (YYYY-MM-DD):");
        byte noches = Byte.parseByte(JOptionPane.showInputDialog("Cantidad de noches:"));
        byte cantHuespedes = Byte.parseByte(JOptionPane.showInputDialog("Cantidad de personas:"));
        String estReserva = JOptionPane.showInputDialog("Estado (Pendiente, Confirmada, Finalizada):");
        String metodoPago = JOptionPane.showInputDialog("Metodo de pago (Efectivo, Tarjeta, Transferencia):");

        Reserva nuevaReserva = new Reserva(codReserva, fechaReserva, noches, cantHuespedes, estReserva, metodoPago, huesped);

        hotelStayPlus.realizarReserva(nuevaReserva);

        JOptionPane.showMessageDialog(null, "Reserva registrada a " + huesped.getNombre());
    }

    public static void opcionConsultarHuesped(Hotel hotelStayPlus) {
        String telConsulta = JOptionPane.showInputDialog("Telefono a buscar:");
        Huesped h = hotelStayPlus.consultarHuespedPorTelefono(telConsulta);

        if (h != null) {
            JOptionPane.showMessageDialog(null, h.obtenerInformacionCompleta());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el huesped con telefono " + telConsulta);
        }
    }

    public static void opcionControlDisponibilidad(Hotel hotelStayPlus) {
        JOptionPane.showMessageDialog(null, hotelStayPlus.generarReporteDisponibilidad());
    }

    public static void opcionMatrizOcupacion(Hotel hotelStayPlus) {
        String subMenu = "--- MATRIZ DE OCUPACION ---\n" +
                "1. Ver Matriz Semanal\n" +
                "2. Registrar Ocupacion en un Dia\n" +
                "3. Ver Dia con Mayor y Menor Ocupacion\n" +
                "Ingrese una opcion:";

        int subOpcion = Integer.parseInt(JOptionPane.showInputDialog(subMenu));

        if (subOpcion == 1) {
            JOptionPane.showMessageDialog(null, hotelStayPlus.obtenerMatrizOcupacionComoTexto());
        } else if (subOpcion == 2) {
            int numHab = Integer.parseInt(JOptionPane.showInputDialog("Numero de habitacion:"));
            int dia = Integer.parseInt(JOptionPane.showInputDialog("Dia (0=Lun, 1=Mar, 2=Mie, 3=Jue, 4=Vie, 5=Sab, 6=Dom):"));
            String estado = JOptionPane.showInputDialog("Ingrese O (Ocupada) o D (Disponible):");

            hotelStayPlus.cambiarEstadoOcupacionMatriz(numHab, dia, estado);
            JOptionPane.showMessageDialog(null, "Ocupacion registrada.");
        } else if (subOpcion == 3) {
            String reporte = "Dia con Mayor Ocupacion: " + hotelStayPlus.getDiaMayorOcupacion() + "\n" +
                    "Dia con Menor Ocupacion: " + hotelStayPlus.getDiaMenorOcupacion() + "\n" +
                    "Total Ocupadas Semana: " + hotelStayPlus.getCantidadTotalOcupadasSemana();
            JOptionPane.showMessageDialog(null, reporte);
        }
    }

    public static void opcionReservasCapicua(Hotel hotelStayPlus) {
        String reporte = "RESERVAS ESPECIALES:\n\n";

        if (hotelStayPlus.getListaReservas().size() == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas.");
            return;
        }

        for (int i = 0; i < hotelStayPlus.getListaReservas().size(); i++) {
            Reserva r = hotelStayPlus.getListaReservas().get(i);
            if (r.esEspecial()) {
                reporte += "Reserva " + r.getCodigoReserva() + " es especial\n";
            } else {
                reporte += "Reserva " + r.getCodigoReserva() + " no es especial\n";
            }
        }

        JOptionPane.showMessageDialog(null, reporte);
    }

    public static void opcionConsultarIngresos(Hotel hotelStayPlus) {
        String fecha = JOptionPane.showInputDialog("Fecha a buscar (YYYY-MM-DD):");
        double total = hotelStayPlus.calcularIngresosPorFecha(fecha);
        JOptionPane.showMessageDialog(null, "Total ingresos de la fecha " + fecha + ": $" + total);
    }
}