package ATM_system;

// Class which holds the data of the current user
public class User {
    public long cardNumber;
    public short pin;
    public long balance;

    public User(long cardNumber, short pin, long balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }
}
