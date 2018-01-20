# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-ignorewarnings

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}


#1.这是所有app都必须填写混淆规则

#代码混淆压缩比，在0-7之间，默认为5一般不需要更改
-optimizationpasses 5

#混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames

#指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses

#指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers

#不做预检验，preverify是proguard的4个步骤之一
#Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify

#有了verbose这句话，混淆后就会生成映射文件，包含有类名->混淆后类名的映射关系
#然后使用printmapping指定映射文件的名称
-verbose
-printmapping proguardMapping.txt

#指定混淆时采用的算法，后面的参数是一个过滤器，一般不改变，这是谷歌推荐的算法
-optimizations !code/simplification/arithmetic,!field/,!class/merging/

#保护代码中的Annotation不被混淆
-keepattributes Annotation

#避免混淆泛型
-keepattributes Signature

#抛出异常时保留代号行号
-keepattributes SourceFile,LineNumberTable

# 保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 所有继承自Activity/Application/Service/BroadcastReceiver
# /ContentProvider/Throwable/Exception 不要被混淆
#-keep public class * extends com.yuyh.library.Base.BaseActivity
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends java.lang.Throwable {*;}
-keep public class * extends java.lang.Exception {*;}

# 保留在Activity中的方法参数是view的方法
# 从而保证我们在layout里面编写onClick就不会被影响
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# 枚举类不能被混淆
-keepclasseswithmembers enum * {
    public static **[] values;
    public static ** valueOf(java.lang.String);
}

# 保留自定义控件(继承自View)不能被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context,android.util.AttributeSet);
    public <init>(android.content.Context,android.util.AttributeSet,int);
}

# 保留Parcelable序列化的类不被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}

# 保留Serializable序列化的类不被混淆
-keepclasseswithmembers class * implements java.io.Serializable{
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 对于R(资源)下的所有类及其方法，都不能被混淆
-keep class **.R$* {
    *;
}

# 对于带有回调函数onXXEvent的，不能被混淆
-keepclasseswithmembers class * {
    void *(**On*Event);
}

-keep class com.exsun.meizi.BuildConfig { *; }
-keep public class * extends android.os.Binder

# Keep the support library
-keep class android.support.** { *; }
-keep interface android.support.** { *; }



#===========================================================
#2.针对本app的量身定制的规则

# a.保留实体类和成员不要被混淆
-keep public class com.exsun.meizi.entity.**{
    public void set*(***);
    public *** get*();
    public *** is*();
}

# b.对内嵌类的混淆处理,例如假若HomeActivity里有内嵌类，那么处理如下
-keep class com.exsun.meizi.ui.home.activity.HomeActivity$* {*;}

# c.对webview的混淆处理，如果项目中用到了WebView的复杂操作,加入如下混淆代码
-keepclasseswithmembers class * extends android.webkit.webViewClient{
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclasseswithmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, java.lang.String);
}

# d.对JavaScript的混淆处理,对所有添加addJavascriptInterface注解类混淆
# 假若JSInterface是HomeActivity的子类，要保留js调用原生的方法不被混淆
-keepclasseswithmembers class com.exsun.meizi.ui.home.activity.HomeActivity$JSInterface{
    <methods>;
}

# 使用注解
-keepattributes *Annotation*,Signature
#
# 保持混淆时类的实名及行号(——————— 调试时打开 ———————)
-keepattributes SourceFile,LineNumberTable
#
# For using GSON @Expose annotation
-keepattributes *Annotation*
#
-keepattributes EnclosingMethod
#
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# MPermission
-dontwarn com.zhy.m.**
-keep class com.zhy.m.** {*;}
-keep interface com.zhy.m.** { *; }
-keep class **$$PermissionProxy { *; }

# AgentWeb
-keep class com.just.library.** {
    *;
}
-dontwarn com.just.library.**

# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }

# eventbus
 -keepattributes *Annotation*
 -keepclassmembers class ** {
     @org.greenrobot.eventbus.Subscribe <methods>;
 }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }

 # Only required if you use AsyncExecutor
 -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
     <init>(java.lang.Throwable);
 }
 #jsoup
 -dontwarn org.jsoup.**
 -keep class org.jsoup.**{*;}



 -dontwarn okio.**

 #okhttp
 -dontwarn com.squareup.okhttp3.**
 -keep class com.squareup.okhttp3.** { *;}
 -dontwarn okio.**
 # Retrofit
 -dontwarn retrofit2.**
 -keep class retrofit2.** { *; }
 -keepattributes Exceptions
 # Retrolambda
 -dontwarn java.lang.invoke.*
 # RxJava RxAndroid
 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }
 ###rxandroid-1.2.1
 -keepclassmembers class rx.android.**{*;}
