var path = require('path');
var webpack = require('webpack');

module.exports = {
  entry: [
    'webpack-dev-server/client?http://localhost:8080',
    'webpack/hot/dev-server',
    path.resolve(__dirname, './src/main.js'),
  ],
  output: {
    path: path.resolve(__dirname, './assets/'),
    filename: 'main.js',
    publicPath: '/assets/',
  },
  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: '/node_module/',
        loader: 'babel-loader',
        query: {
          plugins: ['transform-decorators-legacy'],
          presets: ['es2015', 'stage-0'],
        },
      },
    ],
  },
  resolveLoader: {
    root: path.join(__dirname, 'node_modules'),
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin(),
  ],
  devtool: 'source-map',
};
