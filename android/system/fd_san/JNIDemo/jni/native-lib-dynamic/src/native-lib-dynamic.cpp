//
// Created by jonny on 18-6-21.
//

#include <jni.h>
#include <stdlib.h>
#include <stdio.h>
#include <android/log.h>
#include <err.h>
#include <unistd.h>
#include <fcntl.h>

#include <chrono>
#include <thread>
#include <vector>

#include <android-base/unique_fd.h>

using namespace std::chrono_literals;
using std::this_thread::sleep_for;

#define TAG "JNIDemo_Native"

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)


void victim(int origin_fd) {
    LOGI("run victim");
    sleep_for(300ms);
    int fd = dup(origin_fd);
    sleep_for(200ms);
    ssize_t rc = write(fd, "good\n", 5);
    if (rc == -1) {
        err(1, "good failed to write?!");
    }
    close(fd);
}

void bystander(int origin_fd) {
    LOGI("run bystander");
    sleep_for(100ms);
    int fd = dup(origin_fd);
    sleep_for(300ms);
    close(fd);
}

void offender(int origin_fd) {
    LOGI("run offender");
    int fd = dup(origin_fd);
    close(fd);
    sleep_for(200ms);
    close(fd);
}


jstring get_string(JNIEnv *env, jclass clz) {
    return env->NewStringUTF("Hello, here is dynamic native string");
}

jint add(JNIEnv *env, jclass clz, jint a, jint b) {
    return a + b;
}

void call_java_static_method(JNIEnv *env, jclass clz) {
    LOGI("call_java_static_method()");
    //1. Find class
    jclass clazz = env->FindClass("com/pjh/jnidemo/CallMethodFromJni");
    if (NULL == clazz) {
        LOGE("NOT FOUND CLASS");
        return;
    }
    //2. Get static method id
    jmethodID  id = env->GetStaticMethodID(clazz, "staticMethod", "(Ljava/lang/String;)V");
    if (NULL == id) {
        LOGE("Not find Id");
        return;
    }
    //3, Call method
    jstring str_arg = env->NewStringUTF("Call static method from JNI");
    //env->CallStaticLongMethodV(clazz, id, str_arg);
    env->CallStaticVoidMethod(clazz, id, str_arg);

}

void call_java_instance_method(JNIEnv *env, jclass clz) {
    LOGI("call_java_instance_method()");
    //1. Find class
    jclass clazz = env->FindClass("com/pjh/jnidemo/CallMethodFromJni");
    if (NULL == clazz) {
        LOGE("NOT FOUND CLASS");
        return;
    }
    //2. Get construct method id, no args constructor
    jmethodID constructor_id = env->GetMethodID(clazz, "<init>", "()V");
    if (NULL == constructor_id) {
        LOGE("Not find constructor id");
        return;
    }
    //3. Get method id
    jmethodID id = env->GetMethodID(clazz, "instanceMethod", "(Ljava/lang/String;)V");
    if (NULL == id) {
        LOGE("Not find instance method id");
        return;
    }
    //4. New CallMethodFromJni object
    jobject obj = env->NewObject(clazz, constructor_id);
    if (NULL == obj) {
        LOGE("Not new object");
        return;
    }
    //5. Call instance method
    jstring str_arg = env->NewStringUTF("Call instance method from JNI");
    env->CallVoidMethod(obj, id, str_arg);

    //New object with args
    jmethodID args_constructor = env->GetMethodID(clazz, "<init>", "(Ljava/lang/String;)V");
    if (NULL == id) {
        LOGE("Not find args constructor id");
        return;
    }
    jstring str_arg_constructor = env->NewStringUTF("Call args constructor");
    jobject obj_with_args = env->NewObject(clazz, args_constructor, str_arg_constructor);
    if (NULL == obj_with_args) {
        LOGI("Call instance failed");
    }

}

void fdsanTest(JNIEnv *env, jclass clz) {
    if (NULL == clz) {
        LOGE("Not from Java");
        return;
    }
    std::vector<std::thread> threads;
    int fd;
    if ((fd = creat("/data/data/com.pjh.jnidemo/aaa.txt", S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH)) < 0) {
        LOGE("create file failed");
        return;
    }
    for (auto function : { victim, bystander, offender }) {
        threads.emplace_back(function, fd);
    }
    for (auto& thread : threads) {
        thread.join();
    }
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
        {"nativeFdsanTest", "()V", (void *) fdsanTest},
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
        LOGE("JNI_OnLoad env == NULL");
        return result;
    }

    //2. Find class
    jclass clazz = env->FindClass("com/pjh/jnidemo/DynamicJniTest");
    if (clazz == NULL) {
        LOGE("JNI_OnLoad clazz == NULL");
        return result;
    }

    //3. Register methods which define in gMethods
    if (env->RegisterNatives(clazz, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        LOGE("JNI_OnLoad register failed");
        return result;
    }

    return JNI_VERSION_1_6;
}
