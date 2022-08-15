#include <iostream>

using std::cout;
using std::endl;

int main() {
    const char* a = "string";
    const char* a1 = u8"string";//UTF-8 unicode
    const char16_t* b = u"string";//UTF-16 unicode
    const char32_t* c = U"string";//UTF-32 unicode
    const char* d = R"(string \\)"; //raw string

}