#include <iostream>
#include <vector>
#include <string>
#include <initializer_list>

using std::vector;
using std::string;
using std::cout;
using std::endl;

class A {
    public:
    int a;
    string s;
    A(int a, string s = "hello"){
        cout << "A(int)" << endl;
    }
    A(const std::initializer_list<int>& list){
        cout << "A(vecotr<int>)" << endl;
    }
};

int main() {
    A a = {5};
    A b{6, "hello2"};
    cout << a.a << endl;
    vector<int> v = {1, 2, 3};
}