package soolutions.hometaskapp.core;

public class CurrencyMismatchException extends RuntimeException {
  public CurrencyMismatchException(String message) {
     super(message);
  }
}
