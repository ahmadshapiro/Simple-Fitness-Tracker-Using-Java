package sample;

import java.text.DecimalFormat;
import java.util.Comparator;

public abstract class Activity implements Comparable<Activity> {
    String name;
    int calroiesBurnedBy;
    double heartRateIncrease;
    DecimalFormat deci = new DecimalFormat("#.###");

    void clearAllData() {
        this.calroiesBurnedBy = 0;
        this.heartRateIncrease = 0;
    }

    abstract int burnCalories(int calories, int minutes);

    abstract double changeHeartRate(double oldRate, int minutes);

    public void setCalroiesBurnedBy(int calroiesBurnedBy) {
        this.calroiesBurnedBy = calroiesBurnedBy;
    }

    public void setHeartRateIncrease(double heartRateIncrease) {
        this.heartRateIncrease = heartRateIncrease;
    }

    public String getName() {
        return name;
    }

    public int getCalroiesBurnedBy() {
        return calroiesBurnedBy;
    }

    public double getHeartRateIncrease() {
        return Double.parseDouble(deci.format(this.heartRateIncrease));
    }

    @Override
    public int compareTo(Activity x) {
        if (this.calroiesBurnedBy > x.getCalroiesBurnedBy())
            return 1;
        if (this.calroiesBurnedBy < x.getCalroiesBurnedBy())
            return -1;
        else
            return 0;

    }

}
