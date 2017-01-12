package luiztadeu.com.ontoucheventview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import luiztadeu.com.ontoucheventview.adapter.DropUpCardAdapter;

public class MainActivity extends AppCompatActivity {

    private ListDropUp listDropUp;
    private Activity mActivity;
    private List<String> stringList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;

        stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        stringList.add("7");
        stringList.add("8");
        stringList.add("9");
        stringList.add("10");
        stringList.add("11");
        stringList.add("12");
        stringList.add("13");
        stringList.add("14");
        stringList.add("15");
        stringList.add("16");

        listDropUp = (ListDropUp) findViewById(R.id.listDropUp);
        listDropUp.init(mActivity, new DropUpCardAdapter(mActivity, stringList));

    }

}