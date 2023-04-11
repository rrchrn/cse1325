#ifndef ITEM_H
#define ITEM_H

#include <string>
#include <iostream>

class Item {
public:
    Item(std::string name, int price);
    Item();
    std::string getName() const;
    int getPrice() const;
    friend std::ostream& operator<<(std::ostream& os, const Item& item);
    friend std::istream& operator>>(std::istream& is, Item& item);

private:
    int price;
    std::string name;
};

#endif
