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
public class TrafficRegistrarTests {
  @Mock private BillLog log;
  @Mock private LoggableRule alwaysLog;
  @Mock private LoggableRule neverLog;
  @Mock private Bill bill;
  @Mock private LineItem item;
  private List<LoggableRule> rules = new ArrayList<LoggableRule>();
  private TrafficRegistrar registrar;
  
  @Before
  public void setUp() {
    when(alwaysLog.shouldLog(any(LineItem.class))).thenReturn(true);
    when(neverLog.shouldLog(any(LineItem.class))).thenReturn(false);
  }
  
  @Test
  public void logsBillAndLineItems_WhenNeeded() {
    rules.add(alwaysLog);
    registrar = new TrafficRegistrar(rules,log);
    registrar.documentBill(bill);
    registrar.documentLineItem(item);
    registrar.start();
    verify(log,times(1)).log(bill);
  }

  @Test
  public void doesNotLogs_WhenNotNeeded() {
    rules.add(neverLog);
    registrar = new TrafficRegistrar(rules,log);
    registrar.documentBill(bill);
    registrar.documentLineItem(item);
    verify(log,never()).log(bill);
  }

  @Test
  public void logsWhenBillSetLate() {
    rules.add(alwaysLog);
    registrar = new TrafficRegistrar(rules,log);
    registrar.documentLineItem(item);
    registrar.documentBill(bill);
    verify(log,times(1)).log(bill);
  }

  @Test
  public void logsOnlyOnce_onMultipleAlwaysRules() {
    rules.add(alwaysLog);
    rules.add(alwaysLog);
    rules.add(alwaysLog);
    registrar = new TrafficRegistrar(rules,log);
    registrar.documentLineItem(item);
    registrar.documentBill(bill);
    verify(log,times(1)).log(bill);
  }
}