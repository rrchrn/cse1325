#include "item.h"
#include <iomanip>
#include <sstream>

Item::Item(std::string name, int price)
    : name(name), price(price) {
    if (price < 0) {
        throw std::runtime_error("Price cannot be negative!");
    }
}

Item::Item()
    : name(""), price(0) {}

std::string Item::getName() const {
    return name;
}

int Item::getPrice() const {
    return price;
}

std::ostream& operator<<(std::ostream& os, const Item& item) {
    std::stringstream stream;
    stream << std::fixed << std::setprecision(2) << static_cast<double>(item.price) / 100.0;
    os << item.name << " ($" << stream.str() << ")";
    return os;
}

std::istream& operator>>(std::istream& is, Item& item) {
    std::string name;
    std::getline(is, name);
    item.name = name;

    std::string priceStr;
    std::getline(is, priceStr);
    item.price = std::stoi(priceStr);

    return is;
}
