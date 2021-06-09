/*Mahnaz Ameer*/
/*roll no : 63 */

import java.util.*;
import java.text.*;

class Bank{

	public static BankSystem bank;

	public static Scanner input; 

	public static void main(String args[]){

		bank = new BankSystem();
		input = new Scanner(System.in);

		Boolean loop = true;

		while(loop){
			bank.printAvailableOptions();
			int operation = input.nextInt();
			input.nextLine();
			switch(operation) {
				case 1:
					Date date = Calendar.getInstance().getTime();

					System.out.println("Please Enter Your Name:");
					String name = input.nextLine();
                    

                    System.out.println("Select account type :");
					System.out.println("1 - Savings Account");
					System.out.println("2 - Current Account");
					
					int type = input.nextInt();
					if(type>2 || type<1){
						System.out.println("Not Valid");
						break;
					}

					System.out.println("Enter initial deposit amount:");
					double deposit = input.nextDouble();

					if(deposit <= 0.0){
						System.out.println("W R O N G ...please check it again"); 
						break;
					}

					if(type == 1){

						String acNum = bank.openSavingsAccount(name, date, deposit);
						System.out.println("Account number is : " + acNum);
						
					} else {
						
						String acNum = bank.openCurrentAccount(name, date, deposit);
						System.out.println("Account number is : " + acNum);
					}
					break;

        
                    case 2:
					System.out.println("Enter interest rate");
					double interest = input.nextDouble();
					if(interest <= 0.0){
						System.out.println("Interest must be greater than 0");
					} else {
						bank.setSavingsAccountInterestRate(interest);
					}
					break;
                case 3:
					System.out.println("Enter sender account nubmer:");
					String senderAc = input.nextLine();

					if(bank.checkAccountNumValidity(senderAc)){
						System.out.println("Enter receiver account number:");
						String receiverAc = input.nextLine();

						if(bank.checkAccountNumValidity(receiverAc)) {
							System.out.println("Enter amount to transfer");
							double transfer = input.nextDouble();

							if(transfer < 0.0) {
								System.out.println("Amount must be positive");
							} else {
								if(bank.transferMoney(senderAc, receiverAc, transfer)){
									System.out.println("Transfer successful");
								} else {
									System.out.println("Insufficient Balance For Transfer");
								}
							}

						} else {
							System.out.println("Invalid account number");
						}

					} else {
						System.out.println("Invalid account number");
					}
					break;
				


				case 4:
					System.out.println("Enter account number for updating");
					String accountNum = input.nextLine();

					if(bank.checkAccountNumValidity(accountNum)){
						bank.updateAccount(accountNum);
					} else {
						System.out.println("Invalid account number");
					}
					break;


                 case 5:
					System.out.println("Enter account number: ");
					String depositTo = input.nextLine();
					if(bank.checkAccountNumValidity(depositTo)){
						System.out.println("Enter amount to deposit");
						double toDeposit = input.nextDouble();
						if(toDeposit < 0.0){
							System.out.println("Invalid amount");
						} else {
							bank.depositMoneyToAc(depositTo, toDeposit);
						}
					} else {
						System.out.println("Invalid account number");
					}
					break;
				
               case 6:
					System.out.println("Enter account number for withdrawal");
					String withdrawFrom = input.nextLine();

					if(bank.checkAccountNumValidity(withdrawFrom)){
						System.out.println("Enter amount for withdrawal:");
							double amountWithdraw = input.nextDouble();
							if(amountWithdraw < 0.0){
								System.out.println("Amount cannot be negative");
							} else {
								if(bank.withdrawMoneyFromAc(withdrawFrom, amountWithdraw)) {
									System.out.println("Withdrawal successful");
								} else {
									System.out.println("Could not be withdrawn");
								}
							}
					} else {
						System.out.println("Invalid account number");
					}	
					break;	
				
				
               case 7:
					System.out.println(bank.displayNumberOfAccounts());
					break;


				
                   case 8:
					System.out.println("Enter account number for displaying information");
					String printingInfo = input.nextLine();

					if(bank.checkAccountNumValidity(printingInfo)){
						System.out.println(bank.displayAccountInfo(printingInfo));
					} else {
						System.out.println("Invalid account number");
					}
					break;
				
					case 9:
					System.out.println("Enter account number for closing");
					String acNum = input.nextLine();

					if(!bank.checkAccountNumValidity(acNum)){
						System.out.println("No account of this number found in existence");
					} else {
						bank.deleteAccount(acNum);
					}
					break;
				case 10: 
					loop = false;
					break;
			}
		}

	}
}