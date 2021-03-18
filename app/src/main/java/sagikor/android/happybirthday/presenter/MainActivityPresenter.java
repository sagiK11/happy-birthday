package sagikor.android.happybirthday.presenter;



import android.util.Log;
import android.view.View;

import java.util.Date;

import sagikor.android.happybirthday.model.Baby;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private final Baby baby;
    private final MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view){
        this.baby = new Baby();
        this.view = view;
    }

    public void setName(String name){
        baby.setName(name);
        if(testInput())
            enableNavigationButton();
    }

    public void setBirthday(String birthday){
        baby.setBirthday(birthday);
        view.setBirthday(baby.getBirthday());
        if(testInput())
            enableNavigationButton();
    }
    public void setImage(String url){
        baby.setImagePath(url);
        view.setImage(url);
        if(testInput())
            enableNavigationButton();
    }

    @Override
    public void enableNavigationButton() {
        view.enableNavigationButton();
    }
    private boolean testInput(){
        return isValidInput(baby.getName()) && isValidInput(baby.getBirthday())
                && isValidInput(baby.getImagePath());
    }
    private boolean isValidInput(String input) {
        return input != null && input.length() != 0;
    }
}
