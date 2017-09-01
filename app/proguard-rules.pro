# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/ansen/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class com.sardine.yiyuanduobao.app.KKFunctionRouterImpl {
   public *;
}

-optimizationpasses 5  #指定代码的压缩级别
-dontusemixedcaseclassnames #包明不混合大小写
-dontskipnonpubliclibraryclasses #不去忽略非公共的库类
-dontoptimize  #优化  不优化输入的类文件
-dontpreverify  #预校验
-verbose #混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

#忽略警告
-ignorewarning

-keepattributes *Annotation*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable,Signature

-dontwarn okio.**

-dontwarn javax.annotation.**
-dontwarn javax.inject.**


-keepclasseswithmembernames class * { #【保护指定的类和类的成员的名称，如果所有指定的类成员出席（在压缩步骤之后）】
    native <methods>;
}

-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**

-keep class **$Properties


#所有监听的方法都要列在这里
-keepclassmembers class ** {
    public void onEventMainThread(**);
}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-dontwarn android.net.http.SslError



-keep class com.google.gson.** { *; }  #忽略json包
-keep class org.greenrobot.eventbus.**{*;} #忽略EventBus包


-keep class android.support.v4.**{*;}