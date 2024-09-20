package Payment;

public class CreditCardPaymentProcessor implements PaymentProcessor
{

    @Override
    public boolean doPayment(double amount) {
        return false;
    }
}
