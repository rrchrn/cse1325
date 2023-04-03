#include <iostream>
#include <string>

int main(int argc, char* argv[]) {

    std::string current = argv[1];
    std::cout << current;
    for(int i = 2; i < argc; i++)
    {
        std::string arg = argv[i];
        if (arg != current)
        {
            std::cout << std::string{arg + " "};
            current = arg;
        }
    }

    std::cout << std::endl;

    return 0;
}