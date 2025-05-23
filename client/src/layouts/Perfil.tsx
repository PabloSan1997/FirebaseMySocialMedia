/* eslint-disable react-hooks/exhaustive-deps */
import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { userApi } from "../redux/extraReducers/userApi";
import { UserInfo } from "../components/UserInfo";
import { socialApi } from "../redux/extraReducers/socialApi";
import { ImageComponent } from "../components/ImageComponent";
import { FollowCountCom } from "../components/FollowCountCom";
import { EdtiProfileForms } from "../components/EdtiProfileForms";
import { ImageForm } from "../components/ImageForm";

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
      <EdtiProfileForms />
      <UserInfo {...userstate.userInfo} perfil={true} />
      {userstate.userInfo.username && <FollowCountCom thename={userstate.userHeader.username} />}
      <ImageForm />
      {!socialstate.loading ? (
        <div className="home">
          {socialstate.images.map(p => <ImageComponent key={p.id} {...p} />)}
        </div>
      ) : <div className="loading"></div>}
    </>
  );
}
