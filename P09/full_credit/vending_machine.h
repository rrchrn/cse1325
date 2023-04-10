#ifndef VENDING_MACHINE_H
#define VENDING_MACHINE_H

#include "item.h"
#include <string>
#include <vector>

class Vending_Machine {
    public:
        void add(std::string name, int price);
        void buy(int index);
        std::string menu();
        
    private:
        std::vector<Item> items;

};

#endif