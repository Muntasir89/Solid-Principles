package OCP.GoodCode;

public class Calculator {
    public int calculateNumber(int number1, int number2, Operations operation){
        return operation.perform(number1, number2);
    }
}
