/* eslint-disable react-hooks/exhaustive-deps */


import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { userApi } from "../redux/extraReducers/userApi";

export function HeaderInfoUser() {
  const userstate = useAppSelector(state => state.user);
  const dispatch = useAppDispatch();
  React.useEffect(() => {
    dispatch(userApi.mainUserHeader({ token: userstate.token }));
  }, []);
  return (
    <div className="user_header">
      <span>{userstate.userHeader.fullname}</span>
    </div>
  );
}
