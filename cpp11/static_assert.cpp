#include <iostream>

using std::cout;
using std::endl;
int* p1 = nullptr;
int main() {
    int* p = nullptr;
    assert(p == nullptr);
    //static_assert(p == nullptr, "nothing");//编译失败，因为static_assert只能作用于编译时期，而p是在栈中分配内存
    //static_assert(sizeof(int) == 2, "int size is not 2 bytes"); //编译失败，因为sizeof(int) = 4，是编译时期就能确定的，
}