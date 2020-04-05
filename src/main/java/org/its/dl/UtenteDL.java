package org.its.dl;

public class UtenteDL {
    private int id;
    private  String name;
    private boolean enabled;

    public UtenteDL(int id, String name, boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }

    public  UtenteDL(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getEnabled() {return this.enabled; }

    public void setEnabled(boolean enabled) {this.enabled = enabled; }
}
