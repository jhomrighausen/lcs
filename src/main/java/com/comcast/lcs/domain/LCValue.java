package com.comcast.lcs.domain;

import java.util.Objects;

public class LCValue {
    private String value;

    /**
     * default constructor
     */
    public LCValue()
    {
    }

    /**
     * convenience constructor
     * @param value
     */
    public LCValue(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LCValue)) return false;
        LCValue lcValue = (LCValue) o;
        return Objects.equals(getValue(), lcValue.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
