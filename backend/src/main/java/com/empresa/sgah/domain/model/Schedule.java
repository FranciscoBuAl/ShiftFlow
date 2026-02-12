package com.empresa.sgah.domain.model;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.ArrayList;
import java.util.List;

@PlanningSolution
public class Schedule {

    @PlanningEntityCollectionProperty
    private List<Shift> shifts = new ArrayList<>();

    @ValueRangeProvider(id = "employeeRange")
    @ProblemFactCollectionProperty
    private List<Employee> employees = new ArrayList<>();

    @PlanningScore
    private HardSoftScore score;

    public Schedule() {
    }

    public Schedule(List<Shift> shifts, List<Employee> employees) {
        this.shifts = shifts == null ? new ArrayList<>() : new ArrayList<>(shifts);
        this.employees = employees == null ? new ArrayList<>() : new ArrayList<>(employees);
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts == null ? new ArrayList<>() : new ArrayList<>(shifts);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees == null ? new ArrayList<>() : new ArrayList<>(employees);
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}
