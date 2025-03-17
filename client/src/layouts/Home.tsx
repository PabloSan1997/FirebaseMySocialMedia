/* eslint-disable react-hooks/exhaustive-deps */


import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { ImageComponent } from "../components/ImageComponent";
import { socialApi } from "../redux/extraReducers/socialApi";
import { ImageForm } from "../components/ImageForm";

export default function Home() {
  const dispatch = useAppDispatch();
  const socialstate = useAppSelector(state => state.social);
  const userstate = useAppSelector(state => state.user);

  React.useEffect(()=>{
    dispatch(socialApi.findAll({token:userstate.token, page:0}));
  },[]);
  return (
    <>
    <ImageForm/>
    <div className="home">
      {socialstate.images.map(p => <ImageComponent key={p.id} {...p}/>)}
    </div>
    </>
  );
}
