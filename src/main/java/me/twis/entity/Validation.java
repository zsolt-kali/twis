package me.twis.entity;

/**
 * Created by kalizsolt on 14/01/16.
 */
public class Validation {
    private boolean valid;

    public Validation() {
    }

    public Validation(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
