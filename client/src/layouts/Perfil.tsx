/* eslint-disable react-hooks/exhaustive-deps */
import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { userApi } from "../redux/extraReducers/userApi";
import { UserInfo } from "../components/UserInfo";
import { socialApi } from "../redux/extraReducers/socialApi";
import { ImageComponent } from "../components/ImageComponent";
import { FollowCountCom } from "../components/FollowCountCom";


export function Perfil() {
  const userstate = useAppSelector(state => state.user);
  const socialstate = useAppSelector(state => state.social);
  const dispatch = useAppDispatch();


  React.useEffect(() => {
    dispatch(userApi.mainUserInfo({ token: userstate.token }));
  }, []);

  React.useEffect(() => {
    if (userstate.userHeader.username)
      dispatch(socialApi.findFriendImages({ token: userstate.token, friendname: userstate.userHeader.username, page: 0 }))
  }, [userstate.userHeader.username]);

  return (
    <>
      <UserInfo {...userstate.userInfo} />
      {userstate.userInfo.username && <FollowCountCom thename={userstate.userHeader.username}/>}
      <div className="home">
        {socialstate.images.map(p => <ImageComponent key={p.id} {...p} />)}
      </div>
    </>
  );
}
