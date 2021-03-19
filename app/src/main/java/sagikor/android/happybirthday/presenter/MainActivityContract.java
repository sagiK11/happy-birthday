package sagikor.android.happybirthday.presenter;


public interface MainActivityContract {
    interface View {
        void setBirthday(String date);
        void setImage(String url);
        void enableNavigationButton();
        void disableNavigationButton();
        void navigateToBirthdayScreen();
    }

    interface Presenter {
        void enableNavigationButton();
        void disableNavigationButton();
        void onBirthdayButtonClick();
    }
}
