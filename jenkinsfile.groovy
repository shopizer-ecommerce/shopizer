pipeline {
    agent {label ('shopizer')}
    parameters {
    choice(name: 'branches', choices: ['one', 'two', 'three'], description: '')
    }
}