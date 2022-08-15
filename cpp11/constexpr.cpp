#include <iostream>

using std::cout;
using std::endl;

constexpr int cubed(int x) {
    return x * x * x;
}

int main() {
    cout << cubed(3) << endl;
}