import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],

  server: {
    host: '0.0.0.0',
    port: 5173,
    allowedHosts: [
      'k8s-default-skybooki-5e5ac147ea-1747448855.ap-south-1.elb.amazonaws.com'
    ]
  }
})
