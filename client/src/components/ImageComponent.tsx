
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";
import { UserTitle } from "./UserTitle";

export function ImageComponent({urlImage, user, id}:ImagenInterface) {
  const navigate = useNavigate();

  return (
    <div className="area_image">
        <div className="user_title">
          <UserTitle {...user}/>
        </div>
        <img src={urlImage} alt={user.username} onClick={()=>navigate(`${routesName.oneImage}?im=${id}`)}/>
    </div>
  );
}
