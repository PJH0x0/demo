#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

class Dog {
    private:
        string name;
    public:
        explicit Dog(string _name) : name(_name){}
        void printName() const { 
            cout << name << endl;
            Dog* d = const_cast<Dog*>(this);
            d->name = "hahaha";
            cout << name << endl;
        }
        operator string () const {return name;}
        virtual ~Dog(){}
        
};

class YellowDog : public Dog {
    public:
        int age;
        YellowDog(string name) : Dog(name){}
        void hello() {
            cout << "hello" << endl;
        }
        
};

class A {};
class B : public A {};

int main() {
    const string& str = "hello";
    string& str2 = const_cast<string&>(str);
    cout << str2 << endl;
    str2 ="hello world";
    cout << str2 << endl;
    Dog dog("lele");
    dog.printName();


    Dog* d = new YellowDog("bob");
    YellowDog* d2 = dynamic_cast<YellowDog*>(d);
    d2->hello();
    cout << d2->age << endl;
    YellowDog yd("dahuang");
    Dog& d3 = yd;
    YellowDog& yd2 = dynamic_cast<YellowDog&>(d3);
    YellowDog* yd3 = new YellowDog("aa");
    Dog* d4 = dynamic_cast<Dog*>(yd3);
    d4->printName();

    Dog d5("kaka");
    string name = static_cast<string>(d5);//可以转换任意类型，甚至是两个不同的类型
    cout << name << endl;
    
}