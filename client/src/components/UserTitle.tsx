
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";




export function UserTitle({username, fullname}:UserHeader) {
  const navigate = useNavigate();

  return (
    <div className="title_user">
      <h3>{fullname}</h3>
      <h4 onClick={()=> navigate(`${routesName.perfilfriend}?friendname=${username}`)}>@{username}</h4>
    </div>
  );
}
