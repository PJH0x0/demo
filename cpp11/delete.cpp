#include <iostream>

using std::cout;
using std::endl;

class A {
    public:
    A() {cout << "A()" << endl;};
    A(const A& a) = delete;
    A& operator=(const A&) = delete;
    

};

int main() {
    A a;
    //A a2 = a;//编译失败，调用已delete的拷贝构造函数
    A a3;
    //a3 = a;//编译失败，调用已delete的赋值构造函数
}