package sagikor.android.happybirthday.presenter;

import sagikor.android.happybirthday.model.Baby;


public class MainActivityPresenter implements MainActivityContract.Presenter {
    private final Baby baby;
    private final MainActivityContract.View view;
    private boolean isNavigationButtonEnabled;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.baby = Baby.getInstance();
        this.view = view;
    }

    public void setName(String name) {
        if (isOnlyAlphabet(name)) {
            baby.setName(name);
            if (isNonEmptyInput(baby.getBirthday()))
                enableNavigationButton();
        } else {
            final String message = "Please enter english characters only";
            view.popErrorMessage(message);
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
            view.navigateToBirthdayScreen();
        }
    }

    private boolean isNonEmptyInput(String input) {
        return input != null && input.length() != 0;
    }

    private boolean isOnlyAlphabet(String str) {
        return isNonEmptyInput(str) && str.matches("^[ A-Za-z]+$");
    }
}
