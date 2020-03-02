"use strict"

function oneLine(parts) {
  return parts
    .map((part, index) => {
      return index > 0 ? arguments[index - 1] + part : part
    })
    .join("")
    .trim()
    .split("\n")
    .map((line) => line.trim())
    .join(" ")
}

module.exports = {
  oneLine,
}
