package luiztadeu.com.ontoucheventview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout linearLayoutMove;
    private float dX, dY, defaultPosition, position;
    int lastAction;
    boolean isUp = false;
    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutMove = (LinearLayout) findViewById(R.id.linear_layout_move);
        defaultPosition = linearLayoutMove.getMeasuredHeight();
        linearLayoutMove.setOnTouchListener(this);

        displayMetrics = getResources().getDisplayMetrics();
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
                    linearLayoutMove.animate().setDuration(300).translationY(displayMetrics.heightPixels / 4);
                }else if (isUp){
                    isUp = false;
                    linearLayoutMove.animate().setDuration(300).translationY(displayMetrics.heightPixels * 4);
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
