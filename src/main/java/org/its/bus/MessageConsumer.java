package org.its.bus;

@FunctionalInterface  // finge una landa
public interface MessageConsumer {
    void handle(BusMessage messageType) throws Exception;
}
