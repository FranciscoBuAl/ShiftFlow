package com.horarios.solver.domain;

import java.time.LocalDateTime;

/**
 * Entidad de planificación: representa la asignación de un turno a un empleado.
 */
public class ShiftAssignment {

    private String id;
    private Shift shift;
    private Employee employee;
    private AvailabilityType availabilityType = AvailabilityType.DESIRED;

    public ShiftAssignment() {
    }

    public ShiftAssignment(String id, Shift shift, Employee employee, AvailabilityType availabilityType) {
        this.id = id;
        this.shift = shift;
        this.employee = employee;
        this.availabilityType = availabilityType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public AvailabilityType getAvailabilityType() {
        return availabilityType;
    }

    public void setAvailabilityType(AvailabilityType availabilityType) {
        this.availabilityType = availabilityType;
    }

    public LocalDateTime getStart() {
        return shift != null ? shift.getStart() : null;
    }

    public LocalDateTime getEnd() {
        return shift != null ? shift.getEnd() : null;
    }

    public long getDurationMinutes() {
        return shift != null ? shift.getDurationMinutes() : 0;
    }
}
