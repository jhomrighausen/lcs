package com.comcast.lcs.domain;

import java.util.List;

public class LCRequest {
    private List<LCValue> setOfStrings;

    public List<LCValue> getSetOfStrings() {
        return setOfStrings;
    }

    public void setSetOfStrings(List<LCValue> setOfStrings) {
        this.setOfStrings = setOfStrings;
    }
}
