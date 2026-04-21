---
title: Supported Versions
order: 1
---

# Supported Versions

## Branch Status

Biome Snapshot is maintained in multiple branches to ensure compatibility with different Minecraft versions.

| Branch                                                                                                                                 | Status | Minecraft Versions |
|----------------------------------------------------------------------------------------------------------------------------------------|--------|--------------------|
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v1" target="_blank" rel="noreferrer" class="vp-external-link-icon">v1</a> | Stable | 1.18.2             |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v2" target="_blank" rel="noreferrer" class="vp-external-link-icon">v2</a> | Stable | 1.21 ~ 1.21.4      |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v3" target="_blank" rel="noreferrer" class="vp-external-link-icon">v3</a> | Stable | 1.18 ~ 1.18.1      |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v4" target="_blank" rel="noreferrer" class="vp-external-link-icon">v4</a> | Stable | 1.19 ~ 1.19.2      |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v5" target="_blank" rel="noreferrer" class="vp-external-link-icon">v5</a> | Stable | 1.19.3 ~ 1.19.4    |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v6" target="_blank" rel="noreferrer" class="vp-external-link-icon">v6</a> | Stable | 1.20 ~ 1.20.6      |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v7" target="_blank" rel="noreferrer" class="vp-external-link-icon">v7</a> | Stable | 1.21.5 ~ 1.21.10   |
| <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/v8" target="_blank" rel="noreferrer" class="vp-external-link-icon">v8</a> | Stable | 1.21.11            |

::: tip Tips: Regarding the master branch
The <a href="https://github.com/ALiaoHaolong/biome-snapshot/tree/master" target="_blank" rel="noreferrer" class="vp-external-link-icon">`master`</a> branch is used for documentation and publication only.
:::

## Branch Differences

There are some key differences in the internal implementation of each branch, mainly due to adaptations to the vanilla mechanics.

### v3 (1.18 ~ 1.18.1)

The base branch.

### v1 (1.18.2)

Compared to v3, the way of obtaining biome classification has been modified.

::: info
The interface used in the v3 branch to obtain biome classification is marked as @Deprecated, and this interface was officially removed in 1.19. Affected by this, we use the new tag system to obtain biome classification.
:::

### v4 (1.19 ~ 1.19.2)

Compared to v1, the way of obtaining chunks has been modified.

::: info
The way of obtaining chunks used in the v1 branch cannot be used properly in 1.19. Affected by this, we adjusted the parameters when obtaining chunks.
:::

### v5 (1.19.3 ~ 1.19.4)

Compared to v4, patches have been added for specific commands to avoid OutOfMemory crash issues.

::: info
In version 1.19.3, a certain stage of block generation will issue a delay processing task (this task contains a reference to the ProtoChunk), resulting in all Protochunks being unable to be garbage-collected. Thus, when drawing large graphs, there is a potential risk of out-of-memory crashes.
:::

### v6 (1.20 ~ 1.20.6)

Compared to v5, the way of creating text components has been updated.

::: info
In version 1.20, the way of creating text components has been modified. We have updated the implementation to adapt to this change.
:::

### v2 (1.21 ~ 1.21.4)

Compared to v6, the way of creating identifiers has been updated.

::: info
In version 1.21, the way of creating identifiers has been modified. We have updated the implementation to adapt to this change.
:::

### v7 (1.21.5 ~ 1.21.10)

Compared to v2, the parameters of the method for removing tickets have been modified.

::: info
In version 1.21.5, the method for removing tickets has been modified. We have updated the method for removing tickets to adapt to this change.
:::

### v8 (1.21.11)

Compared to v7, the way of setting command permissions has been updated.
