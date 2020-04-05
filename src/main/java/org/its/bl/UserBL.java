package org.its.bl;

import org.its.pl.Count;
import org.its.pl.Utente;

import java.util.List;

public interface UserBL {
    List<UtenteBO> getAll();

    UtenteBO getById(String id);

    UtenteBO postUtente(UtenteBO utenteBO);

    boolean delete(String id);

    UtenteBO modificaUtente(UtenteBO utenteBO, String id);

    void abilitaUtente(Integer id) throws Exception;

    void disabilitaUtente(Integer id) throws Exception;

}
