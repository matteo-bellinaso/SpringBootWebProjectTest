package org.its.bl;

import org.its.bus.Bus;
import org.its.dl.CountDL;
import org.its.dl.UserDL;
import org.its.dl.UtenteDL;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("userBl")
public class UserBLImpl implements UserBL {
    private final UserDL dataLayer;
    private BLValidator validator;
    private BLValidator validateProfanities;
    // private List<UtenteBO> listaUtenteBO = new ArrayList<UtenteBO>();
    private BOConverterService boConverterService = new BOConverterService();
    private Bus bus;


    public UserBLImpl(
            @Named("userDl") UserDL dataLayer,
            @Named("blValidateName") BLValidator validator,
            @Named("bus") Bus bus) {
        this.dataLayer = dataLayer;
        this.validator = validator;
        this.validateProfanities = validateProfanities;
        this.bus = bus;
    }

    @Override
    public List<UtenteBO> getAll() {
        List<UtenteBO> listaUtenteBO = new ArrayList<>();
        for (UtenteDL utenteDL : dataLayer.getAll()) {
            UtenteBO utenteBo = boConverterService.convertToUtenteBO(utenteDL);
            listaUtenteBO.add(utenteBo);
        }
        return listaUtenteBO;
    }

    @Override
    public UtenteBO getById(String id) {

        for (UtenteDL utenteDL : dataLayer.getAll()) {
            if (utenteDL.getId() == Integer.parseInt(id)) {
                UtenteBO result = boConverterService.convertToUtenteBO(utenteDL);
                return result;
            }
        }
        return null;
    }

    @Override
    public UtenteBO postUtente(UtenteBO utenteBO) {
        validateBl(utenteBO);
        utenteBO.setEnabled(false);
        UtenteDL utenteDL = boConverterService.convertToUtenteDL(utenteBO);
        UtenteBO responseUtenteBo = boConverterService.convertToUtenteBO(dataLayer.add(utenteDL));
        return responseUtenteBo;
    }


    @Override
    public boolean delete(String id) {
        String error;
        try {
            int parsedId = Integer.parseInt(id);
            boolean deleted = dataLayer.delete(parsedId);
            if (deleted) {
                bus.send(new UtenteMessage(false));
            }
            return deleted;
        } catch (NumberFormatException f) {
            error = "Non è un numero";
            return false;
        }
    }

    @Override
    public UtenteBO modificaUtente(UtenteBO utenteBO, String id) {
        validateBl(utenteBO);
        delete(id);
        return postUtente(utenteBO);
    }

    @Override
    public void abilitaUtente(Integer id) throws Exception {
        UtenteBO utente = getById(Integer.toString(id));
        if (utente == null) {
            throw new Exception("utente non trovato");
        } else {
            utente.setEnabled(true);
            boolean sendMex = dataLayer.update(boConverterService.convertToUtenteDL(utente));
            if (sendMex) {
                bus.send(new UtenteMessage(true));
            }
        }
    }

    @Override
    public void disabilitaUtente(Integer id) throws Exception {
        UtenteBO utente = getById(Integer.toString(id));
        if (utente == null) {
            throw new Exception("utente non trovato");
        } else {
            utente.setEnabled(false);
            boolean sendMex = dataLayer.update(boConverterService.convertToUtenteDL(utente));
            if (!sendMex) {
                bus.send(new UtenteMessage(false));
            }
        }
    }


    private boolean validateBl(UtenteBO utenteBO) {
        return validator.validate(utenteBO);
    }
}

