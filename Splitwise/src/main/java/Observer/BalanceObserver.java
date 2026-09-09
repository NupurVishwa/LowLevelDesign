package Observer;

import Model.Expense;

public interface BalanceObserver {

    void onBalanceUpdated(Expense expense);
}