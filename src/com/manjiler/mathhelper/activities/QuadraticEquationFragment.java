/**
 * 
 */
package com.manjiler.mathhelper.activities;

import java.util.ArrayList;

import com.manjiler.mathhelper.quadraticeq.QuadraticEquation;
import com.manjiler.mathhelper.quadraticeq.RootsOfQuadraticEquation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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
 * This fragment gives the UI for the quadratic equation tab. 
 *
 */
public class QuadraticEquationFragment extends Fragment {

	private EditText coefficientAEditText;
	private EditText coefficientBEditText;
	private EditText coefficientCEditText;
	private TextView signATextView;
	private TextView signBTextView;
	private TextView signCTextView;
	private TextView formulaTextView1;
	private TextView formulaTextView2;
	private TextView rootsTextView1;
	private TextView rootsTextView2;
	private TextView natureOfRootsTextView;
	private TextView factorized;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quadratic_equation, container, false);
        
        TextView xSquareText = (TextView) rootView.findViewById(R.id.textViewA);
        xSquareText.setText(Html.fromHtml("X<sup><small>2</small></sup>"));
        
        TextView xText = (TextView) rootView.findViewById(R.id.textViewB);
        xText.setText(Html.fromHtml("X"));
        
        coefficientAEditText = (EditText) rootView.findViewById(R.id.editTextA);
        coefficientBEditText = (EditText) rootView.findViewById(R.id.editTextB);
        coefficientCEditText = (EditText) rootView.findViewById(R.id.editTextC);
        
        signATextView = (TextView) rootView.findViewById(R.id.signATextView);
        signBTextView = (TextView) rootView.findViewById(R.id.signBTextView);
        signCTextView = (TextView) rootView.findViewById(R.id.signCTextView);
        
        formulaTextView1 = (TextView) rootView.findViewById(R.id.quadraticEquationFormula1);
        formulaTextView2 = (TextView) rootView.findViewById(R.id.quadraticEquationFormula2);
        rootsTextView1 = (TextView) rootView.findViewById(R.id.quadraticRoot1);
        rootsTextView2 = (TextView) rootView.findViewById(R.id.quadraticRoot2);
        natureOfRootsTextView = (TextView) rootView.findViewById(R.id.natureOfRoots);
        factorized = (TextView) rootView.findViewById(R.id.factorized);
        
        Button solveEquation = (Button) rootView.findViewById(R.id.solveQuadraticEquation);
        
        coefficientAEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!coefficientAEditText.getText().toString().equalsIgnoreCase("")) {
					if(Integer.parseInt(coefficientAEditText.getText().toString()) > 0) {
						signATextView.setText("+");
					} else {
						signATextView.setText("");
					}
				}
			}
		});
        
        coefficientBEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!coefficientBEditText.getText().toString().equalsIgnoreCase("")) {
					if(Integer.parseInt(coefficientBEditText.getText().toString()) > 0) {
						signBTextView.setText("+");
					} else {
						signBTextView.setText("");
					}
				}
			}
		});
        
        coefficientCEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!coefficientCEditText.getText().toString().equalsIgnoreCase("")) {
					if(Integer.parseInt(coefficientCEditText.getText().toString()) > 0) {
						signCTextView.setText("+");
					} else {
						signCTextView.setText("");
					}
				}
			}
		});
        
        solveEquation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int coefficientA = Integer.parseInt(coefficientAEditText.getText().toString());
				int coefficientB = Integer.parseInt(coefficientBEditText.getText().toString());
				int coefficientC = Integer.parseInt(coefficientCEditText.getText().toString());
				
				QuadraticEquation quadEq = new QuadraticEquation(coefficientA, coefficientB, coefficientC);
				if(!quadEq.isValidQuadraticEquation()) {
					Toast.makeText(v.getContext(), "Invalid quadratic equation!!!", Toast.LENGTH_LONG).show();
					return;
				}
				//RootsOfQuadraticEquation roots = quadEq.getRootsOfEquationByFormula();
				RootsOfQuadraticEquation roots = quadEq.getRootsOfEquationByFactorization();
				//Toast.makeText(v.getContext(), "First Root = " + roots.getFirstRoot() + " ; Second Root = " + roots.getSecondRoot(), Toast.LENGTH_LONG).show();
				/*
				formulaTextView1.setText(getString(R.string.quadraticEquationFormula));
				formulaTextView2.setText(QuadraticEquation.FORMULA);
				
				rootsTextView1.setText(getString(R.string.roots, roots.getFirstRoot()));
				rootsTextView2.setText(getString(R.string.roots, roots.getSecondRoot()));
				natureOfRootsTextView.setText(roots.getDiscriminant().getNatureOfRoots());
				
				formulaTextView1.setVisibility(TextView.VISIBLE);
				formulaTextView2.setVisibility(TextView.VISIBLE);
				rootsTextView1.setVisibility(TextView.VISIBLE);
				rootsTextView2.setVisibility(TextView.VISIBLE);
				natureOfRootsTextView.setVisibility(TextView.VISIBLE);*/
				
				StringBuilder solution = new StringBuilder();
				
				for(String step : roots.getSteps()) {
					solution.append(step + "\n");					
				}
				
				if(roots.isSolvedByFactorization() == false) {
					solution.append(QuadraticEquation.FORMULA + "\n");
				}
				
				solution.append("\nFirst root = " + roots.getFirstRoot() + "\n\n");
				if(roots.getSecondRoot() != null) {
					solution.append("Second root = " + roots.getSecondRoot());
				}
				
				if(roots.isSolvedByFactorization() == false) {
					solution.append("\n\n" + roots.getDiscriminant().getNatureOfRoots());
				}
				
				formulaTextView1.setText(solution.toString());
				formulaTextView1.setVisibility(TextView.VISIBLE);				
			}
		});
        
        // Demonstration of a collection-browsing activity.
        /*
        rootView.findViewById(R.id.demo_collection_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), CollectionDemoActivity.class);
                        startActivity(intent);
                    }
                });
		*/
        // Demonstration of navigating to external activities.
        /*
        rootView.findViewById(R.id.demo_external_activity)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Create an intent that asks the user to pick a photo, but using
                        // FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
                        // the application from the device home screen does not return
                        // to the external activity.
                        Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
                        externalActivityIntent.setType("image/*");
                        externalActivityIntent.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        startActivity(externalActivityIntent);
                    }
                });
        */
        //((TextView)rootView.findViewById(R.id.superscriptText)).setText(Html.fromHtml("AX<sup><small>2</small></sup> + BX + C = 0"));

        return rootView;
    }

}
