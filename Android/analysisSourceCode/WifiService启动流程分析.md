# 总流程分析
SystemServer.run()-->SystemServiceManager.startService(WIFI_SERVICE_CLASS)-->WifiService.WifiService(context)-->WifiService.onStart()-->SystemService.publishBinderService(Context.WIFI_SERVICE, WifiServiceImpl)-->ServiceManager.addService(name, service, false);

## 1. SystemServiceManager.startService(WIFI_SERVICE_CLASS)
	/**
     * Starts a service by class name.
     *
     * @return The service instance.
     */
    @SuppressWarnings("unchecked")
    public SystemService startService(String className) {
        final Class<SystemService> serviceClass;
        try {
            serviceClass = (Class<SystemService>)Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Slog.i(TAG, "Starting " + className);
            throw new RuntimeException("Failed to create service " + className
                    + ": service class not found, usually indicates that the caller should "
                    + "have called PackageManager.hasSystemFeature() to check whether the "
                    + "feature is available on this device before trying to start the "
                    + "services that implement it", ex);
        }
        //返回对应的SystemService,但是SystemServer并没有进行处理
        return startService(serviceClass);
    }
    
	/**
     * Creates and starts a system service. The class must be a subclass of
     * {@link com.android.server.SystemService}.
     *
     * @param serviceClass A Java class that implements the SystemService interface.
     * @return The service instance, never null.
     * @throws RuntimeException if the service fails to start.
     */
    @SuppressWarnings("unchecked")
    public <T extends SystemService> T startService(Class<T> serviceClass) {
        try {
            final String name = serviceClass.getName();
            Slog.i(TAG, "Starting " + name);
            Trace.traceBegin(Trace.TRACE_TAG_SYSTEM_SERVER, "StartService " + name);

            // Create the service.
            if (!SystemService.class.isAssignableFrom(serviceClass)) {
                throw new RuntimeException("Failed to create " + name
                        + ": service must extend " + SystemService.class.getName());
            }
            final T service;
            try {
            	//通过反射创建对象最终调用到Native方法里面
                Constructor<T> constructor = serviceClass.getConstructor(Context.class);
                service = constructor.newInstance(mContext);
            } catch (InstantiationException ex) {
                throw new RuntimeException("Failed to create service " + name
                        + ": service could not be instantiated", ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException("Failed to create service " + name
                        + ": service must have a public constructor with a Context argument", ex);
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException("Failed to create service " + name
                        + ": service must have a public constructor with a Context argument", ex);
            } catch (InvocationTargetException ex) {
                throw new RuntimeException("Failed to create service " + name
                        + ": service constructor threw an exception", ex);
            }

            // Register it.
            //在ServiceManager中进行注册
            mServices.add(service);

            // Start it.
            try {
            	//调用Service的onStart方法
                service.onStart();
            } catch (RuntimeException ex) {
                throw new RuntimeException("Failed to start service " + name
                        + ": onStart threw an exception", ex);
            }
            return service;
        } finally {
            Trace.traceEnd(Trace.TRACE_TAG_SYSTEM_SERVER);
        }
    }

    
    
## 2. WifiService.WifiService(Context)

	public WifiService(Context context) {
        super(context);
        mImpl = new WifiServiceImpl(context);
    }

### 2.1 WifiServiceImpl(Context)
    public WifiServiceImpl(Context context) {
        mContext = context;
        //暂时不知道啥意思，大概是加密和锁之类
        mWifiInjector = WifiInjector.getInstance();
        //获取Setting.Global数据库的东西
        mFacade = new FrameworkFacade();
        //创建一个WifiService线程
        HandlerThread wifiThread = new HandlerThread("WifiService");
        wifiThread.start();
        //创建一个Wifi指标的类似这么个东西
        mWifiMetrics = mWifiInjector.getWifiMetrics();
        //创建一个Wifi轮询通信这么个东西
        mTrafficPoller = new WifiTrafficPoller(mContext, wifiThread.getLooper(),
                WifiNative.getWlanNativeInterface().getInterfaceName());
        mUserManager = UserManager.get(mContext);
        //又创建了一个Wifi状态机的线程
        HandlerThread wifiStateMachineThread = new HandlerThread("WifiStateMachine");
        wifiStateMachineThread.start();
        //地区码
        mCountryCode = new WifiCountryCode(
                WifiNative.getWlanNativeInterface(),
                SystemProperties.get(BOOT_DEFAULT_WIFI_COUNTRY_CODE),
                mFacade.getStringSetting(mContext, Settings.Global.WIFI_COUNTRY_CODE),
                mContext.getResources().getBoolean(
                        R.bool.config_wifi_revert_country_code_on_cellular_loss));
        //创建了一个Wifi状态机
        mWifiStateMachine = new WifiStateMachine(mContext, mFacade,
            wifiStateMachineThread.getLooper(), mUserManager, mWifiInjector,
            new BackupManagerProxy(), mCountryCode);
        mSettingsStore = new WifiSettingsStore(mContext);
        mWifiStateMachine.enableRssiPolling(true);
        mBatteryStats = BatteryStatsService.getService();
        mPowerManager = context.getSystemService(PowerManager.class);
        mAppOps = (AppOpsManager)context.getSystemService(Context.APP_OPS_SERVICE);
        mCertManager = new WifiCertManager(mContext);

        mNotificationController = new WifiNotificationController(mContext,
                wifiThread.getLooper(), mWifiStateMachine, mFacade, null);

        mWifiLockManager = new WifiLockManager(mContext, mBatteryStats);
        mClientHandler = new ClientHandler(wifiThread.getLooper());
        mWifiStateMachineHandler = new WifiStateMachineHandler(wifiThread.getLooper());
        mWifiController = new WifiController(mContext, mWifiStateMachine,
                mSettingsStore, mWifiLockManager, wifiThread.getLooper(), mFacade);
        if (ensureConcurrencyFileExist()) {
            readConcurrencyConfig();
        }
        if (mStaAndApConcurrency == 1) {
            mWifiStateMachine.setStaSoftApConcurrency();
            mSoftApStateMachine = mWifiStateMachine.getSoftApStateMachine();
            if (mSoftApInterfaceName != null) {
                mSoftApStateMachine.setSoftApInterfaceName(mSoftApInterfaceName);
            }
            if (mSoftApChannel != 0) {
                mSoftApStateMachine.setSoftApChannel(mSoftApChannel);
            }
            mWifiController.setSoftApStateMachine(mSoftApStateMachine);
        }
        // Set the WifiController for WifiLastResortWatchdog
        mWifiInjector.getWifiLastResortWatchdog().setWifiController(mWifiController);
    }