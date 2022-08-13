
#include<iostream>
#include<string>
using std::cout;
using std::endl;
using std::string;

class Dog {
    
    private:
    string name;
    public:
    Dog(const string& _name): name(_name){}
    string getName() const {
        name = "haha";
        return name;
    }
};

int main() {
    int i = 10;
    const int* p = &i;
    int const * p2 = &i;
    i = 11;
    //p++;
    //*p = 2;
    cout << i << " " << *p << endl;

    int * const p3 = &i;
    *p3 = 3;
    //p2++;
    cout << i << " " << *p3 << endl;
    Dog dog("hehe");
    cout << dog.getName() << endl;

}
