/*Mahnaz Ameer*/
/*roll no : 63 */


import java.util.*;
import java.text.*;
   

  
	class CurrentAccount extends Account {
	
	private static int numOfActiveCurrentAcs = 0;

	public CurrentAccount( String acName, String acNumber, Date date, double rupees ){
		super(acName, acNumber, date, rupees);
		numOfActiveCurrentAcs++;
	}

	public void closeAccount(){
		if( isAccountactive() ){
			numOfActiveCurrentAcs--;
			super.closeAccount();
		}
	}




	/*Transaction methods*/
	public Boolean withdrawFromAccount(double rupeesToWithdraw){
		double availableBalance = super.getaccBal();

		if( availableBalance - rupeesToWithdraw >= 0.0 ){
			return super.withdrawFromAccount(rupeesToWithdraw);
		} else {
			return false;
		}
	}

	public Boolean transferTo(CurrentAccount receiverAccount, double rupees) {
		
		if( this.withdrawFromAccount(rupees) ) {
			
			System.out.println(receiverAccount.getAccountInfo());
			receiverAccount.depositToAccount(rupees);
			return true;
		} else {
			return false;
		}

	}

	public Boolean transferTo(SavingsAccount receiverAccount, double rupees) {
		
		if( this.withdrawFromAccount(rupees) ) {
			
			System.out.println(receiverAccount.getAccountInfo());
			receiverAccount.depositToAccount(rupees);
			return true;
		} else {
			return false;
		}

	}




	public static int getNumOfActiveCurrentAcs(){
		return numOfActiveCurrentAcs;
	}

	public String getAccountInfo(){
		String accountInfo = "Account name : " + getAcName() + "\nCurrent account number : " + getAcNumber() + "\nBalance : " + getaccBal() + "\nAccount opening date : " + getAcCreationDate() + "\n";
		if(!isAccountactive()){
			accountInfo += "Account closing date : " + getAcClosingDate() + "\n";
		}
		return accountInfo;
	}

}