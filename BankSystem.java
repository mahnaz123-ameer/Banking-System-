import java.util.*;
import java.text.*;

public class BankSystem{

	private CurrentAccount[] currentAccount = new CurrentAccount[200000];
	private SavingsAccount[] savingsAccount = new SavingsAccount[200000];

	private long lastCurrentAcNum = 100000000;
	private long lastSavingsAcNum = 500000000;

	private int currentAcCreated = 0;
	private int savingsAcCreated = 0;

	public BankSystem(){

	}


	public void printAvailableOptions(){
		System.out.println("--------Bank System--------");
		System.out.println("1 - Open new account");
		System.out.println("2 - Set savings account interest rate");
		System.out.println("3 - Transfer money");
		System.out.println("4 - Update account");
		System.out.println("5 - Deposit money to account");
		System.out.println("6 - Withdraw money from account");
		System.out.println("7 - Display number of existing accounts ");
		System.out.println("8 - Display account information ");
		System.out.println("9 - Delete an account");
		System.out.println("10 - Exit");
	}




    /*Method to access account*/
	private Account getAccount(String acNumber){
		/*1 - current account 2 - savings account*/

		long account = Long.parseLong(acNumber);

		account = account % 1000000;

		int number = (int) account;



		if( acNumber.charAt(0) == '1'){
			if(number < currentAcCreated)
			return currentAccount[number];
		} else if(acNumber.charAt(0) == '5'){
			if(number < savingsAcCreated)
			return savingsAccount[number];
		}
		return null;
		
	}



	/*Method for user to check whether input is valid*/
	public Boolean checkAccountNumValidity(String acNumber){
		int size = acNumber.length();

		long account = Long.parseLong(acNumber);

		account = account % 1000000;

		int number = (int) account;

		if(size!=9) return false;
		if(acNumber.charAt(0)!='1' && acNumber.charAt(0)!='5') return false;
		if(acNumber.charAt(0) == '1'){
			if(number >= currentAcCreated) return false;
		} else {
			if(number >= savingsAcCreated) return false;
		}
		return true;
	}

	public String openSavingsAccount(String name, Date date, double initialDeposit){
		String acNumber = "";
		acNumber += lastSavingsAcNum;
		savingsAccount[currentAcCreated] = new SavingsAccount(name, acNumber, date, initialDeposit);
		savingsAcCreated++;
		lastSavingsAcNum++;
		return acNumber;
	}

	public String openCurrentAccount(String name, Date date, double initialDeposit){
		String acNumber = "";
		acNumber += lastCurrentAcNum;
		currentAccount[currentAcCreated] = new CurrentAccount(name, acNumber, date, initialDeposit);
		currentAcCreated++;
		lastCurrentAcNum++;
		return acNumber;
	}

    //2
	public void setSavingsAccountInterestRate(double rate){
		SavingsAccount.setInterestRate(rate);
	}
    
    //3
	public Boolean transferMoney(String senderAcNum, String receiverAcNum, double amount){
		Account sender = getAccount(senderAcNum);
		Account receiver = getAccount(receiverAcNum);

		Boolean withdrawalSuccessful = false;

		if(sender.transferTo(receiver , amount)){
			withdrawalSuccessful = true;
		}

		return withdrawalSuccessful;
	}

	//4
	public void updateAccount(String acNumber){
		if( checkAccountNumValidity(acNumber) ){
			SavingsAccount forUpdate = (SavingsAccount) getAccount(acNumber);
			forUpdate.updateBalance();
		}
	}


	//5
	public void depositMoneyToAc(String acNumber, double amount){
		Account depositTo = getAccount(acNumber);
		depositTo.depositToAccount(amount);
	}

  
  //6
	public Boolean withdrawMoneyFromAc(String acNumber, double amount){
		Account withdrawFrom = getAccount(acNumber);

		Boolean withdrawalSuccessful = false;

		if( withdrawFrom.withdrawFromAccount(amount) ){
			withdrawalSuccessful = true;
		}

		return withdrawalSuccessful;
	}
  
  //7
	public String displayNumberOfAccounts(){
		return "Savings Account : " + SavingsAccount.getNumOfActiveSavingsAc() + "\nCurrent Account : " + CurrentAccount.getNumOfActiveCurrentAcs() + "\nTotal : " + (CurrentAccount.getNumOfActiveCurrentAcs()+SavingsAccount.getNumOfActiveSavingsAc());
	}


  	//8
	public String displayAccountInfo(String acNumber){
		Account forDisplay = getAccount(acNumber);
		return forDisplay.getAccountInfo();
	}

	//9
	public void deleteAccount(String acNumber){
		Account accountToDelete = getAccount(acNumber);
		accountToDelete.closeAccount();
	}





	

}