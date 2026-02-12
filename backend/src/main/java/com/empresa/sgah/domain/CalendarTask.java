package com.empresa.sgah.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.entity.PlanningPin;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import java.time.LocalDateTime;

@PlanningEntity
public class CalendarTask {

    @PlanningId
    private String id;
    private String title;
    private int durationMinutes;
    private TaskType type;

    // --- VARIABLES DE LA PLANIFICACIÓN ---

    // Esta es la fecha que la IA intentará decidir (solo si no está "pinned")
    @PlanningVariable(valueRangeProviderRefs = "timeRange")
    private LocalDateTime startTime;

    public CalendarTask() {}

    public CalendarTask(String id, String title, int durationMinutes, TaskType type) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.type = type;
    }

    // --- LÓGICA DE ANCLAJE (PINNING) ---
    // Si devuelve TRUE, Timefold NO moverá esta tarea. Actúa como un muro.
    @PlanningPin
    public boolean isPinned() {
        return type == TaskType.FIXED_ROUTINE |

| type == TaskType.FIXED_EVENTUAL;
    }

    // --- HELPERS ---
    public LocalDateTime getEndTime() {
        if (startTime == null) return null;
        return startTime.plusMinutes(durationMinutes);
    }

    // Getters y Setters estándar
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
    
    public TaskType getType() { return type; }
    public void setType(TaskType type) { this.type = type; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
}