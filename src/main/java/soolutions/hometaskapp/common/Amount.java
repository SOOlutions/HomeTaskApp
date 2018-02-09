package soolutions.hometaskapp.common;

/**
 * @todo #2:15m/DEV Add class description
 */
public final class Amount {
  private final double value;
  private final String currency;

  public Amount(double value, String currency) {
    this.value = value;
    this.currency = currency;
  }

  public Amount zero() {
      return new Amount(0, this.currency);
  }

  public Amount add(Amount other) {
    if (!currency.equals(other.currency)) {
      throw new CurrencyMismatchException(
          "Cannot apply different currencies " + currency + " : " + other.currency);
    }
    return new Amount(this.value + other.value, currency);
  }

  public Amount withdraw(Amount other) {
      ensureSameCurrency(this, other);
      return new Amount(this.value - other.value, currency);
  }

  public boolean matchesCurrency(String currency) {
      return this.currency.equalsIgnoreCase(currency);
  }

  public int compareTo(Amount other) {
      ensureSameCurrency(this, other);
      return Double.compare(this.value, other.value);
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

  private static void ensureSameCurrency(Amount a, Amount b) {
      if (!a.currency.equals(b.currency)) {
          throw new CurrencyMismatchException(
                  "Cannot apply different currencies " + a.currency + " : " + b.currency);
      }
  }
}
