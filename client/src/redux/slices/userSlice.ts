import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { generateUserExtraReducer } from "../extraReducers/userApi";
import { loginStorage } from "../../utils/loginStorage";


const initialState:UserInitialState = {
    token: loginStorage.read(),
    userHeader: {
        username: "",
        fullname: "",
        urlImage: ""
    },
    userInfo: {
        username: "",
        fullname: "",
        description: "",
        urlImage: "",
        born: "",
        createCount: ""
    },
    message: "",
    showProfileForm: false
}

const userSlice = createSlice({
    name:'slice/user',
    initialState,
    reducers:{
        writeMessage(state, action:PayloadAction<{message:string}>){
            state.message = action.payload.message;
        },
        setProdileForm(state, action:PayloadAction<{showProfileForm:boolean}>){
            state.showProfileForm = action.payload.showProfileForm;
        }
    },
    extraReducers:generateUserExtraReducer
});

export const userActions = userSlice.actions;
export const userReducer = userSlice.reducer;