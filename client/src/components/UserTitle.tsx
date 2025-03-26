
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";
import '../styles/usertitle.scss';
import { stringToDate } from "../utils/stringToDate";

interface UserTitleDate extends UserHeader{
  createAt:string;
}
export function UserTitle({ username, fullname, urlImage, createAt}: UserTitleDate) {
  const navigate = useNavigate();

  return (
    <div className="title_user" onClick={() => navigate(`${routesName.perfilfriend}?friendname=${username}`)}>
      <div className="border_image">
        {urlImage.trim() ? <img src={urlImage} className="my_image" /> : <div className="my_image" />}
      </div>
      <div className="area_info">
        <h3>{fullname}</h3>
        <h4>@{username}</h4>
       {createAt.trim() && <span className="createAt">{stringToDate(createAt)}</span>}
      </div>
    </div>
  );
}
