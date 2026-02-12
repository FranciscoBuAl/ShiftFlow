package com.empresa.sgah.domain.repository;

import com.empresa.sgah.domain.model.Shift;

import java.util.List;
import java.util.Optional;

public interface ShiftRepository {

    Optional<Shift> findById(String id);

    List<Shift> findAll();
}
