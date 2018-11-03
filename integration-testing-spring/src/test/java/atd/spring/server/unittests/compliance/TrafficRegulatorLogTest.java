package atd.spring.server.unittests.compliance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import atd.spring.server.bills.Bill;
import atd.spring.server.bills.LineItem;
import atd.spring.server.compliance.logging.BillLog;
import atd.spring.server.compliance.logging.TrafficRegistrar;
import atd.spring.server.compliance.rules.LoggableRule;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class TrafficRegulatorLogTest {
  @Mock private BillLog log;
  @Mock private LoggableRule alwaysLog;
  @Mock private LoggableRule neverLog;
  @Mock private Bill bill;
  @Mock private LineItem item;
  private List<LoggableRule> rules = new ArrayList<LoggableRule>();
private TrafficRegistrar theUnit;
  
  @Before
  public void setUp() {
    when(alwaysLog.shouldLog(any(LineItem.class))).thenReturn(true);
    when(neverLog.shouldLog(any(LineItem.class))).thenReturn(false);
  }
  
  //TODO: Rewrite tests
  @Test
  public void testLogsWhenNeeded() {
    rules.add(alwaysLog);
    theUnit = new TrafficRegistrar(rules,log);
    theUnit.documentBill(bill);
    theUnit.documentLineItem(item);
    theUnit.start();
    verify(log,times(1)).log(bill);
  }

  @Test
  public void testDoesNotLogsWhenNotNeeded() {
    rules.add(neverLog);
    theUnit = new TrafficRegistrar(rules,log);
    theUnit.documentBill(bill);
    theUnit.documentLineItem(item);
    verify(log,never()).log(bill);
  }

  @Test
  public void LogsWhenBillSetLate() {
    rules.add(alwaysLog);
    theUnit = new TrafficRegistrar(rules,log);
    theUnit.documentLineItem(item);
    theUnit.documentBill(bill);
    verify(log,times(1)).log(bill);
  }

  @Test
  public void testLogsOnlyOnce() {
    rules.add(alwaysLog);
    rules.add(alwaysLog);
    rules.add(alwaysLog);
    theUnit = new TrafficRegistrar(rules,log);
    theUnit.documentLineItem(item);
    theUnit.documentBill(bill);
    verify(log,times(1)).log(bill);
  }
}