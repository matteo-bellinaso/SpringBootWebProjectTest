package org.its.dl;

import javax.inject.Named;
import java.util.*;

@Named("userDl")
public class UserDLImpl implements UserDL {

    HashMap<Integer, UtenteDL> listaUtenteDL = new HashMap<Integer, UtenteDL>();
    int maxId = 1;

    @Override
    public List<UtenteDL> getAll() {
        return new ArrayList<UtenteDL>(listaUtenteDL.values()); //prelievo tutte le value dalla hashmap e la trasformo in List
    }

    public UtenteDL add(UtenteDL utenteDL) {
        utenteDL.setId(maxId);
        maxId++;
        listaUtenteDL.put(utenteDL.getId(), utenteDL);
        return utenteDL;
    }

    @Override
    public boolean delete(Integer id) {
            if (listaUtenteDL.containsKey(id)) {
                listaUtenteDL.remove(id);
                return true;
            }
        return false;
    }

    @Override
    public boolean update(UtenteDL utente) {
        listaUtenteDL.put(utente.getId(), utente);
        if (utente.getEnabled()) {
            return true;
        } else {
            return false;
        }
    }


}

