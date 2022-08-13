#include <iostream>

using std::cout;
using std::endl;

class A {
    //void init() {cout << "init" << endl;}
    int _a;
    public:
    // A() {init();cout << "A()" << endl;}
    // A(int a) {init(); cout << "A(int)" << endl;}
    A() {cout << "A()" << endl;}
    A(int a) :  A() {cout << "A(int)" << endl;}
};

int main() {
    A(3);
}