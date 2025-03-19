/* eslint-disable react-hooks/exhaustive-deps */


import React from "react";
import { Navigate, useSearchParams } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { routesName } from "../utils/routesName";
import { socialApi } from "../redux/extraReducers/socialApi";
import { Comment } from "../components/Comment";
import { CommentForm } from "../components/CommentForm";
import { UserTitle } from "../components/UserTitle";

export function OneImage() {
    const [search] = useSearchParams();
    const idImage = search.get('im');

    const userstate = useAppSelector(state => state.user);
    const socialstate = useAppSelector(state => state.social);
    const dispatch = useAppDispatch();

    React.useEffect(() => {
        if (!isNaN(Number(idImage)))
            dispatch(socialApi.findOneImage({ token: userstate.token, idImage: Number(idImage), page: 0 }))
    }, [idImage]);
    if (isNaN(Number(idImage)))
        return <Navigate to={routesName.home} />
    return (
        <div className="hola">
            <UserTitle {...socialstate.oneImage.user} />
            {socialstate.oneImage.urlImage && <img src={socialstate.oneImage.urlImage} alt="" />}
            <CommentForm token={userstate.token} idImage={Number(idImage)} />
            {socialstate.oneImage.comments.map(c => <Comment key={c.id} {...c} />)}
        </div>
    );
}
