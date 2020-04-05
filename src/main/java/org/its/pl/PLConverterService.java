package org.its.pl;

import org.its.bl.UtenteBO;

public class PLConverterService {

    public Utente convertToUtente(UtenteBO utenteBO) throws Exception{
        Utente utente = new Utente(utenteBO.getId(), utenteBO.getName());
        return  utente;
    }

    public UtenteBO convertToUtenteBO(Utente utente){
        UtenteBO utenteBO = new UtenteBO(utente.getId(), utente.getName(), false);
        return  utenteBO;
    }
}
