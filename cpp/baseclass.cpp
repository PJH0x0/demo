#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

class Base {
    public:
    Base() {cout << "base constructor" << endl;}
    Base(string name, int age) {cout << "base " << name << " age " << age << endl;}
    virtual ~Base() {cout << "base destructor" << endl; }
};

class Dog : public Base {
    public:
    string m_name;
    Dog(string name) : m_name(name){cout << m_name << " is born" << endl;}
    ~Dog() {
        cout << m_name << " is destroied" << endl;
        throw 20;
        
    }
    void bark(){ cout << "wangwang" << endl;throw 10;}
};

int main() {
    try
    {
        Dog d("123");
        Dog d2("345");
        Dog d3("567");
    }
    catch(int e)
    {
        cout << e << endl;
    }
    
    
    return 0;
}