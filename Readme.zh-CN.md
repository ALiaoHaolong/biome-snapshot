# BiomeSnapshot

![](icon128.png)

Modrinth：  
[Biome Snapshot - Minecraft Mod](https://modrinth.com/mod/biome-snapshot)

Mod 百科：  
[Biome Snapshot - MC百科|最大的Minecraft中文MOD百科](https://www.mcmod.cn/class/20861.html)

源码：  
[Gitee.com - 暗夜/Biome Snapshot](https://gitee.com/AnNight/biome-snapshot)

报告问题：  
[Issues · 暗夜/Biome Snapshot - Gitee.com](https://gitee.com/AnNight/biome-snapshot/issues)

Wiki：  
[Wiki - Gitee.com](https://gitee.com/AnNight/biome-snapshot/wikis)

## 介绍

这个模组提供了一个命令，来将指定坐标范围的生物群系或地形状况导出为平面图（需要 4 级管理员权限）。

导出的图片保存在 `config/biome-snapshot/存档名称/` 中。

多语言支持（英文和中文）。

注意事项：

- **导出图片时，游戏服务器将被暂停，直至生成完毕。**
- 导出图片时，Biome Snapshot 会每隔 5 秒反馈一次进度（%），请耐心等待。
- 所有的颜色解析器都不支持 Nether 维度、The End 维度和自定义维度（暂时），Biome Snapshot 不会禁止你在这些维度中导出图片，但结果可能不如人意。
- Biome 颜色解析器暂不支持自定义生物群系，Biome 颜色解析器会将自定义生物群系的颜色设置为黑色。
- Real Coastline 颜色解析器在遇到已经生成完毕的区块时，将以最新的顶层方块为准。

## 命令/biome_snapshot

### 语法

`/biome_snapshot <colorResolver> <from> <to>`

### 参数

* `<colorResolver>`：[颜色解析器](#颜色解析器)

   指定要使用的颜色解析器。必须为`BIOME`、`MAINLAND_OCEAN_BIOME`、`MAINLAND_RIVER_OCEAN_BIOME`或`REAL_COASTLINE`。若不是则此命令将无法解析。

   示例

   * BIOME
   * MAINLAND_OCEAN_BIOME
   * MAINLAND_RIVER_OCEAN_BIOME
   * REAL_COASTLINE

* `<from>`：[column_pos](https://zh.minecraft.wiki/w/%E5%8F%82%E6%95%B0%E7%B1%BB%E5%9E%8B#minecraft:column_pos)和`<to>`：[column_pos](https://zh.minecraft.wiki/w/%E5%8F%82%E6%95%B0%E7%B1%BB%E5%9E%8B#minecraft:column_pos)

   必须为由<X>和<Z>组成的平面坐标，两个值必须为整数、相对坐标，不接受局部坐标。

   示例

   * 0 0
   * ~ ~
   * ~1 ~-2

## 颜色解析器

### 生物群系颜色解析器

ID：`BIOME`。

以生物群系为基础，每个生物群系都有自己独特的颜色。

示例：`/biome-snapshot BIOME -5000 -5000 5000 5000`

![](doc/Biome.png)

### 大陆海洋生物群系颜色解析器

ID：`MAINLAND_OCEAN_BIOME`。

以生物群系为基础，将所有生物群系划分为大陆与海洋两类，大陆使用平原的颜色表示（翠绿色），海洋使用海洋的颜色表示（深蓝色）。

示例：`/biome-snapshot MAINLAND_OCEAN_BIOME -5000 -5000 5000 5000`

![](doc/MainlandOceanBiome.png)

### 大陆河流海洋生物群系颜色解析器

ID：`MAINLAND_RIVER_OCEAN_BIOME`。

以生物群系为基础，将所有生物群系划分为大陆、河流与海洋三类，大陆使用平原的颜色表示（翠绿色），河流使用河流的颜色表示（蓝色），海洋使用海洋的颜色表示（深蓝色）。

示例：`/biome-snapshot MAINLAND_RIVER_OCEAN_BIOME -5000 -5000 5000 5000`

![](doc/MainlandRiverOceanBiome.png)

### 真实的海岸线颜色解析器

ID：`REAL_COASTLINE`。

> “默认情况下，1.18及以上版本的海岸线不会完美匹配。这不是一个bug，而是因为地图显示的是底层的生物群落，而不是确切的地形。靠近海岸、海洋和河流的生物群落通常有陆地，而陆地生物群落可以在水下。”  
> —— [CHUNKBASE](https://www.chunkbase.com/apps/seed-map)

上述现象发生在所有以生物群系为基础的颜色解析器中，如果你只需要精确的生物群系图，这个现象将不会影响到你，如果你需要更真实的反应海岸线，请选择：**真实的海岸线颜色解析器**。

**真实的海岸线颜色解析器**以噪声（NOISE）生成阶段的区块数据为基础，该阶段已经生成并应用了真实的高度图。因为该阶段的地表方块只有石头和水，所以在导出的图片中，使用这两种方块对应的颜色表示即可反应海岸线的情况。

需要注意的是，噪声（NOISE）阶段只是区块生成的一个早期阶段，如果区块已经生成完毕，甚至已经经过玩家的修改，那么将以最新的顶层方块为准，导出的图片中也将使用最新的顶层方块的颜色表示。即，Biome Snapshot 并不会回滚区块到噪声（NOISE）阶段。

另外，后续的地形雕刻（CARVERS）等阶段仍可能少量的改变海岸线，但这些改变不大，并且将区块生成到这些后期阶段所需要的时间和空间相比生成到噪声（NOISE）阶段（早期阶段）都大的多，后续可能会为此拆分成两个独立的颜色解析器以供选择。

示例：`/biome-snapshot REAL_COASTLINE -5000 -5000 5000 5000`

![](doc/RealCoastline.png)

## 计划的任务

- 支持更多 Minecraft 版本。

## 作者

廖浩龙

- aliaohaolong@qq.com
- aliaohaolong@gmail.com

## 许可证

本项目使用 [Apache License 2.0](LICENSE.txt)。
