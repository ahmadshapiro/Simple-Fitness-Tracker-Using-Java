package sample;

public class Running extends Activity  {

    private String name="Running";


    public Running() {
    }

    public String getName() {
        return name;
    }


    int burnCalories(int calories, int minutes) {
        int newCalories= calories+(5*minutes);
        calroiesBurnedBy+=(newCalories-calories);
        return newCalories;
    }


    double changeHeartRate(double oldRate, int minutes) {
        double newRate=oldRate+((oldRate)*(minutes)*(0.3/100));
        heartRateIncrease+=(newRate-oldRate);

        return newRate;
    }
}
