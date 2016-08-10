### 二次缓存 简单实例
##### 类的设计。
1. 缓存：
    **二级**

    - *内存缓存*: 
        MemoryCache
            *get
            *put
            remove
            removeAll

    - *磁盘缓存*: 
        DiskCache
            *get
            *put
            remove
            removeAll

    - *网络获取*: Network
                download


2. 有一个类，用来管理这些缓存:
    **ImageLoader**
  
       | |无    |       无
        --|--|--|--|--
        需要图片---> |内存缓存 ---> |磁盘缓存 --->| 网络获取
                    有  |       有  |        有  |
        直接返回------------------------------
            
  