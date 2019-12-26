package com.lab.backend.dao;

import com.lab.backend.model.Attacks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttacksRepository extends CrudRepository<Attacks, Integer>{
}
