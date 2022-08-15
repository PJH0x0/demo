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
        operator string () const {return name;}
};

class YellowDog : public Dog {
    public:
        YellowDog(string name) : Dog(name){}
};

void func(int i) {
    cout << i << endl;
}

class NumberWrapper{
    private:
        int num;
    public:
        NumberWrapper(int num) : num(num) {}
        const NumberWrapper operator *(const NumberWrapper& rhs) {
            return NumberWrapper(rhs.num * num);
        }
        operator int () {
            return num;
        }
};

int main() {
    NumberWrapper n1 = 23;
    NumberWrapper n2 = n1 * 2;
    NumberWrapper n3 = 3 * n1;
    cout << n1 << n2 << n3 << endl;
}