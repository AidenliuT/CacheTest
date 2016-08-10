1.UI布局分析：
      刚进主页面的时候：
        1.启动页
        2.主页面
            ViewPager+RadioGroup.
            ViewPager里面包含了
                3个Fragment。
                文章、论坛、游戏

            2.1文章的Fragment。
                ViewPager+TabLayout

                需要多个Fragment对象。
                但是，这些Fragment除了请求的地址不一样，其他都一样，所以，选择使用
                一个Fragment就OK。
                注意： 第一个Fragment 需要 ViewPager作为Header。
                    提示：判断typeId即可。

                   Fragment有一个ListView，用来展示请求的数据。

            2.2论坛的Fragment。
                WebView 展示网络内容即可

            2.3游戏的Fragment。
                Spinner + GridView

2.代码结构分析：




