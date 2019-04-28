#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_ndktest_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_ndktest_StaticJniTest_getPackageName(JNIEnv *env, jclass type) {

    // TODO

    std::string returnValue = "com.example.ndktest";

    return env->NewStringUTF(returnValue.c_str());
}