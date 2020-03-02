var resolve = require("path").resolve;
var webpack = require("webpack");

module.exports = {
  entry: "./src/entry.js",
  output: {
    path: resolve(__dirname, "./dist"),
    publicPath: "/dist/",
    filename: "build.js"
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        loader: "vue-loader"
      },
      {
        test: /\.js$/,
        loader: "babel-loader",
        exclude: /node_modules/,
        options: {
          presets: ["es2015"]
        }
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"]
      },
      {
        test: /\.md$/,
        loader: resolve(__dirname, "../index.js"),
        options: {}
      }
    ]
  },
  devServer: {
    historyApiFallback: true,
    noInfo: true
  }
};
