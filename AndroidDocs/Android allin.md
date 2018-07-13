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


### APK安装与解析过程
### Dex加载

	
### MVP MVC
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

### 自定义view

测量onMeasure  
布局onLayout  
绘制onDraw
### 多线程，同步锁，wait&sleep
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

#### 多个进程同时调用一个ContentProvider的query获取数据，ContentPrvoider是如何反应的呢？
一个content provider可以接受来自另外一个进程的数据请求。尽管ContentResolver与ContentProvider类隐藏了实现细节，但是ContentProvider所提供的query()，insert()，delete()，update()都是在ContentProvider进程的线程池中被调用执行的，而不是进程的主线程中。这个线程池是有Binder创建和维护的，其实使用的就是每个应用进程中的Binder线程池。

#### ContentProvider、ContentResolver、ContentObserver关系
ContentProvider是管理者，内容提供者。提供的内容来自文件，数据库等  
ContentObserver是观察者，通过特定URI来感知内容的变化  
ContentResolver是操作者，通过特定URI来对数据进行增删改查

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

### Leecode算法题
### 集合基础知识
### Java内存模型

Java内存模型即Java Memory Model，简称**JMM**。JMM定义了Java 虚拟机(JVM)在计算机内存(RAM)中的工作方式。程序中的变量存储在主内存中，每个线程拥有自己的工作内存并存放变量的拷贝，线程读写自己的工作内存，通过主内存进行变量的交互。JMM就是规定了工作内存和主内存之间变量访问的细节，通过保障原子性、有序性、可见性来实现线程的有效协同和数据的安全。  
	
**JVM如何判断一个对象实例是否应该被回收？**  
垃圾回收器会建立有向图的方式进行内存管理，通过GC Roots来往下遍历，当发现有对象处于不可达状态的时候，就会对其标记为不可达，以便于后续的GC回收。  
	
**JVM的垃圾回收策略**  
JVM采用分代垃圾回收。在JVM的内存空间中把堆空间分为年老代和年轻代。将大量创建了没多久就会消亡的对象存储在年轻代，而年老代中存放生命周期长久的实例对象。  
	
### 常用的设计模式


# 数据库
---
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
