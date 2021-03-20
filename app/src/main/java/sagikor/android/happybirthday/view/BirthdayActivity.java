package sagikor.android.happybirthday.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sagikor.android.happybirthday.R;
import sagikor.android.happybirthday.presenter.BirthdayActivityContract;
import sagikor.android.happybirthday.presenter.BirthdayActivityPresenter;

public class BirthdayActivity extends AppCompatActivity implements BirthdayActivityContract.View {
    BirthdayActivityPresenter presenter;
    ImageView iScreen;
    ImageView iAge;
    ImageView iBabyImage;
    ImageButton bClose;
    ImageButton bChangeImage;
    FrameLayout bShareNews;
    TextView tNameTitle;
    TextView tAgeTitle;
    ConstraintLayout root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        bindViews();
        addViewsOnClickListeners();
        presenter = new BirthdayActivityPresenter(this, this.getApplicationContext());

    }

    private void bindViews() {
        iScreen = findViewById(R.id.background_image_view);
        bClose = findViewById(R.id.close_button);
        bChangeImage = findViewById(R.id.change_image_button);
        tNameTitle = findViewById(R.id.title_name);
        iAge = findViewById(R.id.age);
        tAgeTitle = findViewById(R.id.title_age);
        bShareNews = findViewById(R.id.share_button);
        iBabyImage = findViewById(R.id.baby_image);
        root = findViewById(R.id.screen);
    }

    private void addViewsOnClickListeners() {
        bClose.setOnClickListener(e -> presenter.onCloseClick());
        bChangeImage.setOnClickListener(e -> presenter.onChangePictureClick());
        bShareNews.setOnClickListener(e -> presenter.onShareNewsClick());
    }


    @Override
    public void navigateToPreviousScreen() {
        finish();
    }

    @Override
    public void setNameTitle(String title) {
        tNameTitle.setText(title);
    }

    @Override
    public void setAgeTitle(String title) {
        tAgeTitle.setText(title);
    }

    @Override
    public void setAgeImage(int source) {
        iAge.setImageResource(source);
    }

    @Override
    public void setCameraIconTheme(int source) {
        bChangeImage.setImageResource(source);
    }

    @Override
    public void setBackground(int source) {
        iScreen.setImageResource(source);
        iScreen.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }


    @Override
    public void setImage(String url) {
        Glide.with(this)
                .load(url)
                .circleCrop()
                .into(iBabyImage);
    }

    @Override
    public void replaceImage() {
        final String url = "https://as2.ftcdn.net/jpg/02/50/10/03/1000_F_250100321_1hYz6jdwgiKmXz3mC4q0BCnR83jSTSbL.jpg";
        Glide.with(this)
                .load(url)
                .circleCrop()
                .into(iBabyImage);
    }

    @Override
    public void shareScreenWithFriends() {
        //hide the relevant views.
        changeRelevantViewsVisibilities(View.GONE);
        //take screenshot
        Uri uriToImage = getImageUri();
        //open share menu
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, "Share the news!"));
        //show the views back.
        changeRelevantViewsVisibilities(View.VISIBLE);
    }

    private Uri getImageUri() {
        Bitmap bitmap = getBitmap(root);
        File file = new File(getExternalCacheDir(), "screenshot.jpeg");
        file.setReadable(true, false);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String authority = getApplicationContext().getPackageName() + ".provider";
        return FileProvider.getUriForFile(this, authority, file);
    }


    private void changeRelevantViewsVisibilities(int visibility) {
        bShareNews.setVisibility(visibility);
        bChangeImage.setVisibility(visibility);
        bClose.setVisibility(visibility);
    }

    private Bitmap getBitmap(View root) {
        Bitmap result = Bitmap.createBitmap(root.getWidth(), root.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawColor(Color.WHITE);
        root.draw(canvas);
        return result;
    }

}
