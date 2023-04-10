#include "item.h"
#include "vending_machine.h"
#include <string>
#include <vector>
#include <iostream>

int main()
{
    Vending_Machine vendingMachine;

    // add two item objects
    vendingMachine.add("Celsius", 3);
    vendingMachine.add("Water", 2);
    // print menu to console
    std::cout << vendingMachine.menu() << std::endl;
    // buy one of the items
    vendingMachine.buy(0);

    return 0;
}


