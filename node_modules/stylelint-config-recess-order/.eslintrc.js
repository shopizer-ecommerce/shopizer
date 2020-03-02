module.exports = {
    root: true,
    parser: 'babel-eslint',
    env: {
        browser: true,
        node: true,
    },
    // plugins: ["html"],
    extends: 'standard',
    rules: {
        /**
         * Code style.
         */
        'array-bracket-spacing': ['error', 'never'],
        'arrow-parens': 'off', // Allow paren-less arrow functions.
        'comma-dangle': [
            'error',
            {
                arrays: 'always-multiline',
                objects: 'always-multiline',
                imports: 'always-multiline',
                exports: 'always-multiline',
                functions: 'always-multiline',
            },
        ],
        'generator-star-spacing': 'off', // Allow async-await.
        indent: ['error', 4],
        'no-multiple-empty-lines': ['error', { max: 2, maxEOF: 1 }],

        /**
         * Best practices.
         */
        'no-console': 'warn',

        'import/no-unresolved': 'off',
        'import/no-unassigned-import': 'off',
    },
    globals: {},
}
