package com.qpetit.entities;

import java.time.LocalDateTime;

public class Event {
    private int eventId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int guestCount;
    private int eventTypeId; // FK a TipoEvento
    private int menuType;     // tipo_Menu (columna int)
    private int supplierId;
    private String serviceSheetLink;
    private String customerId; // DNI cliente
    private int menuId;
    private int statusId;

    public Event() {}

    public Event(int eventId, LocalDateTime startDate, LocalDateTime endDate, int guestCount,
                 int eventTypeId, int menuType, int supplierId, String serviceSheetLink,
                 String customerId, int menuId, int statusId) {
        this.eventId = eventId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestCount = guestCount;
        this.eventTypeId = eventTypeId;
        this.menuType = menuType;
        this.supplierId = supplierId;
        this.serviceSheetLink = serviceSheetLink;
        this.customerId = customerId;
        this.menuId = menuId;
        this.statusId = statusId;
    }

    // getters y setters
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public int getGuestCount() { return guestCount; }
    public void setGuestCount(int guestCount) { this.guestCount = guestCount; }

    public int getEventTypeId() { return eventTypeId; }
    public void setEventTypeId(int eventTypeId) { this.eventTypeId = eventTypeId; }

    public int getMenuType() { return menuType; }
    public void setMenuType(int menuType) { this.menuType = menuType; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getServiceSheetLink() { return serviceSheetLink; }
    public void setServiceSheetLink(String serviceSheetLink) { this.serviceSheetLink = serviceSheetLink; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }

    public int getStatusId() { return statusId; }
    public void setStatusId(int statusId) { this.statusId = statusId; }
}
