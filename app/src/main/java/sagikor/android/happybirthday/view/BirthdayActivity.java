package sagikor.android.happybirthday.view;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import sagikor.android.happybirthday.R;
import sagikor.android.happybirthday.presenter.BirthdayActivityContract;
import sagikor.android.happybirthday.presenter.BirthdayActivityPresenter;

public class BirthdayActivity extends AppCompatActivity implements BirthdayActivityContract.View{
    BirthdayActivityPresenter presenter;
    ImageView screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        screen = findViewById(R.id.background_image_view);
        presenter = new BirthdayActivityPresenter(this,this.getApplicationContext());

    }

    @Override
    public void navigateToPreviousScreen() {

    }

    @Override
    public void setMainTitle(String title) {

    }

    @Override
    public void setSubTitle(String title) {

    }

    @Override
    public void setBackground(int source) {
        screen.setBackgroundColor(source);
    }

    @Override
    public void setLowerBanner(int source) {

    }
}
