module.exports = {
    entry: './src/main/webapp/WEB-INF/src/js/main.js',
    output: {
        path: './src/main/webapp',
        filename: 'dist/bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.html$/,
                loader: "raw"
            },
            {
                test: /\.css$/,
                loader: 'style!css'
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'ng-annotate'
            },
            {
                test: /\.(eot|png|svg|gif|ttf|woff|woff2)/,
                loader: 'file?name=dist/[name]'
            }
        ]
    }
};
