package com.horarios.solver.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Empleado asignable a turnos.
 */
public class Employee {

    private String id;
    private Set<String> skills = new HashSet<>();
    /**
     * Minutos objetivo para equilibrar carga semanal/mensual.
     */
    private long targetWorkMinutes;

    public Employee() {
    }

    public Employee(String id, Set<String> skills, long targetWorkMinutes) {
        this.id = id;
        this.skills = skills;
        this.targetWorkMinutes = targetWorkMinutes;
    }

    public String getId() {
        return id;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public void setTargetWorkMinutes(long targetWorkMinutes) {
        this.targetWorkMinutes = targetWorkMinutes;
    }

    public long getTargetWorkMinutes() {
        return targetWorkMinutes;
    }

    public boolean hasAllSkills(Set<String> requiredSkills) {
        return requiredSkills == null || requiredSkills.isEmpty() || skills.containsAll(requiredSkills);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee employee)) {
            return false;
        }
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
