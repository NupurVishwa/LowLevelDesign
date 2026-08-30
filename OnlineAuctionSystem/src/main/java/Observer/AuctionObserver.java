package Observer;

import Entities.Auction;

public interface AuctionObserver {
    void onUpdate(Auction auction, String message);
}