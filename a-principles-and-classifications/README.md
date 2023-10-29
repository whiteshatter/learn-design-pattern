# 《第一章 设计模式的原则和分类》

## 一、七大原则

代码详见 [包路径](src/main/java/com/mint/learn/designpattern/principle)

### 1. 单一职责原则

> 一个类只负责一种职责

不同人和不同项目对单一职责的划分有不同看法。

如对用户注册与用户登录这一需求来看，可以单独设定一个用户类，包含了登录注册功能

```java
// 用户服务
public interface UserService {
    /**
     * 注册
     * @param args
     * @return
     */
    Object register(String... args);

    /**
     * 登录
     */
    Object login(String... args);
}
```

这种做法符合单一职责原则，但假如登录功能需要第三方验证方式，注册功能需要短信、邮箱等校验，则可以对登录注册进行拆分

```java
// 登录服务
public interface LoginService {
    /**
     * 默认登录方式
     * @param args
     * @return
     */
    Object doDefaultLogin(String... args);

    /**
     * 第三方登录
     * @param args
     * @return
     */
    Object do3rdPartLogin(String... args);

    /**
     * 第三方账号授权验证
     */
    Object valid3rdAccount(String... args);
}
```

```java
// 注册服务
public interface RegisterService {
    /**
     * 注册
     * @param args
     * @return
     */
    Object register(String... args);

    /**
     * 短信验证
     * @param phoneNum
     * @return
     */
    Object phoneCodeSend(String phoneNum);

    /**
     * 邮箱验证
     * @param emailAddress
     * @return
     */
    Object mailCodeSend(String emailAddress);
}
```

因此单一职责原则并没有正确与错误，一切以项目实际情况为出发点进行设计

### 2. 接口隔离原则

> 单一职责原则主要注重于职责，接口隔离在单一职责的基础上，注重接口的准确性和最小化

#### 2.1 不使用没有任何依赖的接口

```java
// jdk EmptyList设计
private static class EmptyList<E>
        extends AbstractList<E>
        implements RandomAccess, Serializable {
}
```
其中RandomAccess为可以随机访问的标识接口，但EmptyList类继承了RandomAccess接口，却没有元素用来随机访问，单从接口隔离原则来看该类使用了不必要的接口，不符合接口隔离原则

#### 2.2 一个类对另一个类的依赖性应当建立在最小接口上
```java
// ArrayList设计
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```
从设计可以明显看出该类特征
1. 集合特质：实现了List接口
2. 快速随机访问：实现了RandomAccess接口
3. 支持克隆：实现了Cloneable接口
4. 支持序列化：实现了Serializable接口

该结构细化接口方法，保持接口纯洁性，做到专业、准确、最小化接口

### 3.里式替换原则
#### 3.1 子类需要实现父类所有抽象方法（为实现"替换"做准备）
```java
public void testList() {
    ArrayList<String> arrayList = new ArrayList<>();
    LinkedList<String> linkedList = new LinkedList<>();
    addList(arrayList, "1");
    addList(linkedList, "1");
}
public void addList(List<String> list, String item) {
    list.add(item);
}
```
如ArrayList和LinkedList都实现了AbstractList的抽象方法，不同子类可以替换

#### 3.2 子类可以加入自己的特有方法和属性
```java
public interface BeanFactory {
    String FACTORY_BEAN_PREFIX = "&";

    Object getBean(String var1) throws BeansException;

    <T> ObjectProvider<T> getBeanProvider(Class<T> var1);

    boolean containsBean(String var1);

    boolean isSingleton(String var1) throws NoSuchBeanDefinitionException;

    boolean isPrototype(String var1) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String var1, ResolvableType var2) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String var1, Class<?> var2) throws NoSuchBeanDefinitionException;

    @Nullable
    Class<?> getType(String var1, boolean var2) throws NoSuchBeanDefinitionException;

    String[] getAliases(String var1);
}
```
BeanFactory接口作为最基础的付接口，只包含了对Bean的获取、类型获取及判断等方法。而spring中bean加载的核心类DefaultListableBeanFactory多了对@Primary关注的isPrimary方法，对@Priority关注的getPriority方法等

### 4.依赖倒置原则
> 面向接口编程
```java
public abstract class BeanDefinitionReaderUtils {
    public static void registerBeanDefinition(
            BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry)
            throws BeanDefinitionStoreException {

        // Register bean definition under primary name.
        String beanName = definitionHolder.getBeanName();
        registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());

        // Register aliases for bean name, if any.
        String[] aliases = definitionHolder.getAliases();
        if (aliases != null) {
            for (String alias : aliases) {
                registry.registerAlias(beanName, alias);
            }
        }
    }
}
```
如该工具类的注册bean方法用的registry接口，而不是具体实现类

### 5.迪米特法则
> 只暴露方法入口，不暴露实现细节
```java
public class BeanTest {
    public void testSpring() {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring-config.xml");
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(BeanTest.class);
        System.out.println(context1.getBean("hello"));
        System.out.println(context2.getBean("hello"));
    }
}
```
可以看出我们使用spring只需要关注：
1. xml配置文件或@ComponentScan注解的配置类
2. ApplicationContext的构造函数和入参
3. getBean方法的使用

剩下的扫描spring配置、初始化环境、bean注册、各种处理器等过程都封装在内部

### 6.开闭原则
> 对扩展开放，对修改关闭，即修改功能不直接进行内部代码修改，应使用扩展的方式进行

```java
// AQS
abstract class AbstractQueuedSynchronizer {
    // 加排它锁
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }
    // 释放排它锁
    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }
    // 加共享锁
    protected int tryAcquireShared(int arg) {
        throw new UnsupportedOperationException();
    }
    // 释放共享锁
    protected boolean tryReleaseShared(int arg) {
        throw new UnsupportedOperationException();
    }
    // 该线程是否持有锁
    protected boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }
}
```
如AQS提供了5个需要扩展的方法，都是核心逻辑：加锁、释放锁、是否持有锁的判断，即对外开放
```java
abstract class AbstractQueuedSynchronizer {
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
                acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }

    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }
}
```
tryAcquire和tryRelease的调用时机倍AQS实现，子类无法修改，就是对内修改关闭
### 7.合成复用原则
> 尽可能使用聚合或组合，其次才考虑继承等实现
如SpringBoot项目中controller层使用service通常使用@Resource的方式注入，不使用继承方式

## 二、设计模式分类
### 1.创建型
- 工厂模式
- 抽象工厂模式
- 单例模式
- 建造者模式
- 原型模式

### 2.结构型
- 适配器模式
- 桥接模式
- 装饰模式
- 组合模式
- 外观模式
- 享元模式
- 代理模式

### 3.行为型模式
- 策略模式
- 模板方式模式
- 观察者模式
- 迭代子模式
- 责任链模式
- 命令模式
- 备忘录模式
- 状态模式
- 访问者模式
- 中介模式
- 解释器模式