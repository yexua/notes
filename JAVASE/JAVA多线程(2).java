---------------------------------线程控制---------------------------------
优先级
getPriority();//返回线程的优先级
	|--默认的优先级是5！
setPriority();//更新线程优先级。
	|--只能传入   1-5-10  三个类型的数字。否则会报错。线程优先级仅仅表示线程获取时间片的几率高。
	但是要在次数比较多。或者多次运行的时候。才能看到比较好的效果。
线程休眠
sleep(long);
	|--让线程休眠指定的毫秒数。
	这个线程必须 try catch 处理。不能 throws 抛出。
线程加入
join();
	|--当前线程运行结束后。后面的线程才运行。
礼让线程
yield();
	|--让多个线程的运行更和谐。只能是和谐。
守护线程
setDaemon(true);
	|--设置该线程为守护线程。在start();启动前使用此方法必须。当整个JVM里面只剩下守护线程还在运行，或者还没运行完毕的时候。JVM自动退出。
中断线程
stop();
	|--该方法比较暴力。不建议使用。会直接停止当前的线程。
interrupt();
	|--把线程的状态终止。并抛出一个 InterruptedException.
---------------------------------线程的状态---------------------------------
新建 -- 创建线程对象
就绪 -- 有执行资格，但是没执权
运行 -- 有执行资格，有执行权
	阻塞 -- 由于一些操作，让线程处于了该状态。没有执行资格。没有执行权。
			而另一些操作会让它又处于就绪状态。
死亡 -- 线程对象变成垃圾，等待被回收
-------------------------------------锁--------------------------------！！！重点。JDK5以后的新特性
为了更清晰的表达，如何加锁。释放锁。就可以使用 Lock
Lock 是一个接口。不能直接创建对象。一般选择它的子类
	|--子类 ReentrantLock
在多线程类中创建 ReentrantLock lock = new ReentrantLock();
lock.lock();//获取锁。其实就是加锁。
lock.unlock();//释放锁。
	|----以上两个方法一般和try finally 使用。因为lock代码部分就算是出现异常。也要让锁必须释放。
------------------------------------ 等待唤醒机制 ----------------------------
Object 类中提供了三个方法！
wait(); //当前锁失去执行权限
notify();//唤醒当前锁的线程
notifyAll();//唤醒所有线程
------------------------------------ 定时器---------------------------
对线程进行定时处理。可以通过 Timer 和 TimerTask (抽象类) 类。来定义调度功能。
Timer 定时器
Timer t = new Timer();
TimerTask 任务 继承这个抽象类。覆写里面的 run 方法。run里面存放的就是要定时执行的代码。
t.schedule(new TimerTask(),long);//在指定毫秒数后执行 TimerTaks 子类中run方法中的代码。
//这个方法含有很多个重载形式。可以实现屡次重复等一些列的及时操作。详参阅API！
t.schedule();//停止计时器。
 
------------------------------------线程组-------------------------------
把多个线程组成一个组，'可以对多个线程进行统一的操作'。
getThreadGroup();//返回当前线程所在的线程组.返回就是线程组对象。 ThreadGroup
ThreadGroup
getName();//返回该线程组的名字。返回String类型。线程默认情况下是属于main线程组。
---修改线程所在的组
创建一个新的线程组。创建其他线程的时候。指定为新创建的线程组。
ThreadGroup tg = new ThreadGroup("线程组名字");//创建一个线程组
Thread t = Thread(tg,"线程名字");//在创建线程的时候。通过构造方法把该线程存储到指定的线程组。
----该类方法有很多可以直接操作整个线程组的方法，参阅API。可以一下很方便的操作整个线程组。
-----------------------------------线程池--------------------------------
程序启动一个新的线程成本比较高。涉及到跟操作系统交互。
线程池可以提高性能。尤其是当程序中要创建大量生存期很短的线程时。
更应该考虑使用线程池。
	线程池里面每个线程代码结束后。并不会死亡。而是再次回到线程池中成为空闲状态。
等待下一个对象来使用。JKD1.5以前。我们必须手动实现自己的线程池。从JDK1.5以后。JAVA内置支持线程池。
Executors JDK1.5新增工厂类。来产生线程池。
--例如：
import java.util.*;
import java.util.concurrent.*;
public class Demo
{
	public static void main(String[] args)
	{
		//创建线程池对象,后面的参数是约束。要控制的线程数量
		ExecutorService pool = Executors.newFixedThreadPool(2);
		pool.submit(new Runnable(){public void run(){}});//匿名内部类实现Runnable接口的子类-线程1
		pool.submit(new Runnable(){public void run(){}});//匿名内部类实现Runnable接口的子类-线程2
		pool.shutdown();//关闭线程池。不接受新的线程以及任务。
	}
}
------------创建线程的还有一种方法，理解就行。其实就是依赖上面的线程池。
这种方法创建出来的多线程。有返回值。而且，可以进行try catch ！
Callable<v> 这个是一个带泛型的接口。
public Object call(){};//多线程的代码。
这里指定的泛型，其实是 call();方法返回值的类型。

