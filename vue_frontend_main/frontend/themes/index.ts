import { hex2rgba } from '@unocss/preset-mini/utils'

export const lightTheme = {
  'color-scheme': 'light',
  // 内置 UI
  '--ui-primary': hex2rgba('#0f0f0f')!.join(' '),
  '--ui-text': hex2rgba('#fcfcfc')!.join(' '),
  // 主体
  '--g-bg': '#f2f2f2',
  '--g-container-bg': '#fff',
  '--g-border-color': '#f2f2f2',
  // 头部
  '--g-header-bg': '#fff',
  '--g-header-color': '#0f0f0f',
  '--g-header-menu-color': '#0f0f0f',
  '--g-header-menu-hover-bg': '#dde1e3',
  '--g-header-menu-hover-color': '#0f0f0f',
  '--g-header-menu-active-bg': '#0f0f0f',
  '--g-header-menu-active-color': '#fff',
  // 主导航
  '--g-main-sidebar-bg': '#f2f2f2',
  '--g-main-sidebar-menu-color': '#0f0f0f',
  '--g-main-sidebar-menu-hover-bg': '#dde1e3',
  '--g-main-sidebar-menu-hover-color': '#0f0f0f',
  '--g-main-sidebar-menu-active-bg': '#0f0f0f',
  '--g-main-sidebar-menu-active-color': '#fff',
  // 次导航
  '--g-sub-sidebar-bg': '#fff',
  '--g-sub-sidebar-logo-bg': '#0f0f0f',
  '--g-sub-sidebar-logo-color': '#fff',
  '--g-sub-sidebar-menu-color': '#0f0f0f',
  '--g-sub-sidebar-menu-hover-bg': '#dde1e3',
  '--g-sub-sidebar-menu-hover-color': '#0f0f0f',
  '--g-sub-sidebar-menu-active-bg': '#0f0f0f',
  '--g-sub-sidebar-menu-active-color': '#fff',
}

export const darkTheme = {
  'color-scheme': 'dark',
  // 内置 UI
  '--ui-primary': hex2rgba('#e5e5e5')!.join(' '),
  '--ui-text': hex2rgba('#0f0f0f')!.join(' '),
  // 主体
  '--g-bg': '#0a0a0a',
  '--g-container-bg': '#141414',
  '--g-border-color': '#15191e',
  // 头部
  '--g-header-bg': '#141414',
  '--g-header-color': '#e5e5e5',
  '--g-header-menu-color': '#a8a29e',
  '--g-header-menu-hover-bg': '#141414',
  '--g-header-menu-hover-color': '#e5e5e5',
  '--g-header-menu-active-bg': '#e5e5e5',
  '--g-header-menu-active-color': '#0a0a0a',
  // 主导航
  '--g-main-sidebar-bg': '#0a0a0a',
  '--g-main-sidebar-menu-color': '#a8a29e',
  '--g-main-sidebar-menu-hover-bg': '#141414',
  '--g-main-sidebar-menu-hover-color': '#e5e5e5',
  '--g-main-sidebar-menu-active-bg': '#e5e5e5',
  '--g-main-sidebar-menu-active-color': '#0a0a0a',
  // 次导航
  '--g-sub-sidebar-bg': '#141414',
  '--g-sub-sidebar-logo-bg': '#0f0f0f',
  '--g-sub-sidebar-logo-color': '#e5e5e5',
  '--g-sub-sidebar-menu-color': '#a8a29e',
  '--g-sub-sidebar-menu-hover-bg': '#0a0a0a',
  '--g-sub-sidebar-menu-hover-color': '#e5e5e5',
  '--g-sub-sidebar-menu-active-bg': '#e5e5e5',
  '--g-sub-sidebar-menu-active-color': '#0a0a0a',
}
// export const darkTheme = {
//   'color-scheme': 'dark',
//   // Inner UI
//   '--ui-primary': hex2rgba('#00ffcc')!.join(' '), // Neon Green
//   '--ui-text': hex2rgba('#ffffff')!.join(' '), // White
//   // Main Body
//   '--g-bg': '#0c0c0c', // Dark Grey
//   '--g-container-bg': '#141414', // Charcoal Grey
//   '--g-border-color': '#333333', // Darker Grey
//   // Header
//   '--g-header-bg': '#141414', // Charcoal Grey
//   '--g-header-color': '#ff66cc', // Pink
//   '--g-header-menu-color': '#66ccff', // Light Blue
//   '--g-header-menu-hover-bg': '#1f1f1f', // Darker Charcoal Grey
//   '--g-header-menu-hover-color': '#ff66cc', // Pink
//   '--g-header-menu-active-bg': '#ff66cc', // Pink
//   '--g-header-menu-active-color': '#141414', // Charcoal Grey
//   // Main Navigation
//   '--g-main-sidebar-bg': '#0c0c0c', // Dark Grey
//   '--g-main-sidebar-menu-color': '#66ccff', // Light Blue
//   '--g-main-sidebar-menu-hover-bg': '#141414', // Charcoal Grey
//   '--g-main-sidebar-menu-hover-color': '#ff66cc', // Pink
//   '--g-main-sidebar-menu-active-bg': '#ff66cc', // Pink
//   '--g-main-sidebar-menu-active-color': '#0c0c0c', // Dark Grey
//   // Sub Navigation
//   '--g-sub-sidebar-bg': '#141414', // Charcoal Grey
//   '--g-sub-sidebar-logo-bg': '#ff66cc', // Pink
//   '--g-sub-sidebar-logo-color': '#ffffff', // White
//   '--g-sub-sidebar-menu-color': '#66ccff', // Light Blue
//   '--g-sub-sidebar-menu-hover-bg': '#0c0c0c', // Dark Grey
//   '--g-sub-sidebar-menu-hover-color': '#ff66cc', // Pink
//   '--g-sub-sidebar-menu-active-bg': '#ff66cc', // Pink
//   '--g-sub-sidebar-menu-active-color': '#0c0c0c', // Dark Grey
// }

