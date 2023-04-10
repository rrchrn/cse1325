#include "item.h"

// Constructor,, initializes name and price fields item(name,price)

Item::Item(std::string name, int price): name(name), price(price)
{
     //throw exception in constructor// similar to java
    if (price < 0)
    {
        //throw standard runtime error
        throw std::runtime_error("Price cannot be negative!");
    }

}

// Getter for the item name
std::string Item::getName() 
{
    return name;
}

// Getter for the item price
int Item::getPrice()
{
    return price;
}

std::string Item::to_string(){
    
    std::string output = name + " ($" + std::to_string(static_cast<double>(price) / 100.0) + ") ";
    return output;

}


