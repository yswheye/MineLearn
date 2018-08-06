## Android 

### Android新特性总结
---
##### Android O（8.0）
* 通知渠道 — Notification Channels  
通知渠道是由应用自行定义的通知内容类别，借助渠道，开发者可以让用户对不同种类的通知进行精细控制，用户可以单独拦截或更改每个渠道的行为，而不是统一管理应用的所有通知。

* 画中画 PIP  
Android O 现已支持 Activity 的画中画模式。PIP 是一种多窗口显示模式，多用于视频播放，即你可以一边发微信一边看视频。  
> PIP 模式不会改变 Activity 的生命周期。在指定时间只有最近与用户交互过的 Activity 为活动状态。 该 Activity 将被视为顶级 Activity。 所有其他 Activity 虽然可见，但均处于暂停状态。当一个 Activity 处于 PIP 模式时，其实它是出在暂停状态，但其内容会继续展示。 
>  
> 在 Android O 中新增 PictureInPictureArgs 对象来指明你的 Activity 在 PIP 模式中的属性，比如长宽比等。
Android O 还新增了以下方法来支持 PIP。
Activity.enterPictureInPictureMode(PictureInPictureArgs args)：将Activity置于 PIP 模式之下。
Activity.setPictureInPictureArgs()：用于更新 Activity 在 PIP 模式下的设置。如果 Activity 正处于 PIP 模式之下，那么更改的属性将立即生效。

* 自适应图标 — Adaptive Icons  
Adaptive icons也是一项有趣的新功能，谷歌正在尝试整理Android中不一致的应用程序图标形状，这一功能为应用程序开发人员提供了适应其显示设备的每个图标的多个形状模板。因此，如果你的手机默认应用程序图示形状是圆角正方形，那么所有应用程序的图标都将是这个形状（前提是开发人员使用了这一功能）。也就是说，你将不再看到系统主屏上方形图标和圆形图标混合在一起的现象。
* 固定快捷方式和小部件 — Pinning shortcuts  
Pinning shortcuts 是一个比 APP shortcuts 更小的快捷方式，放置于桌面上，用于更快速的打开某一 APP 的某单一任务。Pinning shortcuts 在桌面上可呈现不同的图标显示。
* TensorFlow Lite  
谷歌机器学习工具移动版

* 其他  
	* 在性能优化上，后台进程限制，Android O 还对隐式广播、后台服务和位置更新等进行了后台自动限制，以此来增加手机电池寿命。   
	* 智能文本选择（Smart Text Selection）  
	* 自动填写（Auto-Fill）

##### Android M&N（6.0&7.0）
* 运行时权限管理  
Android把权限分为了敏感权限与非敏感权限，对于非敏感权限，开发者同样是在AndroidMainifest中进行权限申请，这些权限会在Android App安装的时候显示出来，与现在一样，而敏感权限，则会通过Dialog的方式在使用时弹出
* 多窗口支持  
分屏，画中画
* Chrome App Links，Deep Linking   
在Web中使用Scheme来进行App的跳转
* 通知   
通知样式，分组，快捷回复
* 支持文本选择

### Android动画类型
---
* 帧动画  
一帧一帧的图片产生视觉动画   
xml中定义animation-list
* 属性动画  
通过动态改变对象的属性达到动画效果 
	* ValueAnimator  
	ValueAnimator属性动画中的时间驱动，管理着动画时间的开始、结束属性值，相应时间属性值计算方法等。包含所有计算动画值的核心函数以及每一个动画时间节点上的信息、一个动画是否重复、是否监听更新事件等，并且还可以设置自定义的计算类型。  

		>特别注意：ValueAnimator只是动画计算管理驱动，设置了作用目标，但没有设置属性，需要通过updateListener里设置属性才会生效。
		
	* ObjectAnimator  
	继承自ValueAnimator   
ObjectAnimator类提供了ofInt、ofFloat、ofObject这个三个常用的方法，这些方法都是设置动画作用的元素、属性、开始、结束等任意属性值。当属性值（上面方法的参数）只设置一个时就把通过getXXX反射获取的值作为起点，设置的值作为终点；如果设置两个（参数），那么一个是开始、另一个是结束。  

		>特别注意：ObjectAnimator的动画原理是不停的调用setXXX方法更新属性值，所有使用ObjectAnimator更新属性时的前提是Object必须声明有getXXX和setXXX方法。
		
	* AnimationSet  
	动画集合，提供把多个动画组合成一个组合的机制，并可设置动画的时序关系，如同时播放、顺序播放或延迟播放。具体使用方法比较简单
* 补间动画  
通过平移、旋转、缩放、透明度的变化  
四个动画效果实现类：  
TranslateAnimation、ScaleAnimation、RotateAnimation、AlphaAnimation  
补间动画只能用于View对象  
补间动画只是改变View的显示效果，但是不会真正改变View的属性  

	> 比如单击事件，通过补间动画移动之后，事件还是作用在原来的位置，补间动画只是将其绘制到了屏幕的其他地方。


### Activity启动模式
---
##### standard标准启动模式  
也是Activity的启动模式，以这种模式启动的Activity会新new一个Activity对象并放入Activity堆栈，在这种模式下允许一个Activity类有多个实例，并且可互相叠加
##### singleTop模式
在一个Activity堆栈中允许存在多个实例，比如启动一个Activity，如果该Activity不存在，那么就类似standard模式；如果当前堆栈中已经存在一个Activity实例，但是不在栈顶，那也会新new一个实例，然后put到栈顶；如果当前已经有Activity在栈顶，那就不会再new一个新的Activity，而是直接回调这个Activity的onNewIntent
> FLAG\_ACTIVITY\_SINGLE_TOP

##### singleTask模式
在一个Activity堆栈中只允许存在一个Activity的实例，比如启动一个Activity，如果这个Activity不存在，则跟standard模式一样，生成新的实例，然后put到堆顶；如果这个Activity已经存在于栈中，那么会把该Activity之上的Activity都destroy掉，然后把该Activity显示出来，并回调onNewIntent方法
> FLAG\_ACTIVITY\_CLEAR_TOP

##### singleInstance模式
是只允许有一个实例，而且是运行在自己单独的一个Activity堆栈中的，并且这个堆栈只允许有这个Activity，不能有其他的Activity

FLAG\_ACTIVITY\_NO_HISTORY,  
当活动执行onStop()就会出栈。

##### Activity中的栈
Android的管理主要是通过Activity栈来进行，当一个Activity启动时，系统会根据其配置将它压入到一个特定的栈中，系统处于运行状态。当用户点击返回或则Finish()了该Activity，那么它便会被从栈中压出，随之摧毁，按照Activity的生命周期可以知道，如果当前显示的栈中Activity没有被摧毁，那么打开新的Activity时候，会将新打开的压入到栈，原来的根据其显示情况选择状态变化（原Activity依旧可见，变为暂停状态（Paused），如果被完成遮住了，转变为停止状态（Stopped））。

##### Activity栈和Task联系
Task简单的就是一组以栈的模式聚集在一起的Activity组件集合，类似于一个填充了Activity的容器，最先加入的Activity会处于容器最下面，最后加入的处于容器最上面，而从Task中取出Activity是从最顶端先取出，最后取出的是最开始添加Activity，这就是后进先出（Last In First Out）模式，而Activity在Task中的顺序是可以控制的，在Activity跳转时用到Intent Flag可以设置新建activity的创建方式（这里就涉及到了Intent Flag的使用）。
 > 查看当前系统的任务栈  
 > adb shell dumpsys activity

##### taskAffinity
如果没有对activity设置该属性的话,默认为application的taskAffinity,如果application也没有设置,则为app的包名.
 
### 如何加载一张大图，一张图片占用的内存大小
---
BitmapRegionDecoder
	
##### 图片内存大小跟图片的大小有什么关系？
两个概念  
图片的大小一般指图片在磁盘上的暂用空间，存储在磁盘上的图片（JPG，PNG等格式）是被压缩的。一旦将图片加载到内存中，它就不在被压缩，并占用尽可能多的图片所有像素所需的空间。

##### 一张图片占用多少内存？
* bitmap占用内存大小，与手机的**屏幕密度**、**图片所放文件夹密度**、**图片的色彩格式**有关。
* **mdpi此为baseline，其他均以此为基准，在此设备上，1dp = 1px**  
据【px = dip * density / 160】，则当屏幕密度为160时，px = dip
* 图片放在drawable中，等同于放在drawable-mdpi中，原因为：drawable目录不具有屏幕密度特性，所以采用基准值，即mdpi
* 图片放在某个特定drawable中，比如drawable-hdpi，如果设备的屏幕密度高于当前drawable目录所代表的密度，则图片会被放大，否则会被缩小，放大或缩小比例 = 设备屏幕密度 / drawable目录所代表的屏幕密度
* 为了更全面的适配所有设备，我们应该提供一套针对主流屏幕密度的图片（目前为hdpi或xhdpi），其他密度通过系统自动缩放得到图片 

|density（屏幕密度）||1|1.5|2|3|3.5|4|
---|---|---|---|---|---|---|---
densityDpi|120|160|240|320|480|560|640
文件夹|ldpi|**mdpi**|hdpi|xhdpi|xxhdpi|xxxhdpi|xxxxhdpi


|图片格式（Bitmap.Config）|占用内存的大小|描述|
--------|-------|------
ALPHA_8|2字节|只有一个alpha通道
ARGB_4444|2字节|这个从API 13开始不建议使用，因为质量太差。已废弃。
ARGB_8888|4字节|ARGB四个通道，每个通道8bit
RGB_565|2字节|每个像素占2Byte，其中红色占5bit，绿色占6bit，蓝色占5bit

图片采用的ARGB-8888色彩格式，每个像素点占用4个字节。      
1. 图片占用内存的计算公式：图片高度 * 图片宽度 * 一个像素占用的内存大小  
2. 所以上面的图片占用内存是：800 * 600 * 4 byte = 1875KB = 1.83M   

##### 图片所在目录对内存的影响？
在Android中，图片的存放目录和手机的屏幕密度影响图片最终的大小，举个例子：  
假设我们的图片放到xhdpi目录下，那么我们本文中的图片占用的内存大小如下：  
屏幕密度为2的设备：800 * 600 * 4byte = 1.83M  
屏幕密度为3的设备：800 * 1.5 * 600 * 1.5 * 4byte = 1.83 * 2.25M = 4.12M  
所以，计算图片占用内存大小的时候，要考虑图片所在的目录跟设备密度，这两个因素其实影响的是图片的高宽，android会对图片进行拉升跟压缩。

使用.9图片  
使用drawable.xml来定义  
内存中压缩图片 inSampleSize,inJustDecodeBounds,compress  
用完就回收

### Android事件传递分发
---
事件分发的对象：Touch事件  
事件传递的顺序：Activity -> ViewGroup -> View  
事件触发优先级：onTouch -> onTouchEvent -> onClick  
事件传递三个阶段：分发(dispatchTouchEvent)，拦截(onInterceptTouchEvent)，消费(onTouchEvent)  
	
* getParent().requestDisallowInterecptTouchEvent()，请求父view不拦截事件
* dispatchTouchEvent和onInterceptTouchEvent属于消费事件，返回true消费，false不消费

##### dispatchTouchEvent
* true，消费掉不再分发
* false，不消费，调用上一层onTouchEvent
* super，不消费继续分发，如果是ViewGroup则调用onInterceptTouchEvent

##### onInterceptTouchEvent↓
* true，拦截自身消费掉，调用自身onTouchEvent
* false/super，不拦截，调用下一级dispatchTouchEvent

##### onTouchEvent↑
* true，自己消费掉
* false/super，不消费，调用上一级onTouchEvent

![](https://upload-images.jianshu.io/upload_images/944365-aea821bbb613c195.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

![](https://upload-images.jianshu.io/upload_images/944365-ff627fea1a2244ad.png)

	//获取坐标
	MotionEvent.obtain(event).getX();
	MotionEvent.obtain(event).getY();
	
	//判断用户在进行滑动操作的最小距离
	ViewConfiguration.get(context).getScaledTouchSlop();
	
	//请求父view不拦截事件
	getParent().requestDisallowInterecptTouchEvent()

### 内存泄漏
---
内存泄漏是指应用不再使用的内存对象，但垃圾回收时没有把它辨认出来，不能及时回收，一直在内存中占用空间，不能释放给其他对象。  
频繁的GC操作会导致应用卡顿。   
内存泄漏最终会导致OOM（内存溢出）  

GC机制  
Android系统虚拟机通过GC机制来完成垃圾回收。GC会选择一些还存活的对象作为内存遍历的根节点GC Roots，通过GC Roots的可达性来判断是否需要回收。GC Roots是系统选择的对象根节点，没有被直接或间接遍历到的引用会被GC回收，能遍历到的不回收。

##### 常见的内存泄漏   

1. 单例造成的内存泄漏  
由于单例的静态特性使得其生命周期和应用的生命周期一样长，如果一个对象已经不再需要使用了，而单例对象还持有该对象的引用，就会使得该对象不能被正常回收，从而导致了内存泄漏。  
示例：防止单例导致内存泄漏的实例

		// 使用了单例模式
		public class AppManager {
		    private static AppManager instance;
		    private Context context;
		    private AppManager(Context context) {
		        this.context = context;
		    }
		    public static AppManager getInstance(Context context) {
		        if (instance != null) {
		        	//like this
		            instance = new AppManager(context.getApplicationContext());
		        }
		        return instance;
		    }
		}
	这样不管传入什么Context最终将使用Application的Context，而单例的生命周期和应用的一样长。  
	根本原因是：  
	**长生命周期对象持有短生命周期对象，导致短生命周期对象不能被回收。**

2. 非静态内部类创建静态实例造成的内存泄漏  
例如，有时候我们可能会在启动频繁的Activity中，为了避免重复创建相同的数据资源，可能会出现如下写法：

		public class MainActivity extends AppCompatActivity {
	
		    private static TestResource mResource = null;
		
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_main);
		        if(mResource == null){
		            mResource = new TestResource();
		        }
		        //...
		    }
		    
		    class TestResource {
		    	//...
		    }
		}
这样在Activity内部创建了一个非静态内部类的单例，每次启动Activity时都会使用该单例的数据。虽然这样避免了资源的重复创建，但是这种写法却会造成内存泄漏。**因为非静态内部类默认会持有外部类的引用**，而该非静态内部类又创建了一个静态的实例，该实例的生命周期和应用的一样长，这就导致了该静态实例一直会持有该Activity的引用，从而导致Activity的内存资源不能被正常回收。  
解决方法：将该内部类设为静态内部类或将该内部类抽取出来封装成一个单例，如果需要使用Context，就使用Application的Context。

3. Handler造成的内存泄漏  
示例：创建匿名内部类的静态对象

		public class MainActivity extends AppCompatActivity {
		
		    private final Handler handler = new Handler() {
		        @Override
		        public void handleMessage(Message msg) {
		            // ...
		        }
		    };
		
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_main);
		
		        new Thread(new Runnable() {
		            @Override
		            public void run() {
		                // ...
		                handler.sendEmptyMessage(0x123);
		            }
		        });
		    }
		}

	1. 从Android的角度  
	当Android应用程序启动时，该应用程序的主线程会自动创建一个Looper对象和与之关联的MessageQueue。当主线程中实例化一个Handler对象后，它就会自动与主线程Looper的MessageQueue关联起来。所有发送到MessageQueue的Messag都会持有Handler的引用，所以Looper会据此回调Handle的handleMessage()方法来处理消息。只要MessageQueue中有未处理的Message，Looper就会不断的从中取出并交给Handler处理。另外，主线程的Looper对象会伴随该应用程序的整个生命周期。
	2. Java角度  
在Java中，非静态内部类和匿名类内部类都会潜在持有它们所属的外部类的引用，但是静态内部类却不会。

	对上述的示例进行分析，当MainActivity结束时，未处理的消息持有handler的引用，而handler又持有它所属的外部类也就是MainActivity的引用。这条引用关系会一直保持直到消息得到处理，这样阻止了MainActivity被垃圾回收器回收，从而造成了内存泄漏。  
	
	解决方法：  
	1. 使用一个静态的Handler内部类，然后对Handler持有的对象使用弱引用，这样再回收时，也可以回收Handler持有的对象。  
	2. 在Activity的onDestroy或stop时，及时移除消息队列中的消息，避免Looper线程的消息队列中有待处理的消息。

		public class NoLeakActivity extends AppCompatActivity {
		
		    private NoLeakHandler mHandler;
		
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		
		        mHandler = new NoLeakHandler(this);
		
		        Message message = Message.obtain();
		
		        mHandler.sendMessageDelayed(message,10*60*1000);
		    }
		
		    private static class NoLeakHandler extends Handler{
		        private WeakReference<NoLeakActivity> mActivity;
		
		        public NoLeakHandler(NoLeakActivity activity){
		            mActivity = new WeakReference<>(activity);
		        }
		
		        @Override
		        public void handleMessage(Message msg) {
		            super.handleMessage(msg);
		            NoLeakActivity act = mActivity.get();
		        }
		    }
		}

4. 线程造成的内存泄漏  
示例：AsyncTask和Runnable

		public class MainActivity extends AppCompatActivity {
		
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_main);
		
		        new Thread(new MyRunnable()).start();
		        new MyAsyncTask(this).execute();
		    }
		
		    class MyAsyncTask extends AsyncTask<Void, Void, Void> {
		
		        // ...
		
		        public MyAsyncTask(Context context) {
		            // ...
		        }
		
		        @Override
		        protected Void doInBackground(Void... params) {
		            // ...
		            return null;
		        }
		
		        @Override
		        protected void onPostExecute(Void aVoid) {
		            // ...
		        }
		    }
		
		    class MyRunnable implements Runnable {
		        @Override
		        public void run() {
		            // ...
		        }
		    }
		}
AsyncTask和Runnable都使用了匿名内部类，那么它们将持有其所在Activity的隐式引用。如果任务在Activity销毁之前还未完成，那么将导致Activity的内存资源无法被回收，从而造成内存泄漏。  
解决方法：将AsyncTask和Runnable类独立出来或者使用静态内部类，这样便可以避免内存泄漏。

5. 资源未关闭造成的内存泄漏  
对于使用了BraodcastReceiver，ContentObserver，File，Cursor，Stream，Bitmap等资源，应该在Activity销毁时及时关闭或者注销，否则这些资源将不会被回收，从而造成内存泄漏。

	1. 比如在Activity中register了一个BraodcastReceiver，但在Activity结束后没有unregister该BraodcastReceiver。
	2. 资源性对象比如Cursor，Stream、File文件等往往都用了一些缓冲，我们在不使用的时候，应该及时关闭它们，以便它们的缓冲及时回收内存。它们的缓冲不仅存在于 java虚拟机内，还存在于java虚拟机外。如果我们仅仅是把它的引用设置为null，而不关闭它们，往往会造成内存泄漏。
	3. 对于资源性对象在不使用的时候，应该调用它的close()函数将其关闭掉，然后再设置为null。在我们的程序退出时一定要确保我们的资源性对象已经关闭。
	4. Bitmap对象不在使用时调用recycle()释放内存。2.3以后的bitmap应该是不需要手动recycle了，内存已经在java层了。

6. 使用ListView时造成的内存泄漏  
初始时ListView会从BaseAdapter中根据当前的屏幕布局实例化一定数量的View对象，同时ListView会将这些View对象缓存起来。当向上滚动ListView时，原先位于最上面的Item的View对象会被回收，然后被用来构造新出现在下面的Item。这个构造过程就是由getView()方法完成的，getView()的第二个形参convertView就是被缓存起来的Item的View对象（初始化时缓存中没有View对象则convertView是null）。  
构造Adapter时，没有使用缓存的convertView。  
解决方法：在构造Adapter时，使用缓存的convertView。

