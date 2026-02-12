package com.empresa.sgah.domain;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;

import java.time.LocalDateTime;
import java.util.List;

@PlanningSolution
public class ScheduleSolution {

    // Rango de Tiempo: Los huecos donde la IA puede colocar tareas flexibles.
    // Ej: [Lunes 9:00, Lunes 9:15, Lunes 9:30...]
    @ValueRangeProvider(id = "timeRange")
    @ProblemFactCollectionProperty
    private List<LocalDateTime> availableTimeSlots;

    // Las Tareas: Tanto las que ya tienen hora fija como las que la IA debe asignar.
    @PlanningEntityCollectionProperty
    private List<CalendarTask> taskList;

    // La Puntuación: La IA actualiza esto constantemente.
    // Hard Score (negativo) = Reglas rotas (ej. solapamientos).
    // Soft Score (positivo) = Preferencias cumplidas.
    @PlanningScore
    private HardSoftScore score;

    // Constructor vacío obligatorio
    public ScheduleSolution() {}

    // Getters y Setters
    public List<LocalDateTime> getAvailableTimeSlots() { return availableTimeSlots; }
    public void setAvailableTimeSlots(List<LocalDateTime> availableTimeSlots) { this.availableTimeSlots = availableTimeSlots; }

    public List<CalendarTask> getTaskList() { return taskList; }
    public void setTaskList(List<CalendarTask> taskList) { this.taskList = taskList; }

    public HardSoftScore getScore() { return score; }
    public void setScore(HardSoftScore score) { this.score = score; }
}