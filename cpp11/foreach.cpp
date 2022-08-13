#include <iostream>
#include <vector>
#include <string>

using std::vector;
using std::string;
using std::cout;
using std::endl;

int main() {
    vector<int> v = {1, 2, 3, 4};
    
    for (auto i : v) {
        cout << i << endl;
    }
}