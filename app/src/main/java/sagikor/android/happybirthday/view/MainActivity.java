package sagikor.android.happybirthday.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import sagikor.android.happybirthday.R;
import sagikor.android.happybirthday.presenter.MainActivityContract;
import sagikor.android.happybirthday.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    Button bOpenBirthDayScreen;
    ImageButton bAddImage;
    DatePickerDialog datePickerDialog;
    TextView tBirthdayDate;
    EditText tName;
    ImageView iBabyImage;
    private MainActivityPresenter presenter;
    final int PICTURE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this, this.getApplicationContext());
        bindViews();
        bOpenBirthDayScreen.setEnabled(false);
        addViewsOnClickListeners();
    }

    private void addViewsOnClickListeners() {
        initDatePicker();
        bAddImage.setOnClickListener(e -> addImage());
        bOpenBirthDayScreen.setOnClickListener(e -> presenter.onBirthdayButtonClick());
        tBirthdayDate.setOnClickListener(e -> datePickerDialog.show());
        tName.addTextChangedListener((NameTextWatcher)
                (charSequence, start, count, after) ->
                        presenter.setName(charSequence.toString().trim()));
    }


    private void bindViews() {
        tName = findViewById(R.id.name_input);
        tBirthdayDate = findViewById(R.id.birthday_date);
        bAddImage = findViewById(R.id.button_add_image);
        iBabyImage = findViewById(R.id.baby_image);
        bOpenBirthDayScreen = findViewById(R.id.button_open_birthday_screen);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month += 1; //jan = 0
            String date = dayOfMonth + "/" + month + "/" + year;
            presenter.setBirthday(date);
        };
        //Make the default date as today
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        //Sets the max date to today so the user won't be able to set the birthday into the future.
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private void addImage() {
        final String defaultUrl = "https://as1.ftcdn.net/jpg/02/91/54/38/1000_F_291543809_26XYk48erTYbRDdu7MxOCOzAyEwtCMK5.jpg";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Choose from gallery", (dialog, id) -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(Intent.createChooser(intent, "Select Gallery"), PICTURE_REQUEST_CODE);
        });
        builder.setNegativeButton("Use default", (dialog, id) -> {
            presenter.setImage(defaultUrl);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICTURE_REQUEST_CODE) {
            String url = data.getData().toString();
            presenter.setImage(url);
        }
    }


    @Override
    public void setBirthday(String date) {
        tBirthdayDate.setText(date);
    }

    @Override
    public void setImage(String url) {
        Glide.with(this)
                .load(url)
                .circleCrop()
                .into(iBabyImage);
    }

    @Override
    public void navigateToBirthdayScreen() {
        startActivity(new Intent(this, BirthdayActivity.class));
    }

    @Override
    public void enableNavigationButton() {
        bOpenBirthDayScreen.setEnabled(true);
    }

    @Override
    public void disableNavigationButton() {
        bOpenBirthDayScreen.setEnabled(false);
    }

    private interface NameTextWatcher extends TextWatcher {
        @Override
        default void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        default void afterTextChanged(Editable editable) {

        }
    }

}