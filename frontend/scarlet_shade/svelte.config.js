import adapter from '@sveltejs/adapter-auto';
import path from 'path';

/** @type {import('@sveltejs/kit').Config} */
const config = {
  kit: {
    adapter: adapter(),
    alias: {
      $script: path.resolve('./src/script'),
      $style: path.resolve('./src/style'),
      $assets: path.resolve('./src/assets'),
      $lib: path.resolve('./src/lib')
    }
  }
};

export default config;
