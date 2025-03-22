/* eslint-disable react-hooks/exhaustive-deps */
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { socialApi } from "../redux/extraReducers/socialApi";
import React from "react";

export function FollowButton({ className, thename }: { className: string, thename: string }) {
    const userstate = useAppSelector(state => state.user);
    const socialstate = useAppSelector(state => state.social);
    const dispatch = useAppDispatch();
    const follow = () => {
        dispatch(socialApi.createFollow({ token: userstate.token, friendname: thename }));
    }
    React.useEffect(() => {
        dispatch(socialApi.viewFollowFriend({ token: userstate.token, friendname: thename }));
    }, [socialstate.followsCount.followers]);

    return <button className={className} onClick={follow}>{socialstate.userfollow ? 'Siguiendo' : 'Seguir'}</button>;
}
