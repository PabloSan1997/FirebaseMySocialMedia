import '../styles/images.scss';
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";
import { UserTitle } from "./UserTitle";
import { ChatBubbleBottomCenterTextIcon, HeartIcon } from '@heroicons/react/24/solid';
import { useAppDispatch, useAppSelector } from '../redux/hook';
import { socialApi } from '../redux/extraReducers/socialApi';
import { stringToDate } from '../utils/stringToDate';

export function ImageComponent({ urlImage, user, id, description, comments, likes, userLike, createAt }: ImagenInterface) {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const userstate = useAppSelector(state => state.user);
  const generateLike = () => dispatch(socialApi.generateLike({token:userstate.token, idImage:id}))
  return (
    <div className="area_image">
      <UserTitle {...user} />
      <p className="description">{description}</p>
      <span className='date'>{stringToDate(createAt)}</span>
      <div className="border_image_full">
        <img src={urlImage} alt={user.username} onClick={() => navigate(`${routesName.oneImage}?im=${id}`)} />
      </div>
      <div className="count_image">
        <div className="icon_area">
          <HeartIcon className={`${userLike?'icon like active':'icon like'}`} onClick={generateLike}/>
          <span>{likes}</span>
        </div>
        <div className="icon_area">
          <ChatBubbleBottomCenterTextIcon className='icon' />
          <span>{comments}</span>
        </div>
      </div>
    </div>
  );
}
