package sample;

public class Swimming extends Activity  {

    private String name="Swimming";


    public String getName() {
        return name;
    }

    int burnCalories(int calories, int minutes) {
        int newCalories= calories+(4*minutes);
        calroiesBurnedBy+=(newCalories-calories);
        return newCalories;
    }


    double changeHeartRate(double oldRate, int minutes) {
        double newRate=oldRate+((oldRate)*(minutes)*(0.002));
        heartRateIncrease+=(newRate-oldRate);

        return newRate;
    }
}
