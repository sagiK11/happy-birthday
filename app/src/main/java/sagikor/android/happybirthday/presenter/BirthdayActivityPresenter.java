package sagikor.android.happybirthday.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import sagikor.android.happybirthday.R;
import sagikor.android.happybirthday.model.Baby;

public class BirthdayActivityPresenter implements BirthdayActivityContract.Presenter {

    private Baby baby;
    private final BirthdayActivityContract.View view;
    private final Context context;
    private final static String TAG = "BirthActivityPresenter";

    public BirthdayActivityPresenter(BirthdayActivityContract.View view, Context context) {
        this.view = view;
        this.context = context;
        loadModel();
        selectTheme();
    }

    @Override
    public void onCloseClick() {
        view.navigateToPreviousScreen();
    }

    @Override
    public void onChangePictureClick() {

    }

    public void loadModel() {
        final String packageName = context.getPackageName();
        final String KEY = MainActivityPresenter.KEY;
        Gson gson = new Gson();
        String json = context.getSharedPreferences(packageName, Context.MODE_PRIVATE).getString(KEY, "");
        this.baby = gson.fromJson(json, Baby.class);
        Log.d(TAG,"loadModel: " + this.baby);
    }

    public void selectTheme() {
        view.setBackground(R.drawable.android_pelican_popup_wide);
    }
}
