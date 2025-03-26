import '../styles/images.scss';
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";
import { UserTitle } from "./UserTitle";
import { Bars3Icon, ChatBubbleBottomCenterTextIcon, HeartIcon } from '@heroicons/react/24/solid';
import { useAppDispatch, useAppSelector } from '../redux/hook';
import { socialApi } from '../redux/extraReducers/socialApi';
import { stringToDate } from '../utils/stringToDate';
import { MenuImage } from './MenuImage';
import React from 'react';


export function ImageComponent({ urlImage, user, id, description, comments, likes, userLike, createAt }: ImagenInterface) {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const [showMenu, setShowMenu] = React.useState(false);
  const userstate = useAppSelector(state => state.user);
  const isFromUser = userstate.userHeader.username.trim() && user.username == userstate.userHeader.username;
  const generateLike = () => dispatch(socialApi.generateLike({ token: userstate.token, idImage: id }));
  return (
    <div className="area_image">
      {isFromUser && <Bars3Icon className='button_image_menu' onClick={()=> setShowMenu(t => !t)}/>}
      {isFromUser && showMenu && <MenuImage token={userstate.token} id={id} options='image'/>}
      <UserTitle {...user} />
      <p className="description">{description}</p>
      <span className='date'>{stringToDate(createAt)}</span>
      <div className="border_image_full">
        <img src={urlImage} alt={user.username} onClick={() => navigate(`${routesName.oneImage}?im=${id}`)} />
      </div>
      <div className="count_image">
        <div className="icon_area">
          <HeartIcon className={`${userLike ? 'icon like active' : 'icon like'}`} onClick={generateLike} />
          <span>{likes}</span>
        </div>
        <div className="icon_area">
          <ChatBubbleBottomCenterTextIcon className='icon' onClick={() => navigate(`${routesName.oneImage}?im=${id}`)}/>
          <span>{comments}</span>
        </div>
      </div>
    </div>
  );
}
