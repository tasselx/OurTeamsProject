
针对Android软件开发收集的一系列经典代码的集合体！与大家共同学习探讨！！！
===================================
  
项目名称：XiaoMaFrame
===================================

项目归属者：Everyone ! ! ! 
===================================
  
 
注：该项目目前开发版本仅在target=android-14，请大家自行更改本地project-properties为14，防止冲突。
-----------------------------------
  
  

### 该项目入口基本配置都在FrameApplication.java全局Application中实现，请详细阅读
    /**
     * Invoke this method when this application start
     */
    @SuppressLint("NewApi")
    @Override
    public void onCreate()
    {
        super.onCreate();
        
        /** This step in order to monitoring our app */
        if (BuildConfig.DEBUG)
        {
            Utils.enableStrictMode();
        }
        
        /** register app exception factory */
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        
        /** init app data file path */
        initPath();
        
        /** init app sharepreferences file */
        initPreferences();
        
        /** init app networkconfig */
        networkConfig();
        
    }



### 传送门：
1.[点击这里你可以链接到 "酷_莫名简单、小马果" 的GitHub主页](https://github.com/xiaomaguoguo)<br />
2.[点击这里你可以链接到 "酷_莫名简单、小马果" 的51博客](http://mzh3344258.blog.51cto.com/)<br />
3.[点击这里你可以链接到 "酷_莫名简单、小马果" 的CSDN博客](http://blog.csdn.net/mzh3344258)<br />


### "酷_莫名简单、小马果" 标志性建筑：
![XiaoMaGuo](https://raw.github.com/xiaomaguoguo/XiaoMaFrame/master/res/drawable-mdpi/xiaoma.jpg "酷_莫名简单、小马果，一个很幸福的坏人！！！")


### 项目属于：

> Android开发新手
>
> Android开发熟手、高手
>
> 从事Android应用软件开发的你、我、他！！！



### 项目Library中包含子项目列表如上：

* 经典侧边栏（通吃 上、下、左、右滑动！！！）

* ListView分页显示，用于多条数据分页学习使用,新手跳过

* 多种状态ProgressBar显示效果实现

*最火的下拉刷新（同样的，通吃 上拉、下拉、左拉、右拉刷新列表！！！）


注意：Library路径下的项目会不定期添加更新，大家可自行下载使用、仅删除本地的，不要勿删master分支哦！

-----------------------------------



小马果名言：【誓言、会老】…【承诺、会变】…【目标、遥远】…【选择、不后悔】…【对自己要狠】！！！
===================================