package cisc181.myproject.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class CircleView extends View {

    Paint paint;
    public CircleView(Context context) {
        super(context);
        init(null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(null);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(null);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(null);
    }

    public void init(@Nullable AttributeSet set){

    }
    public void setPaint(String color){
        if(color.equalsIgnoreCase("Red")){
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);
        }else if(color.equalsIgnoreCase("Blue")){
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
        }else if(color.equalsIgnoreCase("Green")){
            paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(10);
        }else if(color.equalsIgnoreCase("Magenta")){
            paint = new Paint();
            paint.setColor(Color.MAGENTA);
            paint.setStrokeWidth(10);
        }else if(color.equalsIgnoreCase("CYAN")){
            paint = new Paint();
            paint.setColor(Color.CYAN);
            paint.setStrokeWidth(10);
        }else if(color.equalsIgnoreCase("BLACK")){
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(10);
        }else if(color.equalsIgnoreCase("Gray")){
            paint = new Paint();
            paint.setColor(Color.GRAY);
            paint.setStrokeWidth(10);
        }else if(color.equalsIgnoreCase("Random Once")){
            Random rand = new Random();
            int c = Color.argb(255, rand.nextInt(256),rand.nextInt(10),rand.nextInt(256));
            paint = new Paint();
            paint.setColor(c);
            paint.setStrokeWidth(10);
        }else if (color.equalsIgnoreCase("Random Every time")){
            Random rand = new Random();
            paint = new Paint();
            int c = Color.argb(255, rand.nextInt(256),rand.nextInt(10),rand.nextInt(256));
            paint.setColor(c);
            paint.setStrokeWidth(10);
        }else{
            paint = new Paint();
            paint.setColor(Color.CYAN);
            paint.setStrokeWidth(10);
        }
    }
    @Override
    protected void onDraw(Canvas canvas){

        canvas.drawCircle(getWidth()/2,getHeight()/2, getWidth()/2, paint);

    }
}
