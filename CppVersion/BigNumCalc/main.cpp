#include <iostream>
#include "bigNumber.h"

int main()
{
    BigNumber numbers;
    BigNumber numbers2;
    BigNumber result;
    char operation;
    std::cout << "Enter the first big number:";
    std::cin >> numbers;
    std::cout << "Enter the operation (+, -, *, /, %):";
    std::cin >> operation;
    std::cout << "Enter the second big number:";
    std::cin >> numbers2;

    switch(operation) {
        case '+':
            result = numbers + numbers2;
            break;
        case '-':
            result = numbers - numbers2;
            break;
        case '*':
            result = numbers * numbers2;
            break;
        case '/':
            result = numbers / numbers2;
            break;
        case '%':
            result = numbers % numbers2;
            break;
        default:
            std::cout << "Invalid operation!";
            return 1;
    }

    std::cout << "Result: " << result;
    return 0;
}

//"12456789031415" * "98765432123456789" = 1230300151558439221348916026435
//12456789031415 + 98765432123456789 = 98777888912488204
// 1230300151558439221348916026435 / 98765432123456789 = 12456789031415