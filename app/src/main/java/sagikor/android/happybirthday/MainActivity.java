package sagikor.android.happybirthday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkViews();
    }

    private void linkViews() {
        button = findViewById(R.id.button_open_birthday_screen);
        //button.setOnClickListener( e -> openBirthdayScreen());

    }

    private void openBirthdayScreen() {
    }
}