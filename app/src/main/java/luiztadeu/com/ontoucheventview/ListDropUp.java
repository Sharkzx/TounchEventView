package luiztadeu.com.ontoucheventview;

/**
 * Created by Shark on 26/12/2016.
 */

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ListDropUp extends RelativeLayout {

    private float lastposition;
    boolean isUp, isLastposition = false;
    private DisplayMetrics displayMetrics;
    private LinearLayout backgroundLinear;
    private LinearLayout rootLayout;
    private RecyclerView recycleView;
    private LinearLayoutManager linearLayoutManager;

    public ListDropUp(Context context) {
        super(context);
    }

    public ListDropUp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListDropUp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(final Activity mActivity, RecyclerView.Adapter adapter) {
        final View view = LayoutInflater.from(mActivity).inflate(R.layout.drop_up_main, this);

        rootLayout = (LinearLayout) view.findViewById(R.id.root_linear);
        backgroundLinear = (LinearLayout) view.findViewById(R.id.background_linear);
        displayMetrics = getResources().getDisplayMetrics();
        recycleView = (RecyclerView) view.findViewById(R.id.recycle_view);
        recycleView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(mActivity);
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setAdapter(adapter);


        rootLayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!isLastposition){
                    lastposition = rootLayout.getY();
                    isLastposition = true;
                }
                    switch (motionEvent.getActionMasked()) {
                        case MotionEvent.ACTION_UP:
                            if (!isUp) {
                                isUp = true;
                                if (recycleView.getMeasuredHeight() < displayMetrics.heightPixels) {
                                    setHeightAnimation(recycleView, recycleView.getMeasuredHeight(), displayMetrics.heightPixels);
                                    setHeightAnimation(backgroundLinear, backgroundLinear.getMeasuredHeight(), displayMetrics.heightPixels);
                                    rootLayout.setY(0);
                                }
                                //rootLayout.animate().setDuration(300).alpha(1.0f);
                                //linearLayoutMove.animate().setDuration(300).translationY(listView.getHeight() - 50);
                            } else if (isUp) {
                                isUp = false;
                                setHeightAnimation(recycleView, recycleView.getMeasuredHeight(), displayMetrics.heightPixels /12);
                                setHeightAnimation(backgroundLinear, backgroundLinear.getMeasuredHeight(), displayMetrics.heightPixels /12);
                                //listView.animate().setDuration(300).translationY(displayMetrics.heightPixels - (displayMetrics.heightPixels - position));//(displayMetrics.heightPixels * 80) / 100
                                //rootLayout.animate().setDuration(300).alpha(0.0f);
                                rootLayout.setY(lastposition);
                            }
                            break;

                        case MotionEvent.ACTION_MOVE:

                            ViewGroup.LayoutParams paramsBack = backgroundLinear.getLayoutParams();
                            paramsBack.height = (int) (displayMetrics.heightPixels - (motionEvent.getRawY() - 50));
                            backgroundLinear.setLayoutParams(paramsBack);
                            float alpha = (float) (paramsBack.height / 1000.0);
                            backgroundLinear.setAlpha(alpha);

                            ViewGroup.LayoutParams params = recycleView.getLayoutParams();
                            params.height = (int) (displayMetrics.heightPixels - (motionEvent.getRawY() - 50));
                            recycleView.setLayoutParams(params);
                            break;
                    }
                    return true;
                }
        });

        recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            private int mLastFirstVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (firstVisibleItem != 0){
                    rootLayout.setVisibility(GONE);
                }else{
                    rootLayout.setVisibility(VISIBLE);
                }

                if (mLastFirstVisibleItem < firstVisibleItem) {
                    Log.i("SCROLLING DOWN", "TRUE");

                }
                mLastFirstVisibleItem = firstVisibleItem;
            }
        });

    }

    private void setHeightAnimation(final View view, int fromHeight, int toHeight) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(fromHeight, toHeight);
        valueAnimator.setDuration(400);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = val;
                view.setLayoutParams(layoutParams);
                float alpha = (float) (layoutParams.height / 1000.0);
                backgroundLinear.setAlpha(alpha < 0.200 ? 0 : alpha);
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

