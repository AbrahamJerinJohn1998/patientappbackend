package com.nest.patientappbackend.controller;

import com.nest.patientappbackend.dao.PatientDao;
import com.nest.patientappbackend.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
    @RestController
    public class PatientController {
        @Autowired
        private PatientDao dao;

        @CrossOrigin(origins = "*")
        @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
        public Map<String,String> AddPatient(@RequestBody Patient p) {
            System.out.println(p.getName().toString());
            System.out.println(p.getPid());
            System.out.println(p.getAddress().toString());
            System.out.println(p.getContact());
            System.out.println(p.getDoa().toString());
            System.out.println(p.getImage().toString());
            System.out.println(p.getDname().toString());
            dao.save(p);
            HashMap<String, String> map=new HashMap<>();
            map.put("status","success");
            return map;


        }
        @CrossOrigin(origins = "*")
        @GetMapping("/view")
        public List<Patient> ViewPatient() {
            return (List<Patient>) dao.findAll();
        }
        @CrossOrigin(origins = "*")
        @PostMapping(path = "/search",consumes = "application/json",produces = "application/json")
        public List<Patient> SearchPatient(@RequestBody Patient p)
        {
            String pid=String.valueOf(p.getPid());
            System.out.println(pid);
            return (List<Patient>) dao.SearchPatient(p.getPid());

        }
        @CrossOrigin(origins = "*")
        @PostMapping(path = "/delete",consumes ="application/json",produces = "application/json")
        public Map<String,String> DeletePatient(@RequestBody Patient p)
        {
            String id=String.valueOf(p.getId());
            System.out.println(id);
            dao.DeletePatient(p.getId());
            HashMap<String,String> map=new HashMap<>();
            map.put("status","success");
            return map;
        }
}
