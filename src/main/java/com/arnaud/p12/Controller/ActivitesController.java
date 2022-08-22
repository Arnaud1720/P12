package com.arnaud.p12.Controller;

import com.arnaud.p12.model.Activites;
import com.arnaud.p12.service.ActivitesService;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/act")
public class ActivitesController {

    @Autowired
    ActivitesService activitesService;

    @GetMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Activites findById(@PathVariable(name = "id")int id){
        return activitesService.findById(id);
    }

}
