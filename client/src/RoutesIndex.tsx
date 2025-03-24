import { HashRouter, Navigate, useRoutes } from "react-router-dom";
import { routesName } from "./utils/routesName";
import { Login } from "./layouts/Login";
import Home from "./layouts/Home";
import { useAppSelector } from "./redux/hook";
import { JSX } from "react";
import { Perfil } from "./layouts/Perfil";
import { OneImage } from "./layouts/OneImage";
import { PerfilFriend } from "./layouts/PerfilFriend";
import { ListFollow } from "./layouts/ListFollow";

function ViewDataToken() {
  const token = useAppSelector(state => state.user.token);
  const routes = token.trim() ? routesName.home : routesName.login;
  return <Navigate to={routes} />
}

function ViewToken({ children }: { children: JSX.Element | JSX.Element[] }) {
  const token = useAppSelector(state => state.user.token);
  if (token.trim())
    return (
      <>
        {children}
      </>
    );
  return <Navigate to={routesName.login} />;
}

function ViewTokenLogin() {
  const token = useAppSelector(state => state.user.token);
  if (!token.trim())
    return <Login />
  return <Navigate to={routesName.home} />
}

const Routes = () => useRoutes([
  {
    path: routesName.login,
    element: <ViewTokenLogin />
  },
  {
    path: routesName.home,
    element: (
      <ViewToken>
        <Home />
      </ViewToken>
    )
  },
  {
    path: routesName.perfil,
    element: (
      <ViewToken>
        <Perfil />
      </ViewToken>
    )
  },
  {
    path: routesName.oneImage,
    element: (
      <ViewToken>
        <OneImage />
      </ViewToken>
    )
  },
  {
    path: '/',
    element: <ViewDataToken />
  },
  {
    path: routesName.perfilfriend,
    element: (
      <ViewToken>
        <PerfilFriend />
      </ViewToken>
    )
  },
  {
    path: routesName.followList,
    element: (
      <ViewToken>
        <ListFollow />
      </ViewToken>
    )
  }
]);

export default function RoutesIndex({ children }: { children: JSX.Element[] | JSX.Element }) {
  return (
    <HashRouter>
      {children}
      <Routes />
    </HashRouter>
  );
}
