# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/yuyuhang/Library/Android/sdk/tools/proguard/proguard-android.txt
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

#-ignorewarnings

#-keep class * {
#    public private *;
#}
#
#-keep interface * {
#    public private *;
#}

##1.这是所有app都必须填写混淆规则
##代码混淆压缩比，在0-7之间，默认为5一般不需要更改
#-optimizationpasses 5
#
##混淆时不使用大小写混合，混淆后的类名为小写
#-dontusemixedcaseclassnames
#
##指定不去忽略非公共的库的类
#-dontskipnonpubliclibraryclasses
#
##指定不去忽略非公共的库的类的成员
#-dontskipnonpubliclibraryclassmembers
#
##不做预检验，preverify是proguard的4个步骤之一
##Android不需要preverify，去掉这一步可以加快混淆速度
#-dontpreverify
#
##有了verbose这句话，混淆后就会生成映射文件，包含有类名->混淆后类名的映射关系
##然后使用printmapping指定映射文件的名称
#-verbose
#-printmapping proguardMapping.txt
#
##指定混淆时采用的算法，后面的参数是一个过滤器，一般不改变，这是谷歌推荐的算法
#-optimizations !code/simplification/arithmetic,!field/,!class/merging/
#
##保护代码中的Annotation不被混淆
#-keepattributes Annotation
#
##避免混淆泛型
#-keepattributes Signature
#
##抛出异常时保留代号行号
#-keepattributes SourceFile,LineNumberTable
#
## 保留所有的本地native方法不被混淆
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
##
### 所有继承自Activity/Application/Service/BroadcastReceiver
### /ContentProvider/Throwable/Exception 不要被混淆
#-keep public class * extends android.app.Activity
#-keep public class * extends android.support.v4.app.Fragment
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends java.lang.Throwable {*;}
#-keep public class * extends java.lang.Exception {*;}
##
### 保留在Activity中的方法参数是view的方法
### 从而保证我们在layout里面编写onClick就不会被影响
#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}
##
### 枚举类不能被混淆
#-keepclasseswithmembers enum * {
#    public static **[] values;
#    public static ** valueOf(java.lang.String);
#}
##
### 保留自定义控件(继承自View)不能被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context,android.util.AttributeSet);
    public <init>(android.content.Context,android.util.AttributeSet,int);
}
##
### 保留Parcelable序列化的类不被混淆
#-keep class * implements android.os.Parcelable{
#    public static final android.os.Parcelable$Creator *;
#}
##
### 保留Serializable序列化的类不被混淆
-keepclasseswithmembers class * implements java.io.Serializable{
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
##
## 对于R(资源)下的所有类及其方法，都不能被混淆
-keep class **.R$* {
    *;
}

-keep class com.yuyh.library.utils.**{*;}

-keep class com.yuyh.library.Base.**{*;}

#-keep class * implements com.yuyh.library.Base.BaseModel{
#    *;
#}

##
#-keep class com.exsun.meizi.BuildConfig { *; }
#-keep public class * extends android.os.Binder
##
## Keep the support library
#-keep class android.support.** { *; }
#-keep interface android.support.** { *; }
##
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#    **[] $VALUES;
#    public *;
#}
#-dontwarn com.squareup.okhttp.**
##
#-keep class butterknife.** { *; }
#-dontwarn butterknife.internal.**
#-keep class **$$ViewBinder { *; }
##
#-keepclasseswithmembernames class * {
#    @butterknife.* <fields>;
#}
#
#-keepclasseswithmembernames class * {
#    @butterknife.* <methods>;
#}
##
##-keepattributes *Annotation*
##-keepclassmembers class ** {
##    @com.squareup.otto.Subscribe public *;
##    @com.squareup.otto.Produce public *;
##}
#
##
##-keep public class * implements com.bumptech.glide.module.GlideModule
##-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
##    **[] $VALUES;
##    public *;
##}
##
### Keep the support library
##-keep class android.support.** { *; }
##-keep interface android.support.** { *; }
##
## For RxJava:
#-dontwarn rx.**
#-dontwarn org.mockito.**
#-dontwarn org.junit.**
#-dontwarn org.robolectric.**
#-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
#    long producerIndex;
#    long consumerIndex;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
#    long producerNode;
#    long consumerNode;
#}
#
#-keepattributes Signature
#-keepattributes *Annotation*
-dontwarn okio.**