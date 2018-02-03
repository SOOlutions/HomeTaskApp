package soolutions.hometaskapp.core;

// TODO(vivek): Write docs for each public class
public final class Amount {
  private final double value;
  private final String currency;

// TODO(vivek): Check if there is an exisiting library for currency handling
  public Amount(double value, String currency) {
    if (Double.MAX_VALUE < value || value < 0) {
      throw new InvalidAmountException(value);
    }

    this.value = value;
    this.currency = currency;
  }

  public Amount add(Amount other) {
    if (!currency.equals(other.currency)) {
      throw new CurrencyMismatchException(
          "Cannot add different currencies " + currency + " : " + other.currency);
    }
    return new Amount(this.value + other.value, currency);
  }

  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Amount other = (Amount) obj;
    if (this.value != other.value || this.currency != other.currency) {
      return false;
    }
    return true;
  }

  public String toString() {
    return this.value + " (" + this.currency + ")";
  }
}
