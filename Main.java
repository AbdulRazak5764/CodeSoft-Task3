public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456789", 1000.0);
        ATM atm = new ATM(account);
        atm.start();
    }
}
