package Calculator;
public class Calc {
    private BigNumber memory;
    private BigNumber lastResult;

    public Calc() {
        this.memory = new BigNumber("0");
        this.lastResult = new BigNumber("0");
    }

    public BigNumber add(BigNumber x, BigNumber y) {
        BigNumber result = new BigNumber(x.sumOfNumbers(x.getNumber(), y.getNumber()));
        lastResult = result;
        return result;
    }

    public BigNumber subtract(BigNumber x, BigNumber y) {
        BigNumber result = new BigNumber(x.subtraction(x.getNumber(), y.getNumber()));
        lastResult = result;
        return result;
    }

    public BigNumber multiply(BigNumber x, BigNumber y) {
        BigNumber result = new BigNumber(x.multiplyNumbers(x.getNumber(), y.getNumber()));
        lastResult = result;
        return result;
    }

    public BigNumber division(BigNumber x, BigNumber y) {
        if (!y.getNumber().equals("0")) {
            BigNumber result = new BigNumber(x.divisionOfNumbers(x.getNumber(), y.getNumber()));
            lastResult = result;
            return result;
        } else {
            System.out.println("Error: Divison by zero!");
            return null;
        }
    }
    public BigNumber module(BigNumber x, BigNumber y)
    {
        if (!y.getNumber().equals("0")) {
            BigNumber result = new BigNumber(x.moduleDivision(x.getNumber(), y.getNumber()));
            lastResult = result;
            return result;
        } else {
            System.out.println("Error: Divison by zero!");
            return null;
        }
    }

    public void pressMC() {
        memory = new BigNumber("0");
    }

    // Memory Recall
    public void pressMR() {
        lastResult = memory;
    }

    // Memory Store
    public void pressMS() {
        memory = lastResult;
    }

    // Memory Add
    public void pressMPlus() {
        memory = add(memory, lastResult);
    }

    public void StoreMemory(BigNumber value) {
        memory = new BigNumber(value.getNumber());
    }

    // Store in Memory
    public void storeInMemory(BigNumber value) {
        memory = value;
    }

    // Recall Memory
    public BigNumber recallMemory() {
        return memory;
    }

    public BigNumber evaluateExpression(String expression) {
        // Split the expression into operands and operators
        String[] tokens = expression.split("(?<=\\d)(?=[+\\-*/%])|(?<=[+\\-*/%])(?=\\d)");

        BigNumber result = null;
        String currentOperator = null;

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                // Operand
                BigNumber operand = new BigNumber(token);

                if (result == null) {
                    result = operand;
                } else {
                    // Apply the current operator to the result and operand
                    result = applyOperator(result, currentOperator, operand);
                }
            } else if (isOperator(token)) {
                // Operator
                currentOperator = token;
            }
        }

        // Return the final result
        return result;
    }


    private boolean isOperator(String token) {
        return token.matches("[+\\-*/%]");
    }

    private BigNumber applyOperator(BigNumber operand1, String operator, BigNumber operand2) {
        switch (operator) {
            case "+":
                return add(operand1, operand2);
            case "-":
                return subtract(operand1, operand2);
            case "*":
                return multiply(operand1, operand2);
            case "/":
                return division(operand1, operand2);
            case "%":
                return module(operand1,operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}