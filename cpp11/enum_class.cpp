#include <iostream>

using std::cout;
using std::endl;

enum apple {green, red};
enum orange {small, large};

enum class apple_2 {green, red};
enum class orange_2 {small, large};

int main() {
    apple a = green;
    orange o = small;
    if (a == o) {
        cout << "same" << endl;
    } else {
        cout << "diff" << endl;
    }

    apple_2 a2 = apple_2::green;
    orange_2 o2 = orange_2::small;
    if (a2 == o2) {
        cout << "same" << endl;
    } else {
        cout << "diff" << endl;
    }
}