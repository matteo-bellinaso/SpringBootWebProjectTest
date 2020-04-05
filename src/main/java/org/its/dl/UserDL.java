package org.its.dl;

import java.util.List;

public interface UserDL {
    List<UtenteDL> getAll();

    UtenteDL add(UtenteDL utenteDL);

    boolean delete(Integer id);

    boolean update(UtenteDL utente);
}
