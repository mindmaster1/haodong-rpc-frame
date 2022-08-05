package github.javaguide.compress;

import github.javaguide.extension.SPI;

/**
 * @author wangtao .
 * @createTime on 2020/10/3
 */

@SPI
public interface Compress {
    //压缩
    byte[] compress(byte[] bytes);

    //解压缩
    byte[] decompress(byte[] bytes);
}
