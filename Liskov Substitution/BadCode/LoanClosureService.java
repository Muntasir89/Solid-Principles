public class LoanClosureService {
    private LoanPayment loanPayment;

    public LoanClosureService(LoanPayment loanPayment){
        this.loanPayment = loanPayment;
    }

    public void forceCloseLoan(){
        loanPayment.forceCloseLoan();
    }
}
