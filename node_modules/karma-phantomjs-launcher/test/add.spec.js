/* globals describe, it, expect */
describe('add', function () {
  it('adds two numbers', function () {
    function add (a, b) {
      return a + b
    }

    expect(add(1, 4)).toBe(5)
  })
})
