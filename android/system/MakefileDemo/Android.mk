LOCAL_PATH := $(my-dir)
$(warning ----$(LOCAL_PATH))
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_PACKAGE_NAME := Demo
LOCAL_SRC_FILES := $(call all-subdir-java-files)
LOCAL_PRIVATE_PLATFORM_APIS := true
LOCAL_PRIVILEGED_MODULE := true
LOCAL_USE_AAPT2 := true
LOCAL_CERTIFICATE := platform
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
include $(BUILD_PACKAGE)
