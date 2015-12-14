package com.business.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Defect {

    // I assign a logical score to each defect type:
    // none = 0, minor = 1, major = 2, massive = 3, Incomplete = Integer.MAX
    public enum Type {
        NONE(0), MINOR(1), MAJOR(2), MASSIVE(3), INCOMPLETE(Integer.MAX_VALUE);

        private int value;
        private Type(int value) {
            this.value = value;
        }
        public int getTypeValue() {
            return value;
        }
    }
    private Type type;
    public Defect() {}
    public Defect(Type type) {
        this.type = type;
    }
    public Type getType() {
        return type;
    }
    public int getDefectScore() {
        return getType().getTypeValue();
    }
    public boolean equals(Object o) {
        if (false == o instanceof Defect)
            return false;

        Defect other = (Defect) o;
        return other.getDefectScore() == getDefectScore();
    }
    public int hashCode() {
        return new HashCodeBuilder(3, 53).append(getDefectScore()).toHashCode();
    }


}