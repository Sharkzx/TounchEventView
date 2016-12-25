package luiztadeu.com.ontoucheventview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout linearLayoutMove;
    private Activity mActivity;
    private float dX, dY, defaultPosition, position;
    int lastAction;
    boolean isUp = false;
    private ListAdapter adapter;
    private DisplayMetrics displayMetrics;
    private ListView listView;
    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;
        linearLayoutMove = (LinearLayout) findViewById(R.id.linear_layout);
        listView = (ListView) findViewById(R.id.listview1);

        stringList = new ArrayList<>();
        stringList.add("PSOne");
        stringList.add("PS2");
        stringList.add("PS3");
        stringList.add("PSVita");
        stringList.add("PS4");
        stringList.add("Xbox");
        stringList.add("Xbox360");
        stringList.add("XboxOne");
        stringList.add("PSOne");
        stringList.add("PS2");
        stringList.add("PS3");
        stringList.add("PSVita");
        stringList.add("PS4");
        stringList.add("Xbox");
        stringList.add("Xbox360");
        stringList.add("XboxOne");

        adapter = new ListAdapter(mActivity, stringList);
        listView.setAdapter(adapter);
        defaultPosition = linearLayoutMove.getMeasuredHeight();
        linearLayoutMove.setOnTouchListener(this);

        displayMetrics = getResources().getDisplayMetrics();

        linearLayoutMove.setY((displayMetrics.heightPixels * 80) / 100);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        position = view.getY();

        switch (motionEvent.getActionMasked()) {
//
            case MotionEvent.ACTION_DOWN:
                dX = (int) view.getX() - motionEvent.getRawX();
                dY = (int) view.getY() - motionEvent.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;

                if (!isUp){
                    isUp = true;
                    linearLayoutMove.animate().setDuration(300).translationY((displayMetrics.heightPixels * 35
                    ) / 100);// 25 porcent
                }else if (isUp){
                    isUp = false;
                    linearLayoutMove.animate().setDuration(300).translationY((displayMetrics.heightPixels * 80
                    ) / 100);
                }

                //linearLayoutMove.setY(displayMetrics.heightPixels / 4);
                break;

            case MotionEvent.ACTION_MOVE:
                if (dY == (displayMetrics.heightPixels / 2)){

                }

                String s = String.valueOf((int) view.getY());
                view.setY(motionEvent.getRawY() + dY);
                Log.d("Eixo Y", s);
                break;

//            case MotionEvent.ACTION_UP:
//                if (view.getX() > (defaultPosition + defaultPosition)) {
//                    view.setX(motionEvent.getRawX() * (defaultPosition));
//                } else if (view.getX() < (defaultPosition - defaultPosition)) {
//                    view.setX(motionEvent.getRawX() * (-defaultPosition));
//                } else {
//                    view.setX(defaultPosition);
//                }
        }

        return true;
    }
}
