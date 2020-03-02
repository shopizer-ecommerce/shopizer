/*eslint no-undef: 0 */

function request (url) {
  return new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest()

    xhr.open('GET', url)
    xhr.setRequestHeader('Content-Type', 'application/json')
    xhr.addEventListener('load', _ => {
      try {
        const response = JSON.parse(xhr.response)

        resolve(response)
      } catch (e) {
        reject(e)
      };
    })

    xhr.send(null)
  })
}

export function get (url) {
  return request(url)
}

export function createTemplate (template) {
  return source => {
    const re = /{([^}]+)}/
    let m
    let result = template

    while (m = re.exec(result)) {
      result = result.replace(m[0], source[m[1]])
    }

    return result
  }
}
