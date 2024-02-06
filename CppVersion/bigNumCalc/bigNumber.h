#pragma once

#include <string>
#include <iostream>

class BigNumber {
public:
    BigNumber();
    BigNumber(std::string number);
    BigNumber operator+(BigNumber n1);
    BigNumber operator-(BigNumber n1);
    BigNumber operator*(BigNumber n1);
    BigNumber operator/(BigNumber n1);
    BigNumber operator%(BigNumber n1);
    std::string getNumber () const;

    friend std::istream& operator>> (std::istream& is, BigNumber& num);
    friend std::ostream& operator<< (std::ostream& os, BigNumber& num);
private:
    std::string number;
    char toChar(int dummy);
    int toDigit(char arr);
    std::string sumOfNumbers(std::string str1, std::string str2);
    bool isSmaller(std::string str1, std::string str2);
    std::string subtractOfNumbers(std::string str1, std::string str2);
    std::string multiplyNumbersHelper(std::string str1,char a);
    std::string multiplyNumbers(std::string str1, std::string str2);
    std::string divisionOfNumbers (std::string str1,std::string str2);
    std::string remainderFromDivisionOfNumbers(std::string str1, std::string str2);
};

