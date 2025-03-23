/* eslint-disable react-hooks/exhaustive-deps */
import { Navigate, useSearchParams } from "react-router-dom";
import { routesName } from "../utils/routesName";
import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { socialApi } from "../redux/extraReducers/socialApi";
import { UserInfo } from "../components/UserInfo";
import { ImageComponent } from "../components/ImageComponent";
import { FollowCountCom } from "../components/FollowCountCom";
import { FollowButton } from "../components/FollowButton";
import '../styles/followbutton.scss';

export function PerfilFriend() {
    const [search] = useSearchParams();
    const username = search.get('friendname');
    const userstate = useAppSelector(state => state.user);
    const socialstate = useAppSelector(state => state.social);
    const dispatch = useAppDispatch();


    React.useEffect(() => {
        if (username?.trim()) {
            dispatch(socialApi.findFriendInfo({ token: userstate.token, friendname: username }));
            dispatch(socialApi.findFriendImages({ token: userstate.token, friendname: username, page: 0 }))
        }

    }, [username]);

    if (username == userstate.userHeader.username)
        return <Navigate to={routesName.perfil} />

    if (username == null)
        return <Navigate to={routesName.home} />
    return (
        <>
            <UserInfo {...socialstate.perfilUser} perfil={false} />
            <FollowButton thename={username} className="follow_button"/>
            <FollowCountCom thename={username}/>
            <div className="home">
                {socialstate.images.map(p => <ImageComponent key={p.id} {...p} />)}
            </div>
        </>
    );
}
