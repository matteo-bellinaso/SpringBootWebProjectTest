package org.its.pl;

import org.its.bl.CountBL;
import org.its.bl.UserBL;
import org.its.bl.UtenteBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/utente")
public class UserController {

    private final UserBL businessLayer;

    private PLValidator validator;
    private PLConverterService plConverterService = new PLConverterService();

    @Inject
    public UserController(
            @Named("userBl") UserBL businessLayer,
            @Named("plValidator") PLValidator validator) {
        this.businessLayer = businessLayer;
        this.validator = validator;
    }


    //getAll
    @RequestMapping(
            path = "/getAll",
            method = RequestMethod.GET,
            produces = "application/json")

    @ResponseBody
    public List<Utente> getAll() {
        List<Utente> result = new ArrayList<Utente>();
        for (UtenteBO utenteBO : businessLayer.getAll()) {
            Utente utente = null;
            try {
                utente = plConverterService.convertToUtente(utenteBO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.add(utente);
        }
        return result;
    }

    //getById
    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json")

    @ResponseBody
    public Utente getById(@PathVariable("id") String id) throws Exception {

        String currentId = id;
        UtenteBO utenteBO = businessLayer.getById(id);
        Utente utente = plConverterService.convertToUtente(utenteBO);

        if (utente.getName() == null) throw new Exception("Utente non presente");
        return utente;
    }

    //post
    @RequestMapping(
            path = "/postUtente",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public Utente postUtente(@RequestBody Utente request) throws Exception {
        validator.validate(request);
        UtenteBO utenteBO = plConverterService.convertToUtenteBO(request);
        if (!validator.validate(request))
            throw new Exception("Nome non conforme, deve sempre essere di almeno 3 caratteri e non deve contenere la parola test");
        else {
            UtenteBO responsePostUtenteBO = businessLayer.postUtente(utenteBO);
            Utente response = plConverterService.convertToUtente(responsePostUtenteBO);
            return response;
        }
    }

    //deleteById
    @RequestMapping(
            path = "/delete/{id}",
            method = RequestMethod.GET,
            produces = "application/json")

    @ResponseBody
    public String deleteById(@PathVariable("id") String id) throws Exception {
        if (businessLayer.delete(id) == true) return "Utente eliminato";
        else throw new Exception("Impossibile eliminare l'utente");
    }


    //put
    @RequestMapping(
            path = "/modifica/{id}",
            method = RequestMethod.PUT,
            produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public Utente modificaUtente(@RequestBody Utente request, @PathVariable("id") String id) throws Exception {
        UtenteBO utenteBO = plConverterService.convertToUtenteBO(request);

        if (!validator.validate(request))
            throw new Exception("Nome non conforme, deve sempre essere di almeno 3 caratteri e non deve contenere la parola test");
        else {
            UtenteBO responsePostUtenteBO = businessLayer.modificaUtente(utenteBO, id);
            Utente response = plConverterService.convertToUtente(responsePostUtenteBO);
            return response;
        }
    }


    @RequestMapping(
            path = "/abilita/{id}",
            method = RequestMethod.PUT,
            produces = "application/json")
    @ResponseBody
    public void abilitaUtente(@PathVariable("id") String id) throws Exception {
        businessLayer.abilitaUtente(Integer.parseInt(id));
    }


    @RequestMapping(
            path = "/disabilita/{id}",
            method = RequestMethod.PUT,
            produces = "application/json")
    @ResponseBody
    public void disabilitaUtente(@PathVariable("id") String id) throws Exception {
        businessLayer.disabilitaUtente(Integer.parseInt(id));
    }
}
