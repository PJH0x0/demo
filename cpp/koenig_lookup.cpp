#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

namespace A {
    struct X{};
    int m = 1;
    void a(X x) {cout << "A scope a" << endl;}
    void b(int m) { cout << "A scope " << endl;}
    namespace B {
        void a() {cout << "B scope a" << endl;}
        void b() {
            X x;
            a(x);
        }
    }
}

class C {
    public:
    void a(A::X x) {cout << "class C a" << endl;}
    void d() {
        A::X x;
        a(x);
    }
};
class D {
    public:
    void d() {
        A::X x;
        a(x);
    }
};

//namespace num {
    class Num {
        public:
        int num;
    };
    Num& operator+(Num& num, int n) {
        num.num = num.num + n;
        return num;
    }
//}

// num::Num& operator+(num::Num& num, int n) {
//     num.num = num.num + n;
//     return num;
// }

namespace b {
    struct C{};
    int operator+(struct C& a, int b) {return b;}
    void a(){
        Num n;
        n = n + 1;
    }
}

int main() {
    A::X x;
    A::m = 5;
    a(x);
    //b(m);

    C c;
    c.d();
    D d;
    d.d();
}