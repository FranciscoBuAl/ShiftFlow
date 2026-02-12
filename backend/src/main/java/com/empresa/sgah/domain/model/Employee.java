package com.empresa.sgah.domain.model;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Employee {

    @PlanningId
    private String id;
    private String name;
    private Set<Skill> skills = new HashSet<>();

    public Employee() {
    }

    public Employee(String id, String name, Set<Skill> skills) {
        this.id = id;
        this.name = name;
        this.skills = skills == null ? new HashSet<>() : new HashSet<>(skills);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills == null ? new HashSet<>() : new HashSet<>(skills);
    }

    public boolean hasSkill(Skill skill) {
        return skill != null && skills.contains(skill);
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
