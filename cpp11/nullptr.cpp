#include <stddef.h>
#include <iostream>
#include <vector>
#include <string>

using std::vector;
using std::string;
using std::cout;
using std::endl;

void foo(int i) {cout << "foo int" << i << endl;}
void foo(char* a) {cout << "foo char* " << a << endl;}
int main() {
    //foo(NULL);//产生了二义性，因为NULL可以转为int
    foo(nullptr); //调用void foo(char*)
}