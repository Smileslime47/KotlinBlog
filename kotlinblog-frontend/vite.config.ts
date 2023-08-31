// @ts-ignore
import path from 'path'
import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

import Unocss from 'unocss/vite'
import {presetAttributify, presetIcons, presetUno, transformerDirectives, transformerVariantGroup,} from 'unocss'

const pathSrc = path.resolve(__dirname, 'src')

// https://vitejs.dev/config/
export default defineConfig({
    resolve: {
        alias: {
            '~/': `${pathSrc}/`,
        },
    },
    css: {
        preprocessorOptions: {
            scss: {
                additionalData: `@use "~/styles/element/index.scss" as *;`,
            },
        },
    },
    plugins: [
        vue(),
        //unplugin-auto-import
        AutoImport({
            //导入vue相关的函数
            imports: [
                'vue',
                'vue-router',
            ],
            resolvers: [
                // 自动导入图标组件
                // 图标前缀为i
                IconsResolver(),
                // 自动导入 Element Plus 相关函数
                ElementPlusResolver(),
            ],
            dts: 'src/auto-imports.d.ts',
        }),
        //unplugin-vue-components
        Components({
            // 自动注册vue组件
            include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
            resolvers: [
                // 自动注册图标组件
                // 图标集合中缀为ep
                IconsResolver({enabledCollections: ['ep'],}),
                // 自动导入 Element Plus 组件
                ElementPlusResolver({importStyle: 'sass',}),
            ],
            dts: 'src/components.d.ts',
        }),
        //自动导入图标
        Icons(),
        // https://github.com/antfu/unocss
        // see unocss.config.ts for config
        Unocss({
            presets: [
                presetUno(),
                presetAttributify(),
                presetIcons({
                    scale: 1.2,
                    warn: true,
                }),
            ],
            transformers: [
                transformerDirectives(),
                transformerVariantGroup(),
            ]
        }),
    ],
})
