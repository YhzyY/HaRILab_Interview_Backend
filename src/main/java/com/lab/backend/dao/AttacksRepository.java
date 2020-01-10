package com.lab.backend.dao;

import com.lab.backend.model.Attacks;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttacksRepository extends CrudRepository<Attacks, Integer>, PagingAndSortingRepository<Attacks, Integer> {
    public Optional<Attacks> findById(Integer id);
    public List<Attacks> findByUuid(String uuid, Sort and);
    public Iterable<Attacks> findByAttackDateAndUuid(LocalDate attackDate, String uuid);
    public Attacks findByAttackDateAndAttackTimeAndAttackLocationAndUuid(LocalDate attackDate, LocalTime attackTime, String attackLocation, String uuid);
}
