# BiomeSnapshot

![](icon128.png)

Mod 百科：  
[Biome Snapshot - MC百科|最大的Minecraft中文MOD百科](https://www.mcmod.cn/class/20861.html)

报告问题：  
[Issues · 暗夜/Biome Snapshot - Gitee.com](https://gitee.com/AnNight/biome-snapshot/issues)

## 介绍

一个 Minecraft 模组，提供`/biome-snapshot`命令来生成不同模式的地图快照。

模组特色：

- 提供`/biome-snapshot <模式> <x1> <z1> <x2> <z2>`命令生成指定区域的快照（需要 4 级管理员权限）。
- 支持多种生物群系模式，包括生物群系模式、大陆海洋生物群系模式、以及大陆河流海洋生物群系模式。
- 支持真实的海岸线模式。
- 生成的图片保存在 `游戏文件夹/config/biome_snapshot/存档名称/` 文件夹中。
- 多语言支持，包括英文和中文。

注意事项：

- **生成快照时，游戏/服务器将被暂停，直至生成完毕。**
- 生成快照时，将每隔5秒反馈一次生成进度，请耐心等待。
- 暂不支持 Nether 维度、The End 维度和自定义维度。命令不会禁止你在这些不支持的维度中使用，但结果可能不如人意。
- 暂不支持自定义生物群系，自定义生物群系的颜色统一为黑色。

## 模式

### 生物群系模式

**生物群系模式**以生物群系为基础。快照中，每个生物群系都有自己独特的颜色。

示例命令：`/biome-snapshot biome -5000 -5000 5000 5000`

![](doc/Biome.png)

### 大陆海洋生物群系模式

**大陆海洋生物群系模式**以生物群系为基础，将所有生物群系划分为大陆与海洋两类。快照中，大陆使用翠绿色表示，海洋使用深蓝色表示。

示例命令：`/biome-snapshot mainland_ocean_biome -5000 -5000 5000 5000`

![](doc/MainlandOceanBiome.png)

### 大陆河流海洋生物群系模式

**大陆河流海洋生物群系模式**以生物群系为基础，将所有生物群系划分为大陆、河流与海洋三类。快照中，大陆使用翠绿色表示，河流使用蓝色表示，海洋使用深蓝色表示。

示例命令：`/biome-snapshot mainland_river_ocean_biome -5000 -5000 5000 5000`

![](doc/MainlandRiverOceanBiome.png)

### 真实的海岸线模式

> 默认情况下，1.18及以上版本的海岸线不会完美匹配。这不是一个bug，而是因为 地图显示的是底层的生物群落，而不是确切的地形。靠近海岸、海洋和河流的生物群落通常有陆地， 而陆地生物群落可以在水下。
> —— https://www.chunkbase.com/apps/seed-map

上述现象发生在所有以生物群系为基础的模式中，如果你只需要精确的生物群系图，这个现象将不会影响到你，如果你需要真实的反应海岸线，请选择：**真实的海岸线模式**。

**真实的海岸线模式**以噪声（NOISE）生成阶段的区块数据为基础，该阶段已经生成并应用了真实的高度图。因为该阶段的地表方块只有石头和水，所以在快照中，使用这两种方块对应的颜色表示即可真实的反应海岸线的情况。

> 需要注意的是，NOISE 阶段是区块生成的一个早期阶段，如果区块已经生成完毕，甚至已经经过玩家的修改，那么将以最新的顶层方块为准，快照中也将使用最新的顶层方块的颜色表示。

示例命令：`/biome-snapshot real_coastline -5000 -5000 5000 5000`

![](doc/RealCoastline.png)

## 计划的任务

- 支持更多 Minecraft 版本。
- 支持 Nether 和 The End 维度。

## 开发者

- [An_Night](https://gitee.com/AnNight)

## 许可证

本项目使用 [Apache License 2.0](LICENSE.txt)。
