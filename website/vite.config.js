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
              items: [
                {
                  title: "Something 1",
                  to: "/guide/",
                },
                {
                  title: "Something 2",
                  to: "/guide/something2/",
                },
              ],
            },
            {
              title: "Features",
              to: "/guide/features/",
              items: [
                {
                  title: "Feature 1",
                  to: "/guide/features/",
                },
                {
                  title: "Feature 2",
                  to: "/guide/features/feature2",
                },
              ],
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
        description: "Description",
      },
    }),
  ],
});

export default config;
