package Entities;

import Observer.AuctionObserver;
import Enum.AuctionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// Auction Entity
public class Auction {

    private final String id;
    private final String itemName;
    private final String description;
    private final BigDecimal startingPrice;
    private final LocalDateTime endTime;

    private final List<Bid> bids;

    // Observers interested in auction updates
    private final java.util.Set<AuctionObserver> observers;

    private AuctionStatus state;
    private Bid winningBid;

    // Constructor
    public Auction(
            String itemName,
            String description,
            BigDecimal startingPrice,
            LocalDateTime endTime) {

        this.id = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.description = description;
        this.startingPrice = startingPrice;
        this.endTime = endTime;

        this.bids = new ArrayList<>();
        this.observers = ConcurrentHashMap.newKeySet();

        this.state = AuctionStatus.ACTIVE;
    }

    // ---------------------------------------------------------
    // Core Bidding Logic
    // ---------------------------------------------------------

    public synchronized void placeBid(User bidder, BigDecimal amount) {

        // Check whether auction is active
        if (state != AuctionStatus.ACTIVE) {
            throw new IllegalStateException(
                    "Auction is not active."
            );
        }

        // Check whether auction has expired
        if (LocalDateTime.now().isAfter(endTime)) {
            endAuction();

            throw new IllegalStateException(
                    "Auction has already ended."
            );
        }

        // Find current highest bid
        Bid highestBid = getHighestBid();

        BigDecimal currentMaxAmount =
                (highestBid == null)
                        ? startingPrice
                        : highestBid.getAmount();

        // New bid must be greater than current highest bid
        if (amount.compareTo(currentMaxAmount) <= 0) {
            throw new IllegalArgumentException(
                    "Bid must be higher than the current highest bid."
            );
        }

        // Remember previous highest bidder
        User previousHighestBidder =
                (highestBid != null)
                        ? highestBid.getBidder()
                        : null;

        // Create and store new bid
        Bid newBid = new Bid(bidder, amount);
        bids.add(newBid);

        // New bidder becomes an observer
        addObserver(bidder);

        System.out.printf(
                "SUCCESS: %s placed a bid of $%.2f on '%s'.%n",
                bidder.getName(),
                amount,
                itemName
        );

        // Notify previous highest bidder
        if (previousHighestBidder != null
                && !previousHighestBidder.equals(bidder)) {

            notifyObserver(
                    previousHighestBidder,
                    String.format(
                            "You have been outbid on '%s'! " +
                                    "The new highest bid is $%.2f.",
                            itemName,
                            amount
                    )
            );
        }
    }

    // ---------------------------------------------------------
    // End Auction
    // ---------------------------------------------------------

    public synchronized void endAuction() {

        // Auction is already closed
        if (state != AuctionStatus.ACTIVE) {
            return;
        }

        // Change state
        this.state = AuctionStatus.CLOSED;

        // Determine winner
        this.winningBid = getHighestBid();

        String endMessage;

        if (winningBid != null) {

            endMessage = String.format(
                    "Auction for '%s' has ended. " +
                            "Winner is %s with a bid of $%.2f!",
                    itemName,
                    winningBid.getBidder().getName(),
                    winningBid.getAmount()
            );

        } else {

            endMessage = String.format(
                    "Auction for '%s' has ended. " +
                            "There were no bids.",
                    itemName
            );
        }

        System.out.println(
                "\n" + endMessage.toUpperCase()
        );

        // Notify all observers
        notifyAllObservers(endMessage);
    }

    // ---------------------------------------------------------
    // Find Highest Bid
    // ---------------------------------------------------------

    public Bid getHighestBid() {

        if (bids.isEmpty()) {
            return null;
        }

        return Collections.max(bids);
    }

    // ---------------------------------------------------------
    // Check Auction Status
    // ---------------------------------------------------------

    public boolean isActive() {
        return state == AuctionStatus.ACTIVE;
    }

    // ---------------------------------------------------------
    // Observer Pattern
    // ---------------------------------------------------------

    private void addObserver(AuctionObserver observer) {
        observers.add(observer);
    }

    private void notifyAllObservers(String message) {

        for (AuctionObserver observer : observers) {
            observer.onUpdate(this, message);
        }
    }

    private void notifyObserver(
            AuctionObserver observer,
            String message) {

        observer.onUpdate(this, message);
    }

    // ---------------------------------------------------------
    // Getters
    // ---------------------------------------------------------

    public String getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<Bid> getBidHistory() {
        return Collections.unmodifiableList(bids);
    }

    public AuctionStatus getState() {
        return state;
    }

    public Bid getWinningBid() {
        return winningBid;
    }
}