// export const darkTheme = {
//   'color-scheme': 'light',
//   // Inner UI
//   '--ui-primary': hex2rgba('#ff8533')!.join(' '), // Burnt Orange
//   '--ui-text': hex2rgba('#e0e0e0')!.join(' '), // Lighter Grey
//   // Main Body
//   '--g-bg': '#3d3d3d', // Dark Taupe
//   '--g-container-bg': '#4f4f4f', // Taupe
//   '--g-border-color': '#666666', // Darker Taupe
//   // Header
//   '--g-header-bg': '#4f4f4f', // Taupe
//   '--g-header-color': '#ff8533', // Burnt Orange
//   '--g-header-menu-color': '#99cc99', // Sage Green
//   '--g-header-menu-hover-bg': '#666666', // Darker Taupe
//   '--g-header-menu-hover-color': '#ff8533', // Burnt Orange
//   '--g-header-menu-active-bg': '#ff8533', // Burnt Orange
//   '--g-header-menu-active-color': '#4f4f4f', // Taupe
//   // Main Navigation
//   '--g-main-sidebar-bg': '#3d3d3d', // Dark Taupe
//   '--g-main-sidebar-menu-color': '#99cc99', // Sage Green
//   '--g-main-sidebar-menu-hover-bg': '#4f4f4f', // Taupe
//   '--g-main-sidebar-menu-hover-color': '#ff8533', // Burnt Orange
//   '--g-main-sidebar-menu-active-bg': '#ff8533', // Burnt Orange
//   '--g-main-sidebar-menu-active-color': '#3d3d3d', // Dark Taupe
//   // Sub Navigation
//   '--g-sub-sidebar-bg': '#4f4f4f', // Taupe
//   '--g-sub-sidebar-logo-bg': '#ff8533', // Burnt Orange
//   '--g-sub-sidebar-logo-color': '#e0e0e0', // Lighter Grey
//   '--g-sub-sidebar-menu-color': '#99cc99', // Sage Green
//   '--g-sub-sidebar-menu-hover-bg': '#3d3d3d', // Dark Taupe
//   '--g-sub-sidebar-menu-hover-color': '#ff8533', // Burnt Orange
//   '--g-sub-sidebar-menu-active-bg': '#ff8533', // Burnt Orange
//   '--g-sub-sidebar-menu-active-color': '#3d3d3d', // Dark Taupe
// }
