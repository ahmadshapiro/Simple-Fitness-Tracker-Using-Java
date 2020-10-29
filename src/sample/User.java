package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;

public class User {
    private String name;
    private double hearBeat;
    private int calories;

    public ArrayList<Activity> getActivitiesplayed() {
        return activitiesplayed;
    }

    private ArrayList<Activity> activitiesplayed;

    public double getHearBeat() {
        return hearBeat;
    }

    public int getCalories() {
        return calories;
    }

    public User(String name) {
        this.name = name;
        this.hearBeat = 80;
        this.calories = 0;
        this.activitiesplayed=new ArrayList<>();
    }

    public void play(Activity activity,int time)
    {
        this.calories=activity.burnCalories(this.calories,time);
        this.hearBeat=activity.changeHeartRate(this.hearBeat,time);
        if(activitiesplayed.contains(activity))
        {
            int index =activitiesplayed.indexOf(activity);
            activitiesplayed.get(index).setCalroiesBurnedBy(activity.getCalroiesBurnedBy());
            activitiesplayed.get(index).setHeartRateIncrease(activitiesplayed.get(index).getHeartRateIncrease());
        }
        else
            activitiesplayed.add(activity);
    }
    public ObservableList<Activity> printRanks()
    {
        Collections.sort(activitiesplayed,Collections.reverseOrder());
//        for(Activity n:activitiesplayed) {
//            System.out.println(n.getName());
//            System.out.println("Calories Burned : "+n.getCalroiesBurnedBy());
//            System.out.println("Hear Rate Increase: "+n.getHeartRateIncrease());
//        }
        ObservableList<Activity> list= FXCollections.observableArrayList(activitiesplayed);
        return list;
    }
    public void clearAll()
    {
        this.calories=0;
        this.hearBeat=80;
    }


}
