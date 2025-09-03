# BiomeSnapshot

![](icon128.png)

Modrinth：  
[Biome Snapshot - Minecraft Mod](https://modrinth.com/mod/biome-snapshot)

Mod 百科:   
[Biome Snapshot - MC百科|最大的Minecraft中文MOD百科](https://www.mcmod.cn/class/20861.html)

Source:  
[Gitee.com - 暗夜/Biome Snapshot](https://gitee.com/AnNight/biome-snapshot)

Issues:   
[Issues · 暗夜/Biome Snapshot - Gitee.com](https://gitee.com/AnNight/biome-snapshot/issues)

Wiki:  
[Wiki - Gitee.com](https://gitee.com/AnNight/biome-snapshot/wikis)

## Introduction

This mod provides a command to export the biome or terrain status of a specified coordinate range as a flat map (requires level 4 admin privileges).

The exported image is saved in `config/biome-snapshot/<levelName>/`.

Multi-language support (English and Chinese).

Matters needing attention:

- **When exporting images, the game server will be paused until the generation is complete.**
- When exporting images, Biome Snapshot will feedback the progress (%) every 5 seconds, please be patient.
- None of the color resolvers support the Nether dimension, The End dimension, and custom dimensions (for now). Biome Snapshot will not prevent you from exporting images in these dimensions, but the results may not be satisfactory.
- The Biome color resolver does not support custom biomes for now. The Biome color resolver will set the color of custom biomes to black.
- The Real Coastline color resolver will use the latest top block as the standard when encountering chunks that have been fully generated.

## /biome_snapshot

### Grammar

`/biome_snapshot <colorResolver> <from> <to>`

### Parameters

- `<colorResolver>`: [Color Resolvers](#color-resolvers)

   Specifies the color resolver to use. It must be `BIOME`, `MAINLAND_OCEAN_BIOME`, `MAINLAND_RIVER_OCEAN_BIOME`, or `REAL_COASTLINE`. If not, this command will not be parsed.

   Examples

   - BIOME
   - MAINLAND_OCEAN_BIOME
   - MAINLAND_RIVER_OCEAN_BIOME
   - REAL_COASTLINE

- `<from>`: [column_pos](https://minecraft.wiki/w/Argument_types#minecraft:column_pos) and`<to>`: [column_pos](https://minecraft.wiki/w/Argument_types#minecraft:column_pos)

   Must be a column coordinates composed of <X> and <Z>, each of which must be an integer or tilde notation.

   Examples

   - 0 0
   - ~ ~
   - ~1 ~-2

## Color Resolvers

### Biome Color Resolver

ID: `BIOME`.

Based on biomes, each biome has its own unique color.

Example: `/biome-snapshot BIOME -5000 -5000 5000 5000`

![](doc/Biome.png)

### Mainland Ocean Biome Color Resolver

ID: `MAINLAND_OCEAN_BIOME`.

Based on biomes, all biomes are divided into two categories: mainland and ocean. Mainland is represented by the color of plains (green), and ocean is represented by the color of ocean (dark blue).

Example: `/biome-snapshot MAINLAND_OCEAN_BIOME -5000 -5000 5000 5000`

![](doc/MainlandOceanBiome.png)

### Mainland River Ocean Biome Color Resolver

ID: `MAINLAND_RIVER_OCEAN_BIOME`.

Based on biomes, all biomes are divided into three categories: mainland, river, and ocean. Mainland is represented by the color of plains (green), river is represented by the color of river (blue), and ocean is represented by the color of ocean (dark blue).

Example: `/biome-snapshot MAINLAND_RIVER_OCEAN_BIOME -5000 -5000 5000 5000`

![](doc/MainlandRiverOceanBiome.png)

### Real Coastline Color Resolver

ID: `REAL_COASTLINE`.

> "By default, the coastlines for versions 1.18 and above don’t match up perfectly. This is not a bug, but rather because the map shows the underlying biomes instead of the exact terrain. Near coasts, ocean and river biomes often have land, while land biomes can be underwater."  
> By [CHUNKBASE](https://www.chunkbase.com/apps/seed-map)

The above phenomenon occurs in all biome-based color resolvers. If you only need an accurate biome map, this phenomenon will not affect you. If you need a more realistic reflection of the coastline, please choose: **Real Coastline Color Resolver**.

The **Real Coastline Color Resolver** is based on the chunk data of the NOISE generation stage, which has generated and applied the real height map. Since the surface blocks at this stage are only stone and water, the colors corresponding to these two blocks can be used to reflect the situation of the coastline in the exported image.

It should be noted that the NOISE stage is only an early stage of chunk generation. If the chunk has been fully generated, or even modified by players, the latest top block will be used as the standard, and the exported image will also use the color of the latest top block. In other words, Biome Snapshot will not roll back the chunk to the NOISE stage.

In addition, subsequent stages such as terrain carving (CARVERS) may still slightly change the coastline, but these changes are not significant. Moreover, the time and space required to generate chunks to these later stages are much greater than those required to generate to the NOISE stage (early stage). In the future, this may be split into two independent color resolvers for selection.

Example: `/biome-snapshot REAL_COASTLINE -5000 -5000 5000 5000`

![](doc/RealCoastline.png)

## Planned Tasks

- Support more Minecraft versions.

## Author

廖浩龙

- aliaohaolong@qq.com
- aliaohaolong@gmail.com

## License

This project uses [Apache License 2.0](LICENSE.txt).
