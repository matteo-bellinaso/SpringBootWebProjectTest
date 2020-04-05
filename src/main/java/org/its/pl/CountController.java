package org.its.pl;

import org.its.bl.CountBL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;

@Controller
@RequestMapping("/api/utente")
public class CountController {

    private final CountBL businessLayer;

    @Inject
    public CountController(
            @Named("countBL") CountBL businessLayer
            ) {
        this.businessLayer = businessLayer;
    }


    //getAll
    @RequestMapping(
            path = "/countEnabled",
            method = RequestMethod.GET,
            produces = "application/json")

    @ResponseBody
    public Count getAll() {
        return new Count(businessLayer.countEnabled());
    }


}
