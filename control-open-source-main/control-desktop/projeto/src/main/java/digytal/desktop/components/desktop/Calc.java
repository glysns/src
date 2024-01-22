package digytal.desktop.components.desktop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calc {
	public static void main(String[] args) {
		Double q = 1.048;
		Double u = 27.99;
		
		BigDecimal bq = new BigDecimal(q);
		BigDecimal bu = new BigDecimal(u);
		
		BigDecimal bt = bq.multiply(bu).setScale(2,RoundingMode.HALF_EVEN);
		System.out.println(bt);
		
	}
}
