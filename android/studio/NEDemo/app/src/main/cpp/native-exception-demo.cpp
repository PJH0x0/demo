//
// Created by jonny on 18-6-21.
//

#include <jni.h>
#include <stdlib.h>
#include <stdio.h>
#include <android/log.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/prctl.h>
#include <unistd.h>
#include <string>
#include <functional>
#include <utility>
#include <thread>
#include <dirent.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <linux/seccomp.h>
#include <dlfcn.h>
//#include "seccomp_policy.h"



#define TAG "PJH"

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG,__VA_ARGS__)
#define noinline __attribute__((__noinline__))
extern "C" void android_set_abort_message(const char* msg);

class FuntionTest {
    std::function<void(int* a, int* b)> swap_function;
public:
    void setFunc(std::function<void(int* a, int* b)> __swap_function) {
        swap_function = __swap_function;
    }
    std::function<void(int* a, int* b)> getFunc() {
        return  swap_function;
    }
    FuntionTest() {
        LOGI("FuntionTest()");
    }
    ~FuntionTest() {
        LOGI("~FuntionTest()");
    }
};

jstring get_string(JNIEnv *env, jclass clz) {
    return env->NewStringUTF("Hello, here is dynamic native string");
}

jint add(JNIEnv *env, jclass clz, jint a, jint b) {
    return a + b;
}

