package luiztadeu.com.ontoucheventview;

/**
 * Created by Shark on 26/12/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ListDropUp extends RelativeLayout implements View.OnTouchListener {

    private LinearLayout linearLayoutMove;
    private Activity mActivity;
    private float dX, dY, position;
    int lastAction;
    boolean isUp = false;
    private ListAdapter adapter;
    private DisplayMetrics displayMetrics;
    private ListView listView;
    private List<String> stringList;

    public ListDropUp(Context context) {
        super(context);
    }

    public ListDropUp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListDropUp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Activity mActivity){

        View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_main, this);
        linearLayoutMove = (LinearLayout) view.findViewById(R.id.linear_layout);
        listView = (ListView) view.findViewById(R.id.listview1);
        displayMetrics = getResources().getDisplayMetrics();
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (displayMetrics.heightPixels * 40) / 100));

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
        linearLayoutMove.setOnTouchListener(this);
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
                    linearLayoutMove.animate().setDuration(300).translationY(listView.getHeight() - 50);
                }else if (isUp){
                    isUp = false;
                    linearLayoutMove.animate().setDuration(300).translationY(listView.getHeight() * 2);//(displayMetrics.heightPixels * 80) / 100
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

