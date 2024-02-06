#include "bigNumber.h"
#include <iostream>
BigNumber::BigNumber() {}

BigNumber ::BigNumber(std::string number) {
    this->number = number;
}
BigNumber BigNumber :: operator+(BigNumber n1)
{
    return BigNumber(sumOfNumbers(number,n1.number));
}
BigNumber BigNumber :: operator-(BigNumber n1)
{
    return BigNumber(subtractOfNumbers(number,n1.number));
}
BigNumber BigNumber :: operator*(BigNumber n1)
{
    return BigNumber(multiplyNumbers(number,n1.number));
}
BigNumber BigNumber :: operator/(BigNumber n1) {
    return BigNumber(divisionOfNumbers(number,n1.number));
}
BigNumber BigNumber::operator%(BigNumber n1) {
    return BigNumber(remainderFromDivisionOfNumbers(number,n1.number));
}

std::string BigNumber :: getNumber () const{
    return number;
}

int BigNumber::toDigit(char arr) {
    return arr - '0';
}

char BigNumber::toChar(int dummy) {
    return dummy + '0';
}

std::string BigNumber::sumOfNumbers(std::string str1, std::string str2)
{
    //checking to see which is smaller and swapping it in order to start with the smaller number
    if(str1.length() > str2.length())
        swap(str1,str2);

    int remainder = 0;
    std::string result = "";

    int str1Length = str1.length() - 1;
    int str2Length = str2.length() - 1;

    //traversing the smaller string and adding each element from the first one (starting from the last element) with the second string
    while (str1Length >= 0)
    {
        int sum = (toDigit(str1[str1Length--]) + (toDigit(str2[str2Length--])) + remainder);
        result.push_back(toChar(sum % 10));
        remainder = sum/10;
    }
    //adding the remaining numbers from the bigger number to the result since we didn't work with them in the first while loop
    while (str2Length >= 0)
    {
        int sum = ((toDigit(str2[str2Length--])) + remainder);
        result.push_back(toChar(sum%10));
        remainder = sum/10;
    }

    //if there still is a remainder the push it into the result
    if(remainder)
    {
        result.push_back(toChar(remainder));
    }

    std::reverse(result.begin(),result.end());

    return result;
}

bool BigNumber::isSmaller(std::string str1, std::string str2)
{
    int str1Length = str1.length();
    int str2Length = str2.length();

    //if the first string is shorter return true
    if(str1Length < str2Length)
        return true;
    //if the 2nd string is shorter return false
    if(str2Length < str1Length)
        return false;
    //else if the strings are the same size we start comparing their digits one by one
    for (int i = 0; i < str1Length; ++i)
    {
        if (str1[i] < str2[i])
            return true;
        else if (str1[i] > str2[i])
            return false;
    }

    return false;
}

std::string BigNumber ::subtractOfNumbers(std::string str1, std::string str2)
{
    //lets say is we have 123 and 1234 we can't subtract 1234 from 123 (technically we can but since we are working with 'Big Numbers'
    //the answer should be positive integers
    //this is why we check which string of numbers is smaller and we swap them in order to subtract the smaller one from the bigger one
    if(isSmaller(str1,str2))
        swap(str1,str2);

    int str1Length = str1.length() - 1;
    int str2Length = str2.length() - 1;

    int remainder = 0;
    std::string result = "";
    //we start traversing the second(the smallest) string from the back(that's the way we were taught subtracting numbers in school)
    while (str2Length >= 0)
    {
        int subtract = (toDigit(str1[str1Length--]) - (toDigit(str2[str2Length--])) - remainder);
        //if the result from subtracting two numbers is < 0 for example:
        //if we are subtracting 46 from 123 (123-46) first we are subtracting 3 and 6 (3 - 6 = - 3)
        // -3 + 10 = 7 (the number that we are pushing into the result and by making the remainder 1 we are 'taking' one
        // from the number after it
        if (subtract < 0)
        {
            subtract = subtract + 10;
            remainder = 1;
        }
        else
        {
            remainder = 0;
        }

        result.push_back(toChar(subtract));
    }

    //this while loop is only for the first string and it enters this loop only if str1 length is greater than str2
    //this loop pushes the remaining numbers of the longer string into the answer because these numbers were not traversed in the first one
    while (str1Length >= 0)
    {
        int subtract = (toDigit(str1[str1Length--]) - remainder);

        if (subtract < 0)
        {
            subtract = subtract + 10;
            remainder = 1;
        }
        else
        {
            remainder = 0;
        }

        result.push_back(toChar(subtract));
    }
    //this while loop removes the excess of zeros
    //for example if we subtract 100 - 100 it pushes three 0s in the result string
    //this while loop removes all the excessive zeros and leaves only one
    while (result.back() == '0')
    {
        if(result.size() == 1)
        {
            break;
        }
        result.pop_back();
    }

    std::reverse(result.begin(),result.end());

    return result;

}