/*jint get_java_crash_type(JNIEnv *env, jclass clz, const char *field_name) {
    LOGI("get java static field");
    jclass clazz = env->FindClass("com/example/nedemo/NativeExceptionFunc$CrashType");
    if (NULL == clazz) {
        LOGE("NOT FOUND CrashType CLASS");
    }
    jfieldID id = env->GetStaticFieldID(clazz, field_name, "I");
    if (NULL == id) {
        LOGE("NOT FOUND id");
    }
    jint result = env->GetStaticIntField(clazz, id);
    LOGI("result = %d", result);
    return result;
}*/

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
    sigaction(SIGABRT, nullptr, &old_sa);
    sigaction(SIGSEGV, nullptr, &old_sa);
    sigaction(SIGABRT, &sa, nullptr);
    sigaction(SIGSEGV, &sa, nullptr);
    LOGI("old signal_handler addr %p", old_sa.sa_sigaction);
}
extern "C" {
void crashnostack(void);
noinline void sigsegv_non_null() {
    int *a = (int *) (&signal_handler);
    *a = 42;
}
char *smash_stack_dummy_buf;
noinline void smash_stack_dummy_function(volatile int *plen) {
    smash_stack_dummy_buf[*plen] = 0;
}
noinline int smash_stack(volatile int *plen) {
    LOGI("%s: deliberately corrupting stack...", getprogname());

    char buf[128];
    smash_stack_dummy_buf = buf;
    // This should corrupt stack guards and make process abort.
    smash_stack_dummy_function(plen);
    return 0;
}

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winfinite-recursion"

void *global = 0; // So GCC doesn't optimize the tail recursion out of overflow_stack.

noinline void overflow_stack(void *p) {
    void *buf[1];
    buf[0] = p;
    global = buf;
    overflow_stack(&buf);
}

#pragma clang diagnostic pop
noinline int crash_null() {
    int (*null_func)() = nullptr;
    return null_func();
}
noinline int crash3(int a) {
    *reinterpret_cast<int*>(0xdead) = a;
    return a*4;
}

noinline int crash2(int a) {
    a = crash3(a) + 2;
    return a*3;
}
noinline int crash_(int a) {
    a = crash2(a) + 1;
    return a*2;
}
noinline void maybe_abort() {
    if (time(0) != 42) {
        abort();
    }
}
noinline void fprintf_null() {
    fprintf(nullptr, "oops");
}
noinline void readdir_null() {
    readdir(nullptr);
}
noinline int strlen_null() {
    char* sneaky_null = nullptr;
    return strlen(sneaky_null);
}
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wfree-nonheap-object"

noinline void abuse_heap() {
    char buf[16];
    free(buf); // GCC is smart enough to warn about this, but we're doing it deliberately.
}
#pragma clang diagnostic pop
noinline void leak() {
    while (true) {
        void* mapping =
                mmap(nullptr, PAGE_SIZE, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
        static_cast<volatile char*>(mapping)[0] = 'a';
    }
}
noinline void xom() {

}
};

noinline void function_nullptr(JNIEnv* env) {
    FuntionTest test;
    test.setFunc([](int* a, int* b) {
        LOGI("a = %d", *a);
    });
    auto func = test.getFunc();
    int a = 2;
    int b = 3;
    func(&a, &b);
    test.setFunc(nullptr);
    if (test.getFunc()) {
        LOGI("Function not null");
    } else {
        LOGI("Function is null");
    }
}

noinline void crash(JNIEnv *env, jclass clz, jstring jstr, jboolean jIsNativeThread) {
    //abort();
    //jint re = get_java_crash_type(env, clz, "SIGSEGV");
    std::string crash_type(env->GetStringUTFChars(jstr, nullptr));
    LOGI("crash_type = %s", crash_type.c_str());
    std::function<void(const std::string&)> do_crash = [](const std::string& crash_type) {
        if (crash_type == "SIGSEGV-non-null") {
            sigsegv_non_null();
        } else if (crash_type == "smash-stack") {
            volatile int len = 128;
            smash_stack(&len);
        } else if (crash_type == "stack-overflow") {
            overflow_stack(nullptr);
        } else if (crash_type == "nostack") {
            crashnostack();
        } else if (crash_type == "exit") {
            exit(1);
        } else if (crash_type == "call-null") {
            crash_null();
        } else if (crash_type == "crash" || crash_type == "SIGSEGV") {
            crash_(42);
        } else if (crash_type == "abort") {
            maybe_abort();
        } else if (crash_type == "abort_with_msg") {
            android_set_abort_message("Aborting due to crasher");
            maybe_abort();
        } else if (crash_type == "abort_with_null") {
            android_set_abort_message(nullptr);
            maybe_abort();
        } else if (crash_type == "assert") {
            __assert("some_file.c", 123, "false");
        } else if (crash_type == "assert2") {
            __assert2("some_file.c", 123, "some_function", "false");
        } else if (crash_type == "fortify") {
            char buf[10];
            __read_chk(-1, buf, 32, 10);
            while (true) pause();
        } else if (crash_type == "fdsan_file") {
            FILE* f = fopen("/dev/null", "r");
            close(fileno(f));
        } else if (crash_type == "fdsan_dir") {
            DIR* d = opendir("/dev/");
            close(dirfd(d));
        } else if (crash_type == "LOG-FATAL") {
            LOGF("LOG-FATAL");
        } else if (crash_type == "LOG_ALWAYS_FATAL") {
            LOGF("LOG_ALWAYS_FATAL");
        } else if (crash_type == "LOG_ALWAYS_FATAL_IF") {
            LOGF("LOG_ALWAYS_FATAL_IF");
        } else if (crash_type == "SIGFPE") {
            raise(SIGFPE);
            //return EXIT_SUCCESS;
        } else if (crash_type == "SIGILL") {
#if defined(__aarch64__)
            __asm__ volatile(".word 0\n");
#elif defined(__arm__)
            __asm__ volatile(".word 0xe7f0def0\n");
#elif defined(__i386__) || defined(__x86_64__)
      __asm__ volatile("ud2\n");
#else
#error
#endif
        } else if (crash_type == "SIGTRAP") {
            raise(SIGTRAP);
        } else if (crash_type == "fprintf-NULL") {
            fprintf_null();
        } else if (crash_type == "readdir-NULL") {
            readdir_null();
        } else if (crash_type == "strlen-NULL") {
            strlen_null();
        } else if (crash_type == "pthread_join-NULL") {
            pthread_join(0, nullptr);
        } else if (crash_type == "heap-usage") {
            abuse_heap();
        } else if (crash_type == "leak") {
            leak();
        } else if (crash_type == "SIGSEGV-unmapped") {
            char* map = reinterpret_cast<char*>(
                    mmap(nullptr, sizeof(int), PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0));
            munmap(map, sizeof(int));
            map[0] = '8';
        } else if (crash_type == "seccomp") {
//            set_system_seccomp_filter();
//            syscall(99999);
        } else if (crash_type == "xom") {//xom = excute only memory
            // Try to read part of our code, which will fail if XOM is active.
            //printf("*%lx = %lx\n", reinterpret_cast<long>(xom), *reinterpret_cast<long*>(xom));
            LOGE("*%lx = %lx\n", reinterpret_cast<long>(xom), *reinterpret_cast<long*>(xom));
        } else if (crash_type == "no_new_privs") {
            if (prctl(PR_SET_NO_NEW_PRIVS, 1) != 0) {
                //fprintf(stderr, "prctl(PR_SET_NO_NEW_PRIVS, 1) failed: %s\n", strerror(errno));
                LOGE("prctl(PR_SET_NO_NEW_PRIVS, 1) failed: %s", strerror(errno));
                return ;
            }
            abort();
        } else if (crash_type == "functional-nullptr") {
            //function_nullptr(env);
        }
    };
    if (jIsNativeThread) {
        std::thread t(do_crash, crash_type);
        t.join();
    } else {
        do_crash(crash_type);
    }
}


noinline void null_pointer(JNIEnv *env, jclass clz) {
    int* a = nullptr;
    (*a)++;
}

noinline void abort_example(JNIEnv *env, jclass clz) {
    LOGF("ne example abort test");
    abort();
}
noinline void fix_bti_check(JNIEnv *env, jclass cl) {
    //void* start_addr = reinterpret_cast<void*>(0x7e0f08d000);
    //size_t size = 0x7e0f091000 - 0x7e0f08d000;
    static void* libsigchain = []() {
        void* result = dlopen("libsigchain.so", RTLD_LOCAL | RTLD_LAZY);
        if (!result) {
            LOGF("failed to dlopen %s: %s", "libsigchain.so", dlerror());
        }
        return result;
    }();
    void* sigaction = dlsym(libsigchain, "sigaction");
    if (sigaction == nullptr) {
        LOGF("failed to find sigaction in libsigchain");
    }
    LOGI("libsigchain sigaction addr %p", sigaction);
    static void* libc = []() {
        void* result = dlopen("libc.so", RTLD_LOCAL | RTLD_LAZY);
        if (!result) {
            LOGF("failed to dlopen %s: %s", "libc.so", dlerror());
        }
        return result;
    }();
    void* sigaction64 = dlsym(libc, "sigaction64");
    if (sigaction64 == nullptr) {
        LOGF("failed to find sigaction in libc");
    }
    LOGI("libc sigaction64 addr %p", sigaction64);
    void* start_addr = reinterpret_cast<void*>((uint64_t)sigaction & PAGE_MASK);
    size_t size = 4096;
    LOGI("start addr %p", start_addr);
    if (mprotect(start_addr, size, PROT_READ |/* PROT_WRITE |*/ PROT_EXEC) == -1) {
        LOGE("PJH mprotected failed %s", strerror(errno));
    }

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
        {"nativeCrash", "(Ljava/lang/String;Z)V", (void *) crash},
        {"nativeNullPointer", "()V", (void *) null_pointer},
        {"nativeAbort", "()V", (void *) abort_example},
        {"fixBtiCheck", "()V", (void *) fix_bti_check},
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
