#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;
class A {
    public:
    void a() {cout << "a()" << endl;}
    protected:
    void b() {cout << "b()" << endl;}
    private:
    void c() {cout << "c()" << endl;}
};

class B : public A{
    public:
    B() {
        a();
        b();
        //c();
    }
};

class C : protected A {
    public:
    C() {
        a();
        b();
        //c();
    }
    void cast() {
        A* a = dynamic_cast<A*>(this);
        cout << "C: " << a << endl;
    }
};
class C2 : public C {
    public:
    C2() {
        a();
        b();
    }
};
class D : private A {
    public:
    D() {
        a();
        b();
        //c();
    }
    void cast() {
        A* a = dynamic_cast<A*>(this);
        cout << "D: " << a << endl;
    }
};
class D2 : public D {
    public:
    D2() {
        //a();//a()在D中是私有类型，无法调用到
    }
};

int main() {
    B b;
    b.a();
    C c;
    //c.a();//a()在C中是保护类型，无法调用到
}