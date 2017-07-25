package com.example.pujasudip.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    private TextView mTvLoopName, mTvNoOfLoopsMeasure, mTvBeatsPerMeasure, mTvRytherZoomLevel;
    private Spinner mSpInstruments;
    private ImageView mImPlayPause;
    private TextView mTvNotes, mTvInstrumentLegend;
    private boolean play = true;

    final private String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvLoopName = (TextView) findViewById(R.id.tv_loopName);
        mTvNoOfLoopsMeasure = (TextView) findViewById(R.id.tv_noLoopMeasure);
        mTvBeatsPerMeasure = (TextView) findViewById(R.id.tv_noLoopMeasure);
        mTvRytherZoomLevel = (TextView) findViewById(R.id.tv_rytherZoomLevel);
        mSpInstruments = (Spinner) findViewById(R.id.sp_instruments);
        mImPlayPause = (ImageView) findViewById(R.id.im_playPause);
        mTvInstrumentLegend = (TextView) findViewById(R.id.tv_spinner_legend);

        Context context = getApplicationContext();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.instruments, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpInstruments.setAdapter(adapter);

        mTvLoopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                final View newFileView = layoutInflater.inflate(R.layout.activity_file_name, null);
                AlertDialog.Builder newFileDialog = new AlertDialog.Builder(MainActivity.this);

                newFileDialog.setTitle("Name of the file: ");
                newFileDialog.setView(newFileView);

                final EditText edFileName = (EditText) newFileView.findViewById(R.id.ed_filename);

                newFileDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        String fileName = edFileName.getText().toString();
                        mTvLoopName.setText("Loop Name: \n" + fileName);
                    }
                });

                newFileDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                newFileDialog.show();
            }
        });

        mSpInstruments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(mSpInstruments.getSelectedItem().toString().equals("Guitar")){
                    mTvInstrumentLegend.setBackgroundColor(Color.RED);
                } else if(mSpInstruments.getSelectedItem().toString().equals("Piano")){
                    mTvInstrumentLegend.setBackgroundColor(Color.BLUE);
                } else if(mSpInstruments.getSelectedItem().toString().equals("Recorder")){
                    mTvInstrumentLegend.setBackgroundColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void togglePlayPause(View view){
        final ImageView playPause = (ImageView) findViewById(R.id.im_playPause);
        playPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (play) {
                    playPause.setImageResource(R.drawable.ic_pause_circle_outline_black_24px);
                    play = false;
                } else {
                    playPause.setImageResource(R.drawable.ic_play_arrow_black_24px);
                    play = true;
                }
            }
        });
    }
    public void tvClick(View view){
        ColorDrawable col = (ColorDrawable) view.getBackground();
        int colorCode = col.getColor();
        if(colorCode == getColor(R.color.buttonColor)){
            if(mSpInstruments.getSelectedItem().toString().equals("Guitar")){
                view.setBackgroundColor(Color.RED);
            } else if(mSpInstruments.getSelectedItem().toString().equals("Piano")){
                view.setBackgroundColor(Color.BLUE);
            } else if (mSpInstruments.getSelectedItem().toString().equals("Recorder")){
                view.setBackgroundColor(Color.BLACK);
            }
        } else {
            view.setBackgroundColor(getColor(R.color.buttonColor));
        }
        Toast.makeText(getApplicationContext(), view.getContentDescription(), Toast.LENGTH_LONG).show();
        Log.d(TAG, "notes:");
    }
}
