#include <iostream>

using std::cout;
using std::endl;

class A {
    public:
    A(int age) {cout << "A " << endl;}
    A() {cout << "A2" << endl;}

};

int main() {
    A a();
}