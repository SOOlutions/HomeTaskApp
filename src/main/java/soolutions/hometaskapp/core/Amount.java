package soolutions.hometaskapp.core;

public final class Amount {
  private final double value;
  private final String currency;

  public Amount(double value, String currency) {
    if (Double.MAX_VALUE < value || value < 0) {
      throw new InvalidAmountException(value);
    }

    this.value = value;
    this.currency = currency;
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
