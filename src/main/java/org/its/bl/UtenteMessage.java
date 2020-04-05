package org.its.bl;

import org.its.bus.BusMessage;

public class UtenteMessage implements BusMessage {
    private boolean enabled;

    public UtenteMessage(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
