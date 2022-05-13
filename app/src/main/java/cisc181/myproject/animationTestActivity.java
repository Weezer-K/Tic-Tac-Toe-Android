package cisc181.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class animationTestActivity extends AppCompatActivity implements Animation.AnimationListener {
    Button startAnimation;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAnimation = findViewById(R.id.animationButton);
        text = findViewById(R.id.textAnimat);

        startAnimation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startAnimation(v);
            }
        });
    }




    public void startAnimation(View view){
        Animation animate = AnimationUtils.loadAnimation(this, R.anim.animation);
        animate.setAnimationListener(this);
        view.startAnimation(animate);


    }

    @Override
    public void onAnimationStart(Animation animation) {
        Toast.makeText(animationTestActivity.this, "Animation started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(animationTestActivity.this, "Animation Ended", Toast.LENGTH_SHORT).show();
        text.setTextSize(40*text.getTextSize());
        //text.setPivotX(((float).5)*text.getPivotX());
        //text.setPivotY(((float).5)*text.getPivotY());
        text.setPivotX((float).5);
        text.setPivotY((float).5);

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Toast.makeText(animationTestActivity.this, "Animation started", Toast.LENGTH_SHORT).show();
    }
}