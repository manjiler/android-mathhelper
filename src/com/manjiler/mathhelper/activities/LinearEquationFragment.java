/**
 * 
 */
package com.manjiler.mathhelper.activities;

import java.util.ArrayList;

import com.manjiler.mathhelper.lineareq.LinearEquation;
import com.manjiler.mathhelper.lineareq.PointOfIntersection;
import com.manjiler.mathhelper.lineareq.SolveLinearEquations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Manoj Srivatsav
 *
 */
public class LinearEquationFragment extends Fragment {

	private Spinner signSpinnerX1;
	private Spinner signSpinnerY1;
	private Spinner signSpinnerC1;
	private EditText valueOfX1;
	private EditText valueOfY1;
	private EditText valueOfC1;
	private TextView x1TextView;
	private TextView y1TextView;
		
	private Spinner signSpinnerX2;
	private Spinner signSpinnerY2;
	private Spinner signSpinnerC2;
	private EditText valueOfX2;
	private EditText valueOfY2;
	private EditText valueOfC2;
	private TextView x2TextView;
	private TextView y2TextView;
	
	private TextView intersectionText;
	
	private static final String MINUS_SIGN = "-";
	private static final String PLUS_SIGN = "+";
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_linear_equations, container, false);
		
		//Populate the sign spinners
        ArrayList<String> signList = new ArrayList<String>();
        signList.add("+");
        signList.add("-");
        
        signSpinnerX1 = (Spinner) rootView.findViewById(R.id.linearEq1SignSpinnerX);
        signSpinnerY1 = (Spinner) rootView.findViewById(R.id.linearEq1SignSpinnerY);
        signSpinnerC1 = (Spinner) rootView.findViewById(R.id.linearEq1SignSpinnerC);
        signSpinnerX2 = (Spinner) rootView.findViewById(R.id.linearEq2SignSpinnerX);
        signSpinnerY2 = (Spinner) rootView.findViewById(R.id.linearEq2SignSpinnerY);
        signSpinnerC2 = (Spinner) rootView.findViewById(R.id.linearEq2SignSpinnerC);
        signSpinnerX1.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, signList));
        signSpinnerY1.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, signList));
        signSpinnerC1.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, signList));
        signSpinnerX2.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, signList));
        signSpinnerY2.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, signList));
        signSpinnerC2.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, signList));
        
        x1TextView = (TextView) rootView.findViewById(R.id.linearEq1TextViewX);
        y1TextView = (TextView) rootView.findViewById(R.id.linearEq1TextViewY);
        x2TextView = (TextView) rootView.findViewById(R.id.linearEq2TextViewX);
        y2TextView = (TextView) rootView.findViewById(R.id.linearEq2TextViewY);
        
		x1TextView.setText("X");
		y1TextView.setText("Y");
		x2TextView.setText("X");
		y2TextView.setText("Y");
	
		valueOfX1 = (EditText) rootView.findViewById(R.id.linearEq1EditTextX);
		valueOfX2 = (EditText) rootView.findViewById(R.id.linearEq2EditTextX);
		valueOfY1 = (EditText) rootView.findViewById(R.id.linearEq1EditTextY);
		valueOfY2 = (EditText) rootView.findViewById(R.id.linearEq2EditTextY);
		valueOfC1 = (EditText) rootView.findViewById(R.id.linearEq1EditTextC);
		valueOfC2 = (EditText) rootView.findViewById(R.id.linearEq2EditTextC);		
		
		intersectionText = (TextView) rootView.findViewById(R.id.intersectionPointTextView);
		
		Button solveEquation = (Button) rootView.findViewById(R.id.solveLinearEquation);
		
		solveEquation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int x1Coefficient = Integer.parseInt(valueOfX1.getText().toString());
				int x2Coefficient = Integer.parseInt(valueOfX2.getText().toString());
				int y1Coefficient = Integer.parseInt(valueOfY1.getText().toString());
				int y2Coefficient = Integer.parseInt(valueOfY2.getText().toString());
				int c1Coefficient = Integer.parseInt(valueOfC1.getText().toString());
				int c2Coefficient = Integer.parseInt(valueOfC2.getText().toString());
				
				String x1Sign = (String)signSpinnerX1.getSelectedItem();
				String x2Sign = (String)signSpinnerX2.getSelectedItem();
				String y1Sign = (String)signSpinnerY1.getSelectedItem();
				String y2Sign = (String)signSpinnerY2.getSelectedItem();
				String c1Sign = (String)signSpinnerC1.getSelectedItem();
				String c2Sign = (String)signSpinnerC2.getSelectedItem();
				
				if(x1Sign.equals(MINUS_SIGN)) {
					x1Coefficient = -x1Coefficient;
				}
				if(x2Sign.equals(MINUS_SIGN)) {
					x2Coefficient = -x2Coefficient;
				}
				if(y1Sign.equals(MINUS_SIGN)) {
					y1Coefficient = -y1Coefficient;
				}
				if(y2Sign.equals(MINUS_SIGN)) {
					y2Coefficient = -y2Coefficient;
				}
				if(c1Sign.equals(MINUS_SIGN)) {
					c1Coefficient = -c1Coefficient;
				}
				if(c2Sign.equals(MINUS_SIGN)) {
					c2Coefficient = -c2Coefficient;
				}
				
				LinearEquation linearEq1 = new LinearEquation(x1Coefficient, y1Coefficient, c1Coefficient);
				LinearEquation linearEq2 = new LinearEquation(x2Coefficient, y2Coefficient, c2Coefficient);
				
				SolveLinearEquations solveEquation = new SolveLinearEquations();
				solveEquation.setLinearEq1(linearEq1);
				solveEquation.setLinearEq2(linearEq2);
				
				PointOfIntersection pointOfIntersection = solveEquation.solveEquations();
				//Toast.makeText(v.getContext(), pointOfIntersection.toString(), Toast.LENGTH_LONG).show();
				
				intersectionText.setText(getString(R.string.point_intersection, pointOfIntersection.getxValue(), pointOfIntersection.getyValue()));
				intersectionText.setVisibility(TextView.VISIBLE);
				
			}
		});
		
		return rootView;
	}
	
}
