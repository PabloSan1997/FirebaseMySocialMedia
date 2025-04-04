/* eslint-disable react-hooks/exhaustive-deps */


import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { userApi } from "../redux/extraReducers/userApi";
import { Link } from "react-router-dom";
import { routesName } from "../utils/routesName";
import { onlyFistName } from "../utils/onlyFistName";

export function HeaderInfoUser() {
  const userstate = useAppSelector(state => state.user);
  const dispatch = useAppDispatch();
  React.useEffect(() => {
    dispatch(userApi.mainUserHeader({ token: userstate.token }));
  }, [userstate.userInfo.urlImage, userstate.userInfo.fullname]);
  return (
    <Link className="user_header" to={routesName.perfil}>
      {userstate.userHeader.urlImage.trim() && <img src={userstate.userHeader.urlImage} alt="imagen" />}
      <span className="name_user" >{onlyFistName(userstate.userHeader.fullname)}</span>
    </Link>
  );
}
