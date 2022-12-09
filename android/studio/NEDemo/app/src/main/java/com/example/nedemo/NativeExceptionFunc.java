package com.example.nedemo;

import android.content.Context;

import com.example.nedemo.recyclerview.CrashItem;

import java.util.ArrayList;
import java.util.List;

public class NativeExceptionFunc {
    static {
        System.loadLibrary("native-exception-lib");
    }

    static final int[] sCrashType = new int[] {
           R.string.smash_stack,
            R.string.stack_overflow,
            R.string.nostack,
            R.string.heap_usage,
            R.string.call_null,
            R.string.leak,
            R.string.abort,
            R.string.abort_with_msg,
            R.string.abort_with_null_msg,
            R.string.assert1,
            R.string.assert2,
            R.string.exit,
            R.string.fortify,
            R.string.fdsan_file,
            R.string.fdsan_dir,
            R.string.seccomp,
            R.string.xom,
            R.string.log_always_fatal,
            R.string.log_always_fatal_if,
            R.string.log_fatal,
            R.string.sigfpe,
            R.string.sigill,
            R.string.sigsegv,
            R.string.sigsegv_non_null,
            R.string.sigsegv_unmapped,
            R.string.sigtrap,
            R.string.fprintf_null,
            R.string.readdir_null,
            R.string.strlen_null,
            R.string.pthread_join_null,
            R.string.no_new_privs,

    };

    static final int[] sCrashDesc = new int[] {
            R.string.smash_stack_desc,
            R.string.stack_overflow_desc,
            R.string.nostack_desc,
            R.string.heap_usage_desc,
            R.string.call_null_desc,
            R.string.leak_desc,
            R.string.abort_desc,
            R.string.abort_with_msg_desc,
            R.string.abort_with_null_msg_desc,
            R.string.assert1_desc,
            R.string.assert2_desc,
            R.string.exit_desc,
            R.string.fortify_desc,
            R.string.fdsan_file_desc,
            R.string.fdsan_dir_desc,
            R.string.seccomp_desc,
            R.string.xom_desc,
            R.string.log_always_fatal_desc,
            R.string.log_always_fatal_if_desc,
            R.string.log_fatal_desc,
            R.string.sigfpe_desc,
            R.string.sigill_desc,
            R.string.sigsegv_desc,
            R.string.sigsegv_non_null_desc,
            R.string.sigsegv_unmapped_desc,
            R.string.sigtrap_desc,
            R.string.fprintf_null_desc,
            R.string.readdir_null_desc,
            R.string.strlen_null_desc,
            R.string.pthread_join_null_desc,
            R.string.no_new_privs_desc,
    };
    public static List<CrashItem> createCrashItems(Context context) {
        int size = sCrashType.length;
        List<CrashItem> crashItemList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            crashItemList.add(new CrashItem(context.getString(sCrashType[i]), context.getString(sCrashDesc[i])));
        }
        return crashItemList;
    }
    public static native String stringFromDynamicJni();
    public static native int nativeAdd(int a, int b);
    public static native void callStaticMethodFromJni();
    public static native void callInstanceMethodFromJni();
    public static native void nativeRegisterSignal();
    public static native void nativeKillSelf();
    public static native void nativeCrash(String type);
    public static native void nativeNullPointer();
    public static native void nativeAbort();
}
