package spring.testing.server.unittests.compliance;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spring.testing.server.bills.Bill;
import spring.testing.server.bills.LineItem;
import spring.testing.server.compliance.logging.BillLog;
import spring.testing.server.compliance.logging.TrafficRegistrar;
import spring.testing.server.compliance.rules.LoggableRule;
public class TrafficRegistrarTests {
  @Mock private BillLog log;
  @Mock private LoggableRule alwaysLog;
  @Mock private LoggableRule neverLog;
  @Mock private Bill bill;
  @Mock private LineItem item;
  private List<LoggableRule> rules = new ArrayList<LoggableRule>();
  private TrafficRegistrar registrar;
  
  @BeforeEach
  public void setUp() {
	MockitoAnnotations.initMocks(this);
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