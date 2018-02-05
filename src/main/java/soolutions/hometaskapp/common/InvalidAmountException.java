package soolutions.hometaskapp.common;

public class InvalidAmountException extends RuntimeException {
  public InvalidAmountException(double value) {
     super("Invalid amount value: " + value);
  }
}
