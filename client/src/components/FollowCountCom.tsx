/* eslint-disable react-hooks/exhaustive-deps */

import '../styles/followcount.scss';
import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { socialApi } from "../redux/extraReducers/socialApi";
import { Link } from 'react-router-dom';
import { routesName } from '../utils/routesName';

export function FollowCountCom({ thename }: { thename: string }) {
    const dispatch = useAppDispatch();
    const socialstate = useAppSelector(state => state.social);
    const userstate = useAppSelector(state => state.user);
    React.useEffect(() => {
        if (thename.trim())
            dispatch(socialApi.viewCountFollow({ token: userstate.token, friendname: thename }));
    }, [thename]);
    const {followers, followings} = socialstate.followsCount;
    return (
        <div className="area_follow_count">
            <Link className='follow' to={`${routesName.followList}?name=${thename}&mode=followers`}>Followers: {followers}</Link>
            <Link className='follow' to={`${routesName.followList}?name=${thename}&mode=followings`}>Followings: {followings}</Link>
        </div>
    );
}
