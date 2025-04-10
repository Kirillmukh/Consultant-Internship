module.exports = {
  devServer: {
    host: '0.0.0.0',
    port: 5173,
    allowedHosts: 'all',
    client: {
      webSocketURL: 'ws://138.124.51.90:5173/ws', // замени на IP или домен сервера
    },
  }
}