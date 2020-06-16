package spring.testing.server.exceptions;

public class UnknownCurrencyException extends RuntimeException {

  private static final long serialVersionUID = 3L;

  public UnknownCurrencyException(String currency) {
    super("Unknown currency "+currency);
  }

}
