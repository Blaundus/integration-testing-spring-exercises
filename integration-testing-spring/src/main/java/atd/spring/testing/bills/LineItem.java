package atd.spring.testing.bills;

import java.math.BigDecimal;

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
  
}
