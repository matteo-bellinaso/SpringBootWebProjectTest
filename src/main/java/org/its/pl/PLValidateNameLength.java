package org.its.pl;

import javax.inject.Named;

@Named("plValidator")
public class PLValidateNameLength implements PLValidator {
    @Override
    public boolean validate(Utente utenteBO) {
        return utenteBO.getName().length() >= 3;
    }
}
