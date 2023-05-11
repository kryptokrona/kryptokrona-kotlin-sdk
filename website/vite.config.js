import { defineConfig } from "vite";
import { sveltepress } from "@sveltepress/vite";
import { defaultTheme } from "@sveltepress/theme-default";

const config = defineConfig({
    plugins: [
        sveltepress({
            theme: defaultTheme({
                navbar: [
                    {
                        title: "Docs",
                        to: "/docs/getting-started/",
                    },
                    {
                        icon: '<svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="30" height="24" viewBox="0 0 30 24" preserveAspectRatio="xMidYMid meet">\n' +
                            '\n' +
                            '<g transform="translate(0.00000,27.000000) scale(0.06900,-0.069000)" stroke="none">\n' +
                            '<path fill="currentColor" d="M163 343 c-18 -9 -33 -20 -33 -25 0 -9 130 -11 154 -2 25 9 19 21\n' +
                            '-15 33 -42 15 -67 13 -106 -6z"></path>\n' +
                            '<path fill="currentColor" d="M330 276 c-17 -36 -44 -41 -48 -10 -3 19 -11 22 -85 27 -74 5 -84 3\n' +
                            '-100 -15 -34 -40 -10 -113 55 -161 29 -21 111 -34 154 -23 24 6 25 8 10 25\n' +
                            '-21 24 -20 59 3 98 16 26 29 33 66 39 42 6 44 7 26 20 -10 8 -30 18 -43 23\n' +
                            '-20 7 -25 4 -38 -23z"></path>\n' +
                            '</g>\n' +
                            '</svg>',
                        to: "https://hugin.chat",
                    },
                    {
                        icon: '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z"/></svg>',
                        to: "https://twitter.com/mjovanc",
                    }
                ],
                sidebar: {
                    "/docs/": [
                        {
                            title: "Introduction",
                            to: "/docs/getting-started/",
                            items: [
                                {
                                    title: "Getting Started",
                                    to: "/docs/getting-started/",
                                },
                                {
                                    title: "Libraries",
                                    to: "/docs/libraries/",
                                },
                                {
                                    title: "Glossary",
                                    to: "/docs/glossary/",
                                }
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
                                },
                                {
                                    title: "Unit Tests",
                                    to: "/docs/contributing/unit-tests/",
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
