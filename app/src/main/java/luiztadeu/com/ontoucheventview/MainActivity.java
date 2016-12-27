package luiztadeu.com.ontoucheventview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListDropUp listDropUp;
    private Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        mActivity = this;

        listDropUp = (ListDropUp) findViewById(R.id.listDropUp);
        listDropUp.init(mActivity);

    }

}