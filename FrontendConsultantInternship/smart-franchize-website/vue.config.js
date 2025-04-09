module.exports = {
  devServer: {
    host: '0.0.0.0',
    port: 5173, // или любой свободный порт
    allowedHosts: 'all', // важно, если используешь Vue 3 / Vite
  }
}