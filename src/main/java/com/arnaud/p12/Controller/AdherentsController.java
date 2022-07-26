package com.arnaud.p12.Controller;

import com.arnaud.p12.model.Adherents;
import com.arnaud.p12.service.AdherentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/adherent")
public class AdherentsController {

    @Autowired
    AdherentServices adherentServices;

    @PostMapping(value = "/{idUser}/{idAsso}/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private Adherents save(@PathVariable(name = "idUser")long idUser,
                           @PathVariable(name = "idAsso")long idAsso,
                           @RequestBody Adherents adherents){
     return    adherentServices.saveNewAdherent(idUser,idAsso,adherents);
    }

    @GetMapping(value = "/{id}")
    private Adherents findById(@PathVariable(name = "id")long id){
        return adherentServices.findById(id);
    }

    @PostMapping(value = "/{idUser}/{idAsso}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    private void deleteAdherent(@PathVariable(name = "idUser")long idUser,
                           @PathVariable(name = "idAsso")long idAsso,
                           @RequestBody Adherents adherents){
            adherentServices.deleteAdherent(idUser,idAsso,adherents);
    }
    @PostMapping(value = "/{idUser}/{idAsso}/update/",produces = MediaType.APPLICATION_JSON_VALUE)
    private Adherents updateLicenseAdherent(@PathVariable(name = "idUser")long idUser,
                                            @PathVariable(name = "idAsso")long idAsso,
                                            @RequestBody Adherents adherents) {

            return adherentServices.updateLicenseValidity(idUser,idAsso,adherents);
    }
}
