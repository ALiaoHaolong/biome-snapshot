# BiomeSnapshot

![](icon128.png)

一个 Minecraft 模组，提供基于不同模式的生物群系快照功能，支持通过命令生成生物群系地图截图。

## 特性

- 支持多种生物群系颜色解析模式，包括默认模式、大陆海洋模式、以及大陆河流海洋模式。
- 提供命令接口，便于玩家或服务器管理员快速生成生物群系地图截图。
- 多语言支持，包括英文和中文。

## 示例

### 默认模式

`/biome-snapshot default -5000 -5000 5000 5000`

![](doc/Default.png)

### 大陆海洋模式

`/biome-snapshot mainland_ocean -5000 -5000 5000 5000`

![](doc/MainlandOcean.png)

### 大陆河流海洋模式

`/biome-snapshot mainland_river_ocean -5000 -5000 5000 5000`

![](doc/MainlandRiverOcean.png)

## 使用方法

1. 安装 Minecraft Fabric 加载器。
2. 将本模组放入 `mods` 文件夹。
3. 启动游戏或服务器。
4. 在游戏中输入 `/biome_snapshot <模式> <x1> <z1> <x2> <z2>` 命令生成生物群系截图。支持的模式包括：
   - `default` - 使用默认颜色解析，每个生物群系都有独特的颜色。
   - `mainland_ocean` - 仅区分大陆与海洋，二元颜色解析。
   - `mainland_river_ocean` - 仅区分大陆、河流与海洋，三元颜色解析。

## 计划的功能

- 支持地形模式
   > 默认情况下，1.18及以上版本的海岸线不会完美匹配。这不是一个bug，而是因为 地图显示的是底层的生物群落，而不是确切的地形。靠近海岸、海洋和河流的生物群落通常有陆地， 而陆地生物群落可以在水下。  
   > 您可以启用地图下方的“地形”选项来自动调整这种不匹配的颜色。  
   > —— https://www.chunkbase.com/apps/seed-map
- 支持读取 JSON 配置的自定义颜色解析模式。
- 支持 Nether 和 The End 维度。

## 开发者

- [An_Night](https://gitee.com/AnNight)

## 许可证

本项目使用 [Apache License 2.0](LICENSE.txt)。