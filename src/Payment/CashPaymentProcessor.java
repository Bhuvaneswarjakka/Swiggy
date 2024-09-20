package Payment;

public class CashPaymentProcessor implements PaymentProcessor
{

    @Override
    public boolean doPayment(double amount) {
        return false;
    }
}
