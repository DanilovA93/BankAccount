class BankAccount {
    private int balance = 100;

    public int getBalance() {
        return balance;
    }

    public void withDraw(int amount) {
        balance = balance - amount;
    }
}

public class RyanAndMonicaJob implements Runnable {

    private BankAccount account = new BankAccount();

    public static void main (String[] args) {
        RyanAndMonicaJob theJob = new RyanAndMonicaJob();
        Thread one = new Thread(theJob);
        Thread two = new Thread(theJob);
        one.setName ("Ryan");
        two.setName("Monica");
        one.start();
        two.start();
    }

    public void run() {
        for (int i = 0; i <10; i++) {
            makeWithDrawl(10);
            if (account.getBalance() < 0) {
                System.out.println("No many on your card");
            }
        }
    }

    private synchronized void makeWithDrawl(int amount) {
        if (account.getBalance() >= amount) {
            System.out.println(Thread.currentThread().getName() + " wants some money");
            try {
                System.out.println(Thread.currentThread().getName() + " go to sleep");
                Thread.sleep(500);
            } catch (InterruptedException ex) { ex.printStackTrace();}

            System.out.println(Thread.currentThread().getName()+ " awake");
            account.withDraw(amount);
            System.out.println(Thread.currentThread().getName() + " ended transaction");
        }
        else {
            System.out.println("Sorry, there is no money for " + Thread.currentThread().getName());
        }
    }
}
