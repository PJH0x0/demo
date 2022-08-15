#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

template<class T>
class A {
    public:
    void preload() {
        load();
    }
    void load() {
        static_cast<T*>(this)->load();
    }
};

class B : public A<B>{
    public:
    void load() {
        cout << "Child load" << endl;
    }
};

class C : public A<C> {

};

int main() {
    A<B>* a = new B();
    a->preload();
}