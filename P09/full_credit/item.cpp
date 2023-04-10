#include "item.h"

// Constructor,, initializes name and price fields item(name,price)

Item::Item(std::string name, int price)
{
     //throw exception in constructor// similar to java
    if (price < 0)
    {
        //throw standard runtime error
        throw std::runtime_error("Price cannot be negative!");
    }

}

std::string getName(name)
    {
        return name;
    }

std::string Item::to_string(){
    
    std::string output = name + " ($" + std::to_string(price/100) + ") ";
    return output;

}


