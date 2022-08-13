#include <iostream>
#include <vector>
#include <string>

using std::vector;
using std::string;
using std::cout;
using std::endl;

int main() {
    vector<int> v = {1, 2, 3, 4};
    int sum = 0;
    for (auto it = v.begin(); it != v.end(); ++it) {
        sum += *it;
    }
    for (auto i : v) {
        cout << i << endl;
    }
    cout << sum << endl;
    int a = 1;
    auto b = &a;
    auto c = v;
    auto d = 5;
    //auto e;
}