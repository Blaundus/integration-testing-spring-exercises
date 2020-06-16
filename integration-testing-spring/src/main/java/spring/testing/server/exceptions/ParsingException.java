package spring.testing.server.exceptions;

public class ParsingException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ParsingException(String msg) {
    super(msg);
  }

}
