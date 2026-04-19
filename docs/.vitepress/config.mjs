import { defineConfig } from 'vitepress'
import { withSidebar } from 'vitepress-sidebar'
import { withI18n } from 'vitepress-i18n'

// VitePress 基础配置 https://vitepress.dev/zh/
const vitePressOptions = {
  // Site Metadata
  title: 'Biome Snapshot', // 网站标题
  titleTemplate: true, // 将网站标题作为页面标题后缀（页面标题与页面标题后缀一致时，不显示后缀）
  description: 'This mod provides some commands to export the biome or terrain status of a specified coordinate range as a flat map (requires permission level of 4).',
  head: [
      [ 'link', { rel: 'icon', href: '/biome-snapshot/icon.png' } ],
  ],
  lang: 'en-US',
  base: '/biome-snapshot/',
  // Routing
  cleanUrls: false,
  rewrites: {
    'en/:rest*': ':rest*'
  },
  // Build
  // Theming
  themeConfig: {
    search: {
      provider: 'local',
    },
    logo: '/icon.png',
    socialLinks: [
      { icon: 'modrinth', link: 'https://modrinth.com/mod/biome-snapshot' },
      // { icon: 'curseforge', link: 'https://www.curseforge.com/minecraft/mc-mods/biome-snapshot' },
      // { icon: {}, link: 'https://www.mcmod.cn/class/20861.html' },
      { icon: 'github', link: 'https://github.com/aliaohaolong/biome-snapshot' },
    ],
  },
  appearance: true,
  lastUpdated: true,                  // 显示最后更新时间（基于 /git log 的时间）
  // Customization
  // Build Hooks
}

// 国际化配置 https://vitepress-i18n.cdget.com/
const vitePressI18nOptions = {
  locales: [
    { path: 'en', locale: 'en' },     // 英文，路径隐藏
    { path: 'zh', locale: 'zhHans' }  // 简体中文
  ],
  rootLocale: 'en',                   // 默认语言为英文
  searchProvider: 'local',
  searchOptions: undefined,           // Default
  disableAutoSetLangValue: false,     // Default
  debugPrint: false,                  // Default
  label: undefined,                   // Default
  link: undefined,                    // Default
  lang: undefined,                    // Default
  title: undefined,                   // Default
  titleTemplate: undefined,           // Default
  description: {
    en: 'This mod provides some commands to export the biome or terrain status of a specified coordinate range as a flat map (requires permission level of 4).',
    zh: '这个模组提供了一些命令，来将指定坐标范围的生物群系或地形状况导出为平面图（需要 4 级权限等级）。',
  },
  head: undefined,                    // Default
  themeConfig: {
    en: {
      nav: [
        { text: 'Introduction', link: '/guide/introduction' },
        {
          text: 'Downloads',
          items: [
            { text: 'Modrinth', link: 'https://modrinth.com/mod/biome-snapshot/versions'},
            // { text: 'CurseForge', link: 'https://www.curseforge.com/minecraft/mc-mods/biome-snapshot/files/all' },
          ]
        },
        { text: 'Changelogs', link: '/changelogs/1.0.0', activeMatch: '/changelogs/' },
      ],
      footer: {
        message: 'Released under the Apache License, Version 2.0.',
        copyright: 'Copyright © 2026-present Haolong Liao',
      },
      editLink: {
        pattern: 'https://github.com/ALiaoHaolong/biome-snapshot/tree/master/docs/:path',
      },
    },
    zh: {
      nav: [
        { text: '介绍', link: '/zh/guide/introduction' },
        {
          text: '下载',
          items: [
            { text: 'Modrinth', link: 'https://modrinth.com/mod/biome-snapshot/versions'},
            // { text: 'CurseForge', link: 'https://www.curseforge.com/minecraft/mc-mods/biome-snapshot/files/all' },
          ]
        },
        { text: '更新日志', link: '/zh/changelogs/1.0.0', activeMatch: '/changelogs/' },
      ],
      footer: {
        message: '基于 Apache License, Version 2.0 协议发布。',
        copyright: '版权所有 © 2026-至今 廖浩龙',
      },
      editLink: {
        pattern: 'https://github.com/ALiaoHaolong/biome-snapshot/tree/master/docs/:path',
      },
    },
  },
}

// 自动生成侧边栏基础配置 https://vitepress-sidebar.cdget.com/zhHans/
const vitePressSidebarCommonOptions = {
  // ============ [ RESOLVING PATHS ] ============
  // documentRootPath: '/',
  // scanStartPath: null,
  // resolvePath: null,
  // basePath: null,
  // followSymlinks: false,
  //
  // ============ [ GROUPING ] ============
  // collapsed: true,
  // collapseDepth: 2,
  // rootGroupText: 'Contents',
  // rootGroupLink: 'https://github.com/jooy2',
  // rootGroupCollapsed: false,
  //
  // ============ [ GETTING MENU TITLE ] ============
  useTitleFromFileHeading: true,
  useTitleFromFrontmatter: true,
  // useFolderLinkFromIndexFile: false,
  useFolderTitleFromIndexFile: true,
  frontmatterTitleFieldName: 'sidebarTitle', // sidebarTitle 不存在时使用 title 作为后备
  //
  // ============ [ GETTING MENU LINK ] ============
  // useFolderLinkFromSameNameSubFile: false,
  // useFolderLinkFromIndexFile: false,
  // folderLinkNotIncludesFileName: false,
  //
  // ============ [ INCLUDE / EXCLUDE ] ============
  // excludeByGlobPattern: ['README.md', 'folder/'],
  // excludeFilesByFrontmatterFieldName: 'exclude',
  // excludeByFolderDepth: undefined,
  // includeDotFiles: false,
  // includeEmptyFolder: false,
  // includeRootIndexFile: false,
  // includeFolderIndexFile: false,
  //
  // ============ [ STYLING MENU TITLE ] ============
  // hyphenToSpace: true,
  // underscoreToSpace: true,
  // capitalizeFirst: false,
  // capitalizeEachWords: false,
  // keepMarkdownSyntaxFromTitle: false,
  // removePrefixAfterOrdering: false,
  // prefixSeparator: '.',
  //
  // ============ [ SORTING ] ============
  // manualSortFileNameByPriority: ['first.md', 'second', 'third.md'],
  // sortFolderTo: null,
  // sortMenusByName: false,
  // sortMenusByFileDatePrefix: false,
  sortMenusByFrontmatterOrder: true,
  // frontmatterOrderDefaultValue: 0,
  // sortMenusByFrontmatterDate: false,
  // sortMenusOrderByDescending: false,
  // sortMenusOrderNumericallyFromTitle: false,
  // sortMenusOrderNumericallyFromLink: false,
  //
  // ============ [ MISC ] ============
  // debugPrint: false,
}

// 多侧边栏配置
const vitePressSidebarOptions = [
  {
    ...vitePressSidebarCommonOptions,
    documentRootPath: `/docs/en`,
    resolvePath: '/',
  },
  {
    ...vitePressSidebarCommonOptions,
    basePath: '/zh/',
    documentRootPath: `/docs/zh`,
    resolvePath: '/zh/',
  },
]

export default defineConfig(
    withSidebar(withI18n(vitePressOptions, vitePressI18nOptions), vitePressSidebarOptions)
)
