#include <iostream>
#include <string>
#include <vector>
using std::string;
using std::cout;
using std::endl;

class A {
    public:
        A(){ cout << "A constructor" << endl;}
        A(const A& rhs) {cout << "A copy constructor" << endl;}
        virtual A* clone() {return new A(*this);}
};

class B : public A {
    public:
        B() { cout << "B constructor" << endl;}
        B(const B& rhs) {cout << "B copy constructor" << endl;}
        virtual B* clone() {return new B(*this);}
};

void foo(A* a) {
    //A* b = new A(*a);//这里想得到的是B的对象指针，但是返回的是A的
    A* b = a->clone();
}

int main() {
    B b;
    foo(&b);
}