//this helper function multiplies a string with single char
std::string BigNumber::multiplyNumbersHelper(std::string str1, char a)
{
    //checking if one of the numbers is zero and if it is we stop the function
    if (str1 == "0" || a == '0')
        return "0";

    int remainder = 0;
    std::string result = "";
    int str1Length = str1.length() - 1;

    //traversing each number from the first string and multiplying it with the char and then pushing it into the result
    while(str1Length >= 0)
    {
        int sum = ((toDigit(str1[str1Length--]) * toDigit(a) + remainder));
        remainder = sum/10;
        result.push_back(toChar(sum % 10));
    }
    //checking to see if there is a remainder to push it in the result
    if(remainder > 0)
    {
        result.push_back(toChar(remainder));
    }

    std::reverse(result.begin(),result.end());

    return result;
}

//the helper function does all the job here we are just giving the char argument of the helper with one of the numbers from str2
std::string BigNumber::multiplyNumbers(std::string str1, std::string str2)
{
    std::string result = "";
    std::reverse(str2.begin(),str2.end());
    for (int i = 0; i < str2.length(); i++)
    {
        std::string helper = multiplyNumbersHelper(str1,str2[i]);

        //this for loop adds 0s after every change of element in str2
        for (int j = 0; j < i; ++j)
        {
            helper += "0";
        }

        result = sumOfNumbers(result,helper);
    }

    return result;
}

std::string BigNumber::divisionOfNumbers(std::string str1, std::string str2)
{
//checking to see if the first number is smaller than the second one and if it is the functions ends
    if (isSmaller(str1,str2))
        return "0";

    if(str1 == str2)
        return "1";

    std::string result = "";
    std::string helper = "";

    //this bool checks if for example we have to divide 4321 by 42 we subtract 42 from 43 and push 1 to the result
    //after bringing down the 2 we are left with 12 and since 12 is still smaller than 42 we go to the bool and push a 0 in the result
    //afterwards we bring down the 1 and do the subtraction normally
    bool counting = false;

    //this for loop traverses the first string and pushes back each number starting from the first one until it is bigger than str2 and it enters the if
    for (int i = 0; i < str1.length(); ++i)
    {
        helper.push_back(str1[i]);

        if (isSmaller(str2,helper))
        {
            counting = true;
            int resultNum = 0; //this is just a counter for how many times str is subtracted from the helper
            while (isSmaller(str2,helper) || str2 == helper)
            {
                helper = subtractOfNumbers(helper,str2);
                resultNum++;
            }
            result.push_back(toChar(resultNum));
            continue;
        }
        if (counting)
        {
            result.push_back('0');
        }
    }

    return result;
}
// this is the same functions as the division of numbers but at the end we return the helper instead of the result and this turns out to be operator%.
std::string BigNumber::remainderFromDivisionOfNumbers(std::string str1, std::string str2)
{

    std::string result = "";
    std::string helper = "";

    bool counting = false;

    for (int i = 0; i < str1.length(); ++i)
    {
        //1234    38
        helper.push_back(str1[i]);

        if (isSmaller(str2,helper))
        {
            counting = true;
            int resultNum = 0;
            while (isSmaller(str2,helper) || str2 == helper)
            {
                helper = subtractOfNumbers(helper,str2);
                resultNum++;
            }
            result.push_back(toChar(resultNum));
            continue;
        }
        if (counting)
        {
            result.push_back('0');
        }
    }

    return helper;
}

std::ostream &operator<<(std::ostream &os, BigNumber &num) {
    os << num.getNumber();
    return os;
}

std::istream &operator>>(std::istream& is, BigNumber& num) {
    std::string str;
    is >> str;
    num = BigNumber(str);
    return is;
}
