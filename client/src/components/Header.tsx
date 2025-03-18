

import { useAppSelector } from "../redux/hook";
import { HeaderInfoUser } from "./HeaderInfoUser";
import { Logout } from "./Logout";
import { useNavigate } from "react-router-dom";
import { routesName } from "../utils/routesName";

export function Header() {
    const userstate = useAppSelector(state => state.user);
    const navigate = useNavigate();
    return (
        <header>
            <h1 onClick={()=>navigate(routesName.home)}>Mi perfil</h1>
            {userstate.token.trim() ? (
                <>
                    <HeaderInfoUser />
                    <Logout token={userstate.token}/>
                </>
            ) : null}
        </header>
    );
}
