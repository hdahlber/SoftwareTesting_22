/* DISCLAIMER: THIS CODE IS A MODIFIED VERSION OF THE ONE PROVIDED AT
 * https://code.google.com/a/eclipselabs.org/p/vending-machine/ under
 * Eclipse Public Licence 1.0
 */

package vending_machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachine {

	HashMap<String, Integer> inventory = new HashMap<String, Integer>(3);
	
	/**
	* Coin types and prices
	* TC = 20 cent, FC = 50 cent, OE = 1 euro, TE = 2 Euro
	*/
	private enum Coin {
    	TC(0.2), FC(0.5), OE(1.0), TE(2.0);

    	/**
    	 * @param value  double
    	 * Method to return Enumerator double value
    	 */
    	Coin(double value) {
    		this.value = value;
    	}

        private final double value;
        }
    
   
	/**
	* Drink types and prices
	*/
    private enum Drink {
    	COLA(2.5), FANTA(2.0), COFFEE(5.0);

    	/**
    	 * @param value  double
    	 * Method to return Enumerator double value
    	 */
    	Drink(double value) {
    		this.price = value;
    	}

        private final double price;
        }

    /**
    * New line character form OS properties.
    */
	private final String nlc = System.getProperty("line.separator");

    /**
     *  the total amount paid during a drink dispensing session.
     */
    private double amountPaid;

    /**
    * This method is responsible for creation of a drink
    * chamber and loading it with default number of drinks
    */
    public VendingMachine(int defaultItems) {
    	this.amountPaid = 0;
    	
    	inventory.put("COLA", defaultItems);
		inventory.put("COFFEE", defaultItems);
		inventory.put("FANTA", defaultItems);
		
        System.out.println("Vending machine is up and running!");
    }

    /**
     * Displays Menu on console.
     */
    public void displayMenu() {
    	StringBuilder sb = new StringBuilder();
		sb.append("************************************************");
		sb.append(nlc);
		sb.append("Please select your drink from the menu:");
		sb.append(nlc);
		sb.append("\t" + Drink.COLA + "\t" + Drink.valueOf("COLA").price + " euro" + "\t available: " + inventory.get("COLA") + " can");
		sb.append(nlc);
		sb.append("\t" + Drink.FANTA + "\t" + Drink.valueOf("FANTA").price + " euro" + "\t available: " +  inventory.get("FANTA") + " can");
		sb.append(nlc);
		sb.append("\t" + Drink.COFFEE + "\t" + Drink.valueOf("COFFEE").price + " euro" + "\t available: " + inventory.get("COFFEE") + " can");
		sb.append(nlc);
		sb.append("QUIT to exit.");sb.append(nlc); sb.append(nlc);
		sb.append("Select Drink: ");//sb.append(nlc);

		System.out.println(sb.toString());
        }


    /**
     * @param change double  value of returning amount to show in coins.
     * @return String message printed on console for returning the coins.
     */
    public String displayReturningCoins(double change) {
    	// displays the change

    	int[] coins = calculateReturningCoins(change);

    	StringBuilder sb = new StringBuilder();

    	if (coins[0] > 0) 
    		sb.append("\t" + coins[0] + " x 2 Euro"); sb.append(nlc);
    	if (coins[1] > 0) 
    		sb.append("\t" + coins[1] + " x 1 Euro"); sb.append(nlc);
    	if (coins[2] > 0) 
    		sb.append("\t" + coins[2] + " x 50 Cent"); sb.append(nlc);
    	if (coins[3] > 0) 
    		sb.append("\t" + coins[3] + " x 20 Cent"); sb.append(nlc);

        return sb.toString();

    }
    
    /**
     * calculates the change in coins
     * assumes unlimited coins available
     * @param change Double the amount to returns in terms of Coins
     * @return int[]  list of coins to be returned corresponding to TE OE FC TC
     */
    public int[] calculateReturningCoins(double change) {

    		int[] coinList = new int[4];
    		//number of coins corresponding to TE OE FC TC, respectively

            if (change / Coin.TE.value >= 1) {
                    int twoEuro = (int) (change / Coin.TE.value);
                    change = change - (twoEuro * Coin.TE.value);
                    coinList[0] = (int) twoEuro;
            }
            if (change / Coin.OE.value >= 1) {
                    int oneEuro = (int) (change / Coin.OE.value);
                    change = change - (oneEuro * Coin.OE.value);
                    coinList[1] = (int) oneEuro;
            }
            if (change / Coin.FC.value >= 1) {
            		int fiftyCent = (int) (change / Coin.FC.value);
                    change = change - (fiftyCent * Coin.FC.value);
                    coinList[2] = (int) fiftyCent;
            }
            if (change / Coin.TC.value >= 1) {
            	    int twentyCent = (int) (change / Coin.TC.value);
                    change = change - (twentyCent * Coin.TC.value);
                    coinList[3] = (int) twentyCent;
            }
        return coinList;
    }

    /**
     * @param price double price of the drink
     * @param insertedCoins String of coins. insertedCoins is tokenized using spaces, e.g., OE OE OE for 3 Euro.
     * @return BigDecimal the negative difference between inserted coins and price.
     * calculates the change to be returned based on user input
     * eg OE OE OE for 3 Euros
     */
    public BigDecimal calculateChange(final double price, final String insertedCoins) {

        StringTokenizer st = new StringTokenizer(insertedCoins);
  
        while (st.hasMoreElements()) {
                String coin = st.nextToken();

                if (coin.equals("TC")) {
                	amountPaid += Coin.TC.value;
                } else if (coin.equals("FC")) {
                		amountPaid += Coin.FC.value;
            		} else if (coin.equals("OE")) {
            			amountPaid += Coin.OE.value;
                	} else if (coin.equals("TE")) {
                		amountPaid += Coin.TE.value;
                	} else {
                	System.out.printf("Wrong coin type!" );
                }
        }
        
        return BigDecimal.valueOf(amountPaid - price).setScale(2, RoundingMode.FLOOR);
    }

    /**
     * @param selection String drink selected
     * @param coins String coins selected
     * @return - String
     * process the list of coins to pay for a given drink
     * allowed coins: TC: 20 cent, FC: 50 cent, OE: 1 euro, TE: 2 Euro
     */
    public String processSelection(String drink, final String coin) {
       
        BigDecimal change;
        
        //used for comparing the change to 0
        BigDecimal big0 = new BigDecimal(0);
        
        //if CANCEL
        if (coin.contentEquals("CANCEL")) {
    		this.amountPaid = 0;
    		return "CANCELLED";
    	}
        
        // validate drink
        if(inventory.get(drink)<1) {
        	return "Not enough cans of " + drink;
        }
        
        //return initial debt
        if (coin=="empty")
        	return " " + Drink.valueOf(drink).price;
        	
        if (drink.equals("COLA") || drink.equals("COFFEE") || drink.equals("FANTA")) {
        	// a new drink was received. 
        	
        	if (coin.contentEquals("OE") || coin.contentEquals("TE") ||
    				coin.contentEquals("TC") || coin.contentEquals("FC")) {
        		
        		change = calculateChange(Drink.valueOf(drink).price, coin);
        		
    			if (change.compareTo(big0) < 0) {     			
	        		//more coins needed
	        		return "You still have to pay " + (change.abs().toString());
	        	} else {
    				String temp = nlc;
    				if (change.compareTo(big0) > 0) {
    					temp = "Your change is: " + change + " EURO" + displayReturningCoins(change.doubleValue());
    					}
    				this.amountPaid = 0;
    				inventory.put(drink, inventory.get(drink)-1);
					return " Drink delivered! " + temp;
	        		}
	        	} else {
        			return "Invalid coin";
        		}
        	}
        	return "Invalid drink";
        }
                  
        
    /**
     *  selects a drink.
     *  @return EXIT: String
     */
    public void ui() {

    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	
    	Boolean state= false; // false main menu, true coin menu
    	
    	String coin = null; // store the coin ID
    	String drinkSelection = null; // drink selection
    	String result = null; // result of processing
    	
    	while (true) {
    		// drinks menu
    		
    		if (state == false) {
    		
	    		this.displayMenu();
	    			
	    		try {
	                drinkSelection = input.readLine();
	    		} catch (IOException e) {
	                System.out.println("Error in reading input.");
	                System.exit(1);
	    		}
	
	    		//check if valid options
	    		if (drinkSelection.equals("QUIT") || drinkSelection.equals("COLA") || 
	    				drinkSelection.equals("COFFEE") || drinkSelection.equals("FANTA")) {
	    			
	    			if (!drinkSelection.equals("QUIT")) {
	    				System.out.printf("You have selected " + drinkSelection + nlc);
	    				result = processSelection(drinkSelection, "empty");
	    				
	    				if (result.contains("Not enough")) {
	    					System.out.printf(result + nlc + nlc);
	    				} else {
	    					state = true;
	    					System.out.printf("Left to pay: " + result + nlc);
	    				}
	    			} else {
	    				 System.out.println("Exiting...BYE!");
	    				 System.exit(1);
	        		 }
	    		 } else {
	    			 	System.out.println("Wrong input try again!!!" + nlc + nlc);
	    		 	}	
    		} else {
    			// we are in the coin menu, process coins
    			
    			//reading coin input
	    		try {
	    			coin = input.readLine();
	    			
	    				System.out.println("Received coin: " + coin );
	    				
	    				result = processSelection(drinkSelection, coin);
	    				
	    				if (result.contains("delivered")) {
	    					System.out.println(nlc + result + nlc); 
	    					state=false;
	    				} else if (result.contains("CANCELLED")) {
	    					System.out.println("returning coins.. Done." + nlc); 
	    					state = false;	  
	    				} else
	    					System.out.println( result + nlc); 
	    				
	    			} catch (IOException e) {
	    			System.out.println("Error in reading input.");
	    			System.exit(1);
	    		}
	    	}
   		}
    }

        //main should not be called for testing purposes.
        public static void main(final String[] args) {
                VendingMachine vm = new VendingMachine(2);                          
                vm.ui();
         }
}
