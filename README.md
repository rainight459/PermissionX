# 自定义圆角文本框 (Custom Rounded TextView)

这是一个Android项目，演示如何使用Kotlin创建自定义的圆角文本框组件。

## 功能特点

- ✅ 自定义圆角半径
- ✅ 边框颜色和宽度设置
- ✅ 纯色背景或渐变背景
- ✅ 多种渐变方向
- ✅ 动态修改样式
- ✅ 完全可定制的属性

## 使用方法

### 1. 在布局文件中使用

```xml
<com.example.customtextview.RoundedTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="你的文本"
    android:textSize="16sp"
    android:textColor="#333333"
    android:padding="16dp"
    android:gravity="center"
    app:cornerRadius="12dp"
    app:fillColor="#E3F2FD"
    app:strokeWidth="2dp"
    app:strokeColor="#2196F3" />
```

### 2. 可用的自定义属性

- `app:cornerRadius` - 圆角半径
- `app:strokeWidth` - 边框宽度
- `app:strokeColor` - 边框颜色
- `app:fillColor` - 填充颜色
- `app:gradientStartColor` - 渐变开始颜色
- `app:gradientEndColor` - 渐变结束颜色
- `app:gradientOrientation` - 渐变方向 (left_right, top_bottom, tr_bl, br_tl)

### 3. 代码中动态设置

```kotlin
val textView = findViewById<RoundedTextView>(R.id.my_text_view)

// 设置圆角
textView.setCornerRadius(20f)

// 设置边框
textView.setStrokeWidth(3f)
textView.setStrokeColor(Color.BLUE)

// 设置纯色背景
textView.setFillColor(Color.LTGRAY)

// 设置渐变背景
textView.setGradientColors(
    Color.parseColor("#FF6B6B"),
    Color.parseColor("#4ECDC4"),
    GradientDrawable.Orientation.LEFT_RIGHT
)
```

## 项目结构

```
app/
├── src/main/
│   ├── java/com/example/customtextview/
│   │   ├── RoundedTextView.kt          # 自定义文本框组件
│   │   └── MainActivity.kt             # 主活动
│   └── res/
│       ├── layout/
│       │   └── activity_main.xml       # 主布局文件
│       └── values/
│           ├── attrs.xml               # 自定义属性定义
│           ├── colors.xml              # 颜色资源
│           ├── strings.xml             # 字符串资源
│           └── themes.xml              # 主题资源
```

## 运行项目

1. 确保你的开发环境配置了Android SDK
2. 使用Android Studio打开项目
3. 同步Gradle文件
4. 运行应用

## 自定义和扩展

你可以轻松地扩展 `RoundedTextView` 类来添加更多功能：

- 添加阴影效果
- 支持更复杂的渐变
- 添加动画效果
- 支持更多的形状选项

## 最低版本要求

- Android API 24 (Android 7.0)
- Kotlin 1.9.0
- Android Gradle Plugin 8.2.0