#include <iostream>

using std::cout;
using std::endl;

int main(void) {
    int i = 1;
    int&& j = i++;
    return 0;
}

