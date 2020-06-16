package spring.testing.server.bills;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class LineItem {

  private String description;
  private Money itemPrice;
  private BigDecimal itemAmount;
  
  public LineItem(String description, Money itemPrice, BigDecimal itemAmount) {
    super();
    this.description = description;
    this.itemPrice = itemPrice;
    this.itemAmount = itemAmount;
  }
  
  public Money getTotalAmount() {
    return new Money(itemPrice.getAmount().multiply(itemAmount,MoneyConstants.ROUND_RULES),
        itemPrice.getCurrency());
  }

  public String getDescription() {
    return description;
  }

  public Money getItemPrice() {
    return itemPrice;
  }

  public BigDecimal getItemAmount() {
    return itemAmount;
  }
  
  @Override
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append(description);
	  sb.append(":");
	  sb.append(itemPrice.getCurrency());
	  sb.append(":");
	  sb.append(sb.append(NumberFormat.getCurrencyInstance()
			  .format(itemAmount.setScale(2))));
	  sb.append(";");
	  return sb.toString();
  }
}
