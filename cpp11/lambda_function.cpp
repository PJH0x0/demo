#include <iostream>
#include <vector>

using std::cout;
using std::endl;
using std::vector;

template<typename func>
void filter(func f, vector<int> v) {
    for (auto i : v) {
        if (f(i))
        {
            cout << i << endl;
        }
        
    }
}

int main() {
    vector<int> v = {1, 2, 3, 4, 5};
    filter([](int x) {return x > 3; }, v);
    int y = 2;
    filter([&](int x) {return x > y;}, v);
}