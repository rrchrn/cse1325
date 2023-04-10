#include "item.h"
#include "vending_machine.h"

#include <string>
#include <vector>

// add constructor,, construct item object and add it to the items field



// buy method,, prints "#### Buying" and the item that was purchased, which is the item object in the items vector at the index specified by the parameter

// menu method returns std::string listing all items in the vending machine with their index



void Vending_Machine::add(std::string name, int price) {
    Item item(name, price);
    items.push_back(item);
}

void Vending_Machine::buy(int i) {
    if (i >= 0 && i < items.size()) {
        std::string output = "#### Buying " + items[i].getName();
    }
}

std::string Vending_Machine::menu() {
    std::string result = "";
    for (int i = 0; i < items.size(); i++) {
        result += std::to_string(i) + ": " + items[i].getName() + " - $" + std::to_string(items[i].getPrice()) + "\n";
    }
    return result;
}