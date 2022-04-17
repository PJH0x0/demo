#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_pjh_jnidemo_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    //char* p = NULL;
    //*p = 2;
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_pjh_jnidemo_StaticJniTest_getPackageName(JNIEnv *env, jclass type) {

    // TODO

    std::string returnValue = "com.pjh.jnidemo";

    return env->NewStringUTF(returnValue.c_str());
}