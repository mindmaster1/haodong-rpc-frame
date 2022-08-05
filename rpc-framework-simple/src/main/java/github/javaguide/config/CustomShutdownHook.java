package github.javaguide.config;

import github.javaguide.registry.zk.util.CuratorUtils;
import github.javaguide.remoting.transport.netty.server.NettyRpcServer;
import github.javaguide.utils.concurrent.threadpool.ThreadPoolFactoryUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * When the server  is closed, do something such as unregister all services
 *
 */
@Slf4j
public class CustomShutdownHook {
    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownHook() {
        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll() {
        log.info("addShutdownHook for clearAll");
        //Runtime.getRuntime ()可以取得当前JVM的运行时环境，这也是在Java中唯一一个得到运行时环境的方法
        //RunTime.getRunTime ().addShutdownHook的作用就是在JVM销毁前执行的一个线程.当然这个线程依然要自己写.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                //Socket通信-获取地址和端口号
                InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), NettyRpcServer.PORT);
                //Zookeeper-- CuratorUtils 销毁所有注册的连接
                CuratorUtils.clearRegistry(CuratorUtils.getZkClient(), inetSocketAddress);
            } catch (UnknownHostException ignored) {
            }
            //创建 ThreadPool(线程池) 的工具类.
            ThreadPoolFactoryUtil.shutDownAllThreadPool();
        }));
    }
}
