package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView mStoryTextView;
    private Button mButtonTop;
    private Button mButtonBottom;
    private int mStoryLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mStoryTextView = (TextView) findViewById(R.id.storyTextView);
        mButtonTop = (Button) findViewById(R.id.buttonTop);
        mButtonBottom = (Button) findViewById(R.id.buttonBottom);

        if ( savedInstanceState !=null ) {
            mStoryLevel = savedInstanceState.getInt("StoryIndex");
            //Log.d("su","" + mStoryLevel + " " + mUserOption);
            storyCall(0);
        } else {
            mStoryLevel = 1;
        }


        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyCall(1);
            }
        });



        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyCall(2);
            }
        });

    }

    private void storyCall(int userOption) {
        //Log.d("SL","userChoice : " + userOption + " storyLevel : " + mStoryLevel);
        switch (mStoryLevel) {
            case 0 : story1(0); break; //For start over
            case 1 : story1(userOption); break;
            case 2 : story2(userOption); break;
            case 3 : story3(userOption); break;
            case 4 : story4(); break;
            case 5 : story5(); break;
            case 6 : story6(); break;
            default: gameOverAlert();
        }
    }

    private void story1(int userOption) {

        mStoryLevel = 1;
        if (userOption == 1) {
            story3(0);
        } else if (userOption == 2){
            story2(0);
        } else {
            mStoryTextView.setText(R.string.T1_Story);
            mButtonTop.setText(R.string.T1_Ans1);
            mButtonBottom.setText(R.string.T1_Ans2);
            mButtonTop.setVisibility(View.VISIBLE);
            mButtonBottom.setVisibility(View.VISIBLE);
        }
    }

    private void story2(int userOption) {

        mStoryLevel = 2;
        if (userOption == 1) {
            story3(0);
        } else if (userOption == 2){
            story4();
        } else {
            mStoryTextView.setText(R.string.T2_Story);
            mButtonTop.setText(R.string.T2_Ans1);
            mButtonBottom.setText(R.string.T2_Ans2);
        }
    }

    private void story3(int userOption) {

        mStoryLevel = 3;
        if (userOption == 1) {
            story6();
        } else if (userOption == 2) {
            story5();
        } else {
            mStoryTextView.setText(R.string.T3_Story);
            mButtonTop.setText(R.string.T3_Ans1);
            mButtonBottom.setText(R.string.T3_Ans2);
        }
    }

    private void story4() {
        mStoryLevel = 4;
        mStoryTextView.setText(R.string.T4_End);
        mButtonTop.setVisibility(View.INVISIBLE);
        mButtonBottom.setVisibility(View.INVISIBLE);
        gameOverAlert();
    }

    private void story5() {
        mStoryLevel = 5;
        mStoryTextView.setText(R.string.T5_End);
        mButtonTop.setVisibility(View.INVISIBLE);
        mButtonBottom.setVisibility(View.INVISIBLE);
        gameOverAlert();
    }

    private void story6() {
        mStoryLevel = 6;
        mStoryTextView.setText(R.string.T6_End);
        mButtonTop.setVisibility(View.INVISIBLE);
        mButtonBottom.setVisibility(View.INVISIBLE);
        gameOverAlert();
    }

    private void gameOverAlert() {

        AlertDialog.Builder gameOverAlert = new AlertDialog.Builder(this);
        gameOverAlert.setTitle("Game Over!!");
        gameOverAlert.setCancelable(false);
        gameOverAlert.setPositiveButton("Leave Application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        gameOverAlert.setNegativeButton("Start Over", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mStoryLevel=1;
                story1(0);
            }
        });
        gameOverAlert.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("StoryIndex",mStoryLevel);
    }
}
