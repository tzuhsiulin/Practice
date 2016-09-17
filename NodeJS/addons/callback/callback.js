var addon = require("./build/Release/addon.node");

addon(function(msg) {
  console.log(msg);
});