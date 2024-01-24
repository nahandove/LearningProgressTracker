package tracker.course;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Course {
    private final String name;
    private int assignments;
    private int credits;
    private final int completion;
    private boolean isNotified;
    public Course(String name, int assignments, int credits, int completion) {
        this.name = name;
        this.assignments = assignments;
        this.credits = credits;
        this.completion = completion;
        isNotified = false;
    }

    public String getName() {
        return name;
    }

    public int getAssignments() {
        return assignments;
    }

    public int getCredits() {
        return credits;
    }

    public int getCompletion() {
        return completion;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean isNotified) {
        this.isNotified = isNotified;
    }

    public void addCredits(int newPoints) {
        if (newPoints > 0) {
            assignments++;
        }
        credits += newPoints;
    }

    public double getPercentCompletion() {
        BigDecimal bigDecimal = BigDecimal.valueOf(1.0 * credits / completion * 100);
        bigDecimal = bigDecimal.setScale(1, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public boolean isCompleted() {
        return credits >= completion;
    }
}
