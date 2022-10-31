package core.basesyntax;

import static org.junit.Assert.assertSame;

import core.basesyntax.model.Operation;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.strategy.operations.BalanceHandler;
import core.basesyntax.strategy.operations.OperationHandler;
import core.basesyntax.strategy.operations.PurchaseHandler;
import core.basesyntax.strategy.operations.ReturnHandler;
import core.basesyntax.strategy.operations.SupplyHandler;
import java.util.HashMap;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;

public class OperationStrategyTest {
    private static OperationStrategy strategy;
    private String operationLine;

    @BeforeClass
    public static void setUp() {
        Map<Operation, OperationHandler> operationHandlerMap = new HashMap<>();
        operationHandlerMap.put(Operation.BALANCE, new BalanceHandler());
        operationHandlerMap.put(Operation.PURCHASE, new PurchaseHandler());
        operationHandlerMap.put(Operation.RETURN, new ReturnHandler());
        operationHandlerMap.put(Operation.SUPPLY, new SupplyHandler());
        strategy = new OperationStrategyImpl(operationHandlerMap);
    }

    @Test
    public void operationStrategy_getOperationHandler_ok() {
        operationLine = "b,apple,100\n";
        OperationHandler actual = strategy.getHandler(operationLine);
        assertSame(actual.getClass(), BalanceHandler.class);
        operationLine = "s,banana,50\n";
        actual = strategy.getHandler(operationLine);
        assertSame(actual.getClass(), SupplyHandler.class);
        operationLine = "r,apple,10\n";
        actual = strategy.getHandler(operationLine);
        assertSame(actual.getClass(), ReturnHandler.class);
        operationLine = "p,apple,20\n";
        actual = strategy.getHandler(operationLine);
        assertSame(actual.getClass(), PurchaseHandler.class);
    }

    @Test(expected = RuntimeException.class)
    public void operationStrategy_getNonExistingOperationHandler_notOk() {
        operationLine = "m,apple,100\n";
        OperationHandler actual = strategy.getHandler(operationLine);
    }
}
