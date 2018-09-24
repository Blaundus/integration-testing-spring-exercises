package atd.spring.testing.unittests.compliance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import atd.spring.testing.bills.Bill;
import atd.spring.testing.bills.LineItem;
import atd.spring.testing.compliance.LineItemTrafficRule;
import atd.spring.testing.compliance.SnowTrafficLog;
import atd.spring.testing.compliance.TrafficRegulatorImpl;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrafficRegulatorImplTest {
  @Mock private SnowTrafficLog logger;
  @Mock private LineItemTrafficRule alwaysLog;
  @Mock private LineItemTrafficRule neverLog;
  @Mock private Bill bill;
  @Mock private LineItem item;
  private List<LineItemTrafficRule> rules = new ArrayList<LineItemTrafficRule>();
  
  @Before
  public void setUp() {
    when(alwaysLog.shouldLog(any(LineItem.class))).thenReturn(true);
    when(neverLog.shouldLog(any(LineItem.class))).thenReturn(false);
  }
  
  //TODO: Rewrite tests
  @Test
  public void testLogsWhenNeeded() {
    rules.add(alwaysLog);
    TrafficRegulatorImpl theUnit = new TrafficRegulatorImpl();
    theUnit.registerBill(bill);
    theUnit.registerLineItem(item);
    theUnit.apply();
    verify(logger,times(1)).log(bill);
  }

  @Test
  public void testDoesNotLogsWhenNotNeeded() {
    rules.add(neverLog);
    TrafficRegulatorImpl theUnit = new TrafficRegulatorImpl();
    theUnit.registerBill(bill);
    theUnit.registerLineItem(item);
    verify(logger,never()).log(bill);
  }

  @Test
  public void LogsWhenBillSetLate() {
    rules.add(alwaysLog);
    TrafficRegulatorImpl theUnit = new TrafficRegulatorImpl();
    theUnit.registerLineItem(item);
    theUnit.registerBill(bill);
    verify(logger,times(1)).log(bill);
  }

  @Test
  public void testLogsOnlyOnce() {
    rules.add(alwaysLog);
    rules.add(alwaysLog);
    rules.add(alwaysLog);
    TrafficRegulatorImpl theUnit = new TrafficRegulatorImpl();
    theUnit.registerLineItem(item);
    theUnit.registerBill(bill);
    verify(logger,times(1)).log(bill);
  }
}
