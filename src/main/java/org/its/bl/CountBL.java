package org.its.bl;

import org.its.bus.MessageConsumer;


public interface CountBL extends MessageConsumer {

    int countEnabled();
}
