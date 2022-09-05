package com.arnaud.p12.Controller;

import com.arnaud.p12.model.Activites;
import com.arnaud.p12.service.ActivitesService;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/act")
public class ActivitesController {

    @Autowired
    ActivitesService activitesService;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private Activites findById(@PathVariable(name = "id")int id){
        return activitesService.findById(id);
    }

    @GetMapping(value = "/all",produces  = MediaType.APPLICATION_JSON_VALUE)
    private List<Activites> findAll(){
        return activitesService.findall();
    }

    @PostMapping(value = "/save/{username}/{idAsso}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    private Activites save(@RequestBody Activites activites,@PathVariable(name = "username")String username,@PathVariable(name = "idAsso")int idAsso){
        return activitesService.save(activites,username,idAsso );
    }

    @GetMapping(value = "/findall/{idAsso}",produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Activites> findAllByIdAsso(Activites activites,@PathVariable(name = "idAsso")int id)
    {
        return activitesService.findAllByAssoId(activites,id);
    }
    @PostMapping(value = "/save/adh/activite",produces = MediaType.APPLICATION_JSON_VALUE)
    private Activites saveActAdh(@RequestParam(name = "idAdh")int idAdh,
                                 @RequestParam(name = "idAct")int idAct){
        return activitesService.saveToAdh(idAdh, idAct);
    }

    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private void deleteById(@PathVariable(name = "id")int id){
        activitesService.actDeleteById(id);
    }
}