7. 集合容器中的内存泄露  
我们通常把一些对象的引用加入到了集合容器（比如ArrayList）中，当我们不需要该对象时，并没有把它的引用从集合中清理掉，这样这个集合就会越来越大。如果这个集合是static的话，那情况就更严重了。  
解决方法：在退出程序之前，将集合里的东西clear，然后置为null，再退出程序。

8. WebView造成的泄露  
当我们不要使用WebView对象时，应该调用它的destory()函数来销毁它，并释放其占用的内存，否则其长期占用的内存也不能被回收，从而造成内存泄露。  
解决方法：为WebView另外开启一个进程，通过AIDL与主线程进行通信，WebView所在的进程可以根据业务的需要选择合适的时机进行销毁，从而达到内存的完整释放。

##### 如何检查和分析内存泄漏？
因为内存泄漏是在堆内存中，所以对我们来说并不是可见的。通常我们可以借助MAT、LeakCanary等工具来检测应用程序是否存在内存泄漏。  

1. MAT是一款强大的内存分析工具，功能繁多而复杂。  
2. LeakCanary则是由Square开源的一款轻量级的第三方内存泄漏检测工具，当检测到程序中产生内存泄漏时，它将以最直观的方式告诉我们哪里产生了内存泄漏和导致谁泄漏了而不能被回收。

##### 如何避免内存泄漏？
1. Context的正确使用  
在涉及使用Context时，对于生命周期比Activity长的对象应该使用Application的Context。凡是使用Context优先考虑Application的Context，当然它并不是万能的。
2. 对于需要在静态内部类中使用非静态外部成员变量（如：Context、View)，可以在静态内部类中使用弱引用来引用外部类的变量来避免内存泄漏。
3. 对于不再需要使用的对象，显示的将其赋值为null，比如使用完Bitmap后先调用recycle()，再赋为null。
4. 保持对对象生命周期的敏感，特别注意单例、静态对象、全局性集合等的生命周期。
5. 对于生命周期比Activity长的内部类对象，并且内部类中使用了外部类的成员变量，可以这样做避免内存泄漏：
	1. 将内部类改为静态内部类
	2. 静态内部类中使用弱引用来引用外部类的成员变量
6. 构造单例的时候尽量不用Activity的引用
7. 静态引用时注意应用对象的置空或者少用静态引用
8. 使用静态内部类+软引用代替非静态内部类（Handler）
9. 耗时任务、属性动画在Activity销毁时记得cancel
10. 文件流、Cursor等资源及时关闭
11. Activity销毁时WebView的移除和销毁

### View渲染机制
### OkHttp，Rxjava源码分析
### Android插件化和热修复
### ListView和RecyclerView的原理和优化，区别
---
#### ListView的缓存机制
AbsListView, AdapterView  

AbsListView是继承自AdapterView，在该类中实现了一个非常重要的内部类RecycleBin，内部类RecycleBin其实就是AbsListView缓存机制的核心类，它的作用是管理AbsListView的item存储和取得。AbsListview的缓存分为两级，第一级为activeView，第二级为scrapview。二者的间的转换主要是在layoutChildren()方法进行(该抽象方法在LisView中实现)

缓存管理的核心在AbsListView的RecycleBin类  
final RecycleBin mRecycler = new RecycleBin();  
> 这个类是用来帮助在滑动布局时重用View的，RecycleBin包含了两个级别的存储，ActiveViews和ScrapViews，ActiveViews存储的是第一次显示在屏幕上的View；所有的ActiveViews最终都会被移到ScrapViews，ScrapViews存储的是有可能被adapter复用的View。
现在很明确了AbsListView缓存依赖于两个数组，一个数组存储屏幕上当前现实的ItemView，一个显示从屏幕下移除的且可能会被复用的ItemView。

> 如果数据发生变化则把当前的ItemView放入ScrapViews中，否则把当前显示的ItemView放入ActiveViews中。

同时AbsListview中定义了一个ObtainView方法，一般地当Listview加载时若发现没有可复用的itemView时要么从RecycleBin中转换ScrapView,要么是通过mAdapter.getView()获取新的itemView,ObtainView方法就是专门用来处理上述的两种情况。

#### ListView的优化
* convertView的复用

	listivew每次滚动都会调用gitview()方法，所以优化gitview是重中之重。这部分代码很简单，如果没有缓存就加载布局，如果有缓存就直接用convertView对象。所以这样就不用滑动listview的时候调用getView()方法每次都去加载布局了（如果该布局已经加载）。
	
		View view;
		if(convertView == null){
			view = LayoutInfalter.from(getContext()).inflate(resourceID，null)
		} else {
			
		}
		
* ViewHolder的使用

	主要优化getView方法中每次回调用findviewByID()方法来获取一次控件的代码。新增加内部类ViewHolder,用于对控件的实例存储进行缓存。  
	convertView为空时，viewHolder会将空间的实力存放在ViewHolder里，然后用setTag方法讲viewHolder对象存储在view里。  
	convertView不为空时，用getTag方法获取viewHolder对象.
	
		//getView核心代码
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			viewHolder.fruitImage = (ImageView) view.findViewByID(R.id.fruit_image);
			view.setTage(viewHolder);//讲ViewHolder存储在View中
		}else{
			viewHolder = ViewHolder view.getTag();//重获取viewHolder
		}
		viewHolder.fruitImage.setImageResource(fruit.getIMageID);
		
* 分页加载
* 滑动时不加载图片

		listView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView listView, int scrollState) {
                    //停止加载图片 
                    if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                            imageLoader.stopProcessingQueue();
                    } else {
                    //开始加载图片
                            imageLoader.startProcessingQueue();
                    }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    // TODO Auto-generated method stub

            }
    	});
* 将ListView的scrollingCache和animateCache设置为false

	scrollingCache:   
	scrollingCache本质上是drawing cache，你可以让一个View将他自己的drawing保存在cache中（保存为一个bitmap），这样下次再显示View的时候就不用重画了，而是从cache中取出。默认情况下drawing cahce是禁用的，因为它太耗内存了，但是它确实比重画来的更加平滑。而在ListView中，scrollingCache是默认开启的，我们可以手动将它关闭。

	animateCache:   
	ListView默认开启了animateCache，这会消耗大量的内存，因此会频繁调用GC，我们可以手动将它关闭掉
* 减少item的布局的深度

#### item分割线
ListView中设置 divider 非常简单，只需要在 XML 文件中设置就可以了，同时还可以设置divider 高度。

	android:divider="@android:color/black"
	android:dividerHeight="2dp"

