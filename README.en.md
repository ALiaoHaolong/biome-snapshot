# BiomeSnapshot

![](icon128.png)

A Minecraft mod that offers biome snapshot functionality based on different modes and supports generating screenshots of biome maps through commands.

## Features

- Supports multiple biome color resolution modes, including default mode, continental ocean mode, and continental river-ocean mode.
- It provides a command interface to facilitate players or server administrators in quickly generating screenshots of biome maps.
- Multi-language support, including English and Chinese.

## Example

### Default mode

`/biome-snapshot default -5000 -5000 5000 5000`

![](doc/Default.png)

### Mainland Ocean model

`/biome-snapshot mainland_ocean -5000 -5000 5000 5000`

![](doc/MainlandOcean.png)

### Mainland River Ocean model

`/biome-snapshot mainland_river_ocean -5000 -5000 5000 5000`

![](doc/MainlandRiverOcean.png)

## Usage Method

1. Install the Minecraft Fabric loader.
2. Place this module in the 'mods' folder.
3. Start the game or server.
4. In the game, enter the command '/biome_snapshot < mode > <x1> <z1> <x2> <z2>' to generate a screenshot of the biome. The supported modes include:
- 'default' - Use default color resolution. Each biome has a unique color.
- 'mainland_ocean' - Only distinguishes between continents and oceans, with binary color resolution.
- 'mainland_river_ocean' - Only distinguishes between continents, rivers and oceans, with ternary color analysis.

## The functions of the plan

- Supports reading custom color resolution modes configured in JSON.
- Supports Nether and The End dimensions.

## Developer

- [An_Night](https://gitee.com/AnNight)

## License

This project uses [Apache LICENSE 2.0](LICENSE.txt).