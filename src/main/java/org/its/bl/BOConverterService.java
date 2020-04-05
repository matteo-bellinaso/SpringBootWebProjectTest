package org.its.bl;

import org.its.dl.UtenteDL;
import org.its.pl.Utente;

public class BOConverterService {

    public UtenteDL convertToUtenteDL(UtenteBO utenteBO){
        UtenteDL utenteDL = new UtenteDL(utenteBO.getId(), utenteBO.getName(), utenteBO.getEnabled());
        return  utenteDL;
    }

    public UtenteBO convertToUtenteBO(UtenteDL utenteDL){
        UtenteBO utenteBO = new UtenteBO(utenteDL.getId(), utenteDL.getName(), utenteDL.getEnabled());
        return  utenteBO;
    }
}
