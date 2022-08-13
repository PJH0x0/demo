
#include <iostream>
#include <string>

using std::string;
class Dog {
    public:
    string& name;
    Dog(string _name = "bob") : name(_name){
    }
};

class D {
    public:
    string& name;
    D(){}
};

int main() {
    Dog dog1;
    Dog dog2 = dog1;
    Dog dog3;
    D d;
}