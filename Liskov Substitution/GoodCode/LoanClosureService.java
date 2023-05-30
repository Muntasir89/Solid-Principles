public class LoanClosureService {
    private SecureLoan secureLoan;

    public LoanClosureService(SecureLoan secureLoan){
        this.secureLoan = secureLoan;
    }

    public void forceCloseLoan(){
        secureLoan.forceCloseLoan();
    }
}
