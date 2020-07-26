public class User//data base of users
{
    private int account_id;
    private String name;
	private int pin;
	private long balance;

	public User(int account_id, String name, int pin, long balance) 
	{
        this.account_id = account_id;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public long getBalance() {
        return balance;
    }
}