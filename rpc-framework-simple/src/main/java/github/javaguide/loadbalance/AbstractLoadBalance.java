package github.javaguide.loadbalance;

import github.javaguide.remoting.dto.RpcRequest;
import github.javaguide.utils.CollectionUtil;

import java.util.List;

/**
 * Abstract class for a load balancing policy
 */
public abstract class AbstractLoadBalance implements LoadBalance {
    @Override
    public String selectServiceAddress(List<String> serviceAddresses, RpcRequest rpcRequest) {
        //目前没有serviceAddresses
        if (CollectionUtil.isEmpty(serviceAddresses)) {
            return null;
        }
        //只有一个
        if (serviceAddresses.size() == 1) {
            return serviceAddresses.get(0);
        }

        return doSelect(serviceAddresses, rpcRequest);
    }

    //在抽象类里面定义了serviceAddresses > 1的情况如何选择
    protected abstract String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest);

}
