package com.empresa.sgah.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Availability {

    private String employeeId;
    private LocalDateTime start;
    private LocalDateTime end;
    private AvailabilityType type;

    public Availability() {
    }

    public Availability(String employeeId, LocalDateTime start, LocalDateTime end, AvailabilityType type) {
        this.employeeId = employeeId;
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public AvailabilityType getType() {
        return type;
    }

    public void setType(AvailabilityType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Availability that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId)
                && Objects.equals(start, that.start)
                && Objects.equals(end, that.end)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, start, end, type);
    }
}
