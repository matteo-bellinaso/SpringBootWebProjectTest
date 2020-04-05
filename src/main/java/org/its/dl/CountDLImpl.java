package org.its.dl;

import javax.inject.Named;

@Named("countDL")
public class CountDLImpl implements CountDL {

    private int totEnabled;

    @Override
    public void incrementEnabled() {
        totEnabled++;
    }

    @Override
    public void decresmentEnabled() {
        totEnabled--;
    }

    @Override
    public int getEnable() {
        return totEnabled;
    }
}
