
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";

export function ImageComponent({urlImage, user, id}:ImagenInterface) {
  const navigate = useNavigate();

  return (
    <div className="area_image">
        <img src={urlImage} alt={user.username} onClick={()=>navigate(`${routesName.oneImage}?im=${id}`)}/>
    </div>
  );
}