而在RecyclerView里面，想实现这两种需求，稍微复杂一点，需要自己继承RecyclerView.ItemDecoration来实现想要实现的方法。
虽说这样写灵活多了，但是要额外写一个类去做难免麻烦，这里大家可以看我已经实现好的一个封装，包括显示纯色divider、显示图片divider、divider的上下左右的间距、宽高设置 应该可以满足基本需求了：[Divider.java](https://github.com/kymjs/CoreModule/blob/master/CoreModule/recycler/src/main/java/com/kymjs/recycler/Divider.java)，[使用方法。](https://github.com/kymjs/RecyclerViewDemo/blob/master/RecyclerViewDemo/sample/src/main/java/com/kymjs/sample/Demo4Activity.java)

##### ItemDecoration 工作原理
ItemDecoration 是为了显示每个 item 之间分隔样式的。它的本质实际上就是一个 Drawable。当 RecyclerView 执行到onDraw() 方法的时候，就会调用到他的 onDraw()，这时，如果你重写了这个方法，就相当于是直接在 RecyclerView 上画了一个 Drawable 表现的东西。 而最后，在他的内部还有一个叫getItemOffsets()的方法，从字面就可以理解，他是用来偏移每个 item 视图的。当我们在每个 item 视图之间强行插入绘画了一段 Drawable，那么如果再照着原本的逻辑去绘 item 视图，就会覆盖掉 Decoration 了，所以需要getItemOffsets()这个方法，让每个 item 往后面偏移一点，不要覆盖到之前画上的分隔样式了。

##### LayoutManager工作原理
	java.lang.Object  
	   ↳ android.view.View  
	        ↳ android.view.ViewGroup  
	            ↳ android.support.v7.widget.RecyclerView
首先是 RecyclerView 继承关系，可以看到，与 ListView 不同，他是一个 ViewGroup。既然是一个 View，那么就不可少的要经历onMeasure()、onLayout()、onDraw() 这三个方法。 实际上，RecyclerView 就是将onMeasure()、onLayout() 交给了 LayoutManager 去处理，因此如果给 RecyclerView 设置不同的 LayoutManager 就可以达到不同的显示效果，因为onMeasure()、onLayout()都不同了嘛。

##### ItemAnimator
每一个 item 在特定情况下都会执行的动画。说是特定情况，其实就是在视图发生改变，我们手动调用notifyxxxx()的时候。通常这个时候我们会要传一个下标，那么从这个标记开始一直到结束，所有 item 视图都会被执行一次这个动画。

##### Adapter工作原理
首先是适配器，适配器的作用都是类似的，用于提供每个 item 视图，并返回给 RecyclerView 作为其子布局添加到内部。
但是，与 ListView 不同的是，ListView 的适配器是直接返回一个 View，将这个 View 加入到 ListView 内部。而 RecyclerView 是返回一个 ViewHolder 并且不是直接将这个 holder 加入到视图内部，而是加入到一个缓存区域，在视图需要的时候去缓存区域找到 holder 再间接的找到 holder 包裹的 View。

##### ViewHolder
每个 ViewHolder 的内部是一个 View，并且 ViewHolder 必须继承自RecyclerView.ViewHolder类。这主要是因为 RecyclerView 内部的缓存结构并不是像 ListView 那样去缓存一个 View，而是直接缓存一个 ViewHolder ，在 ViewHolder 的内部又持有了一个 View。既然是缓存一个 ViewHolder，那么当然就必须所有的 ViewHolder 都继承同一个类才能做到了。

##### RecyclerView的缓存和复用
RecyclerView 的内部维护了一个二级缓存，滑出界面的 ViewHolder 会暂时放到 cache 结构中，而从 cache 结构中移除的 ViewHolder，则会放到一个叫做RecycledViewPool 的循环缓存池中。
顺带一说，RecycledView 的性能并不比 ListView 要好多少，它最大的优势在于其扩展性。但是有一点，在 RecycledView 内部的这个第二级缓存池RecycledViewPool是可以被多个 RecyclerView 共用的，这一点比起直接缓存 View 的 ListView 就要高明了很多，但也正是因为需要被多个 RecyclerView 公用，所以我们的 ViewHolder 必须继承自同一个基类(即RecyclerView.ViewHolder)。
默认的情况下，cache 缓存 2 个 holder，RecycledViewPool 缓存 5 个 holder。对于二级缓存池中的 holder 对象，会根据 viewType 进行分类，不同类型的 viewType 之间互不影响。

### AsyncTask源码解析
---
AsyncTask 是一个较为轻量级的异步任务类，在底层通过封装 ThreadPool 和 Handler，实现了线程的复用，后台任务执行顺序的控制、子线程和 UI 线程的切换，使得开发者可以以简单的方法来执行一些耗时任务

execute(Params)方法内部调用的是 executeOnExecutor(sDefaultExecutor, params)方法，当中 sDefaultExecutor用于定义任务队列的执行方式，AsyncTask 默认使用的是串行任务执行器。  
executeOnExecutor(Executor, Params)方法可以从外部传入自定义的任务执行器对象，例如可以传入 AsyncTask.THREAD\_POOL_EXECUTOR 使 AsyncTask 中的任务队列以并行的方式来完成。

**串行任务执行器(SerialExecutor)是如何执行的**  
每一个被提交的任务都会被加入任务队列 mTasks当中，mActive表示当前在执行的任务，每当有新任务 Runnable 到来时，就会在 Runnable 的外层多包裹一层Runnable，然后将之插入到任务队列中，当 execute(Runnable)方法第一次被执行时，mActive为null ，因此就会触发 scheduleNext()方法获取任务队列的第一个任务并提交给线程池 THREAD\_POOL_EXECUTOR进行处理，当r.run()方法返回时（即任务处理结束），在finally中又会获取下一个任务进行处理，从而实现了任务队列的串行执行

	//串行任务执行器，即提交给线程池的任务是按照顺序一个接一个被执行的
    private static class SerialExecutor implements Executor {

        //任务队列
        final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();

        //当前在执行的任务
        Runnable mActive;

        public synchronized void execute(final Runnable r) {
            //向任务队列尾端插入任务
            //在外部任务外部包装多一层 Runnable
            mTasks.offer(new Runnable() {
                public void run() {
                    try {
                        r.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            //如果当前没有在执行任务，则调取队列中的任务进行处理
            if (mActive == null) {
                scheduleNext();
            }
        }

        //获取队列的首个任务并处理
        protected synchronized void scheduleNext() {
            if ((mActive = mTasks.poll()) != null) {
                THREAD_POOL_EXECUTOR.execute(mActive);
            }
        }
    }

**几个相关方法的声明**

	//在子线程中被调用，用于执行后台任务
    @WorkerThread
    protected abstract Result doInBackground(Params... params);

    //在 UI 线程中被调用，在 doInBackground() 方法之前调用，用于在后台任务开始前做一些准备工作
    @MainThread
    protected void onPreExecute() {
    }

    //在 UI 线程中被调用，在 doInBackground() 方法之后调用，用于处理后台任务的执行结果
    //参数 result 是 doInBackground() 方法的返回值
    @SuppressWarnings({"UnusedDeclaration"})
    @MainThread
    protected void onPostExecute(Result result) {
    }

    //在 UI 线程中被调用，当调用了 publishProgress() 方法后被触发
    //用于更新任务进度值
    @SuppressWarnings({"UnusedDeclaration"})
    @MainThread
    protected void onProgressUpdate(Progress... values) {
    }

    //在 UI 线程中被调用
    //当调用了 cancel(boolean) 方法取消后台任务后会被调用
    //在 doInBackground() 方法结束时也会被调用
    //方法内部默认调用了 onCancelled() 方法
    @SuppressWarnings({"UnusedParameters"})
    @MainThread
    protected void onCancelled(Result result) {
        onCancelled();
    }

    //在 UI 线程中被调用，被 onCancelled(Result) 方法调用
    @MainThread
    protected void onCancelled() {
    }

看下 AsyncTask 类的三个构造函数。当中，除了无参构造函数，其他两个构造函数都使用 @hide注解隐藏起来了，因此我们在一般情况下只能使用调用无参构造函数来初始化 AsyncTask。  

	public AsyncTask() {
	        this((Looper) null);
	}
	
	/**
     * 隐藏的构造函数
     * 创建一个新的异步任务，必须在UI线程上调用此构造函数
     *
     * @hide
     */
    public AsyncTask(@Nullable Handler handler) {
        this(handler != null ? handler.getLooper() : null);
    }
    
    /**
     * 隐藏的构造函数
     * 创建一个新的异步任务，必须在UI线程上调用此构造函数
     * @hide
     */
    public AsyncTask(@Nullable Looper callbackLooper) {
    }
因此我们传给构造函数 AsyncTask(Looper) 的参数为 null ，因为 mHandler 变量其实是赋值为绑定了 UI 线程 Looper 的 InternalHandler 变量
因为 InternalHandler 绑定了 UI 线程的 Looper 对象，因此 handleMessage(Message)方法其实是在 UI 线程被执行，从而实现了子线程和 UI 线程之间的切换

	//按照正常情况来说，在初始化 AsyncTask 时我们使用的都是其无参构造函数
    //因此 InternalHandler 绑定的 Looper 对象即是与主线程关联的 Looper 对象
    //所以 InternalHandler 可以用来在 UI 线程回调某些抽象方法，例如 onProgressUpdate() 方法
    private static InternalHandler sHandler;

    //等于 sHandler
    private final Handler mHandler;

	private static class InternalHandler extends Handler {

        public InternalHandler(Looper looper) {
            super(looper);
        }

        @SuppressWarnings({"unchecked", "RawUseOfParameterizedType"})
        @Override
        public void handleMessage(Message msg) {
            AsyncTaskResult<?> result = (AsyncTaskResult<?>) msg.obj;
            switch (msg.what) {
                case MESSAGE_POST_RESULT:
                    //处理后台任务的执行结果
                    result.mTask.finish(result.mData[0]);
                    break;
                case MESSAGE_POST_PROGRESS:
                    //更新后台任务的进度
                    result.mTask.onProgressUpdate(result.mData);
                    break;
            }
        }
    }    

	/*********************************************/
	//获取与主线程关联的 Looper 对象，以此为参数构建一个 Handler 对象
    //所以在 Task 的运行过程中，能够通过此 Handler 在 UI 线程执行操作
    private static Handler getMainHandler() {
        synchronized (AsyncTask.class) {
            if (sHandler == null) {
                sHandler = new InternalHandler(Looper.getMainLooper());
            }
            return sHandler;
        }
    }


### Binder机制
---
Binder是Android系统进程间通信方式之一。  
Linux已经拥有的进程间通信IPC手段包括： 管道（Pipe）、信号（Signal）、跟踪（Trace）、插口（Socket）、报文队列（Message）、共享内存（Share Memory）和信号量（Semaphore）。

Binder框架定义了四个角色：Server，Client，ServiceManager以及Binder驱动。  
其中Server，Client，ServiceManager运行于用户空间，驱动运行于内核空间。Binder就是一种把这四个组件粘合在一起的粘结剂了，其中，核心组件便是Binder驱动程序了，Service Manager提供了辅助管理的功能，Client和Server正是在Binder驱动和Service Manager提供的基础设施上，进行Client-Server之间的通信。这四个角色的关系和互联网类似：Server是服务器，Client是客户终端，ServiceManager是域名服务器（DNS），驱动是路由器。

[Binder机制的设计和框架](https://blog.csdn.net/ccjhdopc/article/details/50829082)

#### Binder驱动存在的原因和意义
在回答"Binder机制中Binder驱动存在的原因和意义"之前，先介绍几个基本的概念。

1. Linux系统中的内存划分

	Android是基于Linux内核而打造的操作系统。
以32位Linux系统而言，它的内存最大是4G。在这4G内存中，0~3G为用户空间，3~4G为内核空间。应用程序都运行在用户空间，而Kernel和驱动都运行在内核空间。用户空间和内核空间若涉及到通信(即，数据交互)，两者不能简单地使用指针传递数据，而必须在"内核"中通过copy\_from\_user(),copy\_to\_user(),get\_user()或put\_user()等函数传递数据。copy\_from\_user()和get\_user()是将内核空间的数据拷贝到用户空间，而copy\_to\_user()和put\_user()则是将用户空间的数据拷贝到内核空间。

2. 进程的基本概念

	进程拥有独立的内存单元，它是系统进行资源分配和调度的基本单位。对于Linux系统而言，每一个运行在用户空间的应用程序都可以看作一个进程。
不同的进程在不同的内存中，因此当一个程序崩溃之后，不会对其它的程序造成影响。

	通过上面的"Linux的内存划分"和"进程"，我们可以了解到：应用程序都运行在用户空间，每个应用程序都有它自己独立的内存空间；若不同的应用程序之间涉及到通信，需要通过内核进行中转，因为需要用到内核的copy_from_user()和copy_to_user()等函数。 
现在，再回到上面的框架图中。图中的ServiceManager, MediaPlayerService和MediaPlayer都位于用户空间，它们是不同的进程。前面说过，Binder机制的最终目的是实现"MediaPlayerService和MediaPlayer这两个不同进程之间的通信"。而这两个不同进程的通信必须要内核进行中转，对于Android而言，在内核中起中转作用便是Binder驱动。那么Binder驱动是如何进行数据中转的呢？这里概括的介绍一下，后面再详细说明。 
Android的通信是基于Client-Server架构的，进程间的通信无非就是Client向Server发起请求，Server响应Client的请求。这里以发起请求为例：当Client向Server发起请求(例如，MediaPlayer向MediaPlayerService发起请求)，Client会先将请求数据从用户空间拷贝到内核空间(将数据从MediaPlayer发给Binder驱动)；数据被拷贝到内核空间之后，再通过驱动程序，将内核空间中的数据拷贝到Server位于用户空间的缓存中(Binder驱动将数据发给MediaPlayerService)。这样，就成功的将Client进程中的请求数据传递到了Server进程中。

	实际上，Binder驱动是整个Binder机制的核心。除了实现上面所说的数据传输之外，Binder驱动还是实现线程控制(通过中断等待队列实现线程的等待/唤醒)，以及UID/PID等安全机制的保证。

#### ServiceManager存在的原因和意义
Binder是要实现Android的C-S架构的，即Client-Server架构。而ServiceManager，是以服务管理者的身份存在的。

ServiceManager也是运行在用户空间的一个独立进程。  

1. 对于Binder驱动而言，ServiceManager是一个守护进程，更是Android系统各个服务的管理者。Android系统中的各个服务，都是添加到ServiceManager中进行管理的，而且每个服务都对应一个服务名。当Client获取某个服务时，则通过服务名来从ServiceManager中获取相应的服务。  
2. 对于MediaPlayerService和MediaPlayer而言，ServiceManager是一个Server服务端，是一个服务器。当要将MediaPlayerService等服务添加到ServiceManager中进行管理时，ServiceManager是服务器，它会收到MediaPlayerService进程的添加服务请求。当MediaPlayer等客户端要获取MediaPlayerService等服务时，它会向ServiceManager发起获取服务请求。

	当MediaPlayer和MediaPlayerService通信时，MediaPlayerService是服务端；而当MediaPlayerService则ServiceManager通信时，ServiceManager则是服务端。这样，就造就了ServiceManager的特殊性。于是，在Binder驱动中，将句柄0指定为ServiceManager对应的句柄，通过这个特殊的句柄就能获取ServiceManager对象。这部分的知识后面会详细介绍。

#### 为什么采用Binder机制，而不是其他的IPC通信方式
前面说过，Android是在Linux内核的基础上设计的。而在Linux中，已经拥有"管道/消息队列/共享内存/信号量/Socket等等"众多的IPC通信手段；但是，Google为什么单单选择了Binder，而不是其它的IPC机制呢？

这肯定是因为Binder具有无可比拟的优势。下面就从 "实用性(Client-Server架构)/传输效率/操作复杂度/安全性" 等几方面进行分析。

1. Binder能够很好的实现Client-Server架构

	对于Android系统，Google想提供一套基于Client-Server的通信方式。
例如，将"电池信息/马达控制/wifi信息/多媒体服务"等等不同的服务都由不同的Server提供，当Client需要获取某Server的服务时，只需要Client向Server发送相应的请求，Server收到请求之后进行处理，处理完毕再将反馈内容发送给Client。

	但是，目前Linux支持的"传统的管道/消息队列/共享内存/信号量/Socket等"IPC通信手段中，只有Socket是Client-Server的通信方式。但是，Socket主要用于网络间通信以及本机中进程间的低速通信，它的传输效率太低。

2. Binder的传输效率和可操作性很好  

	前面已经说了，Socket传输效率很低，已经被排除。而消息队列和管道又采用存储-转发方式，使用它们进行IPC通信时，需要经过2次内存拷贝！效率太低！

	为什么消息队列和管道的数据传输需要经过2次内存拷贝呢？ 首先，数据先从发送方的缓存区(即，Linux中的用户存储空间)拷贝到内核开辟的缓存区(即，Linux中的内核存储空间)中，是第1次拷贝。接着，再从内核缓存区拷贝到接收方的缓存区(也是Linux中的用户存储空间)，这是第2次拷贝。
而采用Binder机制的话，则只需要经过1次内存拷贝即可！ 即，从发送方的缓存区拷贝到内核的缓存区，而接收方的缓存区与内核的缓存区是映射到同一块物理地址的，因此只需要1次拷贝即可。

	至于共享内存呢，虽然使用它进行IPC通信时进行的内存拷贝次数是0。但是，共享内存操作复杂，也将它排除。

3. Binder机制的安全性很高

	传统IPC没有任何安全措施，完全依赖上层协议来确保。传统IPC的接收方无法获得对方进程可靠的UID/PID(用户ID/进程ID)，从而无法鉴别对方身份。而Binder机制则为每个进程分配了UID/PID来作为鉴别身份的标示，并且在Binder通信时会根据UID/PID进行有效性检测。
	

[简单理解Binder机制的原理](https://www.jianshu.com/p/4920c7781afe)
#### 为什么要使用Binder？
性能方面

在移动设备上（性能受限制的设备，比如要省电），广泛地使用跨进程通信对通信机制的性能有严格的要求，Binder相对出传统的Socket方式，更加高效。Binder数据拷贝只需要一次，而管道、消息队列、Socket都需要2次，共享内存方式一次内存拷贝都不需要，但实现方式又比较复杂。

安全方面

传统的进程通信方式对于通信双方的身份并没有做出严格的验证，比如Socket通信ip地址是客户端手动填入，很容易进行伪造，而Binder机制从协议本身就支持对通信双方做身份校检，因而大大提升了安全性。

#### IPC原理
从进程角度来看IPC机制

![](https://upload-images.jianshu.io/upload_images/3985563-a3722ee387793114.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/643)

每个Android的进程，只能运行在自己进程所拥有的虚拟地址空间。对应一个4GB的虚拟地址空间，其中3GB是用户空间，1GB是内核空间，当然内核空间的大小是可以通过参数配置调整的。对于用户空间，不同进程之间彼此是不能共享的，而内核空间却是可共享的。Client进程向Server进程通信，恰恰是利用进程间可共享的内核内存空间来完成底层通信工作的，Client端与Server端进程往往采用ioctl等方法跟内核空间的驱动进行交互。

#### Binder原理
Binder通信采用C/S架构，从组件视角来说，包含Client、Server、ServiceManager以及binder驱动，其中ServiceManager用于管理系统中的各种服务。架构图如下所示：

![](https://upload-images.jianshu.io/upload_images/3985563-5ff2c4816543c433.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

##### Binder通信的四个角色

* Client进程：使用服务的进程。
* Server进程：提供服务的进程。
* ServiceManager进程：ServiceManager的作用是将字符形式的Binder名字转化成Client中对该Binder的引用，使得Client能够通过Binder名字获得对Server中Binder实体的引用。
* Binder驱动：驱动负责进程之间Binder通信的建立，Binder在进程之间的传递，Binder引用计数管理，数据包在进程之间的传递和交互等一系列底层支持。

##### Binder运行机制

图中Client/Server/ServiceManage之间的相互通信都是基于Binder机制。既然基于Binder机制通信，那么同样也是C/S架构，则图中的3大步骤都有相应的Client端与Server端。

**注册服务(addService)**：Server进程要先注册Service到ServiceManager。该过程：Server是客户端，ServiceManager是服务端。

**获取服务(getService)**：Client进程使用某个Service前，须先向ServiceManager中获取相应的Service。该过程：Client是客户端，ServiceManager是服务端。

**使用服务**：Client根据得到的Service信息建立与Service所在的Server进程通信的通路，然后就可以直接与Service交互。该过程：client是客户端，server是服务端。

图中的Client,Server,Service Manager之间交互都是虚线表示，是由于它们彼此之间不是直接交互的，而是都通过与Binder驱动进行交互的，从而实现IPC通信方式。其中Binder驱动位于内核空间，Client,Server,Service Manager位于用户空间。Binder驱动和Service Manager可以看做是Android平台的基础架构，而Client和Server是Android的应用层，开发人员只需自定义实现client、Server端，借助Android的基本平台架构便可以直接进行IPC通信。

##### Binder运行的实例解释

首先我们看看我们的程序跨进程调用系统服务的简单示例，实现浮动窗口部分代码：

	//获取WindowManager服务引用
	WindowManager wm = (WindowManager)getSystemService(getApplication().WINDOW_SERVICE);  
	//布局参数layoutParams相关设置略...
	View view=LayoutInflater.from(getApplication()).inflate(R.layout.float_layout, null);  
	//添加view
	wm.addView(view, layoutParams);
	
**注册服务(addService)**：在Android开机启动过程中，Android会初始化系统的各种Service，并将这些Service向ServiceManager注册（即让ServiceManager管理）。这一步是系统自动完成的。

**获取服务(getService)**：客户端想要得到具体的Service直接向ServiceManager要即可。客户端首先向ServiceManager查询得到具体的Service引用，通常是Service引用的代理对象，对数据进行一些处理操作。即第2行代码中，得到的wm是WindowManager对象的引用。

**使用服务**：通过这个引用向具体的服务端发送请求，服务端执行完成后就返回。即第6行调用WindowManager的addView函数，将触发远程调用，调用的是运行在systemServer进程中的WindowManager的addView函数。

**使用服务的具体执行过程**

![](https://upload-images.jianshu.io/upload_images/3985563-727dd63017d2113b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

1. client通过获得一个server的代理接口，对server进行调用。
2. 代理接口中定义的方法与server中定义的方法时一一对应的。
3. client调用某个代理接口中的方法时，代理接口的方法会将client传递的参数打包成Parcel对象。
4. 代理接口将Parcel发送给内核中的binder driver。
5. server会读取binder driver中的请求数据，如果是发送给自己的，解包Parcel对象，处理并将结果返回。
6. 整个的调用过程是一个同步过程，在server处理的时候，client会block住。因此client调用过程不应在主线程。


### Handler消息分发机制
---
Android的消息机制主要是指Handler的运行机制。  
即，消息的创建，传递，处理机制。用来在线程中更新UI。

1. handler封装消息的发送（主要包括消息发送给谁）
2. Looper——消息封装的载体。  
	（1）内部包含一个MessageQueue，所有的Handler发送的消息都走向这个消息队列  
	（2）Looper.Looper方法，就是一个死循环，不断地从MessageQueue取消息，如果有消息就处理消息，没有消息就阻塞。
3. MessageQueue，一个消息队列，添加消息，处理消息
4. handler内部与Looper关联，handler->Looper->MessageQueue,handler发送消息就是向MessageQueue队列发送消息。
总结：handler负责发送消息，Looper负责接收handler发送的消息，并把消息回传给handler自己。  
MessageQueue存储消息的容器。

主线程ActivityThread类的main方法，程序的入口。
	
	public static void main(String[] args) {
        Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "ActivityThreadMain");

        // CloseGuard defaults to true and can be quite spammy.  We
        // disable it here, but selectively enable it later (via
        // StrictMode) on debug builds, but using DropBox, not logs.
        CloseGuard.setEnabled(false);

        Environment.initForCurrentUser();

        // Set the reporter for event logging in libcore
        EventLogger.setReporter(new EventLoggingReporter());

        // Make sure TrustedCertificateStore looks in the right place for CA certificates
        final File configDir = Environment.getUserConfigDirectory(UserHandle.myUserId());
        TrustedCertificateStore.setDefaultUserDirectory(configDir);

        Process.setArgV0("<pre-initialized>");

		//创建Looper和MessageQueue对象，用于处理主线程的消息
        Looper.prepareMainLooper();
		
		//创建ActivityThread对象
        ActivityThread thread = new ActivityThread();
        
        //建立Binder通道 (创建新线程)
        thread.attach(false);

        if (sMainThreadHandler == null) {
            sMainThreadHandler = thread.getHandler();
        }

        if (false) {
            Looper.myLooper().setMessageLogging(new
                    LogPrinter(Log.DEBUG, "ActivityThread"));
        }

        // End of event ActivityThreadMain.
        Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
        
        //消息循环运行
        Looper.loop();

        throw new RuntimeException("Main thread loop unexpectedly exited");
    }
    
所以，在ActivityThread创建的时候就会创建Looper以及Looper对应的MessageQueue。

ActivityThread通过ApplicationThread和AMS进行进程间通讯，AMS以进程间通信的方式完成ActivityThread的请求后会回调ApplicationThread中的Binder方法，然后ApplicationThread会向H发送消息，H收到消息后会将ApplicationThread中的逻辑切换到ActivityThread中去执行，即切换到主线程中去执行，这个过程就是。主线程的消息循环模型.

#### 子线程怎么使用Looper? 
新线程是没有开启消息循环的，所以需要用到Looper的方法创建消息循环（主线程除外，主线程会自动为其创建Looper对象，开启消息循环）  
Looper.prepare()//为当前线程创建一个Looper    
Looper.loop()//开启消息循环  
Looper.quit()//直接退出Looper  
quitSafely()//设置一个标记，把消息队列的所有消息处理完后才会退出  

对此，Android也为我们提供了一个HandlerThread类，方便我们快速的实现需求。
HandlerThread继承了Thread，它的run方法就是这么实现的。

	@Override
    public void run() {
        mTid = Process.myTid();
        Looper.prepare();
        synchronized (this) {
            mLooper = Looper.myLooper();
            notifyAll();
        }
        Process.setThreadPriority(mPriority);
        onLooperPrepared();
        Looper.loop();
        mTid = -1;
    }
    
##### HandlerThread的特点
* HandlerThread将loop转到子线程中处理，说白了就是将分担MainLooper的工作量，降低了主线程的压力，使主界面更流畅。
* 开启一个线程起到多个线程的作用。处理任务是串行执行，按消息发送顺序进行处理。HandlerThread本质是一个线程，在线程内部，代码是串行处理的。
* 但是由于每一个任务都将以队列的方式逐个被执行到，一旦队列中有某个任务执行时间过长，那么就会导致后续的任务都会被延迟处理。
* HandlerThread拥有自己的消息队列，它不会干扰或阻塞UI线程。
* 对于网络IO操作，HandlerThread并不适合，因为它只有一个线程，还得排队一个一个等着。

#### Looper死循环为什么不会导致卡顿？ 

> 对于线程即是一段可执行的代码，当可执行代码执行完成后，线程生命周期便该终止了，线程退出。而对于主线程，我们是绝不希望会被运行一段时间，自己就退出，那么如何保证能一直存活呢？简单做法就是可执行代码是能一直执行下去的，死循环便能保证不会被退出，例如，binder线程也是采用死循环的方法，通过循环方式不同与Binder驱动进行读写操作，当然并非简单地死循环，无消息时会休眠。但这里可能又引发了另一个问题，既然是死循环又如何去处理其他事务呢？通过创建新线程的方式。真正会卡死主线程的操作是在回调方法onCreate/onStart/onResume等操作时间过长，会导致掉帧，甚至发生ANR，looper.loop本身不会导致应用卡死。

主线程的死循环一直运行是不是特别消耗CPU资源呢？ 其实不然，这里就涉及到Linux pipe/epoll机制，简单说就是在主线程的MessageQueue没有消息时，便阻塞在loop的queue.next()中的nativePollOnce()方法里，此时主线程会释放CPU资源进入休眠状态，直到下个消息到达或者有事务发生，通过往pipe管道写端写入数据来唤醒主线程工作。这里采用的epoll机制，是一种IO多路复用机制，可以同时监控多个描述符，当某个描述符就绪(读或写就绪)，则立刻通知相应程序进行读或写操作，本质同步I/O，即读写是阻塞的。 所以说，主线程大多数时候都是处于休眠状态，并不会消耗大量CPU资源。 

#### Handler是如何能够线程切换？
Handler创建的时候会采用当前线程的Looper来构造消息循环系统，Looper在哪个线程创建，就跟哪个线程绑定，并且Handler是在他关联的Looper对应的线程中处理消息的。

那么Handler内部如何获取到当前线程的Looper呢—–ThreadLocal。ThreadLocal可以在不同的线程中互不干扰的存储并提供数据，通过ThreadLocal可以轻松获取每个线程的Looper。 
 
当然需要注意的是  
	①线程是默认没有Looper的，如果需要使用Handler，就必须为线程创建Looper。我们经常提到的主线程，也叫UI线程，它就是ActivityThread    
	②ActivityThread被创建时就会初始化Looper，这也是在主线程中默认可以使用Handler的原因。
	
#### 子线程有哪些更新UI的方法？
* 主线程中定义Handler，子线程通过mHandler发送消息，主线程Handler的handleMessage更新UI。
* 用Activity对象的runOnUiThread方法。
* 创建Handler，传入getMainLooper。
* View.post(Runnable r) 。
	
### App启动流程
---
一个APP从启动到主页面显示经历了哪些过程？
[文章链接，讲的挺好](https://www.jianshu.com/p/a72c5ccbd150)  
![](https://upload-images.jianshu.io/upload_images/3985563-b7edc7b70c9c332f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

#### 1. 启动流程概述
1. 点击桌面App图标，Launcher进程采用Binder IPC向system_server进程发起startActivity请求；
2. system_server进程接收到请求后，向zygote进程发送创建进程的请求；
3. Zygote进程fork出新的子进程，即App进程；
4. App进程，通过Binder IPC向sytem_server进程发起attachApplication请求；
5. system_server进程在收到请求后，进行一系列准备工作后，再通过binder IPC向App进程发送scheduleLaunchActivity请求；
6. App进程的binder线程（ApplicationThread）在收到请求后，通过handler向主线程发送LAUNCH_ACTIVITY消息；
7. 主线程在收到Message后，通过发射机制创建目标Activity，并回调Activity.onCreate()等方法。
8. 到此，App便正式启动，开始进入Activity生命周期，执行完onCreate/onStart/onResume方法，UI渲染结束后便可以看到App的主界面。

#### 2. 理论基础
1. zygote

	zygote意为“受精卵“。Android是基于Linux系统的，而在Linux中，所有的进程都是由init进程直接或者是间接fork出来的，zygote进程也不例外。

	在Android系统里面，zygote是一个进程的名字。Android是基于Linux System的，当你的手机开机的时候，Linux的内核加载完成之后就会启动一个叫“init“的进程。在Linux System里面，所有的进程都是由init进程fork出来的，我们的zygote进程也不例外。
	
	我们都知道，每一个App其实都是
		
	* 一个单独的dalvik虚拟机
	* 一个单独的进程

	所以当系统里面的第一个zygote进程运行之后，在这之后再开启App，就相当于开启一个新的进程。而为了实现资源共用和更快的启动速度，Android系统开启新进程的方式，是通过fork第一个zygote进程实现的。所以说，除了第一个zygote进程，其他应用所在的进程都是zygote的子进程，这下你明白为什么这个进程叫“受精卵”了吧？因为就像是一个受精卵一样，它能快速的分裂，并且产生遗传物质一样的细胞！

2. system_server

	SystemServer也是一个进程，而且是由zygote进程fork出来的。
知道了SystemServer的本质，我们对它就不算太陌生了，这个进程是Android Framework里面两大非常重要的进程之一——另外一个进程就是上面的zygote进程。

	为什么说SystemServer非常重要呢？因为系统里面重要的服务都是在这个进程里面开启的，比如
ActivityManagerService、PackageManagerService、WindowManagerService等等。

3. ActivityManagerService

	ActivityManagerService，简称AMS，服务端对象，负责系统中所有Activity的生命周期。
ActivityManagerService进行初始化的时机很明确，就是在SystemServer进程开启的时候，就会初始化ActivityManagerService。

	**下面介绍下Android系统里面的服务器和客户端的概念。**  
	其实服务器客户端的概念不仅仅存在于Web开发中，在Android的框架设计中，使用的也是这一种模式。服务器端指的就是所有App共用的系统服务，比如我们这里提到的ActivityManagerService，和前面提到的PackageManagerService、WindowManagerService等等，这些基础的系统服务是被所有的App公用的，当某个App想实现某个操作的时候，要告诉这些系统服务，比如你想打开一个App，那么我们知道了包名和MainActivity类名之后就可以打开

		Intent intent = new Intent(Intent.ACTION_MAIN);  
		intent.addCategory(Intent.CATEGORY_LAUNCHER);              
		ComponentName cn = new ComponentName(packageName, className);              
		intent.setComponent(cn);  
		startActivity(intent);
但是，我们的App通过调用startActivity()并不能直接打开另外一个App，这个方法会通过一系列的调用，最后还是告诉AMS说：“我要打开这个App，我知道他的住址和名字，你帮我打开吧！”所以是AMS来通知zygote进程来fork一个新进程，来开启我们的目标App的。这就像是浏览器想要打开一个超链接一样，浏览器把网页地址发送给服务器，然后还是服务器把需要的资源文件发送给客户端的。

	知道了Android Framework的客户端服务器架构之后，我们还需要了解一件事情，那就是我们的App和AMS(SystemServer进程)还有zygote进程分属于三个独立的进程，他们之间如何通信呢？  
App与AMS通过Binder进行IPC通信，AMS(SystemServer进程)与zygote通过Socket进行IPC通信。后面具体介绍。  
那么AMS有什么用呢？在前面我们知道了，如果想打开一个App的话，需要AMS去通知zygote进程，除此之外，其实所有的Activity的开启、暂停、关闭都需要AMS来控制，所以我们说，AMS负责系统中所有Activity的生命周期。

	在Android系统中，任何一个Activity的启动都是由AMS和应用程序进程（主要是ActivityThread）相互配合来完成的。AMS服务统一调度系统中所有进程的Activity启动，而每个Activity的启动过程则由其所属的进程具体来完成。

4. Launcher

	当我们点击手机桌面上的图标的时候，App就由Launcher开始启动了。但是，你有没有思考过Launcher到底是一个什么东西？
Launcher本质上也是一个应用程序，和我们的App一样，也是继承自Activity

		packages/apps/Launcher2/src/com/android/launcher2/Launcher.java
		
		public final class Launcher extends Activity
		        implements View.OnClickListener, OnLongClickListener, LauncherModel.Callbacks,
		                   View.OnTouchListener {
		                   }
Launcher实现了点击、长按等回调接口，来接收用户的输入。既然是普通的App，那么我们的开发经验在这里就仍然适用，比如，我们点击图标的时候，是怎么开启的应用呢？捕捉图标点击事件，然后startActivity()发送对应的Intent请求呗！是的，Launcher也是这么做的，就是这么easy！

5. Instrumentation和ActivityThread

	提到android自动化测试的时候经常会提到Instrumentation，但实际上Instrumentation是什么呢，很多人可能认为Instrumentation就是android的测试框架，实际上当启动一个app的时候都会实例化一个Instrumentation对象，且Instrumentation在每个Activity跳转的时候都会用到且其内部类ActivityMonitor会监控activity的，只是我们不直接使用它；另外Activity的生命周期方法也是通过它来调用的
	
	在自动化测试过程中我们不是直接使用Instrumentation而且使用其子类InstrumentationTestRunner
	
	**每个Activity都持有Instrumentation对象的一个引用，但是整个进程只会存在一个Instrumentation对象。**
	
	Instrumentation这个类里面的方法大多数和Application和Activity有关，这个类就是完成对Application和Activity初始化和生命周期的工具类。Instrumentation这个类很重要，对Activity生命周期方法的调用根本就离不开他，他可以说是一个大管家。

	ActivityThread，依赖于UI线程。App和AMS是通过Binder传递信息的，那么ActivityThread就是专门与AMS的外交工作的。

6. ApplicationThread

	前面我们已经知道了App的启动以及Activity的显示都需要AMS的控制，那么我们便需要和服务端的沟通，而这个沟通是双向的。

	客户端-->服务端
	
	而且由于继承了同样的公共接口类，ActivityManagerProxy提供了与ActivityManagerService一样的函数原型，使用户感觉不出Server是运行在本地还是远端，从而可以更加方便的调用这些重要的系统服务。

	服务端-->客户端
	
	还是通过Binder通信，不过是换了另外一对，换成了ApplicationThread和ApplicationThreadProxy。

	他们也都实现了相同的接口IApplicationThread

		  private class ApplicationThread extends ApplicationThreadNative {}
		
		  public abstract class ApplicationThreadNative extends Binder implements IApplicationThread{}
		
		  class ApplicationThreadProxy implements IApplicationThread {}

#### 3. 启动流程
Click事件会调用startActivity(Intent), 会通过Binder IPC机制, 最终调用到ActivityManagerService. 该Service会执行如下操作:

* 第一步通过PackageManager的resolveIntent()收集这个intent对象的指向信息.
* 指向信息被存储在一个intent对象中.
* 下面重要的一步是通过grantUriPermissionLocked()方法来验证用户是否有足够的权限去调用该intent对象指向的Activity.
* 如果有权限, ActivityManagerService会检查并在新的task中启动目标activity.
* 现在, 是时候检查这个进程的ProcessRecord是否存在了.
* 如果ProcessRecord是null, ActivityManagerService会创建新的进程来实例化目标activity.

> AMS主要是收集intent信息，验证权限，检查目标进程和activity是否存在等

##### 1. 创建进程

1. 先从Launcher的startActivity()方法，通过Binder通信，调用ActivityManagerService的startActivity方法。
2. 一系列折腾，最后调用startProcessLocked()方法来创建新的进程。
3. 该方法会通过前面讲到的socket通道传递参数给Zygote进程。Zygote孵化自身。调用ZygoteInit.main()方法来实例化ActivityThread对象并最终返回新进程的pid。
4. 调用ActivityThread.main()方法，ActivityThread随后依次调用Looper.prepareLoop()和Looper.loop()来开启消息循环。

方法调用流程图如下:  
![](https://upload-images.jianshu.io/upload_images/3985563-25c23ee6ccb48048.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)   

更直白的流程解释：  
![](https://upload-images.jianshu.io/upload_images/3985563-ed91fd7c240e6bd3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)  

1. App发起进程：当从桌面启动应用，则发起进程便是Launcher所在进程；当从某App内启动远程进程，则发送进程便是该App所在进程。发起进程先通过binder发送消息给system_server进程；
2. system_server进程：调用Process.start()方法，通过socket向zygote进程发送创建新进程的请求；
3. zygote进程：在执行ZygoteInit.main()后便进入runSelectLoop()循环体内，当有客户端连接时便会执行ZygoteConnection.runOnce()方法，再经过层层调用后fork出新的应用进程；
4. 新进程：执行handleChildProc方法，最后调用ActivityThread.main()方法。

##### 2. 绑定Application
上面创建进程后，执行ActivityThread.main()方法，随后调用attach()方法。

将进程和指定的Application绑定起来。这个是通过上节的ActivityThread对象中内部类ApplicationThread调用bindApplication()方法完成的。该方法发送一个BIND_APPLICATION的消息到消息队列中, 最终通过ActivityThread中的handleBindApplication()方法处理该消息. 然后调用makeApplication()方法来加载App的classes到内存中。

方法调用流程图如下：  
![](https://upload-images.jianshu.io/upload_images/3985563-0eb6b9d2b091de3b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

更直白的流程解释：  
![](https://upload-images.jianshu.io/upload_images/3985563-d8def9358f4646e1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

##### 3. 显示Activity界面
经过前两个步骤之后, 系统已经拥有了该application的进程。 后面的调用顺序就是普通的从一个已经存在的进程中启动一个新进程的activity了。

实际调用方法是realStartActivity(), 它会调用application线程对象中的scheduleLaunchActivity()发送一个LAUNCH_ACTIVITY消息到消息队列中, 通过 handleLaunchActivity()来处理该消息。在 handleLaunchActivity()通过performLaunchActiivty()方法回调Activity的onCreate()方法和onStart()方法，然后通过handleResumeActivity()方法，回调Activity的onResume()方法，最终显示Activity界面。

![](https://upload-images.jianshu.io/upload_images/3985563-5222775558226c7d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

更直白的流程解释：  
![](https://upload-images.jianshu.io/upload_images/3985563-5f711b4bca6bf21b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

##### 4. Binder通信
![](https://upload-images.jianshu.io/upload_images/3985563-cb3187996516846a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

图解:

1. system_server进程中调用startProcessLocked方法,该方法最终通过socket方式,将需要创建新进程的消息告知Zygote进程,并阻塞等待Socket返回新创建进程的pid;
2. Zygote进程接收到system_server发送过来的消息, 则通过fork的方法，将zygote自身进程复制生成新的进程，并将ActivityThread相关的资源加载到新进程app process,这个进程可能是用于承载activity等组件;
3. 在新进程app process向servicemanager查询system\_server进程中binder服务端AMS, 获取相对应的Client端,也就是AMP. 有了这一对binder c/s对, 那么app process便可以通过binder向跨进程system_server发送请求,即attachApplication()
4. system\_server进程接收到相应binder操作后,经过多次调用,利用ATP向app process发送binder请求, 即bindApplication.
system_server拥有ATP/AMS, 每一个新创建的进程都会有一个相应的AT/AMP,从而可以跨进程 进行相互通信. 这便是进程创建过程的完整生态链。

#### ActivityThread, ApplicationThread的关系？

	public final class ActivityThread
	
	private class ApplicationThread extends IApplicationThread.Stub
	
* ApplicationThread
	
	是ActivityThread的内部类。  
	ApplicationThread不是一个Thread,是一个Binder,主要用于应用进程和ActivityManagerService进程间通信的。  
	用来实现ActivityManagerService与ActivityThread之间的交互。在ActivityManagerService需要管理相关Application中的Activity的生命周期时，通过ApplicationThread的代理对象与ActivityThread通讯。
	
* ApplicationThreadProxy

	是ApplicationThread在服务器端的代理，负责和客户端的ApplicationThread通讯。AMS就是通过该代理与ActivityThread进行通信的。
	
* Instrumentation

	每一个应用程序只有一个Instrumentation对象，每个Activity内都有一个对该对象的引用。Instrumentation可以理解为应用进程的管家，ActivityThread要创建或暂停某个Activity时，都需要通过Instrumentation来进行具体的操作。

* ActivityStack

	Activity在AMS的栈管理，用来记录已经启动的Activity的先后关系，状态信息等。通过ActivityStack决定是否需要启动新的进程。
	
* ActivityRecord

	ActivityStack的管理对象，每个Activity在AMS对应一个ActivityRecord，来记录Activity的状态以及其他的管理信息。其实就是服务器端的Activity对象的映像。

### Activity的启动过程
---
[Activity启动过程全解析](https://www.jianshu.com/p/6037f6fda285)

1. 无论是通过Launcher来启动Activity，还是通过Activity内部调用startActivity接口来启动新的Activity，都通过Binder进程间通信进入到ActivityManagerService进程中，并且调用ActivityManagerService.startActivity接口； 

2. ActivityManagerService调用ActivityStack.startActivityMayWait来做准备要启动的Activity的相关信息；

3. ActivityStack通知ApplicationThread要进行Activity启动调度了，这里的ApplicationThread代表的是调用ActivityManagerService.startActivity接口的进程，对于通过点击应用程序图标的情景来说，这个进程就是Launcher了，而对于通过在Activity内部调用startActivity的情景来说，这个进程就是这个Activity所在的进程了；

4. ApplicationThread不执行真正的启动操作，它通过调用ActivityManagerService.activityPaused接口进入到ActivityManagerService进程中，看看是否需要创建新的进程来启动Activity；

5. 对于通过点击应用程序图标来启动Activity的情景来说，ActivityManagerService在这一步中，会调用startProcessLocked来创建一个新的进程，而对于通过在Activity内部调用startActivity来启动新的Activity来说，这一步是不需要执行的，因为新的Activity就在原来的Activity所在的进程中进行启动；

6. ActivityManagerServic调用ApplicationThread.scheduleLaunchActivity接口，通知相应的进程执行启动Activity的操作；

7. ApplicationThread把这个启动Activity的操作转发给ActivityThread，ActivityThread通过ClassLoader导入相应的Activity类，然后把它启动起来。


### APK安装和启动
---
[概述安卓App的安装和启动](https://www.jianshu.com/p/fa31d64ca57b)

#### 安装
我们知道，Android的安装包Apk其实就是个资源和组件的容器压缩包，安装的过程主要是复制和解析的过程，这个过程大概分这样几步：

##### 1. 复制
安卓的程序目录是/data/app/，所以安装的第一步就是把apk文件复制到这个目录下。这里有四个问题：

* 安卓机有内部存储和SD卡两部分，很多安卓机的内存并不大，需要把apk安装到SD卡上节省内存空间，所以程序目录/data/app/实际上也是在内部存储和SD卡上各一个。
* 系统自带的App是安装在/system/app/目录下的，这个目录只有root权限才能访问，所以系统App在root之前是无法删除和修改的，也就是说，系统App升级时，实际上是在/data/app/里重新安装了一个App，这个路径会重新注册到系统那里，系统再打开App时，就会指向新App的地址。当然，这个新的App是可以卸载的，不过新的App卸载后，系统会把 /system/app/里那个旧的App提供给你，所以是卸掉新的，还你旧的。
* 还是系统App，在root后，我们可以操作/system/app/目录，但是系统安装Apk仍然会装到/data/app/里，所以如果想修改/system/app/目录里的app，必须自己手动push新的apk文件进去，这个新的apk文件不会自动被安装，需要重启设备，系统在重启时检查到apk被更新，才会去安装apk。
* 系统目录有个/system/priv-app/目录，这里面放的是权限更高的系统核心应用，如开机launcher、系统UI、系统设置等，这个目录我们最好不要动，保持系统干净简洁。

##### 2. 安装
安卓系统开机启动时，会启动一个超级管理服务SystemServer，这个SystemServer会启动所有的系统核心服务，其中就包括PackageManagerService，简称PMS，具体的apk安装过程，就是由这个PMS操作的。
PMS会监控/data/app/这个目录，在上一步中，系统安装程序向这个目录复制了一个apk，PMS自己就会定期扫描这个目录，找到后缀为apk的文件，如果这个apk没有被安装过，它就会自动开始安装，安装时会做这么几件事：

* 创建应用目录，路径为/data/data/your package(你的应用包名)，App中使用的数据库、so库、xml文件等，都会放在这个目录下。
* 提取dex文件，dex是App的可执行文件，系统解压apk就能得到dex文件，然后把dex文件放到/data/dalvik-cache，这样可以提前缓存dex到内存中，能加快启动速度。系统还会把dex优化为odex，进一步加快启动速度。
* 判断是否可以安装apk，如检查apk签名等。
* 为应用分配并保存一个UID，UID是来自Linux的用户账户体系，不过在Android这种单用户系统里，UID被用来与App对应，这也是安全机制的一部分，每个App都有自己对应的UID，这种对应关系是持久化保存的，App更新或卸载重装后，系统还会给它分配原来那个UID。用adb pull /data/system/packages.list可以查看所有App的UID。GID（用户组）一般等于UID。
* 利用AndroidManifest文件，注册Apk的各项信息，包括但不限于：
	
	* 根据installLocation属性（internalOnly、auto、preferExternal），选择安装在内部存储器还是SD卡上。
	* 根据sharedUserId属性，为App分配UID，如果两个App使用同一个UID，打包时又使用了相同的签名，它们就被视为同一个用户，可以共享数据，甚至运行在同一个进程上。
	* 向/data/system/packages.xml文件中，记录App的包名、权限、版本号、安装路径等；同时在/data/system/packages.list中，更新所有已安装的app列表。
	* 注册App中的的四大组件（Activity、Service、Broadcast Receiver和Content Provider），包括组件的intent-filter和permission等。
	* 在桌面上添加App的快捷方式，如果AndroidManifest文件中有多个Activity被标注为<action android:name="android.intent.action.MAIN" />和
<category android:name="android.intent.category.LAUNCHER" />，系统就会向桌面添加多个App快捷方式，所以有时候在安装一个App后，用户可能会感觉安装了多个App。

##### 3. 通知
apk安装完成后，PMS会发一个ACTION_PACKAGE_ADDED广播，如果是卸载，会发ACTION_PACKAGE_REMOVED广播。
整个安装过程大概是这样的：

![](https://upload-images.jianshu.io/upload_images/1863579-349f7d80e12f08f5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/405)

#### 启动
启动一个App，首先需要触发启动过程，然后分配系统资源，最后才启动要打开的App组件。

##### 1. 触发启动过程
在安卓系统开机启动时，启动的超级管理服务SystemServer会启动所有的系统核心服务，其中就包括ActivityManagerService，简称AMS，启动App具体都是AMS来负责的。
不过，一般Java程序都有个main函数入口，启动Java程序其实就是执行main函数去了。但是，安卓App不是这样设计的，App并没有统一的程序入口，一个App其实更像是一群组件的集合，启动App其实就是启动了某个组件，即便是从桌面点击应用图标打开某个App，也是系统桌面Home根据安装时注册的组件信息，找到这个图标对应的Activity信息，再由AMS去启动Activity组件。

##### 2. 分配系统资源
在安卓系统里，除非人为设置为多进程（Activity的android:process属性），否则默认每个App都有1个独立的进程和虚拟机，所以在系统启动时，系统会建立一个Linux进程(Process)，在这个进程里放一个虚拟机VM，在这个VM里，运行你的App。
在系统层面，它其实要做这么几件事：

1. 分配UID，App要有UID才能有自己的系统资源，UID是在安装App时由系统分配的，一般每个App都有自己的UID，App的资源不能共享，因为它们不属于同一个用户。
2. 分配进程Process，系统会给App一个进程，每个App的都有自己的进程，进程的PID是系统即时生成的，用完销毁。
如果要让两个App共用进程，除了需要设置同一个进程（android:process），还需要分配同一个UID（android:sharedUserId）来共享系统资源，使用同一个应用签名（同一个签名证书才可以视为同一个程序）。
有时候，如果某些业务特别消耗内存或特别耗时，还可以把1个App分成多个进程，让某些组件在独立的进程中工作，销毁该组建时，把整个进程一起用system.exit来销毁掉。
3. 提供虚拟机VM，安卓App是java程序，需要在java虚拟机上运行，这个虚拟机需要由系统在分配进程时，和进程一起提供。
4. 除非做了跨进程跨用户的配置，否则App之间是隔离的，不能直接互相访问，也不能直接共享资源。
5. AMS管理启动过程，启动App的工作都是AMS统一负责的，AMS里保存了App对应的系统进程ID（PID），在启动App时，AMS会去找App对应的PID，如果找不到PID，说明需要创建进程，就会要求系统为App提供进程和VM。
6. 提供进程和VM，创建VM是非常耗时的，为了加快App启动速度，安卓系统采用了复制的方式：系统开机启动时会启动一个Zygote进程，这个进程会初始化系统的第一个VM, 并预加载framework等app通用资源，当安卓要启动某个App时，Zygote通过fork复制Zygote的VM，就可以快速创建一个带VM的进程，为App运行提供载体。系统开机启动时的第一个进程一般是桌面Home进程。

![提供系统资源](https://upload-images.jianshu.io/upload_images/1863579-ceca3eb82dec3aac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/581)

##### 3. 启动要打开的App组件
App本身没有main函数入口，但是系统在启动进程时，会创建一个主线程ActivityThread对象（Process.start("android.app.ActivityThread",...)），这个ActivityThread是一个final类，虽然不是线程，但是管理者主线程，它是有main函数入口的（Java终于找到组织了），ActivityThread有这么几个作用：

1. 管理App的主线程，也就是UI线程，启动主线程的Looper，只有主线程可以调用View的onDraw函数来刷新界面(为了线程安全)。
至于视频类控件，都不是View而是SurfaceView，所以可以用子线程刷新，而且SurfaceView是直接绘制到屏幕上的，和View是分开管理的。  
另外，BroadCast消息也是主线程处理的，主线程创建BroadCastReceiver对象，并调用其onReceive()函数，处理完就销毁，所以它的生命周期很短（10秒，超时就ANR）。
2. 负责管理调度进程资源、Application和App四大组件中的三个（Activity，Service，ContentProvider），列表中的组件用Token区分，至于BroadCastReceiver，因为是随用随造，用完销毁，所以不需要保存和管理。
3. 构建Context和Application，这个任务包括检查和加载LoadedApk对象、设置分辨率密度、是否高耗内存、获取package和component等启动信息、获取ClassLoader把类加载到内存等，最后，先创建一个context对象contextimpl.createAppContext(this,getSystemContext().mPackageInfo;)，再用context去创建Application对象context.mPackageInfo.makeApplication(true,null)。
4. 对接AMS，AMS自己有专门的系统进程，ActivityThread把一个ApplicationThread（一个Bindler对象）作为自己的Proxy交给AMS，以便由AMS来调度管理ActivityThread中的Activity。
5. 处理消息，ActivityThread是通过消息机制来启动App组件的，ActivityThread有Message队列、Handler和Looper，在AMS启动Activity时，AMS会向ActivityThread发送LAUNCH_ACTIVITY消息启动Activity，ActivityThread收到这个消息后启动Activity，然后，就进入我们熟悉的组件onCreate生命周期了。
6. 重要系统服务如SystemServer也是App，也有ActivityThread，也可能出现ANR之类的异常，为了避免系统“跑飞”，这些应用都有Watchdog看护，出现问题会重启设备。

启动过程大概是这样的：  
![](https://upload-images.jianshu.io/upload_images/1863579-811f6b7a80f77135.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/589)

AMS是通过IPC向ActivityThread传递消息的。

另外，在创建组件时，组件之间有这样几个区别：

1. Application和四大组件的启动时机：
Application是主线程启动时创建的，这是应用程序运行的第一个类、
ContentProvider是主线程启动时创建的（并发布到AMS）、
BroadCastReceiver是主线程收到广播时创建的（前台10秒/后台60秒ANR）、
Activity是AMS发消息让主线程创建的（5秒ANR）、
Service是AMS通过ApplicationThread接口，让主线程创建的（并运行在主线程上，前台20秒/后台200秒ANR）。
2. 关于Context：
App里依靠context来提供资源和上下文，所以Application、Activity和Service有context（它们都extends Context）、
BroadCastReceiver没有context，主线程在调用onReceive时会把Application的context作为参数传进去、
ContentProvider也没有context、
虽然组件有各自的context，但它们指向同一块资源，因为实现ContextImpl时，获取资源的ResourcesManager采用单例模式，所以同一个App的不同context都指向同一个Resource对象
Activity的context多了主题Theme，而Application的context生命周期最长。    

	这些context之间的关系如下：  
![context关系图](https://upload-images.jianshu.io/upload_images/1863579-c43d8440de63f120.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

3. 关于对Application的共享：
App中的Application是一个单例，整个App是共享同一个Application对象的、
Activity和Service都有getApplication函数、这个Application是在创建组件时赋给组件的，比如Activity就是ActivityThread在performLaunchActivity时，把Application实体赋给Application的。
组件有getApplication和getApplicationContext两个函数，这两个函数一个是组件本身的，一个contextwrapper要求实现的，很多情况下他们返回的是一个对象，但是官方并不建议把两者混淆。


### Dex加载

	
### MVC&MVP
---
#### MVC模式
模型(model)－视图(view)－控制器(controller)

![](https://upload-images.jianshu.io/upload_images/5615762-d6339b3f09f89a2c?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

* Model层：适合做一些业务逻辑处理，比如数据库存取操作，网络操作，复杂的算法，耗时的任务等都在model层处理。
* View层：应用层中处理数据显示的部分，XML布局可以视为V层，显示Model层的数据结果。
* Controller层：在Android中，Activity处理用户交互问题，因此可以认为Activity是控制器，Activity读取V视图层的数据（eg.读取当前EditText控件的数据），控制用户输入（eg.EditText控件数据的输入），并向Model发送数据请求（eg.发起网络请求等）。


#### MVC的优缺点
优点：

1. 耦合性低。所谓耦合性就是模块代码之间的关联程度。利用MVC框架使得View（视图）层和Model（模型）层可以很好的分离，这样就达到了解耦的目的，所以耦合性低，减少模块代码之间的相互影响。
2. 可扩展性好。由于耦合性低，添加需求，扩展代码就可以减少修改之前的代码，降低bug的出现率。
3. 模块职责划分明确。主要划分层M,V,C三个模块，利于代码的维护。
4. 部署快
5. 可维护性高
6. 有利于软件工程化管理

缺点：

1. View和Controller的连接过于紧密
2. 很容易把Activity即当View又当Controller使用
3. 没有明确的定义
4. 不适合小型中等规模的应用

#### MVP模式
在MVP模式里通常包含4个要素：

1. View : 负责绘制UI元素、与用户进行交互(在Android中体现为Activity);
2. View interface : 需要View实现的接口，View通过View interface与Presenter进行交互，降低耦合，方便进行单元测试;
3. Model : 也叫数据层，负责存储、检索、操纵数据(有时也实现一个Model interface用来降低耦合);
4. Presenter : 作为View与Model交互的中间纽带，处理与用户交互的负责逻辑。

#### MVP的优缺点
优点：

1. 降低耦合度，实现了Model和View真正的完全分离，可以修改View而不影响Modle
2. 模块职责划分明显，层次清晰（下面会介绍Bob大叔的Clean Architecture）
3. 隐藏数据
4. Presenter可以复用，一个Presenter可以用于多个View，而不需要更改Presenter的逻辑（当然是在View的改动不影响业务逻辑的前提下）
5. 利于测试驱动开发。以前的Android开发是难以进行单元测试的（虽然很多Android开发者都没有写过测试用例，但是随着项目变得越来越复杂，没有测试是很难保证软件质量的；而且近几年来Android上的测试框架已经有了长足的发展——开始写测试用例吧），在使用MVP的项目中Presenter对View是通过接口进行，在对Presenter进行不依赖UI环境的单元测试的时候。可以通过Mock一个View对象，这个对象只需要实现了View的接口即可。然后依赖注入到Presenter中，单元测试的时候就可以完整的测试Presenter应用逻辑的正确性。
6. View可以进行组件化。在MVP当中，View不依赖Model。这样就可以让View从特定的业务场景中脱离出来，可以说View可以做到对业务完全无知。它只需要提供一系列接口提供给上层操作。这样就可以做到高度可复用的View组件。
7. 代码灵活性
8. Activity和Fragment变得非常轻量。他们唯一的职责就是建立/更新UI和处理用户事件。因此，他们变得更容易维护。

缺点：

1. Presenter中除了应用逻辑以外，还有大量的View->Model，Model->View的手动同步逻辑，造成Presenter比较笨重，维护起来会比较困难。
2. 由于对视图的渲染放在了Presenter中，所以视图和Presenter的交互会过于频繁。
3. 如果Presenter过多地渲染了视图，往往会使得它与特定的视图的联系过于紧密。一旦视图需要变更，那么Presenter也需要变更了。
4. 额外的代码复杂度及学习成本。

#### MVP的优化
1. Model和View的数据绑定
	
	标准的方式是通过定义view接口协议来回调数据。   
	需要些大量的view接口类，并定义回调方法。

	![](https://upload-images.jianshu.io/upload_images/1833901-5e7faae173791a47.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)
	
	上图是通过EventBus注解的方式进行数据绑定，Presenter通过EventBus post数据。   
简单，方便，不用定义很多view接口类。

2. Presenter和View生命周期的绑定

	原始的MVP并没有关注Presenter的生命周期  
	
	可以定义一个MVPDispatcher或Presenter的管理类，MVPDispatcher有个list MVPDispatcher并定义了各种生命周期方法，最后在BaseActivity中进行绑定。
	
	利用Loader，它是由系统提供的并和Activity/Fragment 的生命周期绑定。
	
3. 一个View绑定多个Presenter

	最初的方案，使用泛型 BaseActivity<P extends BasePresenter>  
	这样的结果是BaseActivity只能绑定一个Presenter  
	也就是说一个页面的所有逻辑都要放到一个Presenter中去处理
	
	在BaseActivity中createPresenters添加到MVPDispatcher
	
	还有一种实现，通过注解绑定多个Presenter
	
4. 其他演进

	使用 Activity/Fragment 作为 Presenter 的探索  
	使用 Adapter作为 Presenter的探讨
	
#### MVC和MVP区别
MVP与MVC最大的一个区别就是：Model与View层之间倒底该不该通信（甚至双向通信）

### 性能优化
---
#### 1.布局优化  
解决布局太复杂，嵌套太多，层级太多的问题。Android SDK工具箱中有一个叫做Hierarchy Viewer的工具，能够在App运行时分析Layout。  
merge标签的作用是合并UI布局，使用该标签能降低UI布局的嵌套层次。  
merge标签可用于两种情况：  
	1. 布局顶结点是FrameLayout且不需要设置background或padding等属性，可以用merge代替，因为Activity内容试图的parent view就是个FrameLayout，所以可以用merge消除只剩一个。  
	2. 某布局作为子布局被其他布局include时，使用merge当作该布局的顶节点，这样在被引入时顶结点会自动被忽略，而将其子节点全部合并到主布局中。   

include, 用来抽取可复用的布局，其本身并没有减少布局层级。  
ViewStub, 用来在特殊情况下在显示的布局，避免不必要的布局解析。用到的时候通过inflate才解析。  
传统的布局方式存在一定的缺陷，如RelativeLayout要两次测量（measure）它的子View才能知道确切的高度；如果LinearLayout布局的子View有设置了layout_weight，那么它也需要测量两次才能获得布局的高度。  
相对于传统的布局方式，Android官方还推出了两种新的布局方式：ConstraintLayout和FlexboxLayout。  
ConstraintLayout可以有效的解决布局嵌套过多导致的性能问题，官方也对其渲染性能进行了优化，并且ConstraintLayout支持可视化的方式编写布局。  
FlexBoxLayout弹性布局，可以很自由的定义view间的排布方式。

解决办法：

* 减少布局层级  
合理使用RelativeLayout和LinearLayout  
Merge的使用  
布局复用   
一个控件的属性越少解析越快  
尽可能少用wrap_content，会增加measure时的计算出成本  
FlexBoxLayout约束布局  
**工具 Hierarchy View查看视图层级**
* 避免过度绘制  


##### View绘制优化
解决卡顿，是页面流畅

* 控制好刷新频率，避免不必要的刷新
* 缩小刷新区域  
invalidata是更新整个视图，系统同时提供了两个局部刷新的方法：  invalidata(Rect dirty)和invalidata(int left, int top, int right, int bottom).  
ListView优化等
* 合理使用动画，视觉好看，加载过渡
* 硬件加速  
Android3.0引入硬件加速，来提高渲染速度。  
核心类DisplayList，每个View内部都会维护一个DisplayList。
通过draw()方法把所有的绘制命令记录到DisplayList，它包含了输出View层级的绘制代码。加入到DisplayList不会立即执行。  
invalidata()只是在显示列表中记录和显示更新层级，去标记不需要绘制的View。  
硬件加速的控制级别：
	1. Application级别  
	应用全局使用  
	\<applicaiton android:hardwareAccelerated="true">
	2. Activity级别  
	对Activity单独控制  
	\<activity android:hardwareAccelerated="true">
	3. Window级别  
	对某个Window进行硬件加速，如Dialog  
 	getWindow().setFlags(WindowManager.LayoutParams.FLAG\_HARDWARE_ACCELERATED)
	4. View级别  
	View.setLayerType(View.LAYER\_TYPE_SOFTWARE, null)

TraceView绘制耗时分析

##### 内存优化

##### 业务逻辑优化
启动加载优化，数据请求，模块初始化等

* 分布加载，做好优先级
* 异步加载
* 延时加载
* 多线程，将大量 & 耗时操作放在工作线程中执行

#### 性能优化工具
* Hierarchy Viewer  
通过可视化界面显示布局层级
* System Trace  
收集和检测时间信息，显示CPU消耗在哪了。
* TraceView  
一个图形化的工具，用来显示和分析方法的执行时间。
* Android Profiler  
分析CPU，内存，网络
* LeakCanary  
分析内存泄漏

### View的绘制流程
---
[详解View的绘制流程](https://blog.csdn.net/say_from_wen/article/details/79086405)

#### 谁负责View的绘制？
ViewRoot是android用来负责执行View绘制的整个流程的。实际上，View的绘制是由ViewRoot来负责，具体由ViewRootImpl类来实现。在android中，每个应用程序窗口的DecorView都有一个与之关联的ViewRoot对象（ViewRootImpl），这种关联关系是由WindowManager来维护的（windowManagerImpl实现）。在Activity启动时，ActivityThread.handleResumeActivity()方法中建立了它们两者的关联关系。

#### View绘制的源头
我们还要从Activity在第一次setContentView时说起，其实这步操作做的操作是初始化各个view对象， 并制定上下级关系，仅此而已。真正开始的地方是**ActivityThread.handleResumeActivity**中通过windowManager的实现类windowManagerImpl的addView方法，方法内部通过ViewRootImpl的实例调用setView方法。该方法内部的requestlayout方法中会执行scheduleTraversals()方法，在方法中会通过内部的Handler发出消息，开始执行doTraversal，从而执行performTraversals()，View的绘制流程正式开始。

`接口类ViewManager、WindowManager，WindowManager 继承ViewManager。 ` 

ActivityThread的handleResumeActivity()  
WindowManager的实现类WindowManagerImpl addView()    
ViewRootImpl setView()->requestLayout()->scheduleTraversals()通过handler发送消息  
然后执行doTraversal()->performTraversals()


    //ViewRootImpl类部分源码
    public void setView(View view, WindowManager.LayoutParams attrs, View panelParentView) {
                // Schedule the first layout -before- adding to the window
                // manager, to make sure we do the relayout before receiving
                // any other events from the system.
                requestLayout();
    }

    @Override
    public void requestLayout() {
        if (!mHandlingLayoutInLayoutRequest) {
            checkThread();//检查是否在主线程
            mLayoutRequested = true;//mLayoutRequested 是否measure和layout布局。
            scheduleTraversals();
        }
    }

    void scheduleTraversals() {
        if (!mTraversalScheduled) {
            mTraversalScheduled = true;
            mTraversalBarrier = mHandler.getLooper().getQueue().postSyncBarrier();

            //post一个runnable处理-->mTraversalRunnable
            mChoreographer.postCallback(
                    Choreographer.CALLBACK_TRAVERSAL, mTraversalRunnable, null);
            ``````//省略
        }
    }

    final class TraversalRunnable implements Runnable {
        @Override
        public void run() {
            doTraversal();
        }
    }
    final TraversalRunnable mTraversalRunnable = new TraversalRunnable();
    void doTraversal() {
            mHandler.getLooper().getQueue().removeSyncBarrier(mTraversalBarrier);
            performTraversals();//View的绘制流程正式开始。
    }

#### View绘制的主要流程
View的绘制主要流程分为三步，measure、layout和draw。整个View树的绘图流程是在ViewRootImpl类的performTraversals()方法（这个方法巨长）开始的，该函数做的执行过程主要是根据之前设置的状态，判断是否重新计算视图大小(measure)、是否重新放置视图的位置(layout)、以及是否重绘 (draw)，其核心也就是通过判断来选择顺序执行这三个方法中的哪个。下面看源码（ViewRootImpl类中）：

注：此段源码会说明一问题，为什么我们App看到的视图都是全屏。

    private void performTraversals() {
        ......
        //最外层的根视图的widthMeasureSpec和heightMeasureSpec由来
        //lp.width和lp.height在创建ViewGroup实例时等于MATCH_PARENT
        int childWidthMeasureSpec = getRootMeasureSpec(mWidth, lp.width);
        int childHeightMeasureSpec = getRootMeasureSpec(mHeight, lp.height);
        ......
        //mView就是我们的DecorVIew
        mView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        ......
        mView.layout(0, 0, mView.getMeasuredWidth(), mView.getMeasuredHeight());
        ......
        mView.draw(canvas);
        ......
    }
    
    private static int getRootMeasureSpec(int windowSize, int rootDimension) {
        int measureSpec;
        switch (rootDimension) {
    //上面传入参数后这个函数走的是MATCH_PARENT，使用MeasureSpec.makeMeasureSpec方法组装一个MeasureSpec，MeasureSpec的specMode等于EXACTLY，specSize等于windowSize，也就是为何根视图总是全屏的原因。
        case ViewGroup.LayoutParams.MATCH_PARENT:
            measureSpec = MeasureSpec.makeMeasureSpec(windowSize, MeasureSpec.EXACTLY);
            break;
        ......
        }
        return measureSpec;
    }

总而言之，performTraversals就是做了这些事情：

![](https://ws1.sinaimg.cn/large/628a632agy1ftl19p6yzdj20jg058gnn.jpg)

#### Measure
要理解measure，首先要理解下MeasureSpec：

MeasureSpec决定了一个View的尺寸规格，并且View的MeasureSpec受自身的LayoutParams(一般是xml布局中width和height)和父容器MeasureSpec的影响。MeasureSpec又由SpecMode 和SpecSize 组成。SpecMode 是测量模式，有UNSPECIFIED（父容器对子View的尺寸不作限制，由子View自己测量）、 EXACTLY（ SpecSize 表示View的最终大小，因为父容器已经检测出View所需要的精确大小，它对应LayoutParams中的match\_parent和具体的数值这两种模式）、AT\_MOST（SpecSize 表示父容器的可用大小，View的大小不能大于这个值。它对应LayoutParams中的wrap_content。）三种。SpecSize是在某种测量模式下的尺寸和大小。

整个View树的源码measure流程图（引用自凶残的程序员）

![这里写图片描述](https://ws1.sinaimg.cn/large/628a632agy1ftl1dayq0sj20hz0llq49.jpg)

Measure阶段的目的是计算出控件树中的各个控件要显示其内容需要多大尺寸。起点是ViewRootImpl的measureHierarchy()方法，这个方法的源码如下：

	private boolean measureHierarchy(final View host, final WindowManager.LayoutParams lp,
	            final Resources res, final int desiredWindowWidth, final int desiredWindowHeight) {
	        int childWidthMeasureSpec;
	        int childHeightMeasureSpec;
	        boolean windowSizeMayChange = false;
	        ···
	        boolean goodMeasure = false;
	        ···
	        if (!goodMeasure) {
	            childWidthMeasureSpec = getRootMeasureSpec(desiredWindowWidth, lp.width);
	            childHeightMeasureSpec = getRootMeasureSpec(desiredWindowHeight, lp.height);
	            performMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
	            if (mWidth != host.getMeasuredWidth() || mHeight != host.getMeasuredHeight()) {
	                windowSizeMayChange = true;
	            }
	        }
	    ···
	        return windowSizeMayChange;
	}

上面的代码中调用getRootMeasureSpec()方法来获取根MeasureSpec，这个根MeasureSpec代表了对decorView的宽高的约束信息。所以在一开始传入performMeasure()方法的MeasureSpec的SpecMode为EXACTLY，SpecSize为窗口尺寸。接下来执行的是performMeasure方法。

	private void performMeasure(int childWidthMeasureSpec, int childHeightMeasureSpec) {
	  . . .
	  try { 
	   //调用DecorView的mesure方法。
	    mView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
	  } finally {
	    . . .
	  }
	}

接下来会调用到View的meaure方法，在measure()方法中，会决定是否进行重新测量的工作（源码不贴了）如下：

	//final方法，子类不可重写。每个View控件的实际宽高都是由父视图和自身决定的。实际的测量是在onMeasure方法进行，所以在View的子类需要重写onMeasure方法，这是因为measure方法是final的，不允许重载，所以View子类只能通过重载onMeasure来实现自己的测量逻辑。（结合自定义View想想）
	public final void measure(int widthMeasureSpec, int heightMeasureSpec) {
        ......
        //回调onMeasure()方法
        onMeasure(widthMeasureSpec, heightMeasureSpec);
        ......
    }

实际测量工作的是LinearLayout.FrameLayout的onMeasure()方法。DecorView是一个LinearLayout其实。

     //View的onMeasure默认实现方法
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

FrameLayout是ViewGroup的子类，后者有一个View[]类型的成员变量mChildren，代表了其子View集合。通过getChildAt(i)能获取指定索引处的子View，通过getChildCount()可以获得子View的总数。

在上面的源码中，首先调用measureChildWithMargins()方法对所有子View进行了一遍测量，并计算出所有子View的最大宽度和最大高度。而后将得到的最大高度和宽度加上padding，这里的padding包括了父View的padding和前景区域的padding。然后会检查是否设置了最小宽高，并与其比较，将两者中较大的设为最终的最大宽高。最后，若设置了前景图像，我们还要检查前景图像的最小宽高。

经过了以上一系列步骤后，我们就得到了maxHeight和maxWidth的最终值，表示当前容器View用这个尺寸就能够正常显示其所有子View（同时考虑了padding和margin）。而后我们需要调用resolveSizeAndState()方法来结合传来的MeasureSpec来获取最终的测量宽高，并保存到mMeasuredWidth与mMeasuredHeight成员变量中。

**从以上代码的执行流程中，我们可以看到，容器View通过measureChildWithMargins()方法对所有子View进行测量后，才能得到自身的测量结果。也就是说，对于ViewGroup及其子类来说，要先完成子View的测量，再进行自身的测量（考虑进padding等）。**   
接下来我们来看下ViewGroup的measureChildWithMargins()方法的实现：

    protected void measureChildWithMargins(View child,
            int parentWidthMeasureSpec, int widthUsed,
            int parentHeightMeasureSpec, int heightUsed) {
        //获取子视图的LayoutParams
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        //调整MeasureSpec
        //通过这两个参数以及子视图本身的LayoutParams来共同决定子视图的测量规格
        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                mPaddingLeft + mPaddingRight + lp.leftMargin + lp.rightMargin
                        + widthUsed, lp.width);
        final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
                mPaddingTop + mPaddingBottom + lp.topMargin + lp.bottomMargin
                        + heightUsed, lp.height);
        //调运子View的measure方法，子View的measure中会回调子View的onMeasure方法
        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

由以上代码我们可以知道，对于ViewGroup来说，它会调用child.measure()来完成子View的测量。传入ViewGroup的MeasureSpec是它的父View用于约束其测量的，那么ViewGroup本身也需要生成一个childMeasureSpec来限制它的子View的测量工作。这个childMeasureSpec就由getChildMeasureSpec()方法生成。接下来我们来分析这个方法：

	public static int getChildMeasureSpec(int spec, int padding, int childDimension) {
	  // spec为父View的MeasureSpec
	  // padding为父View在相应方向的已用尺寸加上父View的padding和子View的margin
	  // childDimension为子View的LayoutParams的值
	  int specMode = MeasureSpec.getMode(spec);
	  int specSize = MeasureSpec.getSize(spec);
	
	  // 现在size的值为父View相应方向上的可用大小
	  int size = Math.max(0, specSize - padding);
	
	  int resultSize = 0;
	  int resultMode = 0;
	
	  switch (specMode) {
	    // Parent has imposed an exact size on us
	    case MeasureSpec.EXACTLY:
	      if (childDimension >= 0) {
	        // 表示子View的LayoutParams指定了具体大小值（xx dp）
	        resultSize = childDimension;
	        resultMode = MeasureSpec.EXACTLY;
	      } else if (childDimension == LayoutParams.MATCH_PARENT) {
	        // 子View想和父View一样大
	        resultSize = size;
	        resultMode = MeasureSpec.EXACTLY;
	      } else if (childDimension == LayoutParams.WRAP_CONTENT) {
	        // 子View想自己决定其尺寸，但不能比父View大 
	        resultSize = size;
	        resultMode = MeasureSpec.AT_MOST;
	      }
	      break;
	
	    // Parent has imposed a maximum size on us
	    case MeasureSpec.AT_MOST:
	      if (childDimension >= 0) {
	        // 子View指定了具体大小
	        resultSize = childDimension;
	        resultMode = MeasureSpec.EXACTLY;
	      } else if (childDimension == LayoutParams.MATCH_PARENT) {
	        // 子View想跟父View一样大，但是父View的大小未固定下来
	        // 所以指定约束子View不能比父View大
	        resultSize = size;
	        resultMode = MeasureSpec.AT_MOST;
	      } else if (childDimension == LayoutParams.WRAP_CONTENT) {
	        // 子View想要自己决定尺寸，但不能比父View大
	        resultSize = size;
	        resultMode = MeasureSpec.AT_MOST;
	      }
	      break;
	
	      . . .
	  }
	
	  //noinspection ResourceType
	  return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
	}
	
* MeasureSpec.EXACTLY

	使用measureSpec中size的值作为宽高的精确值
当我们将控件的layout_width或layout_height指定为具体数值时如andorid:layout_width="50dip"，或者为FILL\_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
* MeasureSpec.AT_MOST

	使用measureSpec中size的值作为最大值，采用不超过这个值的最大允许值
当控件的layout\_width或layout\_height指定为WRAP\_CONTENT时，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
* MeasureSpec.UNSPECIFIED

	是未指定尺寸，这种情况不多

从上面的分析我们可以得到一个通用的结论，当子View的测量结果能够确定时，子View的SpecMode就为EXACTLY；当子View的测量结果还不能确定（只是暂时设为某个值）时，子View的SpecMode为AT_MOST。

在measureChildWithMargins()方法中，获取了知道子View测量的MeasureSpec后，接下来就要调用child.measure()方法，并把获取到的childMeasureSpec传入。这时便又会调用onMeasure()方法，若此时的子View为ViewGroup的子类，便会调用相应容器类的onMeasure()方法，其他容器View的onMeasure()方法与FrameLayout的onMeasure()方法执行过程相似。

下面会我们回到FrameLayout的onMeasure()方法，**当递归地执行完所有子View的测量工作后，会调用resolveSizeAndState()方法来根据之前的测量结果确定最终对FrameLayout的测量结果并存储起来。**View类的resolveSizeAndState()方法的源码如下：

	public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
	  final int specMode = MeasureSpec.getMode(measureSpec);
	  final int specSize = MeasureSpec.getSize(measureSpec);
	  final int result;
	  switch (specMode) {
	    case MeasureSpec.AT_MOST:
	      if (specSize < size) {
	        // 父View给定的最大尺寸小于完全显示内容所需尺寸
	        // 则在测量结果上加上MEASURED_STATE_TOO_SMALL
	        result = specSize | MEASURED_STATE_TOO_SMALL;
	      } else {
	       result = size;
	      }
	      break;
	
	    case MeasureSpec.EXACTLY:
	      // 若specMode为EXACTLY，则不考虑size，result直接赋值为specSize
	      result = specSize;
	      break;
	
	    case MeasureSpec.UNSPECIFIED:
	    default:
	      result = size;
	  }
	
	  return result | (childMeasuredState & MEASURED_STATE_MASK);
	
	}

对于普通View，会调用View类的onMeasure()方法来进行实际的测量工作，该方法的源码如下：

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
		setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec)); 
	}

对于普通View（非ViewgGroup）来说，只需完成自身的测量工作即可。以上代码中通过setMeasuredDimension()方法设置测量的结果，具体来说是以getDefaultSize()方法的返回值来作为测量结果。getDefaultSize()方法的源码如下：

	public static int getDefaultSize(int size, int measureSpec) {
	  int result = size;
	  int specMode = MeasureSpec.getMode(measureSpec);
	  int specSize = MeasureSpec.getSize(measureSpec);
	  switch (specMode) {
	    case MeasureSpec.UNSPECIFIED:
	      result = size;
	      break;
	    case MeasureSpec.AT_MOST:
	    case MeasureSpec.EXACTLY:
	      result = specSize;
	      break;
	  }
	  return result;
	}

**由以上代码我们可以看到，View的getDefaultSize()方法对于AT_MOST和EXACTLY这两种情况都返回了SpecSize作为result。所以若我们的自定义View直接继承了View类，我们就要自己对wrap\_content (对应了AT\_MOST)这种情况进行处理，否则对自定义View指定wrap\_content就和match\_parent效果一样了。**

##### measure原理总结  

通过上面分析可以看出measure过程主要就是从顶层父View向子View递归调用view.measure方法（measure中又回调onMeasure方法）的过程。具体measure核心主要有如下几点：

MeasureSpec（View的内部类）测量规格为int型，值由高2位规格模式specMode和低30位具体尺寸specSize组成。其中specMode只有三种值：

	MeasureSpec.EXACTLY //确定模式，父View希望子View的大小是确定的，由specSize决定；
	MeasureSpec.AT_MOST //最多模式，父View希望子View的大小最多是specSize指定的值；
	MeasureSpec.UNSPECIFIED //未指定模式，父View完全依据子View的设计值来决定； 

* View的measure方法是final的，不允许重载，View子类只能重载onMeasure来完成自己的测量逻辑。
* 最顶层DecorView测量时的MeasureSpec是由ViewRootImpl中getRootMeasureSpec方法确定的（LayoutParams宽高参数均为MATCH\_PARENT，specMode是EXACTLY，specSize为物理屏幕大小）。
* ViewGroup类提供了measureChild，measureChild和measureChildWithMargins方法，简化了父子View的尺寸计算。
* 只要是ViewGroup的子类就必须要求LayoutParams继承子MarginLayoutParams，否则无法使用layout_margin参数。
* View的布局大小由父View和子View共同决定。
* 使用View的getMeasuredWidth()和getMeasuredHeight()方法来获取View测量的宽高，必须保证这两个方法在onMeasure流程之后被调用才能返回有效值。

#### Layout
子View具体layout的位置都是相对于父容器而言的，View的layout过程和measure同理，也是从顶级View开始，递归的完成整个空间树的布局操作。

performLayout源码，会执行mView的layout方法：

	//ViewRootImpl
    private void performLayout(WindowManager.LayoutParams lp, int desiredWindowWidth,int desiredWindowHeight) {
	
        final View host = mView;
        host.layout(0, 0, host.getMeasuredWidth(), host.getMeasuredHeight());
	
    }

layout方法：

	//ViewGroup

    //尽管ViewGroup也重写了layout方法
    //但是本质上还是会通过super.layout()调用View的layout()方法
    @Override
    public final void layout(int l, int t, int r, int b) {
        if (!mSuppressLayout && (mTransition == null || !mTransition.isChangingLayout())) {

            //如果无动画，或者动画未运行
            super.layout(l, t, r, b);
        } else {
            //等待动画完成时再调用requestLayout()
            mLayoutCalledWhileSuppressed = true;
        }
    }

	//View

    public void layout(int l, int t, int r, int b) {
        int oldL = mLeft;
        int oldT = mTop;
        int oldB = mBottom;
        int oldR = mRight;

        //如果布局有变化，通过setFrame重新布局
        boolean changed = isLayoutModeOptical(mParent) ?
                setOpticalFrame(l, t, r, b) : setFrame(l, t, r, b);

        if (changed || (mPrivateFlags & PFLAG_LAYOUT_REQUIRED) == PFLAG_LAYOUT_REQUIRED) {

            //如果这是一个ViewGroup，还会遍历子View的layout()方法
            //如果是普通View，通知具体实现类布局变更通知
            onLayout(changed, l, t, r, b);

            //清除PFLAG_LAYOUT_REQUIRED标记
            mPrivateFlags &= ~PFLAG_LAYOUT_REQUIRED;
            ``````
            //布局监听通知
        }

        //清除PFLAG_FORCE_LAYOUT标记
        mPrivateFlags &= ~PFLAG_FORCE_LAYOUT;
    }

ViewGroup的onLayout()方法竟然是一个抽象方法，这就是说所有ViewGroup的子类都必须重写这个方法。所以在自定义ViewGroup控件中，onLayout配合onMeasure方法一起使用可以实现自定义View的复杂布局。自定义View首先调用onMeasure进行测量，然后调用onLayout方法动态获取子View和子View的测量大小，然后进行layout布局。重载onLayout的目的就是安排其children在父View的具体位置，重载onLayout通常做法就是写一个for循环调用每一个子视图的layout(l, t, r, b)函数，传入不同的参数l, t, r, b来确定每个子视图在父视图中的显示位置。

layout阶段的大致流程我们就分析完了，这个阶段主要就是根据上一阶段得到的View的测量宽高来确定View的最终显示位置。显然，经过了measure阶段和layout阶段，我们已经确定好了View的大小和位置，那么接下来就可以开始绘制View了。

##### layout原理总结
整个layout过程比较容易理解，从上面分析可以看出layout也是从顶层父View向子View的递归调用view.layout方法的过程，即父View根据上一步measure子View所得到的布局大小和布局参数，将子View放在合适的位置上。具体layout核心主要有以下几点：

* View.layout方法可被重载，ViewGroup.layout为final的不可重载，ViewGroup.onLayout为abstract的，子类必须重载实现自己的位置逻辑。（结合自定义viewgrounp）
* measure操作完成后得到的是对每个View经测量过的measuredWidth和measuredHeight，layout操作完成之后得到的是对每个View进行位置分配后的mLeft、mTop、mRight、mBottom，这些值都是相对于父View来说的。
* **使用View的getWidth()和getHeight()方法来获取View测量的宽高，必须保证这两个方法在onLayout流程之后被调用才能返回有效值。**

#### Draw
draw过程也是在ViewRootImpl的performTraversals()内部调运的，其调用顺序在measure()和layout()之后，这里的mView对于Actiity来说就是PhoneWindow.DecorView，ViewRootImpl中的代码会创建一个Canvas对象，然后调用View的draw()方法来执行具体的绘制工。所以又回归到了ViewGroup与View的树状递归draw过程。

View.draw()方法源码：

	public void draw(Canvas canvas) {
	  . . . 
	  // 绘制背景，只有dirtyOpaque为false时才进行绘制，下同
	  int saveCount;
	  if (!dirtyOpaque) {
	    drawBackground(canvas);
	  }
	  . . . 
	  // 绘制自身内容
	  if (!dirtyOpaque) onDraw(canvas);
	  // 绘制子View
	  dispatchDraw(canvas);
	   . . .
	  // 绘制滚动条等
	  onDrawForeground(canvas);
	
	}

##### 第一步，对View的背景进行绘制。 
可以看见，draw方法通过调运drawBackground(canvas);方法实现了背景绘制。我们来看下这个方法源码，如下：

      private void drawBackground(Canvas canvas) {
        //获取xml中通过android:background属性或者代码中setBackgroundColor()、setBackgroundResource()等方法进行赋值的背景Drawable
        final Drawable background = mBackground;
        ......
        //根据layout过程确定的View位置来设置背景的绘制区域
        if (mBackgroundSizeChanged) {
            background.setBounds(0, 0,  mRight - mLeft, mBottom - mTop);
            mBackgroundSizeChanged = false;
            rebuildOutline();
        }
        ......
            //调用Drawable的draw()方法来完成背景的绘制工作
            background.draw(canvas);
        ......
    }

##### 第二步，对View的内容进行绘制。

可以看到，这里去调用了一下View的onDraw()方法，所以我们看下View的onDraw方法（ViewGroup也没有重写该方法），如下：

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    protected void onDraw(Canvas canvas) {
    }

这是一个空方法。因为每个View的内容部分是各不相同的，所以需要由子类去实现具体逻辑。

##### 第三步，对当前View的所有子View进行绘制，如果当前的View没有子View就不需要进行绘制。 
我们来看下View的draw方法中的dispatchDraw(canvas);方法源码，可以看见如下：

    protected void dispatchDraw(Canvas canvas) {}

View的dispatchDraw()方法是一个空方法，而且注释说明了如果View包含子类需要重写他，所以我们有必要看下ViewGroup的dispatchDraw方法源码（这也就是刚刚说的对当前View的所有子View进行绘制，如果当前的View没有子View就不需要进行绘制的原因，因为如果是View调运该方法是空的，而ViewGroup才有实现），如下：

        @Override
    protected void dispatchDraw(Canvas canvas) {
        ......
        final int childrenCount = mChildrenCount;
        final View[] children = mChildren;
        ......
        for (int i = 0; i < childrenCount; i++) {
            ......
            if ((child.mViewFlags & VISIBILITY_MASK) == VISIBLE || child.getAnimation() != null) {
                more |= drawChild(canvas, child, drawingTime);
            }
        }
        ......
        // Draw any disappearing views that have animations
        if (mDisappearingChildren != null) {
            ......
            for (int i = disappearingCount; i >= 0; i--) {
                ......
                more |= drawChild(canvas, child, drawingTime);
            }
        }
        ......
    }

可以看见，ViewGroup确实重写了View的dispatchDraw()方法，该方法内部会遍历每个子View，然后调用drawChild()方法，我们可以看下ViewGroup的drawChild方法，如下：

	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
	    return child.draw(canvas, this, drawingTime);
	}

**可以看见drawChild()方法调运了子View的draw()方法。所以说ViewGroup类已经为我们重写了dispatchDraw()的功能实现，我们一般不需要重写该方法，但可以重载父类函数实现具体的功能。**

##### 第四步，对View的滚动条进行绘制。

可以看到，这里去调用了一下View的onDrawScrollBars()方法，所以我们看下View的onDrawScrollBars(canvas);方法，如下：

    protected final void onDrawScrollBars(Canvas canvas) {
        //绘制ScrollBars分析不是我们这篇的重点，所以暂时不做分析
        ......
    }

可以看见其实任何一个View都是有（水平垂直）滚动条的，只是一般情况下没让它显示而已。 
到此，View的draw绘制部分源码分析完毕，我们接下来进行一些总结。

##### draw原理总结
可以看见，绘制过程就是把View对象绘制到屏幕上，整个draw过程需要注意如下细节：

* 如果该View是一个ViewGroup，则需要递归绘制其所包含的所有子View。
* View默认不会绘制任何内容，真正的绘制都需要自己在子类中实现。
* View的绘制是借助onDraw方法传入的Canvas类来进行的。
* 区分View动画和ViewGroup布局动画，前者指的是View自身的动画，可以通过setAnimation添加，后者是专门针对ViewGroup显示内部子视图时设置的动画，可以在xml布局文件中对ViewGroup设置layoutAnimation属性（譬如对LinearLayout设置子View在显示时出现逐行、随机、下等显示等不同动画效果）。
* 在获取画布剪切区（每个View的draw中传入的Canvas）时会自动处理掉padding，子View获取Canvas不用关注这些逻辑，只用关心如何绘制即可。
* 默认情况下子View的ViewGroup.drawChild绘制顺序和子View被添加的顺序一致，但是你也可以重载ViewGroup.getChildDrawingOrder()方法提供不同顺序。

#### invalidate and postInvalidate
我们知道invalidate()(在主线程)和postInvalidate()(可以在子线程)都是用于请求View重绘的方法，那么它是如何实现的呢？

这个ViewRootImpl类的invalidateChildInParent方法直接返回了null，也就是上面ViewGroup中说的，层层上级传递到ViewRootImpl的invalidateChildInParent方法结束了那个do while循环。看见这里调运的scheduleTraversals这个方法吗？scheduleTraversals会通过Handler的Runnable发送一个异步消息，调运doTraversal方法，然后最终调用performTraversals()执行重绘。开头背景知识介绍说过的，performTraversals就是整个View数开始绘制的起始调运地方。

**所以说View调运invalidate方法的实质是层层上传到父级，直到传递到ViewRootImpl后触发了scheduleTraversals方法，然后整个View树开始重新按照上面分析的View绘制流程进行重绘任务。**

postInvalidate方法

上面分析invalidate方法时注释中说该方法只能在UI Thread中执行，其他线程中需要使用postInvalidate方法，所以我们来分析分析postInvalidate这个方法源码。如下：

  	public void postInvalidate() {
        postInvalidateDelayed(0);
    }

	public void dispatchInvalidateDelayed(View view, long delayMilliseconds) {
        Message msg = mHandler.obtainMessage(MSG_INVALIDATE, view);
        mHandler.sendMessageDelayed(msg, delayMilliseconds);
    }

**通过ViewRootImpl类的Handler发送了一条MSG_INVALIDATE消息，handleMessage运行在主线程中，所以实质就是又在UI Thread中调运了View的invalidate()**

#### View的requestLayout方法源码分析
和invalidate类似，其实在上面分析View绘制流程时或多或少都调运到了这个方法，而且这个方法对于View来说也比较重要，所以我们接下来分析一下他。如下View的requestLayout源码：

  	public void requestLayout() {
        ......
        if (mParent != null && !mParent.isLayoutRequested()) {
            //由此向ViewParent请求布局
            //从这个View开始向上一直requestLayout，最终到达ViewRootImpl的requestLayout
            mParent.requestLayout();
        }
        ......
    }

当我们触发View的requestLayout时其实质就是层层向上传递，直到ViewRootImpl为止，然后触发ViewRootImpl的requestLayout方法，如下就是ViewRootImpl的requestLayout方法：

 	@Override
    public void requestLayout() {
        if (!mHandlingLayoutInLayoutRequest) {
            checkThread();
            mLayoutRequested = true;
            //View调运requestLayout最终层层上传到ViewRootImpl后最终触发了该方法
            scheduleTraversals();
        }
    }

**requestLayout()方法会改变mLayoutRequested 的值，在重绘过程中只调用measure过程和layout过程，不会调用draw过程，也不会重新绘制任何View包括该调用者本身。  
而invalidate只会出发draw。**

### ViewRoot和DecorView
---
[window、Activity、DecorView、ViewRoot关系](https://www.jianshu.com/p/049df709ddbf)

Activity并不负责视图控制，它只是控制生命周期和处理事件，真正控制视图的是Window。一个Activity包含了一个Window，Window才是真正代表一个窗口，Window 中持有一个 DecorView，而这个DecorView才是 view 的根布局。

#### DecorView
DecorView是FrameLayout的子类，它可以被认为是Android视图树的根节点视图。DecorView作为顶级View，一般情况下它内部包含一个竖直方向的LinearLayout，在这个LinearLayout里面有上下两个部分（具体情况和Android版本及主体有关），上面的是标题栏，下面的是内容栏。在Activity中通过setContentView所设置的布局文件其实就是被加到内容栏之中的，而内容栏的id是content，在代码中可以通过ViewGroup content = （ViewGroup)findViewById(R.android.id.content)来得到content对应的layout。

**DecorView是顶级View，内部有titlebar和contentParent两个子元素，contentParent的id是content，而我们设置的main.xml布局则是contentParent里面的一个子元素。**

#### ViewRoot
ViewRoot对应ViewRootImpl类，它是连接WindowManagerService和DecorView的纽带，View的三大流程(测量（measure），布局（layout），绘制（draw）)均通过ViewRoot来完成。ViewRoot并不属于View树的一份子。从源码实现上来看，它既非View的子类，也非View的父类，但是，它实现了ViewParent接口，这让它可以作为View的名义上的父视图。RootView继承了Handler类，可以接收事件并分发，Android的所有触屏事件、按键事件、界面刷新等事件都是通过ViewRoot进行分发的。ViewRoot可以被理解为“View树的管理者”——它有一个mView成员变量，它指向的对象和上文中Window和Activity的mDecor指向的对象是同一个对象。

Android系统中，ViewRoot对应于ViewRootImpl类，它是连接WindowManager和DecorView的纽带，View的三大流程（measure、layout、draw）都是通过ViewRoot来完成的。 
在Activity对象被创建完毕后，会将DecorView添加到Window中。同时，会创建ViewRootImpl对象，并将ViewRootImpl对象和DecorView建立关联。

	ViewRootImpl  root = new ViewRootImpl(view.getContext,display);
	root.setView(view,params,panelParentView);

View的绘制流程是从ViewRoot的performTraversals方法开始的，它经过measure、layout、draw三个流程，最终才可以将这个view绘制出来。

measure： 负责测量view的宽度 和 高度；  
layout： 负责确定view在父容器中的位置；  
draw： 负责将view绘制在屏幕上。

performTraversals的大致流程如图所示。 
ViewRoot和DectorView

如图所示，performTraversals时会依次调用performMeasure、performLayout和performDraw。这三个方法会完成View的measure、layout和draw三个流程。  
performMeasure会内在调用measure方法，measure方法实质上又会调用onMeasure方法，在onMeasure方法方法里面对所有的子元素进行测量操作。之后measure的流程就会从父容器传递到子元素中了，这样 就完成了一次measure操作。子元素也会重复父容器中的measure操作，如是反复就完成了View树的遍历。同理，performLayout和performDraw的实现原理也是这样的。

1. measure过程决定了View的测量后的宽度/高度，measure过程完成之后，就可以通过getMeasuredWidth和getMeasuredHeight方法来获取View的测量宽度/高度； 
2. layout过程决定了View的四个顶点的位置和实际的View的宽度和高度。完成之后，可以通过getTop/getBottom/getLeft/getRight获取四个顶点的位置，并且可以通过getWidth/getHeight获取View的最终高度/宽度。 
3. draw过程则决定了View的显示，只有draw方法完成后我们才可以在屏幕上看到View。

### 自定义view
---
测量onMeasure  
布局onLayout  
绘制onDraw

### Android多线程
---
Android提供了四种常用的操作多线程的方式，分别是：

1. Handler+Thread
2. AsyncTask  
	适用单个任务的处理
	
3. ThreadPoolExecutor  
	ThreadPoolExecutor提供了一组线程池，可以管理多个线程并行执行。这样一方面减少了每个并行任务独自建立线程的开销，另一方面可以管理多个并发线程的公共资源，从而提高了多线程的效率。所以ThreadPoolExecutor比较适合一组任务的执行。Executors利用工厂模式对ThreadPoolExecutor进行了封装，使用起来更加方便。

	适用批处理任务
	
4. IntentService  
	IntentService继承自Service，是一个经过包装的轻量级的Service，用来接收并处理通过Intent传递的异步请求。客户端通过调用startService(Intent)启动一个IntentService，利用一个work线程依次处理顺序过来的请求，处理完成后自动结束Service。
	
5. Rxjava  
	线程调度

### Android进程间通信（IPC）
---
* Bundle/Intent

	四大组件中三大组件Activity、Service、Receiver都支持在Intent中传递Bundle数据。

	由于Bundle实现了Parcelable接口，所以它可以很方便的在不同的进程间传输数据。当然我们传输的数据必须能够被序列化，比如基本类型、实现了Parcelable接口的对象、实现了Serializable接口的对象以及一些Android支持的特殊对象。
* 文件

	两个进程通过读写同一个文件来交换数据，比如A进程把数据写入文件，B进程通过读取这个文件来获取数据。

	Android系统基于Linux，使得并发读写文件可以没有限制的进行，甚至两个线程同时对文件读写操作都是允许的，尽管可能出问题，因此文件共享方式适合在对数据同步要求不高的进程间进行通信。

	SharedPreferences也属于文件的一种，但是由于系统对它的读写有一定的缓存策略，即在内存中会有一份SharedPreferences文件的缓存；因此在多进程模式下，系统对它的读写就变得不可靠，会有很大几率丢失数据，不建议在进程间通信中使用SharedPreferences。

* Messenger信使

	Messenger可以理解为信使，通过它可以再不同进程中传递Message对象，在Message中放入我们需要传递的数据，就可以实现数据的进程间传递了。

	Messenger是一种轻量级的IPC方案，它的底层实现是AIDL。由于它一次处理一个请求，因此在服务端不需要考虑线程同步的问题，因为服务端不存在并发执行的情形。

* AIDL

	AIDL是 Android Interface definition language的缩写，它是一种android内部进程通信接口的描述语言。AIDL可以处理发送到服务器端大量的并发请求（不同与Messenger的串行处理方式），也可以实现跨进程的方法调用。

	在Android中使用方法：创建一个Service和一个AIDL接口，接着创建一个类继承自AIDL接口中的Stub类并实现Stub中的抽象方法，在Service的onBind方法中返回这个类的对象，然后客户端绑定服务端Service，建立连接后就可以访问远程服务器了。

* ContentProvier

	ContentProvider是Android中提供的专门用于不同应用间进行数据共享的方式，天生适合进程间通信。

	ContentProvider的底层实现也是Binder，但是它的使用过程比AIDL简单的多，因为系统做了封装，使得无需关心细节即可轻松实现IPC。ContentProvider主要以表格的形式组织数据，和数据库很类似，但ContentProvider对底层的数据存储方式没有任何要求，既可以使用Sqlite数据库，也可以使用文件方式，甚至可以使用内存中的一个对象来存储。
	
* Socket

	Socket套接字，是网络通信中的概念，分为流式套接字和用户数据套接字两种，对应于网络的传输控制层中的TCP和UDP协议。

	两个进程可以通过Socket来实现信息的传输，Socket本身可以支持传输任意字节流。
	
* 广播

	通过Binder机制实现的全局广播。
	

名称	|优点	|缺点	|适用场景
----|----|----|----
Bundle|简单易用|只能传输Bundle支持的数据类型|四大组件的进程间通信
文件共享|简单易用|不适合高并发场景，并且无法做到进程间即时通信|无并发访问清醒，交换简单的数据，实时性不搞的场景
AIDL|功能强大，支持一对多并发通信，支持实时通信|使用稍复杂，需要处理好线程同步|一对多通信且有RPC需求
Messenger|功能一般，支持一对多串行通信，支持实时通信|不能很好的处理高并发情形，不支持RPC，数据通过Message进行传输，因此只能传输Bundle支持的数据类型|低并发的一对多即时通信，无RPC需求
ContentProvider|在数据源访问方面功能强大，支持一对多并发数据共享，可通过Call方法扩展其他操作|可以理解为受约束的AICL，主要提供数据的CRUD数据|一对多的进程间数据共享
Socket|功能强大，可以通过网络传输字节流，支持一对多并发实时通信|实现细节稍微繁琐，不支持直接的RPC	|网络数据交换

#### Android中IPC带来的问题

两个应用共享数据：Android系统会为每个应用分配一个唯一的UID，具有相同UID的应用才能共享数据。两个应用通过ShareUID跑在同一个进程是有要求的，需要这两个应用有相同的ShareUID并且签名相同才可以。在这种情况下，他们可以相互访问对方的私有数据，比如data目录，组件信息等，不管他们是否跑在同一个进程。

Android系统为每个应用分配了一个独立的虚拟机，或者说为每一个进程都分配一个独立的虚拟机，不同的虚拟机在内存分配上有不同的地址空间，这就导致在不同的虚拟机中访问同一个对象会产生多分副本。所有运行在不同进程中的四大组件，只要它们之间需要通过内存来共享数据，都会共享失败，这也是多进程带来的主要影响。

一般来说，使用多进程会造成如下的问题：  

（1）静态成员和单例模式完全失效（不同的虚拟机中访问同一个对象会产生多分副本）

（2）线程同步机制完全失效（不在同一块内存，不管是所对象还是锁全局类都无法保证线程同步）

（3）SharePreferences的可靠性下降（不支持两个进程同时写操作）

（4）Application会多次创建（因为创建新进程会分配独立虚拟机，相当于启动一个新的应用）

虽说不能直接的共享内存，但是通过跨进程通信还是可以实现数据交互。

### AIDL
---
AIDL能够实现进程间通信，其内部是通过Binder机制来实现的

#### Android Studio AIDL的使用

1. Client端/src/main右键新建aidl文件，定义方法

	build一下，会自动生成IMyAidlInterface.java文件，\build\generated\source\aidl
2. 将aidl文件连同目录一起拷贝到服务器端，保持包名一致
3. 服务端/main/java/创建Service

		public class AidlService extends Service {
	
		    private IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
		        @Override
		        public int plus(int a, int b) throws RemoteException {
		            return a + b;
		        }
		
		        @Override
		        public String toUpperCase(String str) throws RemoteException {
		            return str.toUpperCase();
		        }
		
		        @Override
		        public String getMessageStr(HelloMsg msg) throws RemoteException {
		            return msg.getMsg();
		        }
		
		        @Override
		        public HelloMsg getMessage() throws RemoteException {
		            return new HelloMsg(("msg from service at Thread " + Thread.currentThread().toString() + "\n" +
		                    "tid is " + Thread.currentThread().getId() + "\n" +
		                    "main thread id is " + getMainLooper().getThread().getId() + "\npid is " + Process.myPid()), Process.myPid());
		        }
		
		    };
		
		    @Nullable
		    @Override
		    public IBinder onBind(Intent intent) {
		        return stub;
		    }
		}

4. 编写客户端代码

		private IMyAidlInterface aidlInterface;
	    private ServiceConnection connection = new ServiceConnection() {
	        @Override
	        public void onServiceConnected(ComponentName name, IBinder service) {
	            aidlInterface = IMyAidlInterface.Stub.asInterface(service);
	            connectBtn.setText("已连接");
	        }
		
	        @Override
	        public void onServiceDisconnected(ComponentName name) {
	            connectBtn.setText("未连接");
	        }
		
	        @Override
	        public void onBindingDied(ComponentName name) {
	            connectBtn.setText("onBindingDied");
	        }
	    };
	    
		Intent intent = new Intent("android.intent.action.AIDLService");
	    intent.setPackage("com.android.garry.demo.aidl");
	    bindService(intent, connection, Context.BIND_AUTO_CREATE);
	    
	    通过aidlInterface调用方法
	    
	    Android5.0以上不支持Service的隐式调用
	    /****************************/
	    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
	        // Retrieve all services that can match the given intent
	        PackageManager pm = context.getPackageManager();
	        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
	        // Make sure only one match was found
	        if (resolveInfo == null || resolveInfo.size() != 1) {
	            return null;
	        }
	        // Get component info and create ComponentName
	        ResolveInfo serviceInfo = resolveInfo.get(0);
	        String packageName = serviceInfo.serviceInfo.packageName;
	        String className = serviceInfo.serviceInfo.name;
	        ComponentName component = new ComponentName(packageName, className);
	        // Create a new intent. Use the old one for extras and such reuse
	        Intent explicitIntent = new Intent(implicitIntent);
	        // Set the component to be explicit
	        explicitIntent.setComponent(component);
	        return explicitIntent;
    	}

5. 自定义数据类型

	默认支持的数据类型包括：   
		Java中的八种基本数据类型，包括 byte，short，int，long，float，double，boolean，char。
		String 类型。  
		CharSequence类型。  
		List类型：List中的所有元素必须是AIDL支持的类型之一，或者是一个其他AIDL生成的接口，或者是定义的parcelable（下文关于这个会有详解）。List可以使用泛型。  
		Map类型：Map中的所有元素必须是AIDL支持的类型之一，或者是一个其他AIDL生成的接口，或者是定义的parcelable。Map是不支持泛型的。  
	
	自定义类型要实现Parcelable接口
	
	* 在/main/java下定义和/main/aidl同包名，然后定义数据类
	* 在/main/aidl下定义同名的.aidl文件

			// HelloMsg.aidl
			package com.android.garry.aidl;

			/**
			* 定义了一个Parcelable类，告诉系统我们需要序列化和反序列化的类型。
			* 每一个实现了Parcelable的类型都需要对应的.aidl文件。
			* AIDL编译器在编译AIDL文件时会自动查找此类文件。
			*/
			parcelable HelloMsg;
			
	* 同样拷贝到服务端一份

IMyAidlInterface.java文件包含两个静态内部类—Stub和Proxy（其中Proxy是Stub的内部类）。

	public static abstract class Stub extends android.os.Binder implements com.hx.binder.IMyAidlInterface
	
其中Stub是个抽象类，它继承了Binder，并实现了IMyAidlInterface接口。Stub提供了几个方法：asInterface、asBinder、onTransact，但并没有实现IMyAidlInterface接口的方法，所以需要交给Stub的实现类去实现。

	private static class Proxy implements com.hx.binder.IMyAidlInterface

Proxy是Stub的内部类，也实现了IMyAidlInterface接口。并提供了几个方法：asBinder、getInterfaceDescriptor，并实现了IMyAidlInterface接口的方法plus和toUpperCase。
### 网络TCP/IP, HTTP
### sqlite数据库知识
### ContentProvider相关知识
---
ContentProvider是通过IBinder实现通信过程的  
getContentResolver获得到的是ApplicationContentResolver（在ContextImpt中实现的）  
Client端ApplicationContentResolver使用ContentProviderProxy作为IBinder的Proxy（ContentProviderNative中实现）  
Provider端通过Transport作为IBinder的实现端（ContentProvider中实现）

#### 多个进程同时调用一个ContentProvider的query获取数据，ContentPrvoider是如何反应的呢？
一个content provider可以接受来自另外一个进程的数据请求。尽管ContentResolver与ContentProvider类隐藏了实现细节，但是ContentProvider所提供的query()，insert()，delete()，update()都是在ContentProvider进程的线程池中被调用执行的，而不是进程的主线程中。这个线程池是有Binder创建和维护的，其实使用的就是每个应用进程中的Binder线程池。

#### ContentProvider、ContentResolver、ContentObserver关系
ContentProvider是管理者，内容提供者。提供的内容来自文件，数据库等  
ContentObserver是观察者，通过特定URI来感知内容的变化  
ContentResolver是操作者，通过特定URI来对数据进行增删改查

### apk瘦身优化
---

1. 使用一套资源就好  
这是最基本的一条规则，但非常重要。  
对于绝大对数APP来说，只需要取一套设计图就足够了。  
鉴于现在分辨率的趋势，建议取720p的资源，放到xhdpi目录。

2. 移除无用资源  
minifyEnabled true  
shrinkResources true// 压缩，清除无用资源。需要配合上面混淆使用  
zipAlignEnabled true

3. 删除R.class文件，使用常量替换  
android中的R文件，除了styleable类型外，所有字段都是int型变量/常量，且在运行期间都不会改变。所以可以在编译时，记录R中所有字段名称及对应值，然后利用asm工具遍历所有class，将引用R字段的地方替换成对应常量。  
https://github.com/meili/ThinRPlugin/blob/master/README.zh-cn.md

4. 移除未使用的国际化资源包  
只包含使用到的语言即可  

		defaultConfig {  
		         resConfigs "zh", "zh_CN", "zh_HK", "zh_MO", "zh_TW", "en"
		}
  
4. 资源名称混淆  
微信资源混淆方案  
https://github.com/shwenzhang/AndResGuard

5. libs下so库的优化  
降低so库的体积  
只提供对主流架构的支持，比如arm，对于mips和x86架构可以考虑不提供支持，系统会自动提供相应的兼容。爱奇艺客户端只在armeabi下面放置了一套so库文件。  
删除armable-v7包下的so，基本上armable的so也是兼容armable-v7的，armable-v7a的库会对图形渲染方面有很大的改进，如果没有这方面的要求，可以精简。  

6. 使用新的签名工具apksigner  
Google在Android7.0系统提供了新的apksigner签名工具，相比使用java提供的jarsigner签名工具，APK体积可以减小约5%（依赖文件数量）。

7. 用注解替代枚举  
枚举最大的作用是提供了类型安全。为了弥补Android平台不建议使用枚举的缺陷，官方推出了两个注解，IntDef和StringDef,用来提供编译期的类型检查。

8. 减少使用切图，背景大图。  
尽量使用shape，和纯色背景图

9. 图片压缩  
https://www.tinypng.com/  
https://pngquant.org/  
https://developers.google.com/speed/docs/insights/OptimizeImages  
市面上有许多工具可用来对JPEG和PNG文件执行进一步的无损压缩，且不会对图片质量造成任何影响。对于JPEG文件，我们建议您使用jpegtran或jpegoptim（仅适用于Linux；使用--strip-all选项运行）。对于PNG文件，我们建议使用OptiPNG或PNGOUT。

10. 采用压缩率更高的webp图片格式，代替当前的png格式

11. 删除一些用户量极少，“无意义”的功能  

12. 检查第三方包，把不需要的组件、图片之类的删除或替换

13. 把部分页面做成H5，客户端删除这部分功能

### Android组件化&插件化
---
* 组件化开发就是将一个app分成多个模块，每个模块都是一个组件（Module），开发的过程中我们可以让这些组件相互依赖或者单独调试部分组件等，但是最终发布的时候是将这些组件合并统一成一个apk，这就是组件化开发。
* 插件化开发和组件化开发略有不用，插件化开发时将整个app拆分成很多模块，这些模块包括一个宿主和多个插件，每个模块都是一个apk（组件化的每个模块是个lib），最终打包的时候将宿主apk和插件apk分开或者联合打包。

**组件化是在编译期分模块，插件化是在运行期。**

### Android组件化开发
---
模块化开发的升级

[Android 组件化开发详解](https://www.jianshu.com/p/b7d4e6612e0c)  
[终极组件化框架项目方案详解——讲的很好](https://www.jianshu.com/p/e6eb9c8d120f)

正常一个App中可以有多个module，但是一般只会有一个module是设置为application的，其他均设置为library，组件化开发就是要每个module都可以运行起来，因此在开发期间每个module均设置为application，发布时再进行合并。

组件化架构图

![](https://upload-images.jianshu.io/upload_images/4290785-369cb20535596b56.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/565)

因为组件与组件之间不能相互引用，所以组件与组件的交互需要一个中间件Router,组件进行交互之前需要通过Router进行寻址中转传递彼此的接口。

#### 为什么需要组件化和组件化带来的好处？
1. 现在Android项目中代码量达到一定程度，编译将是一件非常痛苦的事情，一般都需要变异5到6分钟。Android studio推出instant run由于各种缺陷和限制条件（比如采用热修复tinker）一般情况下是被关闭的。而组件化框架可以使模块单独编译调试，可以有效地减少编译的时间。
2. 通过组件化可以更好的进行并行开发，因为我们可以为每一个模块进行单独的版本控制，甚至每一个模块的负责人可以选择自己的设计架构而不影响其他模块的开发，与此同时组件化还可以避免模块之间的交叉依赖，每一个模块的开发人员可以对自己的模块进行独立测试，独立编译和运行，甚至可以实现单独的部署。从而极大的提高了并行开发效率。

#### 组件化的优点和缺点
优点：

1. 单独编译，便于开发，提升编译速度。
2. 组件分离，便于维护，提高重用效率。
3. 单独发布，便于测试，提升测试准度。

缺点：

对开发管理的要求更高

## 题目
1. 一个apk从开始安装启动,系统都做了哪些事情?请从AMS,WMS,PMS的角度考虑下,以及进程是如何启动的?

	[概述安卓App的安装和启动](https://www.jianshu.com/p/fa31d64ca57b)
	
2. AMS与WMS相关的数据结构和沟通的桥梁是什么?AMS的堆栈是如何管理的?WMS的堆栈是如何管理的?
3. Android内存管理机制以及LMK相关的机制,以及AMS中进程管理机制,请分别介绍下
4. PMS相关的开机流程,apk安装的流程, adb install和 pms scan的区别有哪
5. Broadcast的机制,分发的流程是什么?如何传递到每个app进程的?动态广播和静态广播的处理流程在哪里不一样?
6. 多用户最主要的机制以及创建一个新的用户系统需要做哪些事情
7. Runtime permission,如何把一个预置的app默认给它权限,不要授权。
8. 如何实现预装的apk在卸载后,通过恢复出厂设置恢复过来,请介绍下方案
9. Android资源加载和打包的机制介绍,一个图片在app中调用R.id 调用后是如何找到的?
10. Android Overlay的机制是什么?

	Android Overlay是一种资源替换机制，它能在不重新打包apk的情况下，实现资源文件的替换（res目录非assert目录），Overlay又分为静态Overlay(Static Resource Overlay)与运行时Overlay(Runtime Resource Overlay)。
	
	SRO–Static resource overlay(静态替换)  
SRO是在编译时完成的，我们可以根据不同的产品，为app/framework加载不同的资源。编译时资源就已经覆盖了，一般用在有源码的apk中。

	RRO-Runtime Resource Overlay  
	运行时 overlay，当 apk 在手机中运行时才发生资源覆盖，一般用在无源码的apk中。

11. Android权限管理的机制是什么？

	权限是Android中一个非常重要的组成部分，许多操作都需要获取到权限才能进行。在Android6.0之后，权限机制发生了重大变化，加入了运行时权限这一概念。
	
	Android大致将权限分为两类，即普通权限和危险权限。对于普通权限，依旧使用Android6.0之前的权限机制，只需要在AndroidManifest中声明即可。而对于危险权限，则必须在应用运行时主动申请，由用户决定是否授予。
	
12. 为何 android.uid.system相关的进程不能访问 sdcard
13. 开机流程和关机流程请描述下
14. Bootanimation是如何启动和退出的
15. Binder相关的机制以及在 Android平台的使用, Android还有什么IPC通信方式,各有什么优缺点?
16. 死机,重启等 stability问题分析流程? watchdog reset如何分析
17. Native Crash问题如何分析,以及 crash在art相关的oat,odex文件如何分析
18. Android ART机制,与 dalvik的区别,JIT与AOT的区别是什么? ART GC有什么改善,还有什么缺点?
19. ANR, OOM等问题的分析流程介绍
20. Android++智能指针相关的使用介绍
21. Android编译,优化,ART相关编译优化
22. input相关事件的分发机制,tp相关问题解决
23. 按键事件和tp事件的处理有什么不同点和相同点吗?
24. 功耗相关问题的分析
25. 性能相关问题的分析
26. Android N与M的一些典型的改变有哪些? Multi-window机制介绍
27. PowerManagerService主要做了哪些相关的操作?系统亮灭屏都有哪些流程?
28. Wakelock机制, android如何和linux管理这些 wakelock
29. Alarm相关机制，doze相关的机制及运行方式


## Java 
### Java类加载
### 强引用、弱引用、软引用
* 强引用（StrongReference）
	
	强引用是最普遍的引用。如果一个对象具有强引用，垃圾回收器（GC）绝不会回收它。当内存不足时，Java虚拟机会抛出OutOfMemoryError错误，不会回收强引用的对象来解决内存不足。所以，强引用的对象，在应用的生命后期如果不再使用，一定要释放它，以便让系统回收。
	
	如果想中断强引用和某个对象之间的关联，可以显示地将引用赋值为null，这样一来的话，JVM在合适的时间就会回收该对象。
	
* 软引用（SoftReference）

	如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。**软引用可用来实现内存敏感的高速缓存。比如网页缓存、图片缓存等。** 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。

* 弱引用（WeakReference）

	弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。

* 虚引用（PhantomReference）

	“虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
虚引用主要用来跟踪对象被垃圾回收器回收的活动。虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之 关联的引用队列中。

		ReferenceQueue queue = new ReferenceQueue ();
		PhantomReference pr = new PhantomReference (object, queue); 

	程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。

### Java中的volatile 变量是什么？
1. volatile声明的变量，将由本地内存强制刷新到主内存
2. 写操作会导致其他线程中的缓存失效

可用于单例模式的加强

volatile是一个特殊的修饰符，只有成员变量才能使用它。在Java并发程序缺少同步类的情况下，多线程对成员变量的操作对其它线程是透明的。volatile变量可以保证下一个读取操作会在前一个写操作之后发生，就是上一题的volatile变量规则。

### 多线程，同步锁，wait&sleep&join
* wait是Object的一个函数   
wait()方法是Object类里的方法；当一个线程执行到wait()方法时，它就进入到一个和该对象相关的等待池中，同时失去（释放）了对象的机锁（暂时失去机锁，wait(long timeout)超时时间到后还需要返还对象锁）；其他线程可以访问；  
wait()使用notify或者notifyAlll或者指定睡眠时间来唤醒当前等待池中的线程。  
wait()必须放在synchronized block中，否则会在program runtime时扔出”java.lang.IllegalMonitorStateException“异常。

* sleep则是Thread的一个函数  
sleep()是Thread类的Static(静态)的方法；因此他不能改变对象的机锁，所以当在一个Synchronized块中调用Sleep()方法是，线程虽然休眠了，但是对象的机锁并木有被释放，其他线程无法访问这个对象（即使睡着也持有对象锁）。  
在sleep()休眠时间期满后，该线程不一定会立即执行，这是因为其它线程可能正在运行而且没有被调度为放弃执行，除非此线程具有更高的优先级。线程调度需要时间。

	多线程下的区别：
	1. sleep可以在任何地方使用，而wait只能在同步方法或者同步块中使用。
	2. sleep,wait调用后都会暂停当前线程并让出cpu的执行时间，但不同的是sleep不会释放当前持有的对象的锁资源，到时间后会继续执行，而wait会放弃所有锁并需要notify/notifyAll后重新获取到对象锁资源后才能继续执行。
	3. sleep需要捕获或者抛出异常，而wait/notify/notifyAll不需要。
	4. sleep()方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用使用同步控制块或方法。
	5. wait()和sleep()都可以通过interrupt()方法打断线程的暂停状态，从而使线程立刻抛出InterruptedException（但不建议使用该方法）。

* join也是Thread的一个函数  
join的作用就相当于阻塞掉除了当前线程以外的所有的线程（包括主线程），等待自己执行结束，并且遵守先来后到的排队原则，先join的先执行，后join后执行，必须注意的是，最后的线程不能使用join，否则线程就全部在等待中，可以把最后一条不使用join的线程，当做此次排队的结束。

### 集合基础知识
ConcurrentHashMap

### Java内存模型
---
Java内存模型即Java Memory Model，简称**JMM**。JMM定义了Java 虚拟机(JVM)在计算机内存(RAM)中的工作方式。程序中的变量存储在主内存中，每个线程拥有自己的工作内存并存放变量的拷贝，线程读写自己的工作内存，通过主内存进行变量的交互。JMM就是规定了工作内存和主内存之间变量访问的细节，通过保障**原子性、有序性、可见性**来实现线程的有效协同和数据的安全。  
	
**JVM如何判断一个对象实例是否应该被回收？**  
垃圾回收器会建立有向图的方式进行内存管理，通过GC Roots来往下遍历，当发现有对象处于不可达状态的时候，就会对其标记为不可达，以便于后续的GC回收。  
	
**JVM的垃圾回收策略**  
JVM采用分代垃圾回收。在JVM的内存空间中把堆空间分为年老代和年轻代。将大量创建了没多久就会消亡的对象存储在年轻代，而年老代中存放生命周期长久的实例对象。  

### JVM内存结构 & Java内存模型 & Java对象模型
---
#### Java内存结构
JVM运行时内存区域结构如下  
![](https://mmbiz.qpic.cn/mmbiz_png/6fuT3emWI5IUn7IK1IHXbPncn0qUVqFDPOXDhD9dqSdaMa09ibl7QBFXBZgQ0C7vvb0UUAO3zqszZlNVcPBrMgg/640?wxfrom=5&wx_lazy=1)

Java代码是要运行在虚拟机上的，而虚拟机在执行Java程序的过程中会把所管理的内存划分为若干个不同的数据区域，这些区域都有各自的用途。
其中有些区域随着虚拟机进程的启动而存在，而有些区域则依赖用户线程的启动和结束而建立和销毁。

**做个总结，JVM内存结构，由Java虚拟机规范定义。描述的是Java程序执行过程中，由JVM管理的不同数据区域。各个区域有其特定的功能。**

#### Java内存模型
关于JVM的内存结构的图中，我们可以看到，其中Java堆和方法区的区域是多个线程共享的数据区域。也就是说，多个线程可能可以操作保存在堆或者方法区中的同一个数据。这也就是我们常说的“Java的线程间通过共享内存进行通信”。

简单总结下，Java的多线程之间是通过共享内存进行通信的，而由于采用共享内存进行通信，在通信过程中会存在一系列如可见性、原子性、顺序性等问题，而JMM就是围绕着多线程通信以及与其相关的一系列特性而建立的模型。JMM定义了一些语法集，这些语法集映射到Java语言中就是volatile、synchronized等关键字。

**在JMM中，我们把多个线程间通信的共享内存称之为主内存，而在并发编程中多个线程都维护了一个自己的本地内存（这是个抽象概念），其中保存的数据是主内存中的数据拷贝。而JMM主要是控制本地内存和主内存之间的数据交互的。**

![](https://mmbiz.qpic.cn/mmbiz_png/6fuT3emWI5IUn7IK1IHXbPncn0qUVqFDISlBmo2qybFM7JhbWt1SKicU3Tqd1myTXKy91aUicjawZRyOYunLB17w/640?wxfrom=5&wx_lazy=1)

在Java中，JMM是一个非常重要的概念，正是由于有了JMM，Java的并发编程才能避免很多问题。

#### Java对象模型
Java是一种面向对象的语言，而Java对象在JVM中的存储也是有一定的结构的。而这个关于Java对象自身的存储模型称之为Java对象模型。

每一个Java类，在被JVM加载的时候，JVM会给这个类创建一个instanceKlass，保存在方法区，用来在JVM层表示该Java类。当我们在Java代码中，使用new创建一个对象的时候，JVM会创建一个instanceOopDesc对象，这个对象中包含了对象头以及实例数据。

#### 总结
* JVM内存结构，和Java虚拟机的运行时区域有关。
* Java内存模型，和Java的并发编程有关。
* Java对象模型，和Java对象在虚拟机中的表现形式有关。
	
### 常用的设计模式

### Leecode算法题


## 数据库
### 基本操作
1. INSERT INTO table_name (列) VALUES(值)
2. INSERT INTO table_name VALUES(值)
3. UPDATE table_name SET column1=value1 WHERE ...
4. DELETE FROM table_name WHERE column1=value1
5. SELECT * FROM table WHERE name IN (v1,v1,v3)
6. BETWEEN a AND b //[a, b)

### 连接关键字   
LEFT JOIN，从左表返回所有的行，即使右表中没有匹配的行。  
RIGHT JOIN，从左表返回所有的行，即使右表中没有匹配的行。    
FULL JOIN，返回所有的行，只要某个表中存在匹配的行。即显示两个表的所有信息。
INNER JOIN，只显示两个表相关联的信息。    
	
SELECT columns FROM table1 LEFT JOIN table2 ON table1.name=table2.name
	
### HAVING和WHERE的区别  
WHERE关键字无法与合计函数一起使用，这时候可以使用having。  
where子句的作用是对查询结果进行分组前，将不符合where条件的行去掉，即在分组前进行过滤。不能包含合计函数。  
having子句的作用是筛选满足条件的组，即在分组之后进行过滤，条件中经常包含合计函数。
	
### GROUP BY  
根据一列或多列对结果进行分组，可对大量数据进行去重。  
select count(distinct name) ...  
select count(name) from table group by name ...   
**查找重复行**   
select Email from Person group by Email having count(Email)>1
	
### DISTINCT  
去重  
select distinct column_name from table  
	
### UPDATE CASE WHEN  
if else这种条件判断  
update table set name=(case when name is null then xxx else vvv end)  
多条件判断case  
update table set name=(case sex when 0 then 'xxx' when 1 then 'aaa' when 2 then 'ccc' end)
	
### UNION
用于合并多个select的结果集  
> unionn内部的select必须拥有相同数量的列，且顺序和数据类型相似。  
默认选取不同的值，即不重复。  
如果允许重复的值，可以使用UNION ALL。  
	
	select s.id,s.student from (
	    //奇数id+1
	    select id+1 as id,student from seat where id%2=1 and id!=(select count(*) from seat)
	    union
	    //偶数id-1
	    select id-1 as id,student from seat where id%2=0
	    union
	    //奇数最后一个
	    select id,student from seat where id%2=1 and id=(select count(*) from seat)
	) s order by s.id asc 
		
### DATEDIFF  
日期比较函数  
datediff(date1,date2) 返回两个日期之间的天数date1-date2
> 参数必须是合法的日期或日期/时间表达式
	
### COUNT 制定条件  
count(case when 重载里程>0 then 1 else 0 end) as 重载趟次 //统计所有行数  
count(case when 重载里程>0 then 1 else null end) as 重载趟次 //null值不计数，统计为1的行数  
sum(case when 重载里程>0 then 1 else 0 end) as 重载趟次 //相加每行的值  
	
### ROUND  
保留指定的小数位数  
select round(column_name, decimals)//decimals规定返回的小数位数，四舍五入  

### TOP
**sqlite3中没有top的语法结构**，但是可以通过limit和order by来实现相同的功能
SELECT TOP num * FROM table// 选取前num条
SELECT TOP num PERCENT * FROM table//选取前num%条数据

### SELECT INTO
备份数据或迁移数据  

* select * into new_table from old_table
* select * into new_table IN 'xxx.db' from old_table//同时向xxx.db中拷贝表
* SELECT Persons.LastName,Orders.OrderNo 
INTO Persons_Order_Backup
FROM Persons
INNER JOIN Orders
ON Persons.Id_P=Orders.Id_P //操作多个表

### LIKE和通配符

符号|描述  
---|---
%|包含0个或多个字符的任意字符串
_下划线|任意单个字符
[]|指定范围（[a-f]或[ahjgsjghs]）中的任何单个字符
[^fafas]或[!fafas]|不在字符序列中的任何单一字符

select * from table where name like '[!ADJ]%'//不以ADJ开头的字符串
	
	  
## 数据库实例 
 
### 两列值互换  
	
	update table set column1=column2,column2=column1
	
### c1去重之后查找c2重复多次的  
	
	select c.class from (select distinct * from courses) as c group by c.class having count(*)>=5
		
### 奇偶行某列数据交换

	select s.id,s.student from (
		//奇数id+1
		select id+1 as id,student from seat where id%2=1 and id!=(select count(*) from seat)
		union
		//偶数id-1
	    select id-1 as id,student from seat where id%2=0
	    union
	    //奇数最后一个
	    select id,student from seat where id%2=1 and id=(select count(*) from seat)
	) s order by s.id asc 
		
### 分数排名

	//思路：排名=计数大于等于自己的分数有几个
	select Score,(
	    select count(*) from (select distinct Score as S from Scores) c_table
	    where S>=Score
	) as Rank from Scores order by Rank asc
		
### 连续出现至少三次的数字

	select distinct l1.Num as ConsecutiveNums from Logs l1,Logs l2,Logs l3 
	where l1.Id = l2.Id+1 and l2.Id = l3.Id+1 
	and l1.Num = l2.Num and l2.Num = l3.Num;
		
### 删除重复的Email，保留Id最小的那个

	delete p1 from Person p1,Person p2 where p1.Id>p2.Id and p1.Email=p2.Email
		
### 查找温度上升的那天日期，即当天温度比前一天温度高
		
	select w2.Id from Weather w1, Weather w2 
	where datediff(w1.RecordDate, w2.RecordDate)=-1 
	and w1.Temperature<w2.Temperature

	select w2.Id from Weather w1 join Weather w2 on 
	datediff(w1.RecordDate, w2.RecordDate)=-1 
	and w1.Temperature<w2.Temperature
		
### 部门工资前三高的员工

	select d.Name as Department,e.Name as Employee,e.Salary as Salary
	from Employee e inner join Department d
	on e.DepartmentId=d.Id
	and (
	    select count(*) from (select distinct Salary,DepartmentId from Employee) e2 where e2.DepartmentId=e.DepartmentId and e2.Salary>e.Salary
	) <=2
	order by Department,Salary desc
		
### 形成和用户  
	统计取消率  
			
	select t.Request_at as Day,
    round(
        sum(case when t.Status like 'cancelled%' then 1 else 0 end)/count(*), 2
    ) as 'Cancellation Rate'
	from Trips t 
	join Users u1 on t.Client_Id=u1.Users_Id and u1.Banned='No'
	join Users u2 on t.Driver_Id=u2.Users_Id and u2.Banned='No' 
	where Request_at between "2013-10-01" and "2013-10-03"
	group by t.Request_at  
	
	或  
	
	select t.Request_at as Day,
    round(
        sum(case when t.Status like 'cancelled%' then 1 else 0 end)/count(*), 2
    ) as 'Cancellation Rate'
	from Trips t 
	join Users u1 on t.Client_Id=u1.Users_Id and u1.Banned='No'
	join Users u2 on t.Driver_Id=u2.Users_Id and u2.Banned='No'
	group by t.Request_at 
	having Request_at between "2013-10-01" and "2013-10-03"
