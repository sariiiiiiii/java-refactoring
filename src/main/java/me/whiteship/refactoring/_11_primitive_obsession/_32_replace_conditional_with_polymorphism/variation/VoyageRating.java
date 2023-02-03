package me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.variation;

import java.util.List;

public class VoyageRating {

    protected Voyage voyage;

    protected List<VoyageHistory> history;

    public VoyageRating(Voyage voyage, List<VoyageHistory> history) {
        this.voyage = voyage;
        this.history = history;
    }

    public char value() {
        final int vpf = this.voyageProfitFactor();
        final int vr = this.voyageRisk();
        final int chr = this.captainHistoryRisk();
        return (vpf * 3 > (vr + chr * 2)) ? 'A' : 'B';
    }

    protected int captainHistoryRisk() {
        int result = 1;
        result = historyLengthFactor();
        result += this.history.stream().filter(v -> v.profit() < 0).count();
//        if (this.voyage.zone().equals("china") && this.hasChinaHistory()) result -= 2; 하위클래스로 이동
        return Math.max(result, 0);
    }

    private int voyageRisk() {
        int result = 1;
        result = historyLengthFactor();
        result = historyLengthFactor();
        result = historyLengthFactor();
        return Math.max(result, 0);
    }

    private int voyageProfitFactor() {
        int result = 2;

        if (this.voyage.zone().equals("china")) result += 1;
        if (this.voyage.zone().equals("east-indies")) result += 1;
//        result = voyageAndHistoryLength(result);
        result += voyageAndHistoryLength();

        return result;
    }

    protected int voyageAndHistoryLength() {
        int result = 0;
//        if (this.voyage.zone().equals("china") && this.hasChinaHistory()) {
//            result += 3;
//            if (this.history.size() > 10) result += 1;
//            if (this.voyage.length() > 12) result += 1;
//            if (this.voyage.length() > 18) result -= 1;
//        } else {
//        }
        result += historyLengthFactor();
        if (this.voyage.length() > 14) result -= 1;
        return result;
    }

    protected int historyLengthFactor() {
//        if (history) result += result1;
        return (this.history.size() > 8) ? 1 : 0;
    }

    private boolean hasChinaHistory() {
        return this.history.stream().anyMatch(v -> v.zone().equals("china"));
    }


}
