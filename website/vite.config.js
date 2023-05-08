import {defineConfig} from "vite";
import {sveltepress} from "@sveltepress/vite";
import {defaultTheme} from "@sveltepress/theme-default";

const config = defineConfig({
    plugins: [
        sveltepress({
            theme: defaultTheme({
                navbar: [
                    {
                        title: "Docs",
                        to: "/docs/",
                    },
                ],
                sidebar: {
                    "/docs/": [
                        {
                            title: "Introduction",
                            to: "/docs/",
                            items: [
                                {
                                    title: "Getting Started",
                                    to: "/docs/getting-started/",
                                },
                                {
                                    title: "Libraries",
                                    to: "/docs/libraries/",
                                },
                            ],
                        },
                        {
                            title: "Usage",
                            to: "/docs/usage/",
                            items: [
                                {
                                    title: "How To Use",
                                    to: "/docs/usage/how-to-use/",
                                },
                                {
                                    title: "Usage Ideas",
                                    to: "/docs/usage/usage-ideas/",
                                },
                            ],
                        },
                        {
                            title: "Contributing",
                            to: "/docs/contributing/",
                            items: [
                                {
                                    title: "Branching and Merging",
                                    to: "/docs/contributing/branching-and-merging/",
                                },
                                {
                                    title: "Changelog Format",
                                    to: "/docs/contributing/changelog-format/",
                                },
                                {
                                    title: "Managing Pull Requests",
                                    to: "/docs/contributing/managing-pull-requests/",
                                },
                                {
                                    title: "Release Checklist",
                                    to: "/docs/contributing/release-checklist/",
                                },
                                {
                                    title: "Log Levels",
                                    to: "/docs/contributing/log-levels/",
                                }
                            ],
                        },
                        {
                            title: "Contribute",
                            to: "/docs/contribute/",
                            items: [
                                {
                                    title: "Pull Request",
                                    to: "/docs/contribute/pull-request/",
                                },
                                {
                                    title: "Create Issues",
                                    to: "/docs/contribute/issues/",
                                },
                                {
                                    title: "Donate",
                                    to: "/docs/contribute/donate/",
                                }
                            ],
                        },
                        {
                            title: "Other",
                            items: [
                                {
                                    title: "About",
                                    to: "/docs/other/about/",
                                },
                                {
                                    title: "Additional Reading",
                                    to: "/docs/other/additional-reading/",
                                }
                            ],
                        }

                    ],
                },
                themeColor: {
                    light: "#f5f5f5",
                    dark: "#171717",
                    primary: "#7c3aed",
                    hover: "#db2777",
                    gradient: {
                        start: "#8b5cf6",
                        end: "#ec4899",
                    },
                },
                github: "https://github.com/kryptokrona/kryptokrona-kotlin-sdk",
                discord: 'https://discord.gg/VTgsTGS9b7',
                logo: "/kotlin_logo.png",
            }),
            siteConfig: {
                title: "Kryptokrona Kotlin SDK",
                description:
                    "The most fully featured implementation of the Kryptokrona Network protocols",
            },
        }),
    ],
});

export default config;
