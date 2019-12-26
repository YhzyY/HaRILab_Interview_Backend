package com.lab.backend;

import com.lab.backend.dao.ParticipantsRepository;
import com.lab.backend.model.Participants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ImportResource("classpath:applicationContext.xml")
public class BackendController {

    @Autowired
    ParticipantsRepository participantsRepository;

//    private ParticipantsRepository participantsRepository;
//    @Autowired
//    public void setDependency(ParticipantsRepository participantsRepository){
//        this.participantsRepository = participantsRepository;
//    }
//    @Autowired
//    CliniciansRepository cliniciansRepository;
//    @Autowired
//    AttacksRepository attacksRepository;


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

//    @PostMapping("/newUser")
//    public Employee createEmployee(@Valid @RequestBody Employee employee) {
//        return employeeRepository.save(employee);
//    }

}