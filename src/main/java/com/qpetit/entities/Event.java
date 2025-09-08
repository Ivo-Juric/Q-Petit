package com.qpetit.entities;

import java.time.LocalDateTime;

public class Event {
    private int idEvent;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int cantidadInvitados;
    private int idTipoEvento; // FK a TipoEvento
    private int tipoMenu;     // tipo_Menu (columna int)
    private int idProveedor;
    private String linkHojaServicio;
    private String idCliente; // DNI cliente
    private int idMenu;
    private int idEstado;

    public Event() {}

    public Event(int idEvent, LocalDateTime fechaInicio, LocalDateTime fechaFin, int cantidadInvitados,
                 int idTipoEvento, int tipoMenu, int idProveedor, String linkHojaServicio,
                 String idCliente, int idMenu, int idEstado) {
        this.idEvent = idEvent;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadInvitados = cantidadInvitados;
        this.idTipoEvento = idTipoEvento;
        this.tipoMenu = tipoMenu;
        this.idProveedor = idProveedor;
        this.linkHojaServicio = linkHojaServicio;
        this.idCliente = idCliente;
        this.idMenu = idMenu;
        this.idEstado = idEstado;
    }

    // getters y setters
    public int getIdEvent() { return idEvent; }
    public void setIdEvent(int idEvent) { this.idEvent = idEvent; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }

    public int getCantidadInvitados() { return cantidadInvitados; }
    public void setCantidadInvitados(int cantidadInvitados) { this.cantidadInvitados = cantidadInvitados; }

    public int getIdTipoEvento() { return idTipoEvento; }
    public void setIdTipoEvento(int idTipoEvento) { this.idTipoEvento = idTipoEvento; }

    public int getTipoMenu() { return tipoMenu; }
    public void setTipoMenu(int tipoMenu) { this.tipoMenu = tipoMenu; }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getLinkHojaServicio() { return linkHojaServicio; }
    public void setLinkHojaServicio(String linkHojaServicio) { this.linkHojaServicio = linkHojaServicio; }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public int getIdMenu() { return idMenu; }
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }

    public int getIdEstado() { return idEstado; }
    public void setIdEstado(int idEstado) { this.idEstado = idEstado; }
}
