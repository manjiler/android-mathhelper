/**
 * 
 */
package com.manjiler.mathhelper.common;

import android.text.Html;

/**
 * @author Manoj Srivatsav
 *
 */
public class StringUtils {

	public static String getXSquare() {
		return Html.fromHtml("X<sup><small>2</small></sup>").toString();
	}
	
	public static String getX() {
		return "X";
	}
}
