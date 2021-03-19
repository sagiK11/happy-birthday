package sagikor.android.happybirthday.presenter;



public interface BirthdayActivityContract {
    public interface Presenter{
        void onCloseClick();
        void onChangePictureClick();
        void onShareNewsClick();
    }
    public interface View{
        void setNameTitle(String title);
        void setAgeTitle(String title);
        void setAgeImage(int source);
        void setBackground(int source);
        void setCameraIconTheme(int source);
        void setImage(String url);
        void replaceImage();
        void navigateToPreviousScreen();
    }
}
