package org.its.bl;

import org.its.bus.Bus;
import org.its.bus.BusMessage;
import org.its.dl.CountDL;

import javax.inject.Named;

@Named("countBL")
public class CountBLImpl implements CountBL {

    private final CountDL countDL;

    public CountBLImpl(@Named("countDL") CountDL countDL,
                       @Named("bus") Bus bus) {
        this.countDL = countDL;
        bus.register(UtenteMessage.class, this);
    }

    @Override
    public int countEnabled() {
        return countDL.getEnable();
    }

    @Override
    public void handle(BusMessage messageType) throws Exception {
        UtenteMessage umsg = (UtenteMessage)messageType;
        if (umsg.isEnabled()) {
            countDL.incrementEnabled();
        } else {
            countDL.decresmentEnabled();
        }
    }
}
