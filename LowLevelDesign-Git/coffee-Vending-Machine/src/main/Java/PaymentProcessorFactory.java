public class PaymentProcessorFactory {

    public static PaymentProcessor getProcessor(
            PaymentType paymentType) {

        /*
         * Factory decides which payment processor
         * object should be created.
         *
         * Client does not need to know:
         *
         * new CashPaymentProcessor()
         * new CardPaymentProcessor()
         * new UpiPaymentProcessor()
         */

        switch (paymentType) {

            case CASH:
                return new CashPaymentProcessor();

            case CARD:
                return new CardPaymentProcessor();

            case UPI:
                return new UpiPaymentProcessor();

            default:
                throw new IllegalArgumentException(
                        "Unsupported payment type"
                );
        }
    }
}