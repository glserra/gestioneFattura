package it.exp75.gestionefatture.test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Locale.Category;

public class Test {

	public static void main(String[] args) {
		Locale italian = new Locale("it", "IT", "EURO");
		Locale.setDefault(italian);
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		System.out.println(nf.format(123));
		
		DecimalFormat nf2 = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ITALIAN);
		System.out.println(nf2.format(123));
/*
 * 
 */
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator(',');
		formatSymbols.setGroupingSeparator('.');

		DecimalFormat df = new DecimalFormat("####,####.##", formatSymbols);

		System.out.println("Formatted decimal: " + df.format(4567));
/*
 * 		
 */
		double d1 = 123456.78;
		double d2 = 567890;
		// self commenting issue, the code is easier to understand
		Locale fmtLocale = Locale.getDefault(Category.FORMAT);
		NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		System.out.println(formatter.format(d1));
		System.out.println(formatter.format(d2));
		System.out.println(fmtLocale.toLanguageTag());
	}

}
