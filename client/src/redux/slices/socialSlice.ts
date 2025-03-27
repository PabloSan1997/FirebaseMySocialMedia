import { createSlice } from "@reduxjs/toolkit";
import { generateSocialExtraReducer } from "../extraReducers/socialApi";


const initialState: SocialInitialState = {
    message: '',
    perfilUser: {
        username: "",
        fullname: "",
        description: "",
        urlImage: "",
        born: "",
        createCount: ""
    },
    images: [],
    oneImage: {
        id: 0,
        urlImage: "",
        description: "",
        likes: 0,
        comments: [],
        userLike: false,
        user: {
            username: "",
            fullname: "",
            urlImage: ""
        },
        createAt: ""
    },
    userfollow: false,
    followsCount: {
        followings: 0,
        followers: 0
    },
    followHeaderUserInfo: [],
    loading:false
}

const socialSlice = createSlice({
    name: "slice/social",
    initialState,
    reducers:{},
    extraReducers:generateSocialExtraReducer
});


export const socialAction = socialSlice.actions;
export const socialReducer = socialSlice.reducer;