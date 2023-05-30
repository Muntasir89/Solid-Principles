public class CreditCardLoan  implements LoanPayment{

    @Override
    public void doPayment(int amount) {
        
    }

    @Override
    public void forceCloseLoan() {
        throw new UnsupportedOperationException("Unimplemented method 'forceCloseLoan'");
    }

    @Override
    public void doRepayment(int amount) {
        
    }
}
