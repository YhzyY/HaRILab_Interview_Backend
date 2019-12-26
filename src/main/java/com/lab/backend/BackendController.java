package com.lab.backend;

import com.lab.backend.dao.ParticipantsRepository;
import com.lab.backend.model.Participants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
//@ImportResource("classpath:applicationContext.xml")
public class BackendController {

    @Autowired
    ParticipantsRepository participantsRepository;
//    @Autowired
//    CliniciansRepository cliniciansRepository;
//    @Autowired
//    AttacksRepository attacksRepository;


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
    public String create(@RequestParam String firstName, String lastName, Integer uuid){
        participantsRepository.save(new Participants(firstName, lastName, uuid));
        return "newUser is added";
    }

//    @PostMapping("/newUser")
//    public String create(@RequestBody Participants participants){
//        participantsRepository.save(new Participants(participants.getFirstName(), participants.getLastName(), participants.getUuid()));
//        return "newUser is added";
//    }

}