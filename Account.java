/*Mahnaz Ameer*/
/*roll no : 63 */

import java.util.*;
import java.text.*;


abstract class Account{
    
       private final java.util.Date account_creation_date;
	   private java.util.Date account_closing_date;

	   private final String accName;
	   private final String accNo;
	   private double accBal = 0.0;

	   private static int no_of_active_accounts = 0;
	   private Boolean isactive = false;

	
	     protected Account(String acName, String acNumber, Date date, double rupees){

	     	     isactive = true;
	     	     no_of_active_accounts++;
		   
		         accName = acName;
		         accNo = acNumber;
		         account_creation_date = date;
		         accBal = rupees;
		 
		   
	}
	    protected void closeAccount(){
		      if( isactive ){

		      	no_of_active_accounts--;
		        isactive = false;
		        
		        account_closing_date = Calendar.getInstance().getTime();
		
		}
	        }


    public Boolean isAccountactive(){
		return isactive;
	}

	public String getAcName(){
		String acName = "";
		acName += accName;
		return acName;
	}

	public String getAcNumber(){
		String acNumber = "";
		acNumber += accNo;
		return acNumber;
	}

	public abstract String getAccountInfo();

	public String getAcCreationDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String acCreationDate = dateFormat.format(account_creation_date);
		return acCreationDate;
	}

	public double getaccBal(){
		return accBal;
	}

	public int getNumOfExistingAccounts(){
		return no_of_active_accounts;
	}

	public String getAcClosingDate(){
		if( isactive ){
			return "Account still active";
		} else{
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String acClosingDate = dateFormat.format(account_closing_date);
			return acClosingDate;
		}
	}


	public void depositToAccount(double rupeesToDeposit){
		if( isactive ){
			accBal += rupeesToDeposit;
		}
	}

	public Boolean withdrawFromAccount(double rupeesToWithdraw){
		if( isactive ){
			accBal -= rupeesToWithdraw;
			return true;
		} else{
			return false;
		}
		
	}

	public Boolean transferTo(Account receiverAccount, double rupees){
		return true;
	}
	
}