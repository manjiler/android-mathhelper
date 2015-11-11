/**
 * 
 */
package com.manjiler.mathhelper.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Manoj Srivatsav
 *
 */
public class AlgebraicExpressionFragment extends Fragment {

	private EditText input;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
	
		View rootView = inflater.inflate(R.layout.fragment_algebra_expressions, container, false);
		
		input = (EditText) rootView.findViewById(R.id.editText1);
		
		Button button = (Button) rootView.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String inputText = input.getText().toString();
				
				int number = Integer.parseInt(inputText);
				
				Toast.makeText(v.getContext(), "Entered Number is " + number, Toast.LENGTH_LONG).show();
			}
		});
		
		return rootView;
	}	
		
	
}
