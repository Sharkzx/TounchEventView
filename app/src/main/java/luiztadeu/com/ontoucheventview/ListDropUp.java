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

public class ListDropUp extends RelativeLayout implements View.OnTouchListener {

    private LinearLayout linearLayoutMove;
    private float dX, dY;
    private int position = dpToPx(460);
    int lastAction;
    boolean isUp = false;
    private ListAdapter adapter;
    private DisplayMetrics displayMetrics;
    private ListView listView;
    private LinearLayout rootLayout;
    private ArrayList<String> stringList;

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
        rootLayout = (LinearLayout) view.findViewById(R.id.root_layout);
        linearLayoutMove = (LinearLayout) view.findViewById(R.id.linear_layout);
        listView = (ListView) view.findViewById(R.id.listview1);
        displayMetrics = getResources().getDisplayMetrics();
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));//(displayMetrics.heightPixels * 40) / 100));

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

        adapter = new ListAdapter(mActivity, stringList);
        listView.setAdapter(adapter);
        linearLayoutMove.setOnTouchListener(this);
        linearLayoutMove.setY(displayMetrics.heightPixels - (displayMetrics.heightPixels - position));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getActionMasked()) {
//
            case MotionEvent.ACTION_UP:
                dX = (int) view.getX() - motionEvent.getRawX();
                dY = (int) view.getY() - motionEvent.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;

                if (!isUp){
                    isUp = true;
                    linearLayoutMove.animate().setDuration(300).translationY(0);
                    rootLayout.animate().setDuration(300).alpha(1.0f);
                    //linearLayoutMove.animate().setDuration(300).translationY(listView.getHeight() - 50);
                }else if (isUp){
                    isUp = false;
                    linearLayoutMove.animate().setDuration(300).translationY(displayMetrics.heightPixels - (displayMetrics.heightPixels - position));//(displayMetrics.heightPixels * 80) / 100
                    rootLayout.animate().setDuration(300).alpha(0.0f);
                }

                //linearLayoutMove.setY(displayMetrics.heightPixels / 4);
                break;

            case MotionEvent.ACTION_MOVE:
                String s = String.valueOf((int) view.getY());
                Log.d("Eixo Y", s);
//                if (position >= 0)
//                    linearLayoutMove.setTranslationY(position);
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

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

}

