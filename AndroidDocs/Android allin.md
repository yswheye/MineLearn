## Android 

### Android新特性总结

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
内存泄漏是指应用不再使用的内存对象，但垃圾回收时没有把它辨认出来，不能及时回收，一直在内存中占用空间，不能释放给其他对象。  
频繁的GC操作会导致应用卡顿。   
内存泄漏最终会导致OOM（内存溢出）  

GC机制  
Android系统虚拟机通过GC机制来完成垃圾回收。GC会选择一些还存活的对象作为内存遍历的根节点GC Roots，通过GC Roots的可达性来判断是否需要回收。GC Roots是系统选择的对象根节点，没有被直接或间接遍历到的引用会被GC回收，能遍历到的不回收。

##### 常见的内存泄漏   

##### 如何避免内存泄漏

### View渲染机制
### OkHttp源码分析
### Android插件化和热修复
### ListView和RecyclerView的原理和优化，区别
### AsyncTask源码解析
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
### Handler消息分发机制

即，消息的创建，传递，处理机制。用来在线程中更新UI。  
	
### APK安装与解析过程
### Dex加载
### App启动流程
### Activity的启动过程

	
### MVP MVC
### 性能优化 	
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

### 自定义view

测量onMeasure  
布局onLayout  
绘制onDraw
### 多线程，同步锁，wait&sleep
### 多进程，进程间通信
### 网络TCP/IP, HTTP
### sqlite数据库知识
### ContentProvider相关知识

**多个进程同时调用一个ContentProvider的query获取数据，ContentPrvoider是如何反应的呢？**  
一个content provider可以接受来自另外一个进程的数据请求。尽管ContentResolver与ContentProvider类隐藏了实现细节，但是ContentProvider所提供的query()，insert()，delete()，update()都是在ContentProvider进程的线程池中被调用执行的，而不是进程的主线程中。这个线程池是有Binder创建和维护的，其实使用的就是每个应用进程中的Binder线程池。

## Java 
### Java类加载
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
