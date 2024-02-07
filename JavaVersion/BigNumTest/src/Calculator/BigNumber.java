package Calculator;

public class BigNumber {
    private String number;

    public BigNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private int toDigit(char arr) {
        return arr - '0';
    }

    private char toChar(int dummy) {
        return (char) (dummy + '0');
    }

    @Override
    public String toString() {
        return getNumber();
    }

    String sumOfNumbers(String str1, String str2) {
        if (str1.length() > str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }

        int remainder = 0;
        StringBuilder result = new StringBuilder();

        int str1Length = str1.length() - 1;
        int str2Length = str2.length() - 1;

        while (str1Length >= 0) {
            int sum = (toDigit(str1.charAt(str1Length--)) + toDigit(str2.charAt(str2Length--)) + remainder);
            result.append(toChar(sum % 10));
            remainder = sum / 10;
        }

        while (str2Length >= 0) {
            int sum = (toDigit(str2.charAt(str2Length--)) + remainder);
            result.append(toChar(sum % 10));
            remainder = sum / 10;
        }

        if (remainder > 0) {
            result.append(toChar(remainder));
        }

        return result.reverse().toString();
    }

    private boolean isSmaller(String str1, String str2) {
        int str1Length = str1.length();
        int str2Length = str2.length();

        if (str1Length < str2Length) {
            return true;
        }

        if (str2Length < str1Length) {
            return false;
        }

        for (int i = 0; i < str1Length; ++i) {
            if (str1.charAt(i) < str2.charAt(i)) {
                return true;
            } else if (str1.charAt(i) > str2.charAt(i)) {
                return false;
            }
        }
        return false;
    }

    String subtraction(String str1, String str2) {
        if (isSmaller(str1, str2)) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }

        int str1Length = str1.length() - 1;
        int str2Length = str2.length() - 1;

        int remainder = 0;

        StringBuilder result = new StringBuilder();

        while (str2Length >= 0) {
            int subtract = (toDigit(str1.charAt(str1Length--)) - toDigit(str2.charAt(str2Length--)) - remainder);
            if (subtract < 0) {
                subtract += 10;
                remainder = 1;
            } else {
                remainder = 0;
            }

            result.append(toChar(subtract));
        }

        while (str1Length >= 0) {
            int subtract = (toDigit(str1.charAt(str1Length--)) - remainder);

            if (subtract < 0) {
                subtract += 10;
                remainder = 1;
            } else {
                remainder = 0;
            }

            result.append(toChar(subtract));
        }

        while (result.length() > 0 && result.charAt(result.length() - 1) == '0') {
            if (result.length() == 1) {
                break;
            }
            result.deleteCharAt(result.length() - 1);
        }
        result.reverse();

        return result.toString();
    }

    String multiplyNumberHelper(String str1, char a) {
        if (str1.equals("0") || a == '0') {
            return "0";
        }
        int remainder = 0;
        StringBuilder result = new StringBuilder();
        int str1Length = str1.length() - 1;

        while (str1Length >= 0) {
            int sum = (toDigit(str1.charAt(str1Length--)) * toDigit(a) + remainder);
            remainder = sum / 10;
            result.append(toChar(sum % 10));
        }

        if (remainder > 0) {
            result.append(toChar(remainder));
        }
        return result.reverse().toString();
    }

    String multiplyNumbers(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        str2 = new StringBuilder(str2).reverse().toString();

        for (int i = 0; i < str2.length(); ++i) {
            String helper = multiplyNumberHelper(str1, str2.charAt(i));

            for (int j = 0; j < i; ++j) {
                helper += "0";
            }
            result = new StringBuilder(sumOfNumbers(result.toString(), helper));
        }

        return result.toString();
    }

    String divisionOfNumbers(String str1, String str2) {
        if (isSmaller(str1, str2)) {
            return "0";
        }

        if (str1.equals(str2)) {
            return "1";
        }

        StringBuilder result = new StringBuilder();
        StringBuilder helper = new StringBuilder();

        boolean counting = false;

        for (int i = 0; i < str1.length(); ++i) {
            helper.append(str1.charAt(i));

            if (isSmaller(str2, helper.toString())) {
                counting = true;
                int resultNum = 0;
                while (isSmaller(str2, helper.toString()) || str2.equals(helper.toString())) {
                    helper = new StringBuilder(subtraction(helper.toString(), str2));
                    resultNum++;
                }
                result.append(toChar(resultNum));
                continue;
            }
            if (counting) {
                result.append('0');
            }
        }
        return result.toString();
    }

    String moduleDivision(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        StringBuilder helper = new StringBuilder();

        boolean counting = false;

        for (int i = 0; i < str1.length(); ++i) {
            helper.append(str1.charAt(i));

            if (isSmaller(str2, helper.toString())) {
                counting = true;
                int resultNum = 0;
                while (isSmaller(str2, helper.toString()) || str2.equals(helper.toString())) {
                    helper = new StringBuilder(subtraction(helper.toString(), str2));
                    resultNum++;
                }
                result.append(toChar(resultNum));
                continue;
            }
            if (counting) {
                result.append('0');
            }
        }
        return result.toString();
    }
}