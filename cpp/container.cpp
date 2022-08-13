#include <iostream>
#include <string>
#include <vector>
using std::string;
using std::cout;
using std::endl;

class Person {
    private:
        string* name_;
    public:
        Person(const Person& rhs) = delete;
        Person& operator =(const Person& rhs) = delete;
        Person(string name) {name_ = new string(name);}
        ~Person() {delete name_;}
        string printName() const {return *name_;}
};

int main() {
    std::vector<Person> v;
    v.push_back(Person("Bob"));

    cout << v.back().printName() << endl;
}