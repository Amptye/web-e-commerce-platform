const path = require('path');
const webpack = require('webpack');
const copyPlugin = require('copy-webpack-plugin');
const { parseHTML } = require('jquery');

module.exports = {
  entry: {
    'index':'./src/js/index.js',
    'login':'./src/js/login.js',
    'register':'/src/js/register.js',
    'message':'./src/js/message.js',
  },
  output: {
    globalObject: 'this',
    filename: 'js/[name].js',
    publicPath: './',
    path: path.resolve(__dirname, 'dist')
  },
  plugins: [
    new webpack.ProvidePlugin({
      $: 'jQuery',
      jQuery: 'jquery',
      process: 'process/browser',
    }),
    new copyPlugin({
      patterns: [
        {
          from: path.resolve(__dirname, 'src', 'index.html'),
          to: path.resolve(__dirname, 'dist')
        },
        {
          from: path.resolve(__dirname, 'src', 'login.html'),
          to: path.resolve(__dirname, 'dist')
        },
        {
          from: path.resolve(__dirname, 'src', 'register.html'),
          to: path.resolve(__dirname, 'dist')
        },
        {
          from: path.resolve(__dirname, 'src', 'message.html'),
          to: path.resolve(__dirname, 'dist')
        },
        {
          from: path.resolve(__dirname, 'public'),
          to: path.resolve(__dirname, 'dist', 'public')
        }
      ],
    }),
  ],
  resolve: {
    fallback: {
      "http": require.resolve("stream-http"),
      "url": require.resolve("url/"),
      "util": require.resolve("util/"),
      "path": require.resolve("path-browserify"),
      "stream": require.resolve("stream-browserify"),
      "buffer": require.resolve("buffer/"),
      "querystring": require.resolve("querystring-es3"),
      "crypto": require.resolve("crypto-browserify"),
      "zlib": require.resolve("browserify-zlib"),
      "os": require.resolve("os-browserify"),
      "assert": require.resolve("assert/"),
      "vm": require.resolve("vm-browserify"),
    },
    extensions: ['.js', '.jsx', '.json'],
    alias: {
      jquery: 'jquery/dist/jquery.min.js',
    }
  },
  module: {
    rules: [
      {
        test: /\.css$/i,
        use: ['style-loader', 'css-loader'],
      },
    ],
  },
  target: 'node',
  externals: {
    fs: require('fs'),
  }
};

