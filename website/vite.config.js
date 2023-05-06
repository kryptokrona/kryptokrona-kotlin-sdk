import { defineConfig } from "vite";
import { sveltepress } from "@sveltepress/vite";
import { defaultTheme } from "@sveltepress/theme-default";

const config = defineConfig({
  plugins: [
    sveltepress({
      theme: defaultTheme({
        navbar: [
          {
            title: "Guide",
            to: "/guide/",
          },
        ],
        sidebar: {
          "/guide/": [
            {
              title: "Introduction",
              to: "/guide/",
            },
            {
              title: "Getting started",
              to: "/guide/getting_started/",
            },
            {
              title: "Contribute",
              to: "/guide/contribute/",
            },
            {
              title: "Our libraries",
              to: "/guide/libraries/",
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
