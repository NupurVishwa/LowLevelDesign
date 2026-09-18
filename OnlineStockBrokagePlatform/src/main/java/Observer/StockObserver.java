package Observer;

import Entities.Stock;

public interface StockObserver {
    void update(Stock stock);
}