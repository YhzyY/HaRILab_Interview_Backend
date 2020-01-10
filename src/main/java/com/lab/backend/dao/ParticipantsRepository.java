package com.lab.backend.dao;

import com.lab.backend.model.Participants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantsRepository extends CrudRepository<Participants, Integer> {
    public Optional<Participants> findById(Integer id);
    public Participants findByName(String name);
    public Participants findByUuid(String uuid);
//    public Optional<Participants> findByLastName(String lastName);
//    public Optional<Participants> findByFirstName(String firstName);
//    public Optional<Participants> findByUuid(Integer uuid);
//
//    public Object save(Participants participants);
//    public void deleteById(Integer id);

}
