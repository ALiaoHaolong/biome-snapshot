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

这个模组提供了一些命令，来将指定坐标范围的生物群系或地形状况导出为平面图（需要 4 级权限等级）。

导出的图片保存在 `config/biome-snapshot/存档名称/` 中。

支持 en_us、zh_cn、zh_hk 和 zh_tw 语言。

注意事项：

- **导出图片时，游戏服务器将被暂停，直至生成完毕。**
- 导出图片时，Biome Snapshot 会每隔 5 秒反馈一次进度（%），请耐心等待。
- 大陆海洋生物群系颜色解析器和大陆河流海洋生物群系颜色解析器仅支持《我的世界》的主世界维度。
- 真实的海岸线颜色解析器在遇到已经生成完毕的区块时，将以最新的顶层方块为准。

## 命令/biome-snapshot

将指定坐标范围的生物群系或地形状况导出为平面图（需要 4 级权限等级）。

### 语法

- `/biome-snapshot <colorResolver> <from> <to>`

### 参数

* `<colorResolver>`：[颜色解析器](#颜色解析器)

   指定要使用的颜色解析器。必须为`biome`、`mainland_ocean_biome`、`mainland_river_ocean_biome`或`real_coastline`。若不是则此命令将无法解析。

   示例

   * biome
   * mainland_ocean_biome
   * mainland_river_ocean_biome
   * real_coastline

* `<from>`：[column_pos](https://zh.minecraft.wiki/w/%E5%8F%82%E6%95%B0%E7%B1%BB%E5%9E%8B#minecraft:column_pos)和`<to>`：[column_pos](https://zh.minecraft.wiki/w/%E5%8F%82%E6%95%B0%E7%B1%BB%E5%9E%8B#minecraft:column_pos)

   必须为由<X>和<Z>组成的平面坐标，两个值必须为整数、相对坐标，不接受局部坐标。

   示例

   * 0 0
   * ~ ~
   * ~1 ~-2

## 命令/biome-snapshot-config

配置各个颜色解析器（需要 4 级权限等级）。游戏重启后，所有配置将重置到默认值。

### 语法

`/biome-snapshot-config`有三个子命令（`biome`、`mainland_ocean_biome`和`mainland_river_ocean_biome`）。

- `/biome-snapshot-config ...`

   - `... biome ...`

      配置生物群系颜色解析器。允许配置其他模组或数据包的自定义生物群系的颜色。

      - `... set <namespace> <path> <color>`

         设置（生物群系的）颜色。

      - `... remove <namespace> <path> <color>`

         移除（生物群系的）颜色。

      - `... get <namespace> <path>`

         查询（生物群系的）颜色。

   - `... mainland_ocean_biome ...`

      配置大陆海洋颜色解析器。

      - `... set mainland <color>`

         设置大陆的颜色。

      - `... set ocean <color>`

         设置海洋的颜色。

      - `... get mainland`

         查询大陆的颜色。

      - `... get ocean`

         查询海洋的颜色。

   - `... mainland_river_ocean_biome ...`

      配置大陆河流海洋颜色解析器。

      - `... set mainland <color>`

         设置大陆的颜色。

      - `... set river <color>`

         设置河流的颜色。

      - `... set ocean <color>`

         设置海洋的颜色。

      - `... get mainland`

         查询大陆的颜色。

      - `... get river`

         查询河流的颜色。

      - `... get ocean`

         查询海洋的颜色。

### 参数

- `<namespace>`：[string](https://zh.minecraft.wiki/w/%E5%8F%82%E6%95%B0%E7%B1%BB%E5%9E%8B#brigadier:string)和`<path>`：[string](https://zh.minecraft.wiki/w/%E5%8F%82%E6%95%B0%E7%B1%BB%E5%9E%8B#brigadier:string)

   指定生物群系的命名空间和路径。两个值必须为单个词（不含空格）。

   对于已配置过颜色的生物群系，`<namespace>` 参数和 `<path>` 参数会自动提示它们的命名空间和路径。

   示例

   - minecraft plains
   - biomesoplenty auroral_garden
   - betterend amber_land

- `<color>`：[integer](https://zh.minecraft.wiki/w/%E5%8F%82%E6%95%B0%E7%B1%BB%E5%9E%8B#brigadier:integer)

   指定颜色。必须为 0 到 16777215 之间的整数。

   `<color>` 参数会自动提示 Biome Snapshot 提供的默认颜色值（如果有）。

   示例

   - 0
   - 255
   - 16777215

## 颜色解析器

### 生物群系颜色解析器

ID：`biome`。

以生物群系为基础，每个生物群系都有自己独特的颜色。

示例：`/biome-snapshot biome -5000 -5000 5000 5000`

![](doc/Biome.png)

### 大陆海洋生物群系颜色解析器

ID：`mainland_ocean_biome`。

以生物群系为基础，将所有生物群系划分为大陆与海洋两类，大陆默认使用平原的颜色表示（翠绿色），海洋默认使用海洋的颜色表示（深蓝色）。

示例：`/biome-snapshot mainland_ocean_biome -5000 -5000 5000 5000`

![](doc/MainlandOceanBiome.png)

### 大陆河流海洋生物群系颜色解析器

ID：`mainland_river_ocean_biome`。

以生物群系为基础，将所有生物群系划分为大陆、河流与海洋三类，大陆默认使用平原的颜色表示（翠绿色），河流默认使用河流的颜色表示（蓝色），海洋默认使用海洋的颜色表示（深蓝色）。

示例：`/biome-snapshot mainland_river_ocean_biome -5000 -5000 5000 5000`

![](doc/MainlandRiverOceanBiome.png)

### 真实的海岸线颜色解析器

ID：`real_coastline`。

> “默认情况下，1.18及以上版本的海岸线不会完美匹配。这不是一个bug，而是因为地图显示的是底层的生物群落，而不是确切的地形。靠近海岸、海洋和河流的生物群落通常有陆地，而陆地生物群落可以在水下。”  
> —— [CHUNKBASE](https://www.chunkbase.com/apps/seed-map)

上述现象发生在所有以生物群系为基础的颜色解析器中，如果你只需要精确的生物群系图，这个现象将不会影响到你，如果你需要更真实的反应海岸线，请选择：**真实的海岸线颜色解析器**。

**真实的海岸线颜色解析器**以噪声（NOISE）生成阶段的区块数据为基础，该阶段已经生成并应用了真实的高度图。因为该阶段的地表方块只有石头和水，所以在导出的图片中，使用这两种方块对应的颜色表示即可反应海岸线的情况。

需要注意的是，噪声（NOISE）阶段只是区块生成的一个早期阶段，如果区块已经生成完毕，甚至已经经过玩家的修改，那么将以最新的顶层方块为准，导出的图片中也将使用最新的顶层方块的颜色表示。即，Biome Snapshot 并不会回滚区块到噪声（NOISE）阶段。

另外，后续的地形雕刻（CARVERS）等阶段仍可能少量的改变海岸线，但这些改变不大，并且将区块生成到这些后期阶段所需要的时间和空间相比生成到噪声（NOISE）阶段（早期阶段）都大的多，后续可能会为此拆分成两个独立的颜色解析器以供选择。

示例：`/biome-snapshot real_coastline -5000 -5000 5000 5000`

![](doc/RealCoastline.png)

## 适用于模组和数据包开发者

Biome Snapshot 允许模组和数据包的开发者或爱好者为他们的自定义的生物群系配置默认颜色。

请前往 [Issue](https://gitee.com/AnNight/biome-snapshot/issues) 页面提交一个请求。

请提供模组或数据包的主页链接、您的身份（开发者、管理员、贡献者等等），以及一个包含生物群系信息（命名空间、路径和默认颜色）的列表。

我将在 issue 中回复你的请求。

## 作者

廖浩龙

- aliaohaolong@qq.com
- aliaohaolong@gmail.com

## 许可证

本项目使用 [Apache License 2.0](LICENSE.txt)。
