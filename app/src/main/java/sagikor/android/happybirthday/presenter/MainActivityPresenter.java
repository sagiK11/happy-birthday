package sagikor.android.happybirthday.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


import com.google.gson.Gson;

import sagikor.android.happybirthday.model.Baby;

import static android.content.Context.MODE_PRIVATE;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private final Baby baby;
    private final MainActivityContract.View view;
    private final Context context;
    private boolean isNavigationButtonEnabled;
    final static String KEY = "BABY_OBJECT";

    public MainActivityPresenter(MainActivityContract.View view, Context context) {
        this.baby = new Baby();
        this.view = view;
        this.context = context;
    }

    public void setName(String name) {
        if (isOnlyAlphabet(name)) {
            baby.setName(name);
            if (isNonEmptyInput(baby.getBirthday()))
                enableNavigationButton();
        } else {
            final String message = "Please enter english characters only";
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            baby.resetName();
            disableNavigationButton();
        }
    }

    public void setBirthday(String birthday) {
        baby.setBirthday(birthday);
        view.setBirthday(birthday);
        if (isOnlyAlphabet(baby.getName()))
            enableNavigationButton();
        else
            disableNavigationButton();
    }

    public void setImage(String url) {
        baby.setImagePath(url);
        view.setImage(url);
    }

    @Override
    public void enableNavigationButton() {
        isNavigationButtonEnabled = true;
        view.enableNavigationButton();
    }

    @Override
    public void disableNavigationButton() {
        isNavigationButtonEnabled = false;
        view.disableNavigationButton();
    }

    @Override
    public void onBirthdayButtonClick() {
        if (isNavigationButtonEnabled) {
            saveModel();
            view.navigateToBirthdayScreen();
        }
    }

    private void saveModel() {
        final String packageName = context.getPackageName();
        SharedPreferences.Editor prefsEditor = context.getSharedPreferences(packageName, MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.baby);
        prefsEditor.putString(KEY, json);
        prefsEditor.apply();
    }

    private boolean isNonEmptyInput(String input) {
        return input != null && input.length() != 0;
    }

    private boolean isOnlyAlphabet(String str) {
        return isNonEmptyInput(str) && str.matches("^[ A-Za-z]+$");
    }
}
