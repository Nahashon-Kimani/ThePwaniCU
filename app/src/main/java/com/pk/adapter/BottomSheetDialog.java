package com.pk.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pk.R;

import java.util.Calendar;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    View view;
    TextView bottomDay, bottomProgram;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);

        bottomDay = view.findViewById(R.id.bottom_day);
        bottomProgram = view.findViewById(R.id.bottom_program);

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //Toast.makeText(getContext(), Integer.toString(dayOfWeek), Toast.LENGTH_SHORT).show();

        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                bottomDay.setText(getResources().getString(R.string.sun));
                bottomProgram.setText(getResources().getString(R.string.program));
                break;
            case Calendar.MONDAY:
                bottomDay.setText(getResources().getString(R.string.mon));
                bottomProgram.setText(getResources().getString(R.string.program));
                break;
            case Calendar.TUESDAY:
                bottomDay.setText(getResources().getString(R.string.tue));
                bottomProgram.setText(getResources().getString(R.string.program));
                break;
            case Calendar.WEDNESDAY:
                bottomDay.setText(getResources().getString(R.string.wed));
                bottomProgram.setText(getResources().getString(R.string.program));
                break;
            case Calendar.THURSDAY:
                bottomDay.setText(getResources().getString(R.string.thur));
                bottomProgram.setText(getResources().getString(R.string.program));
                break;
            case Calendar.FRIDAY:
                bottomDay.setText(getResources().getString(R.string.fri));
                bottomProgram.setText(getResources().getString(R.string.program));
                break;
            case Calendar.SATURDAY:
                bottomDay.setText(getResources().getString(R.string.sat));
                bottomProgram.setText(getResources().getString(R.string.program));
                break;
        }


        return view;
    }


}
