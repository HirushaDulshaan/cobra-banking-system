package com.hirusha.bcd.client;

import BankingApp.Account;
import BankingApp.AccountHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class ATMClient {
    public static void main(String[] args) {
        ORB orb = ORB.init(args, null);

        try {
            org.omg.CORBA.Object objectRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objectRef);

            Account account = AccountHelper.narrow(ncRef.resolve_str("Bank"));

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your account number:");
            String accNo = sc.nextLine();

            while (true) {
                System.out.println("Choose an option: 1. Check Balance 2. Deposit 3. Withdraw 4. Exit");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        double balance = account.getBalance(accNo);
                        System.out.println("Current Balance: " + balance);
                        break;
                    case 2:
                        System.out.println("Enter amount to deposit:");
                        double depositAmount = Double.parseDouble(sc.nextLine());
                        account.deposit(accNo, depositAmount);
                        System.out.println("Amount deposited successfully.");
                        break;
                    case 3:
                        System.out.println("Enter amount to withdraw:");
                        double withdrawAmount = Double.parseDouble(sc.nextLine());
                        try {
                            account.withdraw(accNo, withdrawAmount);
                            System.out.println("Amount withdrawn successfully.");
                        } catch (BankingApp.InsufficientBalance e) {
                            System.out.println("Insufficient balance. Please try again.");
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        sc.close(); // ✅ moved here
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}