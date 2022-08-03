# 主要更新

## 1.添加圆圈加载动画

两种方式

+ 第一种用 Animation 设置监听`AnimationListener`实现，但是还有问题，隐藏再次显示之后，第一个动画没有执行

+ 第二种用 ObjectAnimator 和 组合动画`AnimatorSet` 和定时器实现