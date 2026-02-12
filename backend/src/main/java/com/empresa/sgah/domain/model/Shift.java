package com.empresa.sgah.domain.model;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;

import java.time.LocalDateTime;
import java.util.Objects;

@PlanningEntity
public class Shift {

    @PlanningId
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Skill requiredSkill;

    @PlanningVariable(valueRangeProviderRefs = "employeeRange")
    private Employee assignedEmployee;

    public Shift() {
    }

    public Shift(String id, LocalDateTime startTime, LocalDateTime endTime, Skill requiredSkill) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredSkill = requiredSkill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Skill getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(Skill requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shift shift)) {
            return false;
        }
        return Objects.equals(id, shift.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
