#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

class Dog {
    public:
    string m_name;
    Dog(string name) : m_name(name){cout << m_name << " is born" << endl;}
    ~Dog() {
        cout << m_name << " is destroied" << endl;
        
    }
    void bark(){ cout << "wangwang" << endl;throw 10;}
};

int main() {
    try
    {
        Dog dog1("henry");
        Dog dog2("bob");
        dog1.bark();
        dog2.bark();
    }
    catch(int e)
    {
        cout << e << " is caught" << endl;
    }
    
}