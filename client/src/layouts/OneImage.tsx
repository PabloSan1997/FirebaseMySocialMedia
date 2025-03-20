/* eslint-disable react-hooks/exhaustive-deps */
import React from "react";
import { Navigate, useSearchParams } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { routesName } from "../utils/routesName";
import { socialApi } from "../redux/extraReducers/socialApi";
import { Comment } from "../components/Comment";
import { CommentForm } from "../components/CommentForm";
import { UserTitle } from "../components/UserTitle";
import '../styles/one_image.scss';
import { HeartIcon } from '@heroicons/react/24/solid';
import { stringToDate } from "../utils/stringToDate";

export function OneImage() {
    const [search] = useSearchParams();
    const idImage = search.get('im');

    const userstate = useAppSelector(state => state.user);
    const socialstate = useAppSelector(state => state.social);
    const dispatch = useAppDispatch();

    const generateLike = () => {
        dispatch(socialApi.generateOneImageLike({ token: userstate.token, idImage: Number(idImage) }));
    }

    React.useEffect(() => {
        if (!isNaN(Number(idImage)))
            dispatch(socialApi.findOneImage({ token: userstate.token, idImage: Number(idImage), page: 0 }))
    }, [idImage]);
    if (isNaN(Number(idImage)))
        return <Navigate to={routesName.home} />
    return (
        <div className="one_image">
            <UserTitle {...socialstate.oneImage.user} />
            <p>{socialstate.oneImage.description}</p>
            <span className="date">{stringToDate(socialstate.oneImage.createAt)}</span>
            <div className="border_image_full">
                {socialstate.oneImage.urlImage && <img src={socialstate.oneImage.urlImage} alt="" />}
            </div>
            {socialstate.oneImage.userLike !== undefined ? (
                <div className="area_icon">
                    <HeartIcon className={`${socialstate.oneImage.userLike ? 'icon like active' : 'icon like'}`} onClick={generateLike} />
                    <span>{socialstate.oneImage.likes}</span>
                </div>
            ) : null}
            <CommentForm token={userstate.token} idImage={Number(idImage)} />
            {socialstate.oneImage.comments.map(c => <Comment key={c.id} {...c} />)}
        </div>
    );
}
