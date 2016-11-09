# HorizontalProgressBar

##### 一个带动画效果的横向进度条
###### A horizontal progressbar with animation for Android

##### 导入
`compile 'com.miracleshed:HorizontalProgressBar:1.0'`

##### 属性
- `backgroundColor` 背景颜色
- `progressColor` 进度颜色
- `maxProgress` 最大进度值
- `progress` 当前进度值
- `animDuration` 动画时长

##### 方法
- `setProgress(float progress)` 设置当前进度值
- ...

##### XML
```
<com.miracleshed.hpb.HorizontalProgressBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:backgroundColor="@color/colorPrimary"
        app:progressColor="@color/colorAccent"
        app:maxProgress="100"
        app:progress="20"
        app:animDuration="600" />
```


