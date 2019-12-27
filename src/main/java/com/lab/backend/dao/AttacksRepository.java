package com.lab.backend.dao;

import com.lab.backend.model.Attacks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface AttacksRepository extends CrudRepository<Attacks, Integer>{
    public Optional<Attacks> findById(Integer id);
    public Iterable<Attacks> findByAttackDate(LocalDate today);
    public Attacks findByAttackDateAndAttackTimeAndAttackLocationAndUuid(LocalDate attackDate, LocalTime attackTime, String attackLocation, Integer uuid);
}
