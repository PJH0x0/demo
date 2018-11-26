# 前言

有两种情况，一种是设备号显示问号，另一种是设备名显示正确，第一种情况是/etc/udev/rules.d下缺少70-Android.rules设备文件，第二种是设备文件有问题

# 第二种情况的临时解决方案

将usb连接由充电改为传送文件即可

# 永久解决方案

1. lsusb，查看对应的vendorid和productid
``` shell
Bus 001 Device 013: ID 05c6:9091 Qualcomm, Inc. 
Bus 002 Device 002: ID 0e0f:0003 VMware, Inc. Virtual Mouse
Bus 002 Device 003: ID 0e0f:0002 VMware, Inc. Virtual USB Hub
Bus 001 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
Bus 002 Device 001: ID 1d6b:0001 Linux Foundation 1.1 root hub
```

2. cd /etc/udev/rules.d，进入对应文件夹
3. ls，查看是否有70-Android.rules
4. 如果没有就直接创建一个，这个要获取sudo权限
5. 在70-Android.rules中添加，也要获取sudo权限，
``` shell
#注意idvendor和idProduct要和lsusb中对应
SUBSYSTEM=="usb", ATTR{idVendor}=="05c6", ATTR{idProduct}=="9091", MODE="0666"
```

6. sudo chmod a+r 70-Android.rules，然后sudo service udev restart
7. 70-Android.rules的70代表版本号，Android N以下都可以使用