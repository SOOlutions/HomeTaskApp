package soolutions.hometaskapp.core;

public class InvalidAmountException extends RuntimeException {
  public InvalidAmountException(double value) {
     super("Invalid amount value: " + value);
  }
}
