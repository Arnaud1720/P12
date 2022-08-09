package com.arnaud.p12.Controller;
import com.arnaud.p12.model.Association;
import com.arnaud.p12.service.AssociationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/association")
public class AssociationController {

    final AssociationService associationService;

    public AssociationController(AssociationService associationService) {
        this.associationService = associationService;
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Association> findAll()
    {
        return associationService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") long id) {
        associationService.deleteById(id);
    }

    @PostMapping(value = "/save/{username}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Association save(@RequestBody Association association,@PathVariable(name = "username")String username){
        return associationService.save(association, username);
    }


}
