package com.arnaud.p12.Controller;

import com.arnaud.p12.model.Adherents;
import com.arnaud.p12.service.AdherentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/adherent")
public class AdherentsController {

    @Autowired
    AdherentServices adherentServices;

    @PostMapping(value = "/{username}/save", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    private Adherents save(@PathVariable(name = "username")String username, @RequestParam(name = "idAsso") int idAsso,
                           @RequestBody Adherents adherents){
     return    adherentServices.saveNewAdherent(username,idAsso,adherents);
    }

    @GetMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    private Adherents findById(@PathVariable(name = "id")Integer id){
        return adherentServices.findById(id);
    }

    @DeleteMapping(value = "/{idUser}/{idAsso}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    private void deleteAdherent(@PathVariable(name = "idUser")Integer idUser,
                           @PathVariable(name = "idAsso")Integer idAsso,
                           @RequestBody Adherents adherents){
            adherentServices.deleteAdherent(idUser,idAsso,adherents);
    }
    @PostMapping(value = "/{idUser}/{idAsso}/update/",produces = MediaType.APPLICATION_JSON_VALUE)
    private Adherents updateLicenseAdherent(@PathVariable(name = "idUser")Integer idUser,
                                            @PathVariable(name = "idAsso")Integer idAsso,
                                            @RequestBody Adherents adherents) {

            return adherentServices.updateLicenseValidity(idUser,idAsso,adherents);
    }
    @GetMapping(value = "/{idAsso}",produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Adherents> findAdhByIdAsso(@PathVariable(name = "idAsso")int idAsso){
        List<Adherents> adherentsList =  adherentServices.findAllByAssociationId(idAsso);
        System.out.println(adherentsList);
        return adherentsList;
    }
}
