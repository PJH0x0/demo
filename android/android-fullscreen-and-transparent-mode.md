# 前言
Android沉浸式与全屏是不一样的两种主题，接下来我们看看吧

# Android实现全屏
## 通过主题属性来实现
``` xml
<style name="FullScreenTheme">
    <item name="android:windowNoTitle">true</item>
    <item name="android:windowFullscreen">true</item>
    <item name="android:background">#ff00beb4</item>
</style>
```
在AndroidManifest.xml中使用

``` xml
<activity android:name=".TestActivity"
    android:theme="@style/FullScreenTheme">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</activity>
```
## 使用全屏的主题
``` xml
<activity android:name=".TestActivity"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</activity>
```
或者
``` xml
<activity android:name=".TestActivity"
    android:theme="@android:style/Theme.Material.NoActionBar.Fullscreen">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</activity>
```
## java代码中设置属性

``` java
requestWindowFeature(Window.FEATURE_NO_TITLE);//这行代码一定要在setContentView之前，不然会闪退
setContentView(R.layout.activity_test);
Window window = getWindow();
window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
```
## 效果图
![全屏效果图](http://note.youdao.com/yws/public/resource/802e34eb1be646f8114a2adf3735aac3/xmlnote/ACF1FB3A6B6346A595B02715AEEB7A24/657)
# Android实现透明状态栏

## 半沉浸式

``` xml
<style name="TranslucentTheme">
    <item name="android:windowTranslucentNavigation">true</item>
    <item name="android:windowTranslucentStatus">true</item>
    <item name="android:windowNoTitle">true</item>
    <item name="android:background">#ff00beb4</item>
</style>
```
使用：
``` xml
<activity android:name=".TestActivity"
    android:theme="@style/TranslucentTheme">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</activity>
```
## 半透明效果图
![半沉浸式效果图5.x](http://note.youdao.com/yws/public/resource/802e34eb1be646f8114a2adf3735aac3/xmlnote/37F0725CDB5E4F2AA4FFC9316C5AAF04/671)

这不是好好的吗？但这是5.1的系统，当切换到6.0以后的系统的时候

![半沉浸式效果图6.0](http://note.youdao.com/yws/public/resource/a5657490e67fbbabf5a224030ec75c73/xmlnote/C7ED07B7398645AE9FCE8CC382536A7A/927)



## 透明式6.0
``` java
Window window = activity.getWindow();
//这一步最好要做，因为如果这两个flag没有清除的话下面没有生效
window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//设置布局能够延伸到状态栏(StatusBar)和导航栏(NavigationBar)里面
window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//设置状态栏(StatusBar)颜色透明
window.setStatusBarColor(Color.TRANSPARENT);
//设置导航栏(NavigationBar)颜色透明
window.setNavigationBarColor(Color.TRANSPARENT);
```
加上这段代码就可以了，效果如图

![全沉浸式](http://note.youdao.com/yws/public/resource/a5657490e67fbbabf5a224030ec75c73/xmlnote/B3777E391A41427A9D76B42E91DAD11D/944)

## 关于导航栏SystemUiVisibility

```java
final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE//保持系统的稳定性
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//隐藏导航栏的布局，但是SYSTEM_UI_FLAG_HIDE_NAVIGATION不设置不会生效
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//隐藏号航栏
                | View.SYSTEM_UI_FLAG_IMMERSIVE//沉浸式，会全屏
                /*| View.SYSTEM_UI_FLAG_FULLSCREEN//全屏
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN*/
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY//粘性沉浸式，下滑和上滑才能显示状态栏和导航栏
                | 0x00200000 |//隐藏导航栏的back键
                0x00400000 |//隐藏导航栏的home键
                0x01000000;//隐藏导航栏的recent键
window.getDecorView().setSystemUiVisibility(flags);
```
## 关于fitsSystemWindows

``` xml
android:fitsSystemWindows=true<!--可以让你的布局不会顶到状态栏和导航栏上，但是颜色依然会透过去-->
```

