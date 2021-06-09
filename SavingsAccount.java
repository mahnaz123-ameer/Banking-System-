import java.util.*;
import java.text.*;

class SavingsAccount extends Account {

	private static int numOfActiveSavingsAcs = 0;
	private static double minimumBalance = 100.0;
	private static double interestRate = 2.0;
	
	private Date lastUpdated;

	

	public SavingsAccount( String acName, String acNumber, Date date, double amount ){
		super(acName, acNumber, date, amount);
		lastUpdated = date;
		numOfActiveSavingsAcs++;
	}

	public void closeAccount(){
		if( super.isAccountactive() ){
			numOfActiveSavingsAcs--;
			super.closeAccount();
		}
	}


	public Boolean withdrawFromAccount(double amountToWithdraw){
		double availableBalance = super.getaccBal();

		if( availableBalance - amountToWithdraw >= minimumBalance ){
			return super.withdrawFromAccount(amountToWithdraw);
		} else {
			return false;
		}
	}

	public Boolean transferTo(SavingsAccount receiverAccount, double amount) {
		
		if( this.withdrawFromAccount(amount) ) {
			receiverAccount.depositToAccount(amount);
			return true;
		} else {
			return false;
		}

	}

	public Boolean transferTo(CurrentAccount receiverAccount, double amount) {
		
		if( this.withdrawFromAccount(amount) ) {
			
			System.out.println(receiverAccount.getAccountInfo());
			receiverAccount.depositToAccount(amount);
			return true;
		} else {
			return false;
		}

	}

	public void updateBalance(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date today = Calendar.getInstance().getTime();

		

		String todaysDate = dateFormat.format(today); 
        int todayYear = 1000 * (todaysDate.charAt(6) - '0') + 100 * (todaysDate.charAt(7) - '0') + 10 * (todaysDate.charAt(8) - '0') + (todaysDate.charAt(9) - '0');
		int todayDay = 10 * (todaysDate.charAt(0) - '0') + (todaysDate.charAt(1) - '0'); 
		int todayMonth = 10 * (todaysDate.charAt(3) - '0') + (todaysDate.charAt(4) - '0');
		
       

        String lastUpdateDate = dateFormat.format(lastUpdated);
        int lastYear = 1000 * (lastUpdateDate.charAt(6) - '0') + 100 * (lastUpdateDate.charAt(7) - '0') + 10 * (lastUpdateDate.charAt(8) - '0') + (lastUpdateDate.charAt(9) - '0');
		int lastDay = 10 * (lastUpdateDate.charAt(0) - '0') + (lastUpdateDate.charAt(1) - '0');
		int lastMonth = 10 * (lastUpdateDate.charAt(3) - '0') + (lastUpdateDate.charAt(4) - '0');
		

		if(todayYear > lastYear){
			int yearDiff = todayYear - lastYear;
			if(yearDiff > 0){
				if(todayMonth >= lastMonth && todayDay >= lastDay){
					
					applyInterest(yearDiff);
					lastUpdated = today;

				} else {
					if(yearDiff > 1){
						if(todayMonth < lastMonth || 
						  (todayMonth == lastMonth && todayDay < lastDay)){
							yearDiff--;
						}

						applyInterest(yearDiff);
						lastUpdated = today;
		}
	    }
        }
		}
	    }


	private void applyInterest(int yearDiff){
		double amountToDeposit = 0.0;

		for(int yearCount = 1; yearCount <= yearDiff; yearCount++){
			amountToDeposit = getAccountBalance() * interestRate / 100.0;
			depositToAccount(amountToDeposit);
		}
	}






	public static void setMinBalance(double min){
		if( min > 0.0 ){
			minimumBalance = min;
		}
	}

	public static void setInterestRate(double rate){
		if(rate > 0.0){
			interestRate = rate;
		}
	}



	public static int getNumOfActiveSavingsAc(){
		return numOfActiveSavingsAcs;
	}

	public String getAccountInfo(){
		String accountInfo = "Account name : " + getAcName() + "\nBalance : " + getAccountBalance() + "\nSavings account number : " + getAcNumber() + "\nAccount opening date : " + getAcCreationDate() + "\n";
		if(!isAccountactive()){
			accountInfo += "Account closing date :" + getAcClosingDate() + "\n";
		}
		return accountInfo;
	}

	public double getAccountBalance(){
		updateBalance();
		return super.getaccBal();
	}
	public double getInterestRate(){
		return interestRate;
	}

}