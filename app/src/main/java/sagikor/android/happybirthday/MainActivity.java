package sagikor.android.happybirthday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bOpenBirthDayScreen;
    ImageButton bAddImage;
    DatePickerDialog datePickerDialog;
    TextView tBirthdayDate;
    EditText tName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        addViewsOnClickListeners();
    }

    private void addViewsOnClickListeners() {
        tBirthdayDate.setOnClickListener(e -> getBirthday());
        bAddImage.setOnClickListener( e -> addImage());
        bOpenBirthDayScreen.setOnClickListener( e -> openBirthdayScreen());
    }


    private void bindViews() {
        tName = findViewById(R.id.name_input);
        tBirthdayDate = findViewById(R.id.birthday_date);
        bAddImage = findViewById(R.id.button_add_image);
        bOpenBirthDayScreen = findViewById(R.id.button_open_birthday_screen);
    }

    private void openBirthdayScreen() {
        startActivity(new Intent(this,BirthdayActivity.class));
    }

    private void getBirthday() {
    }

    private void addImage() {
    }
}