package com.lab.backend;

import com.lab.backend.dao.AttacksRepository;
import com.lab.backend.dao.CliniciansRepository;
import com.lab.backend.dao.ParticipantsRepository;
import com.lab.backend.model.Attacks;
import com.lab.backend.model.Clinicians;
import com.lab.backend.model.Participants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
//@ImportResource("classpath:applicationContext.xml")
public class BackendController {

    @Autowired
    ParticipantsRepository participantsRepository;
    @Autowired
    CliniciansRepository cliniciansRepository;
    @Autowired
    AttacksRepository attacksRepository;


//    private ParticipantsRepository participantsRepository;
//    @Autowired
//    public void setDependency(ParticipantsRepository participantsRepository){
//        this.participantsRepository = participantsRepository;
//    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/participants")
    public Iterable<Participants> showUsers(){
        try{
            return participantsRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("get participants error");
        }
        return null;
    }

    @PostMapping("/newUser")
    public String createParticipant(@RequestParam String firstName, String lastName, Integer uuid){
        participantsRepository.save(new Participants(firstName, lastName, uuid));
        return "newUser is added";
    }

//    @PostMapping("/newUser")
//    public String create(@RequestBody Participants participants){
//        participantsRepository.save(new Participants(participants.getFirstName(), participants.getLastName(), participants.getUuid()));
//        return "newUser is added";
//    }

    @GetMapping("/participantName")
    public String getParticipantName(@RequestParam Integer uuid) {
        return participantsRepository.findByUuid(uuid).getName();
    }




    @PostMapping("/newAttack")
    public String createAttack(@RequestParam LocalDate attackDate, LocalTime attackTime, String attackLocation, Integer uuid){
        attacksRepository.save(new Attacks(attackDate, attackTime, attackLocation, uuid));
        return "new attack is added";
    }

    @GetMapping("/attacks")
    public Iterable<Attacks> showAttacks(){
        try{
            return attacksRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("get attacks error");
        }
        return null;
    }

    @GetMapping("/todayAttacks")
    public Iterable<Attacks> showTodayAttacks(@RequestParam LocalDate today){
        try{
            return attacksRepository.findByAttackDate(today);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("get attacks error");
        }
        return null;
    }

    @DeleteMapping("/deleteAttack")
    public String delAttack(@RequestParam Integer id){
        attacksRepository.deleteById(id);
        return "attack " + id + " deleted";
    }

    @GetMapping("/attackId")
    public Integer getAttackId(@RequestParam LocalDate attackDate, LocalTime attackTime, String attackLocation, Integer uuid){
        return attacksRepository.findByAttackDateAndAttackTimeAndAttackLocationAndUuid(attackDate, attackTime, attackLocation, uuid).getId();
    }





    @PostMapping("/newClinician")
    public String createClinician(@RequestParam String firstName, String lastName, Integer password){
        cliniciansRepository.save(new Clinicians(firstName, lastName, password));
        return "newClinician is added";
    }

    @GetMapping("/clinicians")
    public Iterable<Clinicians> showClinicians(){
        try{
            return cliniciansRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("get clinicians error");
        }
        return null;
    }

    @GetMapping("/ClinicianLogin")
    public String ClinicianLogin(@RequestParam String firstName, String lastName, Integer password) {
        return cliniciansRepository.findByFirstNameAndLastNameAndPassword(firstName, lastName, password).getFirstName();
    }

}