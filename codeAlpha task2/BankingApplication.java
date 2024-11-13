import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: RS" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: RS" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }
}

class Bank {
    private HashMap<String, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountId) {
        if (!accounts.containsKey(accountId)) {
            accounts.put(accountId, new BankAccount(accountId));
            System.out.println("Account created successfully with ID: " + accountId);
        } else {
            System.out.println("Account ID already exists.");
        }
    }

    public BankAccount getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("Accounts in the bank:");
            for (String accountId : accounts.keySet()) {
                System.out.println("Account ID: " + accountId + ", Balance: RS" + accounts.get(accountId).getBalance());
            }
        }
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\nBanking Application");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice = 0;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter new account ID: ");
                    String newAccountId = scanner.nextLine();
                    bank.createAccount(newAccountId);
                    break;
                case 2:
                    System.out.print("Enter account ID: ");
                    String depositId = scanner.nextLine();
                    BankAccount depositAccount = bank.getAccount(depositId);
                    if (depositAccount != null) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = 0;
                        try {
                            depositAmount = Double.parseDouble(scanner.nextLine());
                            depositAccount.deposit(depositAmount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid deposit amount. Please enter a valid number.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account ID: ");
                    String withdrawId = scanner.nextLine();
                    BankAccount withdrawAccount = bank.getAccount(withdrawId);
                    if (withdrawAccount != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = 0;
                        try {
                            withdrawAmount = Double.parseDouble(scanner.nextLine());
                            withdrawAccount.withdraw(withdrawAmount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid withdrawal amount. Please enter a valid number.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account ID: ");
                    String balanceId = scanner.nextLine();
                    BankAccount balanceAccount = bank.getAccount(balanceId);
                    if (balanceAccount != null) {
                        System.out.printf("Current balance for account %s: RS%.2f\n", balanceId, balanceAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    bank.displayAllAccounts();
                    break;
                case 6:
                    System.out.println("Exiting the application. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}
