

import React from "react";
import { useAppDispatch } from "../redux/hook";
import { userApi } from "../redux/extraReducers/userApi";

export function Logout({ token }: { token: string }) {
    const dispatch = useAppDispatch();

    return (
        <button className="logout" onClick={() => dispatch(userApi.logout({ token }))}>Logout</button>
    );
}
