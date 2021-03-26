package sagikor.android.happybirthday.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import sagikor.android.happybirthday.R;
import sagikor.android.happybirthday.model.Baby;

public class BirthdayActivityPresenter implements BirthdayActivityContract.Presenter {

    private final Baby baby;
    private final BirthdayActivityContract.View view;
    private final static String TAG = "BirthActivityPresenter";

    public BirthdayActivityPresenter(BirthdayActivityContract.View view) {
        this.view = view;
        baby = Baby.getInstance();
        selectTheme();
        updateViews();
    }

    @Override
    public void onCloseClick() {
        view.navigateToPreviousScreen();
    }

    @Override
    public void onChangePictureClick() {
        view.replaceImage();
    }

    @Override
    public void onShareNewsClick() {
        view.shareScreenWithFriends();
    }

    public void selectTheme() {
        Random random = new Random();
        int[] themesArray = {R.drawable.android_pelican_popup_wide,
                R.drawable.android_fox_popup_wide,
                R.drawable.android_elephant_popup_wide};
        int[] cameraIcons = {R.drawable.camera_icon_blue,
                R.drawable.camera_icon_green,
                R.drawable.camera_icon_yellow};
        int randomTheme = random.nextInt(themesArray.length);
        int theme = themesArray[randomTheme];
        int matchingCameraIconTheme = cameraIcons[randomTheme];
        view.setBackground(theme);
        view.setCameraIconTheme(matchingCameraIconTheme);
    }

    private void updateViews() {
        DateDifference dateDifference = new DateDifference(baby.getBirthday());
        updateNameTitle();
        updateAgeImage(dateDifference);
        updateAgeTitle(dateDifference);
        updateBabyImage();
    }

    private void updateBabyImage() {
        view.setImage(this.baby.getImagePath());
    }


    private void updateNameTitle() {
        final String name = baby.getName();
        final String nameTitle = ("TODAY " + name + " IS").toUpperCase();
        view.setNameTitle(nameTitle);
    }

    private void updateAgeImage(DateDifference dateDifference) {
        int[] numbersArray = {R.drawable._0, R.drawable._1, R.drawable._2, R.drawable._3,
                R.drawable._4, R.drawable._5, R.drawable._6, R.drawable._7, R.drawable._8,
                R.drawable._9, R.drawable._10, R.drawable._11, R.drawable._12};
        final int oneAndHalf = R.drawable.__half;
        final int years = dateDifference.getYears();
        final int months = dateDifference.getMonths();
        int imageSource = years > 0 ? numbersArray[years] : numbersArray[months];

        if (years == 1 && (months % 12) == 6)
            imageSource = oneAndHalf;
        view.setAgeImage(imageSource);
    }

    private void updateAgeTitle(DateDifference dateDifference) {
        String ageTitle = "";
        final int years = dateDifference.getYears();
        final int months = dateDifference.getMonths();
        if (years > 0) {
            ageTitle += years == 1 && !(months % 12 == 6) ? "YEAR" : "YEARS";
        } else {
            ageTitle += months == 1 ? "MONTH" : "MONTHS";
        }
        ageTitle += " OLD!";
        view.setAgeTitle(ageTitle);
    }

    public static class DateDifference {
        private final int months;
        private final int years;
        private final int days;

        public DateDifference(String birthday) {
            Date Birthday = null;
            Date currentDate = new Date(System.currentTimeMillis());
            try {
                Birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            final int SECONDS_IN_MINUTE = 60;
            final int MINUTES_IN_HOUR = 60;
            final float DAYS_IN_MONTH = 30.436875f;
            final int MONTHS_IN_YEAR = 12;
            final int HOURS_IN_DAY = 24;
            final long SECONDS_IN_MILLI = 1000;
            final long MINUTES_IN_MILLI = SECONDS_IN_MILLI * SECONDS_IN_MINUTE;
            final long HOURS_IN_MILLI = MINUTES_IN_MILLI * MINUTES_IN_HOUR;

            long different = currentDate.getTime() - Birthday.getTime();
            long daysInMilli = HOURS_IN_MILLI * HOURS_IN_DAY;

            this.days = (int) (different / daysInMilli) +1;
            this.months = (int)(days / DAYS_IN_MONTH);
            this.years = months / MONTHS_IN_YEAR;
        }

        public int getDays() {
            return days;
        }

        public int getMonths() {
            return months;
        }

        public int getYears() {
            return years;
        }
    }
}