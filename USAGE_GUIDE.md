# 自定义圆角文本框使用指南

## 概述

这个项目提供了两个主要的自定义组件：
- `RoundedTextView` - 用于显示文本的圆角文本框
- `RoundedEditText` - 用于用户输入的圆角输入框

## 🎯 RoundedTextView 使用指南

### 基本用法

```xml
<com.example.customtextview.RoundedTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Hello World"
    android:textSize="16sp"
    android:textColor="#333333"
    android:padding="16dp"
    app:cornerRadius="12dp"
    app:fillColor="#E3F2FD"
    app:strokeWidth="2dp"
    app:strokeColor="#2196F3" />
```

### 支持的属性

| 属性 | 类型 | 描述 |
|------|------|------|
| `app:cornerRadius` | dimension | 圆角半径 |
| `app:strokeWidth` | dimension | 边框宽度 |
| `app:strokeColor` | color | 边框颜色 |
| `app:fillColor` | color | 背景填充色 |
| `app:gradientStartColor` | color | 渐变开始颜色 |
| `app:gradientEndColor` | color | 渐变结束颜色 |
| `app:gradientOrientation` | enum | 渐变方向 |

### 渐变方向选项

- `left_right` - 从左到右
- `top_bottom` - 从上到下
- `tr_bl` - 从右上到左下
- `br_tl` - 从右下到左上

### 代码中动态设置

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

## 🎯 RoundedEditText 使用指南

### 基本用法

```xml
<com.example.customtextview.RoundedEditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="请输入文本"
    android:textSize="16sp"
    android:padding="16dp"
    app:cornerRadius="12dp"
    app:fillColor="#F5F5F5"
    app:strokeWidth="2dp"
    app:strokeColor="#E0E0E0"
    app:focusedStrokeColor="#2196F3"
    app:focusedFillColor="#FFFFFF" />
```

### 支持的属性

| 属性 | 类型 | 描述 |
|------|------|------|
| `app:cornerRadius` | dimension | 圆角半径 |
| `app:strokeWidth` | dimension | 边框宽度 |
| `app:strokeColor` | color | 边框颜色 |
| `app:fillColor` | color | 背景填充色 |
| `app:focusedStrokeColor` | color | 获得焦点时边框颜色 |
| `app:focusedFillColor` | color | 获得焦点时背景色 |
| `app:focusedStrokeWidth` | dimension | 获得焦点时边框宽度 |

### 代码中动态设置

```kotlin
val editText = findViewById<RoundedEditText>(R.id.my_edit_text)

// 设置基本样式
editText.setCornerRadius(15f)
editText.setStrokeWidth(2f)
editText.setStrokeColor(Color.GRAY)
editText.setFillColor(Color.WHITE)

// 设置焦点样式
editText.setFocusedColors(Color.BLUE, Color.parseColor("#F0F8FF"))
editText.setFocusedStrokeWidth(3f)
```

## 📱 常见使用场景

### 1. 按钮样式

```xml
<com.example.customtextview.RoundedTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="点击我"
    android:textColor="#FFFFFF"
    android:padding="16dp"
    android:clickable="true"
    android:focusable="true"
    app:cornerRadius="25dp"
    app:fillColor="#4CAF50" />
```

### 2. 卡片样式

```xml
<com.example.customtextview.RoundedTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="卡片内容"
    android:textColor="#333333"
    android:padding="20dp"
    app:cornerRadius="12dp"
    app:fillColor="#FFFFFF"
    app:strokeWidth="1dp"
    app:strokeColor="#E0E0E0" />
```

### 3. 搜索框

```xml
<com.example.customtextview.RoundedEditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="🔍 搜索..."
    android:textSize="16sp"
    android:padding="16dp"
    android:singleLine="true"
    app:cornerRadius="25dp"
    app:fillColor="#F0F0F0"
    app:strokeWidth="1dp"
    app:strokeColor="#E0E0E0"
    app:focusedFillColor="#FFFFFF"
    app:focusedStrokeColor="#2196F3"
    app:focusedStrokeWidth="2dp" />
```

### 4. 表单输入框

```xml
<com.example.customtextview.RoundedEditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="用户名"
    android:textSize="16sp"
    android:padding="16dp"
    android:singleLine="true"
    app:cornerRadius="8dp"
    app:fillColor="#F8F8F8"
    app:strokeWidth="1dp"
    app:strokeColor="#CCCCCC"
    app:focusedStrokeColor="#007BFF"
    app:focusedFillColor="#FFFFFF" />
```

## 🎨 设计技巧

### 1. 颜色搭配

- **主色调**: 使用品牌色作为焦点状态颜色
- **中性色**: 使用灰色系作为默认边框和背景
- **对比度**: 确保文本和背景有足够的对比度

### 2. 圆角设计

- **小圆角 (4-8dp)**: 适合表单控件
- **中圆角 (12-16dp)**: 适合卡片和按钮
- **大圆角 (20-25dp)**: 适合胶囊形按钮
- **半圆**: 设置为高度的一半，创造圆形效果

### 3. 边框使用

- **1dp**: 细边框，适合分割和强调
- **2dp**: 中等边框，适合重要控件
- **3dp+**: 粗边框，适合特别强调的元素

## 🔧 进阶自定义

### 1. 继承扩展

```kotlin
class MyCustomTextView(context: Context, attrs: AttributeSet) : RoundedTextView(context, attrs) {
    
    init {
        // 添加自定义逻辑
        setupCustomBehavior()
    }
    
    private fun setupCustomBehavior() {
        // 例如：添加阴影、动画等
    }
}
```

### 2. 样式资源

在 `styles.xml` 中定义样式：

```xml
<style name="PrimaryButton" parent="Widget.AppCompat.TextView">
    <item name="cornerRadius">12dp</item>
    <item name="fillColor">@color/primary_color</item>
    <item name="strokeWidth">0dp</item>
    <item name="android:textColor">@color/white</item>
    <item name="android:padding">16dp</item>
</style>
```

### 3. 主题集成

```xml
<!-- 在themes.xml中定义 -->
<style name="AppTheme.EditText" parent="Widget.AppCompat.EditText">
    <item name="cornerRadius">8dp</item>
    <item name="strokeWidth">1dp</item>
    <item name="strokeColor">?attr/colorControlNormal</item>
    <item name="focusedStrokeColor">?attr/colorPrimary</item>
</style>
```

## 🐛 常见问题

### 1. 圆角不显示

**原因**: 可能是因为父容器设置了 `clipChildren="false"`
**解决**: 检查父容器的裁剪设置

### 2. 焦点状态不生效

**原因**: EditText 需要设置 `android:focusable="true"`
**解决**: 确保输入框可以获得焦点

### 3. 渐变显示异常

**原因**: 渐变颜色设置不正确
**解决**: 确保同时设置了 `gradientStartColor` 和 `gradientEndColor`

## 📚 更多示例

查看 `activity_main.xml` 文件中的完整示例，包含了各种不同样式的文本框和输入框。

## 🚀 性能优化

1. **避免频繁更新**: 不要在滚动过程中频繁更改样式
2. **复用样式**: 对于相同样式的控件，使用样式资源
3. **合理使用渐变**: 渐变会增加绘制开销，谨慎使用

## 📞 支持

如果您在使用过程中遇到问题，请检查：
1. 是否正确导入了自定义属性
2. 是否在正确的命名空间中使用属性
3. 是否遵循了Android的布局规范