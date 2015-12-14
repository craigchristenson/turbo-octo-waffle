package com.business.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"widgets", "frontCasing", "rearCasing", "hinge"})
public class Frill {
    @JsonProperty
    private Generation generation;
    @JsonProperty
    private Tier tier;
    private List<Widget> widgets;
    private Casing front;
    private Casing rear;
    private Hinge hinge;

    public Frill(){

    }

    @JsonCreator
    public Frill(@JsonProperty("widgets") List<Widget> widgets, @JsonProperty("front") Casing front, @JsonProperty("rear") Casing rear, @JsonProperty("hinge") Hinge hinge) {
        this.widgets = widgets;
        this.generation = getGeneration(widgets);
        this.front = front;
        this.rear = rear;
        this.hinge = hinge;
        this.tier = calculateTier();
    }

    public Tier calculateTier() {
        int score = 0;
        List<Component> all = new ArrayList<Component>();
        all.addAll(widgets);
        all.add(front);
        all.add(rear);
        all.add(hinge);
        for (Component c : all) {
            int dv = c.getDefectValueForTier();
            if (dv == Integer.MAX_VALUE)
                return getTier(dv);
            else
                score += dv;
        }
        return getTier(score);
    }

    public enum Generation {
        DIAMOND(6), EMERALD(5), SAPPHIRE(3), RUBY(2), OPAL(1);
        private int max;
        private Generation(int max) {
            this.max = max;
        }
    }

    public enum Tier {
        FIVE(0), FOUR(2), THREE(5), TWO(7), ONE(16), ZERO(23), INCOMPLETE(Integer.MAX_VALUE);
        // Minimum value that qualifies a Frill for this tier
        private int min;
        private Tier(int min) {
            this.min = min;
        }
        public int getMin() {
            return min;
        }
    }

    private Generation getGeneration(List<Widget> widgets) {
        Generation generation = null;
        int cnt = widgets.size();
        cnt = (cnt == 4 ? cnt+1 : cnt);
        switch (cnt) {
            case 6: generation = Generation.DIAMOND;
                break;
            case 5: generation = Generation.EMERALD;
                break;
            case 3: generation = Generation.SAPPHIRE;
                break;
            case 2: generation = Generation.RUBY;
                break;
            case 1: generation = Generation.OPAL;
                break;
            default: throw new RuntimeException("Widget count invalid, unable to identify Generation.");
        }
        return generation;
    }

    private Tier getTier(int min) {
        Tier tier = null;
        if (min == Tier.FIVE.getMin())
            tier = Tier.FIVE;
        else if (min <= Tier.FOUR.getMin())
            tier = Tier.FOUR;
        else if (min <= Tier.THREE.getMin())
            tier = Tier.THREE;
        else if (min <= Tier.TWO.getMin())
            tier = Tier.TWO;
        else if (min <= Tier.ONE.getMin())
            tier = Tier.ONE;
        else if (min <= Tier.ZERO.getMin())
            tier = Tier.ZERO;
        else if (min == Tier.INCOMPLETE.getMin())
            tier = Tier.INCOMPLETE;
        return tier;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public Hinge getHinge() {
        return hinge;
    }

    public Casing getFrontCasing() {
        return front;
    }

    public Casing getRearCasing() {
        return rear;
    }
}
