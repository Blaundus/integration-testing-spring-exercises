package spring.testing.server.bills;

import java.math.MathContext;
import java.math.RoundingMode;

public class MoneyConstants {

  public static final MathContext ROUND_RULES = new MathContext(8,RoundingMode.HALF_UP);

}
