

import React from "react";
import { useAppSelector } from "../redux/hook";
import { HeaderInfoUser } from "./HeaderInfoUser";
import { Logout } from "./Logout";

export function Header() {
    const userstate = useAppSelector(state => state.user);
    return (
        <header>
            <h1>Mi perfil</h1>
            {userstate.token.trim() ? (
                <>
                    <HeaderInfoUser />
                    <Logout token={userstate.token}/>
                </>
            ) : null}
        </header>
    );
}
