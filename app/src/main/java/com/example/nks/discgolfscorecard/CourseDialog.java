package com.example.nks.discgolfscorecard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nks on 9/10/17.
 */

public class CourseDialog extends AppCompatDialogFragment {
    private SeekBar cSeekbar;
    private int holeSlider = 0;
    private TextView cTextview;
    private Course newCourse=new Course();

    public CourseDialog() {
        // Empty constructor required for DialogFragment
    }

    public static CourseDialog newInstance(String title) {
        CourseDialog frag = new CourseDialog(); // New coursedialog object
        Bundle args = new Bundle(); // Bundle for passing data between objects
        args.putString("title", title); //Setting alertdialog title
        frag.setArguments(args);  //Setting arguments for the Coursedialog object
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       // Create the view to show

        Toast.makeText(getActivity(), "perse", Toast.LENGTH_SHORT).show();

        // Inflate created layout

        View cView = LayoutInflater.from(getActivity()).inflate(R.layout.course_dialog_custom, null);

        // Build the alertdialog and set it

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(cView);

        cSeekbar = cView.findViewById(R.id.courseDialogSeekbar);
        cTextview = cView.findViewById(R.id.seekBarText);

        cSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                // This executes when Seekbar's value is changed aka. moved
                holeSlider = progress;
                cTextview.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Autogenerated, executed when Seekbar is first touch
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Set positive and negative buttons
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for ( int i = 0 ; i <= holeSlider ; i++ ) {
                    newCourse.addHole(new Hole(3,holeSlider));
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return alertDialogBuilder.create();
    }

    public Course getNewCourse() {
        return newCourse;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CourseBuilderActivity) getActivity()).setHoleInfo();
    }
}







