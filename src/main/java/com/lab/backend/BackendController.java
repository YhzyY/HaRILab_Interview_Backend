package com.lab.backend;

import com.lab.backend.dao.AttacksRepository;
import com.lab.backend.dao.CliniciansRepository;
import com.lab.backend.dao.ParticipantsRepository;
import com.lab.backend.model.Attacks;
import com.lab.backend.model.Clinicians;
import com.lab.backend.model.Participants;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
    public String createParticipant(@RequestParam String name, Integer uuid){
        if(participantsRepository.findByName(name) != null)
            return "failed: userName exists";
        else if(participantsRepository.findByUuid(uuid) != null)
            return "already registered";
        else {
            participantsRepository.save(new Participants(name, uuid));
            return "newUser is added";
        }
    }

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
    public Iterable<Attacks> showTodayAttacks(@RequestParam LocalDate today, Integer uuid){
        return attacksRepository.findByAttackDateAndUuid(today, uuid);
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

    @PutMapping("/modifyAttack")
    public String modifyAttack(@RequestParam LocalDate attackDate, LocalTime attackTime, String attackLocation, Integer id){
        Attacks attack = attacksRepository.findById(id).get();
        attack.setAttackDate(attackDate);
        attack.setAttackTime(attackTime);
        attack.setAttackLocation(attackLocation);
        attacksRepository.save(attack);
        return "attack modified";
    }

//    tutorial: https://www.baeldung.com/spring-data-jpa-pagination-sorting
    @GetMapping("/userAttacks")
    public List<Attacks> showUserAttacks(@RequestParam Integer uuid){
        return attacksRepository.findByUuid(uuid, Sort.by("attackDate").descending().and(Sort.by("attackTime")));
    }

    @GetMapping("/attacksReport")
    public List<Integer> showAttacksReport(@RequestParam LocalDate day, Integer uuid){
        List numList = new ArrayList();
        LocalDate date;
        for(int i = 0; i < 7; i++){
            date = day.minusDays(i);
            numList.add(0, IterableUtils.size(attacksRepository.findByAttackDateAndUuid(date, uuid)));
        }
        return numList;
    }

    @GetMapping("/PatientList")
    public List getPatientList(){
        List finalList = new ArrayList();
        List tempList = new ArrayList();
        Attacks lastAttack;
        Integer tempUuid;
        LocalDate tempDate;
        LocalDate thisDay = LocalDate.now();
        Iterable<Participants> allParticipants = participantsRepository.findAll();
        for (Participants p : allParticipants){
            tempList.add(p.getName());
            tempUuid = p.getUuid();
            lastAttack = attacksRepository.findByUuid(tempUuid, Sort.by("attackDate").descending().and(Sort.by("attackTime"))).get(0);
            tempDate = lastAttack.getAttackDate();
            if (tempDate.isBefore(thisDay.minusDays(2))){
                tempList.add("alert");
            }else{
                tempList.add("none");
            }
            tempList.add(lastAttack.getAttackDate());
            tempList.add(lastAttack.getAttackTime());
            finalList.add(tempList);
            tempList.clear();
        }
        return finalList;
    }





    @PostMapping("/newClinician")
    public String createClinician(@RequestParam String userName, Integer password){
        if(cliniciansRepository.findByUserName(userName) != null)
            return "failed: userName exists";
        else{
            cliniciansRepository.save(new Clinicians(userName, password));
            return "newClinician is added";
        }
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
    public String ClinicianLogin(@RequestParam String userName, Integer password) {
        return cliniciansRepository.findByUserNameAndPassword(userName, password).getUserName();
    }

}