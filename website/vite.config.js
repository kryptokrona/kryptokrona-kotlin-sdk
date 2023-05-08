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
            to: "/docs/",
          },
        ],
        sidebar: {
          "/docs/": [
            {
              title: "Introduction",
              to: "/docs/",
            },
            {
              title: "Getting Started",
              to: "/docs/getting-started/",
            },
            {
                title: "How To Use",
                to: "/docs/how-to-use/",
            },
            {
                title: "Usage Ideas",
                to: "/docs/usage-ideas/",
            },
            {
              title: "Contribute",
              to: "/docs/contribute/",
            },
            {
              title: "Our Libraries",
              to: "/docs/libraries/",
            },
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
