package com.horarios.solver.constraint;

import com.horarios.solver.domain.AvailabilityType;
import com.horarios.solver.domain.Employee;
import com.horarios.solver.domain.ShiftAssignment;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.ConstraintCollectors;
import ai.timefold.solver.core.api.score.stream.Joiners;

/**
 * Constraint Provider basado en Constraint Streams (sin DRL).
 *
 * Diseño preparado para escalar:
 * - Una función por regla.
 * - Nombres de constraints claros.
 * - Lógica encapsulada para facilitar evolución.
 */
public class ScheduleConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                noShiftOverlapPerEmployee(constraintFactory),
                employeeMustHaveRequiredSkill(constraintFactory),
                employeeMustNotBeUnavailable(constraintFactory),
                penalizeUndesiredAvailability(constraintFactory),
                balanceTotalWorkedHours(constraintFactory)
        };
    }

    /**
     * HARD: Evita que un empleado tenga dos turnos solapados.
     */
    private Constraint noShiftOverlapPerEmployee(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(
                        ShiftAssignment.class,
                        Joiners.equal(ShiftAssignment::getEmployee),
                        Joiners.overlapping(ShiftAssignment::getStart, ShiftAssignment::getEnd))
                .filter((left, right) -> left.getEmployee() != null && left.getStart() != null && left.getEnd() != null && right.getStart() != null && right.getEnd() != null)
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("No solapamiento de turnos por empleado");
    }

    /**
     * HARD: El empleado asignado debe cubrir todos los skills requeridos por el turno.
     */
    private Constraint employeeMustHaveRequiredSkill(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(ShiftAssignment.class)
                .filter(assignment -> assignment.getEmployee() != null)
                .filter(assignment -> assignment.getShift() != null)
                .filter(assignment -> !assignment.getEmployee().hasAllSkills(assignment.getShift().getRequiredSkills()))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Empleado debe tener skill requerida");
    }

    /**
     * HARD: No permitir asignación cuando la disponibilidad declarada es UNAVAILABLE.
     */
    private Constraint employeeMustNotBeUnavailable(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(ShiftAssignment.class)
                .filter(assignment -> assignment.getEmployee() != null)
                .filter(assignment -> assignment.getAvailabilityType() == AvailabilityType.UNAVAILABLE)
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Empleado no asignado si está no disponible");
    }

    /**
     * SOFT: Penaliza asignaciones válidas pero no deseadas para guiar soluciones más cómodas.
     */
    private Constraint penalizeUndesiredAvailability(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(ShiftAssignment.class)
                .filter(assignment -> assignment.getEmployee() != null)
                .filter(assignment -> assignment.getAvailabilityType() == AvailabilityType.UNDESIRED)
                .penalize(HardSoftScore.ONE_SOFT)
                .asConstraint("Penalizar disponibilidad UNDESIRED");
    }

    /**
     * SOFT: Balancea la carga comparando minutos asignados vs minutos objetivo por empleado.
     *
     * Cuanto mayor sea la desviación absoluta respecto al objetivo, mayor penalización.
     * Esto permite escalar a políticas de balance por semana/mes ajustando targetWorkMinutes.
     */
    private Constraint balanceTotalWorkedHours(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(ShiftAssignment.class)
                .filter(assignment -> assignment.getEmployee() != null)
                .groupBy(ShiftAssignment::getEmployee, ConstraintCollectors.sumLong(ShiftAssignment::getDurationMinutes))
                .penalizeLong(
                        HardSoftScore.ONE_SOFT,
                        (employee, assignedMinutes) -> Math.abs(assignedMinutes - employee.getTargetWorkMinutes()))
                .asConstraint("Balancear horas totales trabajadas");
    }
}
