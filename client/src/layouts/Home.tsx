/* eslint-disable react-hooks/exhaustive-deps */

import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { ImageComponent } from "../components/ImageComponent";
import { socialApi } from "../redux/extraReducers/socialApi";
import { ImageForm } from "../components/ImageForm";
import { SelectPage } from "../components/SelectPage";
import { routesName } from "../utils/routesName";
import { useSearchParams } from "react-router-dom";

export default function Home() {
  const dispatch = useAppDispatch();
  const socialstate = useAppSelector(state => state.social);
  const userstate = useAppSelector(state => state.user);
  const [search] = useSearchParams();

  const searcpage = Number(search.get('page'));

  const page = isNaN(searcpage) ? 0 : searcpage;

  React.useEffect(() => {
    dispatch(socialApi.findAll({ token: userstate.token, page }));
  }, [page]);
  return (
    <>
      <ImageForm />
      <div className="home">
        {socialstate.images.map(p => <ImageComponent key={p.id} {...p} />)}
      </div>
      <SelectPage path={`${routesName.home}?`} />
    </>
  );
}
