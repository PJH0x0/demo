#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;
class Dog{
    public:
        int a;
        void modify() {a = 1;}
};

int add(int a, int b) {
    return a+b;
}

int add2(int& a, int& b) {
    return a+b;
}

int add3(int&& a, int&& b) {
    return a+b;
}

int global_val = 1;

int& func() {
    return global_val;
}

int main() {
    int i = 2;
    int* p = &i;
    int& a = i;
    int* p3 = &(*p);
    int* p2 = &(++i);
    //int& ref = 2;//无法编译通过
    const int& ref = 2;
    Dog d;

    int m = 1;
    int n = 2;
    add2(m, n);
    add3(1, 2);

    i++;
    i+2;
    2;
    add(1, 2);
    Dog().modify();

    
}