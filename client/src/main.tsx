
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import { Provider } from 'react-redux'
import { store } from './redux/stores.ts'
import './styles/index.scss';
import './styles/home.scss';

createRoot(document.getElementById('root')!).render(
  <Provider store={store}>
    <App />
  </Provider>
)


