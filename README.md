
���Android��������ռ���һϵ�о������ļ����壡���ҹ�ͬѧϰ̽�֣�����
===================================
  
��Ŀ���ƣ�XiaoMaFrame
===================================

��Ŀ�����ߣ�Everyone ! ! ! 
===================================
  
 
ע������ĿĿǰ�����汾����target=android-14���������и��ı���project-propertiesΪ14����ֹ��ͻ��
-----------------------------------
  
  

### ����Ŀ��ڻ������ö���FrameApplication.javaȫ��Application��ʵ�֣�����ϸ�Ķ�
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



### �����ţ�
1.[���������������ӵ� "��_Ī���򵥡�С���" ��GitHub��ҳ](https://github.com/xiaomaguoguo)<br />
2.[���������������ӵ� "��_Ī���򵥡�С���" ��51����](http://mzh3344258.blog.51cto.com/)<br />
3.[���������������ӵ� "��_Ī���򵥡�С���" ��CSDN����](http://blog.csdn.net/mzh3344258)<br />


### "��_Ī���򵥡�С���" ��־�Խ�����
![XiaoMaGuo](https://raw.github.com/xiaomaguoguo/XiaoMaFrame/master/res/drawable-mdpi/xiaoma.jpg "��_Ī���򵥡�С�����һ�����Ҹ��Ļ��ˣ�����")


### ��Ŀ���ڣ�

> Android��������
>
> Android�������֡�����
>
> ����AndroidӦ������������㡢�ҡ���������



### ��ĿLibrary�а�������Ŀ�б����ϣ�

* ����������ͨ�� �ϡ��¡����һ�����������

* ListView��ҳ��ʾ�����ڶ������ݷ�ҳѧϰʹ��,��������

* ����״̬ProgressBar��ʾЧ��ʵ��

*��������ˢ�£�ͬ���ģ�ͨ�� ����������������������ˢ���б�������


ע�⣺Library·���µ���Ŀ�᲻������Ӹ��£���ҿ���������ʹ�á���ɾ�����صģ���Ҫ��ɾmaster��֧Ŷ��

-----------------------------------



С������ԣ������ԡ����ϡ�������ŵ����䡿����Ŀ�ꡢңԶ������ѡ�񡢲���ڡ��������Լ�Ҫ�ݡ�������
===================================