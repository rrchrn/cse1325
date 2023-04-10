#ifndef ITEM_H
#define ITEM_H

#include <string>

class Item {
    public:
        Item(std::string name, int price);
        std::string to_string();
        std::string getName();
        
    private:
        int price;
        std::string name;

};

#endif