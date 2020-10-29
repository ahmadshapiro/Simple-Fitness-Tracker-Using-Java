package sample;

public class KickBoxing extends Activity  {
    private String name="KickBoxing";


    public KickBoxing() {
    }

    public String getName() {
        return name;
    }

    int burnCalories(int calories, int minutes) {
        int newCalories= calories+(3*minutes);
        calroiesBurnedBy+=(newCalories-calories);
        return newCalories;
    }


    double changeHeartRate(double oldRate, int minutes) {
        double newRate=oldRate+((oldRate)*(minutes)*(0.5/100));
        heartRateIncrease+=(newRate-oldRate);

        return newRate;
    }
}
