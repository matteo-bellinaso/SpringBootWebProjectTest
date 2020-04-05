package org.its.bl;

import javax.inject.Named;

@Named("finalBlValidator")
public class BLFinalValidator implements BLValidator{
    @Override
    public boolean validate(UtenteBO utente) {
        return true;
    }
}
