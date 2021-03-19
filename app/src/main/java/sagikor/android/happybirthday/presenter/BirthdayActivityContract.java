package sagikor.android.happybirthday.presenter;



public interface BirthdayActivityContract {
    public interface Presenter{
        void onCloseClick();
        void onChangePictureClick();
    }
    public interface View{
        void setMainTitle(String title);
        void setSubTitle(String title);
        void setBackground(int source);
        void setLowerBanner(int source);
        void navigateToPreviousScreen();
    }
}
