package luiztadeu.com.ontoucheventview;

/**
 * Created by Shark on 26/12/2016.
 */

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class ListDropUp extends RelativeLayout implements View.OnTouchListener {

    private LinearLayout linearLayoutMove;
    private float dX, dY;
    private int position = dpToPx(460);
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

    public void init(final Activity mActivity) {

        final View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_main, this);
        //rootLayout = (LinearLayout) view.findViewById(R.id.root_linear);
//        linearLayoutMove = (LinearLayout) view.findViewById(R.id.linear_layout);
        listView = (ListView) view.findViewById(R.id.listview1);
        displayMetrics = getResources().getDisplayMetrics();
        //listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));//(displayMetrics.heightPixels * 40) / 100));

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
        //rootLayout.setOnTouchListener(this);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;
            private boolean isDrop = false;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                listView.setOnTouchListener(new OnTouchListener() {


                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (!isDrop) {
                            switch (motionEvent.getActionMasked()) {
                                case MotionEvent.ACTION_UP:
                                    if (!isUp) {
                                        isUp = true;
                                        isDrop = true;
                                        if (listView.getMeasuredHeight() < displayMetrics.heightPixels) {
                                            setHeightAnimation(listView.getMeasuredHeight(), displayMetrics.heightPixels);
                                        }
                                        //rootLayout.animate().setDuration(300).alpha(1.0f);
                                        //linearLayoutMove.animate().setDuration(300).translationY(listView.getHeight() - 50);
                                    } else if (isUp) {
                                        isUp = false;
                                        setHeightAnimation(listView.getMeasuredHeight(), -displayMetrics.heightPixels);
                                        //listView.animate().setDuration(300).translationY(displayMetrics.heightPixels - (displayMetrics.heightPixels - position));//(displayMetrics.heightPixels * 80) / 100
                                        //rootLayout.animate().setDuration(300).alpha(0.0f);
                                    }
                                    break;

                                case MotionEvent.ACTION_MOVE:
                                    ViewGroup.LayoutParams params = listView.getLayoutParams();
                                    params.height = (int) (displayMetrics.heightPixels - (motionEvent.getRawY() + (motionEvent.getY() - listView.getMeasuredHeight())));
                                    listView.setLayoutParams(params);
                                    listView.invalidate();
                                    break;
                            }
                            return true;
                        }
                        return false;
                    }
                });

                if (mLastFirstVisibleItem < firstVisibleItem) {
                    Log.i("SCROLLING DOWN", "TRUE");

                }
                if (mLastFirstVisibleItem > firstVisibleItem) {
                    Log.i("SCROLLING UP", "TRUE");
                    if (firstVisibleItem == 0) {
                        isDrop = false;
                        listView.setSelection(0);
                    }
                }
                mLastFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_UP:

                if (!isUp) {
                    isUp = true;
                    if (listView.getMeasuredHeight() < displayMetrics.heightPixels) {
                        setHeightAnimation(listView.getMeasuredHeight(), displayMetrics.heightPixels);
                    }
                    //rootLayout.animate().setDuration(300).alpha(1.0f);
                    //linearLayoutMove.animate().setDuration(300).translationY(listView.getHeight() - 50);
                } else if (isUp) {
                    isUp = false;
                    setHeightAnimation(listView.getMeasuredHeight(), -displayMetrics.heightPixels);
                    //listView.animate().setDuration(300).translationY(displayMetrics.heightPixels - (displayMetrics.heightPixels - position));//(displayMetrics.heightPixels * 80) / 100
                    //rootLayout.animate().setDuration(300).alpha(0.0f);
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if (listView.getFirstVisiblePosition() != 0) {
                    ViewGroup.LayoutParams params = listView.getLayoutParams();
                    params.height = (int) (displayMetrics.heightPixels - motionEvent.getRawY());
                    listView.setLayoutParams(params);

                } else {
                    return false;
                }

                break;
        }

        return true;
    }


    private void setHeightAnimation(int fromHeight, int toHeight) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(fromHeight, toHeight);
        valueAnimator.setDuration(400);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
                layoutParams.height = val;
                listView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

}

