import { UserTitle } from "./UserTitle"
import '../styles/comment.scss';
import { stringToDate } from "../utils/stringToDate";
import { useAppSelector } from "../redux/hook";
import { Bars3Icon } from "@heroicons/react/24/solid";
import React from "react";
import { MenuImage } from "./MenuImage";

export  function Comment({comment, user, createAt, id}:CommentInterface) {
  const userstate = useAppSelector(state => state.user);
  const isFromUser = userstate.userHeader.username.trim() && user.username == userstate.userHeader.username;
  const [showMenu, setShowMenu] = React.useState(false);
  return (
    <div className="comments">
         {isFromUser && <Bars3Icon className='button_image_menu' onClick={()=> setShowMenu(t => !t)}/>}
          {showMenu && <MenuImage id={id} token={userstate.token} options={"comment"} />}
        <UserTitle {...user}/>
        <span className="date">{stringToDate(createAt)}</span>
        <p>{comment}</p>
    </div>
  );
}
