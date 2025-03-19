/* eslint-disable react-hooks/exhaustive-deps */


import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { userApi } from "../redux/extraReducers/userApi";
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";

export function HeaderInfoUser() {
  const userstate = useAppSelector(state => state.user);
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  React.useEffect(() => {
    dispatch(userApi.mainUserHeader({ token: userstate.token }));
  }, []);
  return (
    <div className="user_header">
      <span onClick={()=>navigate(routesName.perfil)}>{userstate.userHeader.fullname}</span>
    </div>
  );
}
