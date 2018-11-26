# 前言
前提:
1. Android版本: Android 7.0
2. Android Studio版本: 2.3.2

**Tips:不要太在意版本问题，一般这种都不会出现太大的差池**

目录:
1. 如何导入
2. 遇到的一些问题
3. 解决方案
4. 总结

# 如何导入
1. 在Android源码root目录下source buid/env_setup.sh
2. 执行lunch, 选择一个版本进行lunch
3. 执行make -j4进行编译并通过(已经编译过了的可以跳过这一步)，在编译完之后出现**make compelete successfully**就可以不用管了
4. 执行mmm development/tools/idegen/
5. 执行sh ./development/tools/idegen/idegen.sh，这个命令执行的时间比较长，需要多等一下
6. 打开Android Studio，选择Android源码root目录下的android.ipr文件，先不要进行导入，此时导入会非常慢

**Tips：**
1. 以上所有的命令都要在同一个目录下执行
2. 暂时先不要导入，
# 遇到的一些问题
1. 导入时间很长，原因是导入了一些不需要的目录
2. 跳转文件不对，经常跳转到classes目录下，原因是没有移除.jar文件和out目录
# 解决方案
1. 打开Android源码root目录下的android.iml,添加自己不需要的内容，之前应该有已经排除了的目录，这里在自己添加一些，这样一来导入的速度就加快很多了，但一般机械硬盘也要个十几分钟吧
``` xml
<excludeFolder url="file://$MODULE_DIR$/.repo" />
<excludeFolder url="file://$MODULE_DIR$/abi" />
<excludeFolder url="file://$MODULE_DIR$/art" />
<excludeFolder url="file://$MODULE_DIR$/bootable" />
<excludeFolder url="file://$MODULE_DIR$/cts" />
<excludeFolder url="file://$MODULE_DIR$/external" />
<excludeFolder url="file://$MODULE_DIR$/external/bluetooth" />
<excludeFolder url="file://$MODULE_DIR$/external/chromium" />
<excludeFolder url="file://$MODULE_DIR$/external/emma" />
<excludeFolder url="file://$MODULE_DIR$/external/icu4c" />
<excludeFolder url="file://$MODULE_DIR$/external/jdiff" />
<excludeFolder url="file://$MODULE_DIR$/external/webkit" />
<excludeFolder url="file://$MODULE_DIR$/frameworks/base/docs" />
<excludeFolder url="file://$MODULE_DIR$/hardware" />
<excludeFolder url="file://$MODULE_DIR$/out/eclipse" />
<excludeFolder url="file://$MODULE_DIR$/out/host" />
<excludeFolder url="file://$MODULE_DIR$/out/target/common/docs" />
<excludeFolder url="file://$MODULE_DIR$/out/target/common/obj/JAVA_LIBRARIES/android_stubs_current_intermediates" />
<excludeFolder url="file://$MODULE_DIR$/out/target/product" />
<excludeFolder url="file://$MODULE_DIR$/platform_testing" />
<excludeFolder url="file://$MODULE_DIR$/prebuilt" />
<excludeFolder url="file://$MODULE_DIR$/out" />
<excludeFolder url="file://$MODULE_DIR$/sdk" />
```
2. 跳转问题有两种，第一种是跳转到.classes文件中，解决方案:Project Structure-->Modules-->android-->Dependencies，将所有的jar去除，只保留SDK的，第二种是跳转到out目录下的.java文件，将out目录添加到excludeFolder中，如1中所示