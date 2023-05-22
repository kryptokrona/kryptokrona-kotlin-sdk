import adapter from "@sveltejs/adapter-auto";
import {vitePreprocess} from "@sveltejs/kit/vite";

/**
 * @type {import('@sveltejs/kit').Config}
 */
const config = {
  extensions: [".svelte", ".md"],
  preprocess: [vitePreprocess()],
  kit: {
    adapter: adapter({
      pages: "dist",
    }),
  },
};

export default config;
