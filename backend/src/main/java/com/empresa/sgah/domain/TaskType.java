package com.empresa.sgah.domain;

public enum TaskType {
    // Tareas FIJAS (El algoritmo NO las mueve, actúan como paredes)
    FIXED_ROUTINE,   // Ej: Reunión diaria, Reporte semanal
    FIXED_EVENT,     // Ej: Cita médica, Presentación puntual

    // Tareas FLEXIBLES (El algoritmo decide cuándo ponerlas)
    FLEXIBLE_HABIT,  // Ej: Leer, Gimnasio (se repiten)
    FLEXIBLE_TASK    // Ej: Brainstorming, Tarea creativa (una vez)
}