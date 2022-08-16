//
// Created by jonny on 18-6-21.
//

#include <jni.h>
#include <stdlib.h>
#include <stdio.h>
#include <android/log.h>
#include <signal.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>

#define TAG "JNITEST"

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

jstring get_string(JNIEnv *env, jclass clz) {
    return env->NewStringUTF("Hello, here is dynamic native string");
}

jint add(JNIEnv *env, jclass clz, jint a, jint b) {
    return a + b;
}

void call_java_static_method(JNIEnv *env, jclass clz) {
    LOGI("call_java_static_method()");
    //1. Find class
    jclass clazz = env->FindClass("com/example/nedemo/CallMethodFromJni");
    if (NULL == clazz) {
        LOGI("NOT FOUND CLASS");
    }
    //2. Get static method id
    jmethodID  id = env->GetStaticMethodID(clazz, "staticMethod", "(Ljava/lang/String;)V");
    if (NULL == id) {
        LOGI("Not find Id");
    }
    //3, Call method
    jstring str_arg = env->NewStringUTF("Call static method from JNI");
    //env->CallStaticLongMethodV(clazz, id, str_arg);
    env->CallStaticVoidMethod(clazz, id, str_arg);

}

void call_java_instance_method(JNIEnv *env, jclass clz) {
    LOGI("call_java_instance_method()");
    //1. Find class
    jclass clazz = env->FindClass("com/example/nedemo/CallMethodFromJni");
    if (NULL == clazz) {
        LOGI("NOT FOUND CLASS");
    }
    //2. Get construct method id, no args constructor
    jmethodID constructor_id = env->GetMethodID(clazz, "<init>", "()V");
    if (NULL == constructor_id) {
        LOGI("Not find constructor id");
    }
    //3. Get method id
    jmethodID id = env->GetMethodID(clazz, "instanceMethod", "(Ljava/lang/String;)V");
    if (NULL == id) {
        LOGI("Not find instance method id");
    }
    //4. New CallMethodFromJni object
    jobject obj = env->NewObject(clazz, constructor_id);
    if (NULL == obj) {
        LOGI("Not new object");
    }
    //5. Call instance method
    jstring str_arg = env->NewStringUTF("Call instance method from JNI");
    env->CallVoidMethod(obj, id, str_arg);

    //New object with args
    jmethodID args_constructor = env->GetMethodID(clazz, "<init>", "(Ljava/lang/String;)V");
    if (NULL == id) {
        LOGI("Not find args constructor id");
    }
    jstring str_arg_constructor = env->NewStringUTF("Call args constructor");
    jobject obj_with_args = env->NewObject(clazz, args_constructor, str_arg_constructor);

}
void signal_handler(int signal, siginfo_t *info, void *uc) {
    LOGI("catch signal = %d killer pid = %d", signal, info->si_pid);
    exit(-1);
}
void register_signal(JNIEnv *env, jclass clz) {
    struct sigaction sa;
    memset(&sa, 0, sizeof(sa));
    sigemptyset(&sa.sa_mask);
    //sa.sa_handler = signal_handler;
    sa.sa_sigaction = signal_handler;
    sa.sa_flags = SA_ONSTACK | SA_SIGINFO;
    sigaddset(&sa.sa_mask, SIGABRT);
    sigaddset(&sa.sa_mask, SIGSEGV);
    sigaddset(&sa.sa_mask, SIGKILL);
    LOGI("signal_handler addr %p", signal_handler);
    struct sigaction old_sa;
    memset(&old_sa, 0, sizeof(old_sa));
    sigaction(SIGABRT, NULL, &old_sa);
    sigaction(SIGSEGV, NULL, &old_sa);
    sigaction(SIGKILL, NULL, &old_sa);
    sigaction(SIGABRT, &sa, NULL);
    sigaction(SIGSEGV, &sa, NULL);
    sigaction(SIGKILL, &sa, NULL);
    LOGI("old signal_handler addr %p", old_sa.sa_handler);
}

void crash(JNIEnv *env, jclass clz) {
    abort();
}
void kill_self(JNIEnv *env, jclass clz) {
    pid_t pid = getpid();
    kill(pid, SIGKILL);
}
/**
 * 动态调用的三个要素: Java方法名, Java参数及返回类型, jni的函数名
 * 凡是返回值为某个类的,前面必须要加上L,并且后面加上;,基础类型不需要
 */
static JNINativeMethod gMethods[] = {
        {"stringFromDynamicJni", "()Ljava/lang/String;", (void *) get_string},
        {"nativeAdd", "(II)I", (void *) add},
        {"callStaticMethodFromJni", "()V", (void *) call_java_static_method},
        {"callInstanceMethodFromJni", "()V", (void *) call_java_instance_method},
        {"nativeRegisterSignal", "()V", (void *) register_signal},
        {"nativeKillSelf", "()V", (void *) kill_self},
        {"nativeCrash", "()V", (void *) crash},
};

/*JNIEXPORT*/ jint /*JNICALL */JNI_OnLoad(JavaVM *jvm, void *reserved) {
    JNIEnv *env = NULL;
    jint result = JNI_FALSE;

    //1. Get JNIEnv pointer
    if (jvm->GetEnv((void**) &env, JNI_VERSION_1_6) != JNI_OK) {
        return result;
    }

    LOGI("JNI_OnLoad");

    if (env == NULL) {
        LOGI("JNI_OnLoad env == NULL");
        return result;
    }

    //2. Find class
    jclass clazz = env->FindClass("com/example/nedemo/NativeExceptionFunc");
    if (clazz == NULL) {
        LOGI("JNI_OnLoad clazz == NULL");
        return result;
    }

    //3. Register methods which define in gMethods
    if (env->RegisterNatives(clazz, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        LOGI("JNI_OnLoad register failed");
        return result;
    }

    return JNI_VERSION_1_6;
}
