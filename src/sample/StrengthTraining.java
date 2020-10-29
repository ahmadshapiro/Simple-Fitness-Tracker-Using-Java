package sample;

public class StrengthTraining extends Activity  {
    private String name="Strength Training";


    public StrengthTraining() {
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
        double newRate=oldRate+((oldRate)*(minutes)*(0.6/100));
        heartRateIncrease+=(newRate-oldRate);

        return newRate;
    }
}
