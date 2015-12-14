package com.business.model;

import static com.business.model.Defect.Type;
import java.util.HashSet;
import java.util.Set;

public class Component {
    public Set<Defect> defects = new HashSet<Defect>() {{ add(new Defect(Type.NONE)); }};

    public Set<Defect> getDefects() {
        return defects;
    }
    public void addDefect(Defect defect) {
        defects.add(defect);
    }
    public int getDefectValueForTier() {
        return defects
                .stream()
                .mapToInt(Defect::getDefectScore)
                .max().getAsInt();
    }
}
