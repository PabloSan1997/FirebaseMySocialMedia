
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";
import '../styles/usertitle.scss';


export function UserTitle({ username, fullname, urlImage }: UserHeader) {
  const navigate = useNavigate();

  return (
    <div className="title_user" onClick={() => navigate(`${routesName.perfilfriend}?friendname=${username}`)}>
      <div className="border_image">
        {urlImage.trim() ? <img src={urlImage} className="my_image" /> : <div className="my_image" />}
      </div>
      <div className="area_info">
        <h3>{fullname}</h3>
        <h4>@{username}</h4>
      </div>
    </div>
  );
}
