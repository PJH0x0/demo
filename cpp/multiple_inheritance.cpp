#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

class File {
    public:
    string mFileName;
    File(string m_name) : mFileName(m_name){}
    virtual void open() = 0;
};

class InputFile : virtual public File{
    public:
    InputFile(string m_name) : File(m_name){}
};
class OutputFile : virtual public File{
    public:
    OutputFile(string m_name) : File(m_name){}
};
class IOFile : public InputFile, public OutputFile {
    public:
    IOFile(string m_name) : InputFile(m_name), OutputFile(m_name), File(m_name) {}
    void open() {
        cout << "open " << mFileName << endl;
    }
};


int main() {
    IOFile f("bot");
    f.open();
    f.InputFile::open();
}