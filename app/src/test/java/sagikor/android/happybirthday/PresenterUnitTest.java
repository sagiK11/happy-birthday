package sagikor.android.happybirthday;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sagikor.android.happybirthday.model.Baby;
import sagikor.android.happybirthday.presenter.BirthdayActivityPresenter;
import sagikor.android.happybirthday.presenter.MainActivityPresenter;

import static org.junit.Assert.assertEquals;

public class PresenterUnitTest {

    @Test
    public void setName_isCorrect() {
        //Before running:
        //comment statements related to the view in the 'else' clause in setName method.
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(null);
        HashMap<String, String> validInputMap = getValidInput();
        List<String> invalidInputList = getInvalidInput();
        Baby model = Baby.getInstance();
        final String invalid = "";
        //invalid input test
        for (String invalidInput : invalidInputList) {
            mainActivityPresenter.setName(invalidInput);
            assertEquals(invalid, model.getName());
        }
        //valid input test
        for (Map.Entry<String, String> entry : validInputMap.entrySet()) {
            mainActivityPresenter.setName(entry.getValue());
            assertEquals(entry.getKey(), model.getName());
        }
    }

    private List<String> getInvalidInput() {
        List<String> invalidInputList = new ArrayList<>();
        invalidInputList.add("Chris@tina Ronalds");
        invalidInputList.add("Christina Ronalds@");
        invalidInputList.add("@ChristinaRonalds");
        invalidInputList.add("Christina@Ronalds");
        invalidInputList.add("1ChristinaRonalds");
        invalidInputList.add("ChristinaRonalds1");
        invalidInputList.add("Christina 1 Ronalds");
        return invalidInputList;
    }

    private HashMap<String, String> getValidInput() {
        //   expected | actual
        HashMap<String, String> validInputMap = new HashMap<>();
        validInputMap.put("Christina Ronalds", "Christina Ronalds");
        validInputMap.put("Ron R Ronalds", "Ron R Ronalds");
        validInputMap.put("Ron", "Ron");
        validInputMap.put("Christina Ronalds", "  Christina Ronalds   ".trim());
        return validInputMap;
    }

    @Test
    public void ageDifference_isCorrect() {
        HashMap<String, Integer[]> hashMap = getSampleDates();
        BirthdayActivityPresenter.DateDifference df;
        final int DAYS_INDEX = 0;
        final int MONTHS_INDEX = 1;
        final int YEARS_INDEX = 2;

        for (Map.Entry<String, Integer[]> entry : hashMap.entrySet()) {
            String birthday = entry.getKey();
            df = new BirthdayActivityPresenter.DateDifference(birthday);
            assertEquals((long) entry.getValue()[DAYS_INDEX], df.getDays());
            assertEquals((long) entry.getValue()[MONTHS_INDEX], df.getMonths() % 12);
            assertEquals((long) entry.getValue()[YEARS_INDEX], df.getYears());
        }
    }

    private HashMap<String,Integer[]> getSampleDates(){
        HashMap<String,Integer[]>  hashMap = new HashMap<>();
        //actual days, months, and years since the key value of the map to 26/3/2021
        hashMap.put("1/1/2000", new Integer[]{7756, 2, 21});
        hashMap.put("1/1/2005", new Integer[]{5929, 2, 16});
        hashMap.put("1/1/2008", new Integer[]{4834, 2, 13});
        hashMap.put("1/2/2008", new Integer[]{4803, 1, 13});
        hashMap.put("3/6/2010", new Integer[]{3950, 9, 10});
        hashMap.put("4/3/2015", new Integer[]{2215, 0, 6});
        hashMap.put("29/2/2016", new Integer[]{1853, 0, 5});
        hashMap.put("14/11/2016", new Integer[]{1594, 4, 4});
        hashMap.put("28/7/2017", new Integer[]{1338, 7, 3});
        hashMap.put("15/8/2018", new Integer[]{955, 7, 2});
        hashMap.put("2/5/2019", new Integer[]{695, 10, 1});
        hashMap.put("1/4/2020", new Integer[]{360, 11, 0});
        hashMap.put("29/2/2020", new Integer[]{392, 0, 1});
        hashMap.put("26/3/2020", new Integer[]{366, 0, 1});
        hashMap.put("1/1/2021", new Integer[]{85, 2, 0});
        hashMap.put("28/2/2021", new Integer[]{27, 0, 0});
        hashMap.put("26/3/2021", new Integer[]{1, 0, 0});
        return hashMap;
    }
}