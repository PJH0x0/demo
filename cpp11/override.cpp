#include <iostream>

using std::cout;
using std::endl;

class A {
    public:
    virtual void a(int) {cout << "A::a" << endl;};
    virtual void b()  {cout << "A::b" << endl;};
};

class B : public A {
    public:
    virtual void a(float) override {cout << "B::a" << endl;}
    virtual void a(float) {cout << "B::a" << endl;}
    virtual void b() override {cout << "B::b" << endl;}
};

int main() {
    A* a = new B();
    a->a(5);
    //a->a(4.5);
    a->b();
}