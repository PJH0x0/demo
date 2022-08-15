#include <mutex>
#include <iostream>

using std::mutex;
using std::cout;
using std::endl;

class Lock {
    private:
        std::shared_ptr<mutex> m;
    public:
        explicit Lock(mutex* t) : m(t) {
            m->lock();
            cout << "RAII lock" << endl;
        }
        ~Lock() {
            m->unlock();
            cout << "RAII unlock" << endl;
        }
};

void bad_throw();

void bad() {
    mutex m;
    m.lock();
    cout << "Unsafe lock" << endl;
    return;
    m.unlock();
}

void good() {
    mutex m;
    Lock lock(&m);
    return;
}

int main() {
    bad();
    good();
    return 0;
}