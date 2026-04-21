---
title: 版本支持
order: 1
---

# 版本支持

## 分支状态

Biome Snapshot 在多个分支中进行维护，以确保兼容不同的 Minecraft 版本。

| 分支                                                                                                                                     | 状态 | Minecraft 版本     |
|----------------------------------------------------------------------------------------------------------------------------------------|----|------------------|
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v1" target="_blank" rel="noreferrer" class="vp-external-link-icon">v1</a> | 稳定 | 1.18.2           |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v2" target="_blank" rel="noreferrer" class="vp-external-link-icon">v2</a> | 稳定 | 1.21 ~ 1.21.4    |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v3" target="_blank" rel="noreferrer" class="vp-external-link-icon">v3</a> | 稳定 | 1.18 ~ 1.18.1    |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v4" target="_blank" rel="noreferrer" class="vp-external-link-icon">v4</a> | 稳定 | 1.19 ~ 1.19.2    |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v5" target="_blank" rel="noreferrer" class="vp-external-link-icon">v5</a> | 稳定 | 1.19.3 ~ 1.19.4  |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v6" target="_blank" rel="noreferrer" class="vp-external-link-icon">v6</a> | 稳定 | 1.20 ~ 1.20.6    |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v7" target="_blank" rel="noreferrer" class="vp-external-link-icon">v7</a> | 稳定 | 1.21.5 ~ 1.21.10 |
| <a href="https://github.com/ALiaohaolong/biome-snapshot/tree/v8" target="_blank" rel="noreferrer" class="vp-external-link-icon">v8</a> | 稳定 | 1.21.11          |

::: tip 提示： 关于 master 分支
<a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/master" target="_blank" rel="noreferrer" class="vp-external-link-icon">`master`</a> 分支仅用于文档和发布。
:::

## 分支差异

各分支的内部实现存在一些关键差异，主要源于对原版机制的适配。

### v3 (1.18 ~ 1.18.1)

基础分支。

### v1 (1.18.2)

相比 v3，修改了获取生物群系分类的方式。

::: info
v3 分支中使用的获取生物群系分类的接口被标记 @Deprecated，并且该接口在 1.19 中正式移除。受此影响，我们使用新的标签系统获取生物群系分类。
:::

### v4 (1.19 ~ 1.19.2)

相比 v1，修改了区块的获取方式。

::: info
v1 分支中使用的区块获取方式在 1.19 中无法正常使用。受此影响，我们调整了区块获取时的参数。
:::

### v5 (1.19.3 ~ 1.19.4)

相比 v4，针对特定命令增加了补丁，以避免 OutOfMemory 崩溃问题。

::: info
1.19.3 版本中，区块生成的某个阶段会发布延迟处理任务（该任务包含一个对 ProtoChunk 的引用），导致所有的 ProtoChunk 无法回收，从而在绘制大图时，具有潜在的 OutOfMemory 崩溃风险。
:::

### v6 (1.20 ~ 1.20.6)

相比 v5，更新了文本组件的创建方式。

::: info
v5 分支中的文本组件使用方式在 1.20 中被重构。受此影响，我们更新了文本组件的实现。
:::

### v2 (1.21 ~ 1.21.4)

相比 v6，更新了识别符的创建方式。

::: info
v6 分支中使用的识别符创建方式在 1.21 中被禁用。受此影响，我们更新了识别符的创建方式。
:::

### v7 (1.21.5 ~ 1.21.10)

相比 v2，修改了移除票据方法的参数。

::: info
v2 分支中使用的移除票据方法在 1.21.5 中发生变化。受此影响，我们更新了移除票据的方法。
:::

### v8 (1.21.11)

相比 v7，更新了设置命令权限的方式。
