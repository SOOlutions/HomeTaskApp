package soolutions.hometaskapp.common;

public class CurrencyMismatchException extends RuntimeException {
  public CurrencyMismatchException(String message) {
     super(message);
  }
}
