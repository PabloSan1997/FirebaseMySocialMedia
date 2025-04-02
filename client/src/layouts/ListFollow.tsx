/* eslint-disable react-hooks/exhaustive-deps */

import '../styles/followuserheader.scss';
import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { useSearchParams } from "react-router-dom";
import { socialApi } from "../redux/extraReducers/socialApi";
import { UserTitle } from "../components/UserTitle";
import { convertUpperTitle } from '../utils/convertUpperTitle';
import { SelectPage } from '../components/SelectPage';
import { routesName } from '../utils/routesName';

export function ListFollow() {
  const [search] = useSearchParams();
  const dispatch = useAppDispatch();
  const socialstate = useAppSelector(state => state.social);
  const userstate = useAppSelector(state => state.user);
  const name = search.get('name');
  const typefollow = search.get('mode');
  const page = Number(search.get('page'));
  React.useEffect(() => {

    if ((typefollow == 'followers' || typefollow == 'followings') && name?.trim())
      dispatch(socialApi.findFollowsUserHeader({
        token: userstate.token,
        friendname: name,
        followers: typefollow == 'followers',
        page
      }));
  }, [name, typefollow, page]);

  if (socialstate.loading)
    return <div className="loading"></div>

  return (
    <>
      <h2 className="follow_title">{convertUpperTitle(typefollow as string)}{name?.trim()?`: ${name.trim()}`:''}</h2>
      <div className="area_follows">
        {socialstate.followHeaderUserInfo.map(p => <UserTitle key={p.username} {...p} createAt='' />)}
      </div>
      <SelectPage path={`${routesName.followList}?name=${name}&mode=${typefollow}&`}/>
    </>
  );
}
