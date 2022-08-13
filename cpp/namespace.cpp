#include <iostream>

using namespace std;//case 1:直接使用，作用于全局域
using std::cout;// case 2.a: 使用定义，作用于全局域
class A {
    public:
    void a() {}
};

class B : private A {
    public:
    using A::a;//case 2.b: 使用定义，作用于类域
    //using namespace std;//编译不过，在类域中只能使用基类中的函数或者成员
    //using std::cout;
    void a(int a){}
    void b() {
        using namespace std;//case 1: 直接使用，作用于局部域
    }
    void c() {
        using std::cin;// case 2.a: 使用定义，作用于局部域
    }
};

//using A::a;

int main() {
    B b;
    b.a();
    {
        using namespace std;//case 1: 直接使用，作用于局部域
        using std::cout;// case 2.a: 使用定义，作用于局部域
    }
    
}