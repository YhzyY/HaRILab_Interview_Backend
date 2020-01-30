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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
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
    public String createParticipant(@RequestParam String name, String uuid){
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
    public String getParticipantName(@RequestParam String uuid) {
        return participantsRepository.findByUuid(uuid).getName();
    }

//    @PostMapping("/newAttack")
//    public String createAttack(@RequestParam String attackDate, String attackTime, String attackLocation, String uuid){
//        LocalDate.parse(attackDate, formatter);
//        if (attacksRepository.findByAttackDateAndAttackTimeAndAttackLocationAndUuid(LocalDate.parse(attackDate, formatter), LocalTime.parse(attackTime), attackLocation, uuid) != null){
//            attacksRepository.save(new Attacks(LocalDate.parse(attackDate, formatter), LocalTime.parse(attackTime), attackLocation, uuid));
//            return "new attack is added";
//        }else{
////            return attacksRepository.findByAttackDateAndAttackTimeAndAttackLocationAndUuid(LocalDate.parse(attackDate, formatter), LocalTime.parse(attackTime), attackLocation, uuid).toString();
//            return "duplicate attack";
//        }
//    }

    @PostMapping("/newAttack")
    public String createAttack(@RequestParam String attackDate, String attackTime, String attackLocation, String uuid, String userDate){
        LocalDate.parse(attackDate, formatter);
        LocalDate.parse(userDate, formatter);
        try {
            if (attacksRepository.findByAttackDateAndAttackTimeAndAttackLocationAndUuid(LocalDate.parse(attackDate, formatter), LocalTime.parse(attackTime), attackLocation, uuid).getId() != null) {
                return "duplicate attack";
            }
        }catch (Exception e){
            attacksRepository.save(new Attacks(LocalDate.parse(attackDate, formatter), LocalTime.parse(attackTime), attackLocation, uuid, LocalDate.parse(userDate, formatter)));
            return "new attack is added";
        }
        return "newAttack() error";
    }

//    @PostMapping("/newAttack")
//    public String createAttack(@RequestParam @DateTimeFormat(pattern = "yyyy/M/d")LocalDate attackDate, @DateTimeFormat(pattern = "HH:mm")LocalTime attackTime, String attackLocation, String uuid){
//        attacksRepository.save(new Attacks(attackDate, attackTime, attackLocation, uuid));
//        return "new attack is added";
//    }

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
    public Iterable<Attacks> showTodayAttacks(@RequestParam String today, String uuid){
        return attacksRepository.findByUserDateAndUuid(LocalDate.parse(today, formatter), uuid);
    }

    @DeleteMapping("/deleteAttack")
    public String delAttack(@RequestParam Integer id){
        attacksRepository.deleteById(id);
        return "attack " + id + " deleted";
    }

    @GetMapping("/attackId")
    public Integer getAttackId(@RequestParam String attackDate, String attackTime, String attackLocation, String uuid){
        return attacksRepository.findByAttackDateAndAttackTimeAndAttackLocationAndUuid(LocalDate.parse(attackDate, formatter), LocalTime.parse(attackTime), attackLocation, uuid).getId();
    }

    @PutMapping("/modifyAttack")
    public String modifyAttack(@RequestParam String attackDate, String attackTime, String attackLocation, Integer id, String userDate){
        Attacks attack = attacksRepository.findById(id).get();
        attack.setAttackDate(LocalDate.parse(attackDate, formatter));
        attack.setAttackTime(LocalTime.parse(attackTime));
        attack.setAttackLocation(attackLocation);
        attack.setUserDate(LocalDate.parse(userDate, formatter));
        attacksRepository.save(attack);
        return "attack modified";
    }

    @GetMapping("/userAttacksReport")
    public List<Integer> showUserAttacksReport(@RequestParam String userday, String uuid){
        List numsList = new ArrayList();
        LocalDate userdate;
        for(int i = 0; i < 7; i++){
            userdate = LocalDate.parse(userday, formatter).minusDays(i);
            numsList.add(0, IterableUtils.size(attacksRepository.findByUserDateAndUuid(userdate, uuid)));
        }
        return numsList;
    }

//    tutorial: https://www.baeldung.com/spring-data-jpa-pagination-sorting
    @GetMapping("/userAttacks")
    public List<Attacks> showUserAttacks(@RequestParam String uuid){
        return attacksRepository.findByUuid(uuid, Sort.by("attackDate").descending().and(Sort.by("attackTime")));
    }

    @GetMapping("/attacksReport")
    public List<Integer> showAttacksReport(@RequestParam String day, String uuid){
        List numList = new ArrayList();
        LocalDate date;
        for(int i = 0; i < 7; i++){
            date = LocalDate.parse(day, formatter).minusDays(i);
            numList.add(0, IterableUtils.size(attacksRepository.findByAttackDateAndUuid(date, uuid)));
        }
        return numList;
    }

//    @GetMapping("/PatientList")
//    public List getPatientList(){
//        List finalList = new ArrayList();
//        List tempList = new ArrayList();
//        Attacks lastAttack;
//        String tempUuid;
//        LocalDate tempDate;
//        LocalDate thisDay = LocalDate.now();
//        List allParticipants = participantsRepository.findAll();
//        Participants p;
////        finalList.add(allParticipants.size());
//        for (int j = 0; j < allParticipants.size(); j++){
//            p = (Participants) allParticipants.get(j);
//            tempList.add(p.getUuid());
//            tempList.add(p.getName());
//            tempUuid = p.getUuid();
//            lastAttack = attacksRepository.findByUuid(tempUuid, Sort.by("attackDate").descending().and(Sort.by("attackTime"))).get(0);
//            tempDate = lastAttack.getAttackDate();
//            if (tempDate.isBefore(thisDay.minusDays(2))){
//                tempList.add("alert");
//            }else{
//                tempList.add("none");
//            }
//            tempList.add(lastAttack.getAttackDate());
//            tempList.add(lastAttack.getAttackTime());
//            finalList.add(new ArrayList(tempList));
//            tempList.clear();
//        }
//        return finalList;
//    }

    @GetMapping("/PatientList")
    @ResponseBody
    public Iterable<Map<String,Object>> getPatientList(){
        List finalList = new ArrayList();
        List tempList = new ArrayList();
        Attacks lastAttack;
        String tempUuid;
        LocalDate tempDate;
        LocalDate thisDay = LocalDate.now();
        List allParticipants = participantsRepository.findAll();
        Participants p;
//        finalList.add(allParticipants.size());
        Map<String,Object> map=new HashMap<String,Object>();
        for (int j = 0; j < allParticipants.size(); j++){
            p = (Participants) allParticipants.get(j);
            map.put("id", p.getUuid());
            map.put("name", p.getName());
//            tempList.add(p.getUuid());
//            tempList.add(p.getName());
            tempUuid = p.getUuid();
            lastAttack = attacksRepository.findByUuid(tempUuid, Sort.by("attackDate").descending().and(Sort.by("attackTime"))).get(0);
            tempDate = lastAttack.getAttackDate();
            if (tempDate.isBefore(thisDay.minusDays(2))){
//                tempList.add("alert");
                map.put("alert", "alert");
            }else{
//                tempList.add("none");
                map.put("alert", "none");
            }
            map.put("AttackDate", lastAttack.getAttackDate());
            map.put("AttackTime", lastAttack.getAttackTime());
//            tempList.add(lastAttack.getAttackDate());
//            tempList.add(lastAttack.getAttackTime());
//            finalList.add(new ArrayList(tempList));
            finalList.add(new HashMap(map));
//            tempList.clear();
            map.clear();
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
