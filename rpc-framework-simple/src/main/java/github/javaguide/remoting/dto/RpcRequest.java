package github.javaguide.remoting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author shuang.kou
 * @createTime 2020年05月10日 08:24:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
        private static final long serialVersionUID = 1905122041950251207L;
        private String requestId;
        //向服务器请求的接口名称
        private String interfaceName;
        //向服务器请求的方法名称
        private String methodName;
        //参数
        private Object[] parameters;
        //参数类型
        private Class<?>[] paramTypes;
        //版本
        private String version;
        //接口多实现类下的group处理
        private String group;

    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }
}
