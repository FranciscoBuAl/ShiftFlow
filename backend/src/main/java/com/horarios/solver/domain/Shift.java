package com.horarios.solver.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Turno a cubrir.
 */
public class Shift {

    private String id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Set<String> requiredSkills = new HashSet<>();

    public Shift() {
    }

    public Shift(String id, LocalDateTime start, LocalDateTime end, Set<String> requiredSkills) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.requiredSkills = requiredSkills;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public long getDurationMinutes() {
        if (start == null || end == null) {
            return 0;
        }
        return Duration.between(start, end).toMinutes();
    }
}
