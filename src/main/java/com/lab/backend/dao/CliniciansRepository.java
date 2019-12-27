package com.lab.backend.dao;
import com.lab.backend.model.Clinicians;
import com.lab.backend.model.Participants;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CliniciansRepository extends CrudRepository<Clinicians, Integer>{
    public Clinicians findByFirstNameAndLastNameAndPassword(String firstName, String lastName, Integer password);
}
