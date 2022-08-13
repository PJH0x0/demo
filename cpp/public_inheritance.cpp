#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

class Dog {
    public:
    void bark(int age) {
        cout << age << endl;
    }
    virtual void bark(string name = "Dog") {
        cout << "I am " << name << endl;
    }
};

class YellowDog : public Dog{
    public:
    using Dog::bark;
    virtual void bark(string name = "YellowDog") {
        cout << "YellowDog bark I am " << name << endl;
    }
};

int main() {
    YellowDog* yd = new YellowDog();
    yd->bark(5);
    Dog* d = yd;
    d->bark();
}