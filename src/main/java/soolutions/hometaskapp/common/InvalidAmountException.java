package soolutions.hometaskapp.common;

public class InvalidAmountException extends RuntimeException {
  public InvalidAmountException(Amount amount) {
      super("Invalid amount value: " + amount.toString());
  }

  public InvalidAmountException(double value) {
     super("Invalid amount value: " + value);
  }
}
