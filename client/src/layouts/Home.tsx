/* eslint-disable react-hooks/exhaustive-deps */

import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { ImageComponent } from "../components/ImageComponent";
import { socialApi } from "../redux/extraReducers/socialApi";
import { ImageForm } from "../components/ImageForm";
import { SelectPage } from "../components/SelectPage";
import { routesName } from "../utils/routesName";
import { NavLink, useSearchParams } from "react-router-dom";

export default function Home({ isHome }: { isHome: boolean }) {
  const dispatch = useAppDispatch();
  const socialstate = useAppSelector(state => state.social);
  const userstate = useAppSelector(state => state.user);
  const [search] = useSearchParams();

  const searcpage = Number(search.get('page'));

  const page = isNaN(searcpage) ? 0 : searcpage;

  React.useEffect(() => {
    if (isHome)
      dispatch(socialApi.findAll({ token: userstate.token, page }));
    else
      dispatch(socialApi.findFollowinsImage({ token: userstate.token, page }));
  }, [page, isHome]);



  return (
    <>
      <ImageForm />
      <nav className="home_menu">
        <NavLink to={routesName.home} className={({ isActive }) => isActive ? 'option active' : 'option'}>Home</NavLink>
        <NavLink to={routesName.followsImage} className={({ isActive }) => isActive ? 'option active' : 'option'}>Siguiendo</NavLink>
      </nav>
      {!socialstate.loading ? (
        <div className="home">
          {socialstate.images.map(p => <ImageComponent key={p.id} {...p} />)}
        </div>
      ) : <div className="loading"></div>}
      <SelectPage path={`${isHome ? routesName.home : routesName.followsImage}?`} />
    </>
  );
}
