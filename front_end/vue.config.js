module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        'assets': '@/assets',
        'common': '@/common',
        'components': '@/components',
        'network': '@/api',
        'views': '@/views',

      }
    }
  },
  devServer: {
    port: 8080,
    host: 'localhost',
    open: true,
    https: false,
    proxy: {
      "^/api": {
        target: "http://localhost:8081",
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    },
    disableHostCheck: true
  }

}
