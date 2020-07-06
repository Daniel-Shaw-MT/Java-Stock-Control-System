import javax.swing.JOptionPane;

public class ChangeCalculator {
	static int onehundreds = 10000;
	static int fifties = 5000;
	static int twenty = 2000;
	static int ten = 1000;
	static int five = 500;
	static int two = 200;
	static int one = 100;
	static int fiftycents = 50;
	static int twentycents = 25;
	static int tencents = 10;
	static int fivecents = 5;
	static int onecent = 1;
	
	int[] changeCounter = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	int changetotal;
	public int[] Calculate(double Accumulator, double money) {
		 int price = (int) (Accumulator * 100);
		 int payed = (int) (money * 100);
		
		 int[] currencyVals = {onecent, fivecents, tencents, twentycents, fiftycents, one, two, five, ten, twenty, fifties, onehundreds};
		  

		
		if (payed == price) {
			JOptionPane.showMessageDialog(null, "Transaction Successful.");
		}
		if (payed > price) {
			// Calculate return amount.
			changetotal = payed - price;
			
		}
		if (price > payed) {
			// Calculate required amount.
			JOptionPane.showMessageDialog(null, "Not Enough Money!");
			
		}
		for(int i = 11; i >= 0; i--) {
			int remainder = changetotal % currencyVals[i]; //find the remainder
			
			if(remainder < changetotal) { //don't give out big bills
			
			changeCounter[i] = (changetotal - remainder) / currencyVals[i]; //Find out how many times this bill goes in and returns an even number
			
			changetotal = remainder; //Leave the rest for the next iteration of the loop
			
			}
			
		}
		return changeCounter;
	
		       	    		   
	}
	
}
