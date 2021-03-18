package sagikor.android.happybirthday.presenter;

import java.util.Date;

public interface MainActivityContract {
    interface View {
        void setName(String name);
        void setBirthday(String date);
        void setImage(String url);
        void navigateToBirthdayScreen();
        void enableNavigationButton();
    }

    interface Presenter {
        void enableNavigationButton();
    }
}
